package nl.beehivejava.scriptj.loader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Lesley
 */
public abstract class PluginLoaderTest {

    @Rule
    public final ExpectedException internalExpectedException = ExpectedException.none();

    @Test
    public final void load_nullLocation_throwsException() throws Exception {
        internalExpectedException.expect(NullPointerException.class);

        PluginLoader loader = makeDefaultPluginLoader();

        loader.load(null);
    }

    @Test
    public void addScriptLoader_nullLoader_throwsException() throws Exception {
        internalExpectedException.expect(NullPointerException.class);

        PluginLoader loader = makeDefaultPluginLoader();

        loader.addScriptLoader(null);
    }

    protected abstract PluginLoader makeDefaultPluginLoader() throws Exception;

}
