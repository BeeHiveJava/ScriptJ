package nl.beehivejava.scriptj;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public final class PluginStateTest {

    @Test
    public void superFicialCoverageForStartEnumeration() {
        PluginState expected = PluginState.STARTED;
        PluginState actual = PluginState.valueOf(PluginState.STARTED.name());
        assertEquals(expected, actual);
    }

    @Test
    public void superFicialCoverageForStopEnumeration() {
        PluginState expected = PluginState.STOPPED;
        PluginState actual = PluginState.valueOf(PluginState.STOPPED.name());
        assertEquals(expected, actual);
    }

}
