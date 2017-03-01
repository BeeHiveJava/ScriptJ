package nl.beehivejava.scriptj;

import nl.beehivejava.scriptj.util.builder.ObjectBuilders;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public final class PluginTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullLocation_throwsException() {
        expectedException.expect(NullPointerException.class);

        Plugin plugin = ObjectBuilders.plugin().withLocation(null).build();
    }

    @Test
    public void construct_nullInformation_throwsException() {
        expectedException.expect(NullPointerException.class);

        Plugin plugin = ObjectBuilders.plugin().withPluginInformation(null).build();
    }

    @Test
    public void start_whenPluginIsStopped_setsStateToStarted() {
        Plugin plugin = ObjectBuilders.plugin().build();

        plugin.start();

        PluginState expected = PluginState.STARTED;
        PluginState actual = plugin.state();
        assertEquals(expected, actual);
    }

    @Test
    public void start_whenPluginIsStarted_throwsException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("This plugin is already started.");

        Plugin plugin = ObjectBuilders.plugin().build();
        plugin.start(); // Start it first, because it is stopped initially.

        plugin.start();
    }

    @Test
    public void stop_whenPluginIsStarted_setsStateToStopped() {
        Plugin plugin = ObjectBuilders.plugin().build();
        plugin.start(); // Start it first, because it is stopped initially.

        plugin.stop();

        PluginState expected = PluginState.STOPPED;
        PluginState actual = plugin.state();
        assertEquals(expected, actual);
    }

    @Test
    public void stop_whenPluginIsStopped_throwsException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("This plugin is already stopped.");

        Plugin plugin = ObjectBuilders.plugin().build();

        plugin.stop();
    }

    @Test
    public void state_byDefault_returnsStopped() {
        Plugin plugin = ObjectBuilders.plugin().build();

        PluginState expected = PluginState.STOPPED;
        PluginState actual = plugin.state();
        assertEquals(expected, actual);
    }

    @Test
    public void location_whenCalled_returnsLocation() {
        Plugin plugin = ObjectBuilders.plugin().withLocation("location").build();

        String expected = "location";
        String actual = plugin.location();
        assertEquals(expected, actual);
    }

    @Test
    public void information_whenCalled_returnsPluginInformation() {
        PluginInformation information = ObjectBuilders.pluginInformation().build();
        Plugin plugin = ObjectBuilders.plugin().withPluginInformation(information).build();

        PluginInformation expected = information;
        PluginInformation actual = plugin.information();
        assertEquals(expected, actual);
    }

}
