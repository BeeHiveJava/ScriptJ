package nl.beehivejava.scriptj.loader;

import nl.beehivejava.scriptj.Plugin;
import nl.beehivejava.scriptj.io.ResourceLoader;
import nl.beehivejava.scriptj.parser.PluginInformationParser;
import nl.beehivejava.scriptj.util.builder.ObjectBuilders;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public final class DefaultPluginLoaderIntegrationTest {

    private static final String PATH_SEPARATOR = File.separator;
    private static final String NEW_LINE_SEPARATOR = System.getProperty("line.separator");

    private static final String DEFAULT_RESOURCE_DIRECTORY = "build" + PATH_SEPARATOR + "resources" + PATH_SEPARATOR + "integrationTest" + PATH_SEPARATOR;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void load_validPath_loadsPlugin() throws Exception {
        ResourceLoader resourceLoader = ObjectBuilders.fileResourceLoader().build();
        PluginInformationParser parser = ObjectBuilders.jsonPluginInformationParser().build();
        DefaultPluginLoader loader = ObjectBuilders.defaultPluginLoader().withResourceLoader(resourceLoader).withParser(parser).build();

        Plugin expected = ObjectBuilders.plugin().build();
        Plugin actual = loader.load(DEFAULT_RESOURCE_DIRECTORY + "some_test_directory" + PATH_SEPARATOR + "default_plugin.json");
        assertEquals(expected, actual);
    }

    @Test
    public void load_invalidPath_throwsException() throws Exception {
        expectedException.expect(FileNotFoundException.class);

        ResourceLoader resourceLoader = ObjectBuilders.fileResourceLoader().build();
        PluginInformationParser parser = ObjectBuilders.jsonPluginInformationParser().build();
        DefaultPluginLoader loader = ObjectBuilders.defaultPluginLoader().withResourceLoader(resourceLoader).withParser(parser).build();

        Plugin expected = ObjectBuilders.plugin().build();
        Plugin actual = loader.load("some_invalid_file.unknown");
        assertEquals(expected, actual);
    }

}
