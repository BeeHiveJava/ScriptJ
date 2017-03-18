package nl.beehivejava.scriptj.io;

import nl.beehivejava.scriptj.util.builder.ObjectBuilders;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public final class FileResourceLoaderIntegrationTest {

    private static final String PATH_SEPARATOR = File.separator;
    private static final String NEW_LINE_SEPARATOR = System.getProperty("line.separator");

    private static final String DEFAULT_RESOURCE_DIRECTORY = "build" + PATH_SEPARATOR + "resources" + PATH_SEPARATOR + "integrationTest" + PATH_SEPARATOR;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private static String getStringFromInputStream(InputStream is) throws Exception {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            reader.lines().forEach(l -> builder.append(l).append(NEW_LINE_SEPARATOR));
        }

        int length = builder.length();
        int newLineLength = NEW_LINE_SEPARATOR.length();
        if (length >= newLineLength) {
            builder.setLength(length - newLineLength);
        }

        return builder.toString();
    }

    @Test
    public void load_nonExistentFile_throwsException() throws Exception {
        expectedException.expect(FileNotFoundException.class);

        FileResourceLoader loader = ObjectBuilders.fileResourceLoader().build();

        loader.load("some_non_existent_file.unknown");
    }

    @Test
    public void load_fileIsDirectory_throwsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        FileResourceLoader loader = ObjectBuilders.fileResourceLoader().build();

        loader.load(DEFAULT_RESOURCE_DIRECTORY + "some_test_directory");
    }

    @Test
    public void load_validFile_convertsDataToValidInputStream() throws Exception {
        FileResourceLoader loader = ObjectBuilders.fileResourceLoader().build();

        InputStream is = loader.load(DEFAULT_RESOURCE_DIRECTORY + "some_test_directory" + PATH_SEPARATOR + "some_file.unknown");

        String expected = "some data in a file";
        String actual = getStringFromInputStream(is);
        assertEquals(expected, actual);
    }

}
