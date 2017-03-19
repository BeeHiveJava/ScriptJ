package nl.beehivejava.scriptj.loader;

import nl.beehivejava.scriptj.Plugin;
import nl.beehivejava.scriptj.parser.PluginInformationParser;
import nl.beehivejava.scriptj.util.builder.ObjectBuilders;
import nl.beehivejava.scriptj.util.fake.FakeResourceLoader;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * @author Lesley
 */
public final class DefaultPluginLoaderTest extends PluginLoaderTest {

    private static final String DEFAULT_JSON_INFORMATION = "{\n" +
            "  \"id\": \"id\",\n" +
            "  \"author\": \"author\",\n" +
            "  \"scripts\": [\n" +
            "    {\n" +
            "      \"name\": \"name\",\n" +
            "      \"type\": \"js\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullLoader_throwsException() {
        expectedException.expect(NullPointerException.class);

        DefaultPluginLoader loader = ObjectBuilders.defaultPluginLoader().withResourceLoader(null).build();
    }

    @Test
    public void construct_nullParser_throwsException() {
        expectedException.expect(NullPointerException.class);

        DefaultPluginLoader loader = ObjectBuilders.defaultPluginLoader().withParser(null).build();
    }

    @Test
    public void load_validLocation_loadsPlugin() throws Exception {
        InputStream is = makeDefaultInputStream();

        FakeResourceLoader stubResourceLoader = new FakeResourceLoader();
        stubResourceLoader.inputStreamToReturnOnLoad = is;

        PluginInformationParser stubParser = mock(PluginInformationParser.class);
        doReturn(ObjectBuilders.pluginInformation().build()).when(stubParser).parse(Matchers.any(InputStream.class));

        DefaultPluginLoader loader = ObjectBuilders.defaultPluginLoader().withResourceLoader(stubResourceLoader).withParser(stubParser).build();

        Plugin expected = ObjectBuilders.plugin().build();
        Plugin actual = loader.load("location");
        assertEquals(expected, actual);
    }

    @Override
    protected PluginLoader makeDefaultPluginLoader() {
        return ObjectBuilders.defaultPluginLoader().build();
    }

    private InputStream makeDefaultInputStream() {
        return new ByteArrayInputStream(DEFAULT_JSON_INFORMATION.getBytes());
    }

}
