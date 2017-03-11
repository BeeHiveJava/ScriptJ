package nl.beehivejava.scriptj;

import nl.beehivejava.scriptj.script.ScriptType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Lesley
 */
public abstract class PluginManagerTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public final void load_nullLocation_throwsException() {
        expectedException.expect(NullPointerException.class);

        PluginManager manager = createPluginManager();

        manager.load(null);
    }

    @Test
    public final void addPluginEnvironment_nullEnvironment_throwsException() {
        expectedException.expect(NullPointerException.class);

        PluginManager manager = createPluginManager();

        manager.addEnvironment(null);
    }

    @Test
    public final void addPluginEnvironment_duplicateEnvironment_throwsException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("This PluginManager already has an environment for the given ScriptType.");

        PluginEnvironment jsEnvironment = createPluginEnvironmentFor(ScriptType.JAVASCRIPT);
        PluginEnvironment duplicateJsEnvironment = createPluginEnvironmentFor(ScriptType.JAVASCRIPT);
        PluginManager manager = createPluginManager();

        manager.addEnvironment(jsEnvironment);
        manager.addEnvironment(duplicateJsEnvironment);
    }

    @Test
    public final void removePluginEnvironment_nullEnvironment_throwsException() {
        expectedException.expect(NullPointerException.class);

        PluginManager manager = createPluginManager();

        manager.removeEnvironment(null);
    }

    private PluginEnvironment createPluginEnvironmentFor(ScriptType type) {
        PluginEnvironment environment = mock(PluginEnvironment.class);
        when(environment.type()).thenReturn(type);

        return environment;
    }

    protected abstract PluginManager createPluginManager();

}
