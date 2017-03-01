package nl.beehivejava.scriptj.script;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public final class ScriptStateTest {

    @Test
    public void superFicialCoverageForStartEnumeration() {
        ScriptState expected = ScriptState.STARTED;
        ScriptState actual = ScriptState.valueOf(ScriptState.STARTED.name());
        assertEquals(expected, actual);
    }

    @Test
    public void superFicialCoverageForStopEnumeration() {
        ScriptState expected = ScriptState.STOPPED;
        ScriptState actual = ScriptState.valueOf(ScriptState.STOPPED.name());
        assertEquals(expected, actual);
    }

}
