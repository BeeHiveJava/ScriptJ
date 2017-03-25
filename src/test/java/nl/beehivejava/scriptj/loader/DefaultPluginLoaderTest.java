package nl.beehivejava.scriptj.loader;

import nl.beehivejava.scriptj.Plugin;
import nl.beehivejava.scriptj.parser.PluginInformationParser;
import nl.beehivejava.scriptj.script.Script;
import nl.beehivejava.scriptj.util.builder.ObjectBuilders;
import nl.beehivejava.scriptj.util.fake.FakeResourceLoader;
import nl.beehivejava.scriptj.util.fake.FakeScript;
import nl.beehivejava.scriptj.util.fake.FakeScriptLoader;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertArrayEquals;
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
        FakeScript script = ObjectBuilders.script().build();
        FakeScriptLoader scriptLoader = new FakeScriptLoader();
        scriptLoader.scriptToLoad = script;
        scriptLoader.compatibleScriptType = script.information().type();

        PluginLoader loader = makeDefaultPluginLoader();
        loader.addScriptLoader(scriptLoader);

        Plugin expectedPlugin = ObjectBuilders.plugin().build();
        Plugin actualPlugin = loader.load("location");
        assertEquals(expectedPlugin, actualPlugin);

        Script[] expectedScript = new Script[]{script};
        Script[] actualScript = actualPlugin.scripts().toArray(new Script[0]);
        assertArrayEquals(expectedScript, actualScript);
    }

    @Test
    public void load_noScriptLoader_throwsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("No ScriptLoader found for Script.");

        PluginLoader loader = makeDefaultPluginLoader();

        loader.load("location");
    }

    @Override
    protected PluginLoader makeDefaultPluginLoader() throws Exception {
        InputStream is = makeDefaultInputStream();

        FakeResourceLoader stubResourceLoader = new FakeResourceLoader();
        stubResourceLoader.inputStreamToReturnOnLoad = is;

        PluginInformationParser stubParser = mock(PluginInformationParser.class);
        doReturn(ObjectBuilders.pluginInformation().build()).when(stubParser).parse(Matchers.any(InputStream.class));

        DefaultPluginLoader loader = ObjectBuilders.defaultPluginLoader().withResourceLoader(stubResourceLoader).withParser(stubParser).build();
        return loader;
    }

    private InputStream makeDefaultInputStream() {
        return new ByteArrayInputStream(DEFAULT_JSON_INFORMATION.getBytes());
    }

}
