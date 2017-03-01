package nl.beehivejava.scriptj.script;

import nl.beehivejava.scriptj.util.builder.ObjectBuilders;
import nl.beehivejava.scriptj.util.fake.FakeScript;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Lesley
 */
public final class ScriptTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullLocation_throwsException() {
        expectedException.expect(NullPointerException.class);

        Script script = ObjectBuilders.script().withLocation(null).build();
    }

    @Test
    public void construct_nullScriptInformation_throwsException() {
        expectedException.expect(NullPointerException.class);

        Script script = ObjectBuilders.script().withScriptInformation(null).build();
    }

    @Test
    public void start_whenScriptIsStopped_setsStateToStarted() {
        Script script = ObjectBuilders.script().build();

        script.start();

        ScriptState expected = ScriptState.STARTED;
        ScriptState actual = script.state();
        assertEquals(expected, actual);
    }

    @Test
    public void start_whenScriptIsStopped_callsAbstractOnStart() {
        FakeScript script = ObjectBuilders.script().build();

        script.start();

        assertTrue(script.onStartCalled);
    }

    @Test
    public void start_whenScriptIsStarted_throwsException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("This script is already started.");

        Script script = ObjectBuilders.script().build();
        script.start(); // Start it first, because it is stopped initially.

        script.start();
    }

    @Test
    public void stop_whenScriptIsStarted_callsAbstractOnStop() {
        FakeScript script = ObjectBuilders.script().build();
        script.start();

        script.stop();

        assertTrue(script.onStopCalled);
    }

    @Test
    public void stop_whenScriptIsStarted_setsStateToStopped() {
        Script script = ObjectBuilders.script().build();
        script.start(); // Start it first, because it is stopped initially.

        script.stop();

        ScriptState expected = ScriptState.STOPPED;
        ScriptState actual = script.state();
        assertEquals(expected, actual);
    }

    @Test
    public void stop_whenScriptIsStopped_throwsException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("This script is already stopped.");

        Script script = ObjectBuilders.script().build();

        script.stop();
    }

    @Test
    public void state_byDefault_returnsStopped() {
        Script script = ObjectBuilders.script().build();

        ScriptState expected = ScriptState.STOPPED;
        ScriptState actual = script.state();
        assertEquals(expected, actual);
    }

    @Test
    public void location_whenCalled_returnsLocation() {
        Script script = ObjectBuilders.script().withLocation("location").build();

        String expected = "location";
        String actual = script.location();
        assertEquals(expected, actual);
    }

    @Test
    public void information_whenCalled_returnsScriptInformation() {
        ScriptInformation information = ObjectBuilders.scriptInformation().build();
        Script script = ObjectBuilders.script().withScriptInformation(information).build();

        ScriptInformation expected = information;
        ScriptInformation actual = script.information();
        assertEquals(expected, actual);
    }

}
