package nl.beehivejava.scriptj;

import nl.beehivejava.scriptj.script.Script;
import nl.beehivejava.scriptj.util.builder.ObjectBuilders;
import nl.beehivejava.scriptj.util.fake.FakeScript;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        Script script = ObjectBuilders.script().build();
        Plugin plugin = createDefaultPluginWith(script);

        plugin.start();

        PluginState expected = PluginState.STARTED;
        PluginState actual = plugin.state();
        assertEquals(expected, actual);
    }

    @Test
    public void start_whenPluginIsStopped_callsStartOnScripts() {
        FakeScript script = ObjectBuilders.script().build();
        Plugin plugin = createDefaultPluginWith(script);

        plugin.start();

        assertTrue(script.onStartCalled);
    }

    @Test
    public void start_whenPluginIsStarted_throwsException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("This plugin is already started.");

        Script script = ObjectBuilders.script().build();
        Plugin plugin = createDefaultPluginWith(script);
        plugin.start(); // Start it first, because it is stopped initially.

        plugin.start();
    }

    @Test
    public void start_whenNoScriptsWereAdded_throwsException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("This plugin has no scripts to start.");

        Plugin plugin = createDefaultPluginWith();

        plugin.start();
    }

    @Test
    public void stop_whenPluginIsStarted_setsStateToStopped() {
        Script script = ObjectBuilders.script().build();
        Plugin plugin = createDefaultPluginWith(script);
        plugin.start(); // Start it first, because it is stopped initially.

        plugin.stop();

        PluginState expected = PluginState.STOPPED;
        PluginState actual = plugin.state();
        assertEquals(expected, actual);
    }

    @Test
    public void stop_whenPluginIsStarted_callsStopOnScripts() {
        FakeScript script = ObjectBuilders.script().build();
        Plugin plugin = createDefaultPluginWith(script);
        plugin.start(); // Start it first, because it is stopped initially.

        plugin.stop();

        assertTrue(script.onStopCalled);
    }

    @Test
    public void stop_whenPluginIsStopped_throwsException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("This plugin is already stopped.");

        Plugin plugin = createDefaultPluginWith();

        plugin.stop();
    }

    @Test
    public void addScript_whenPluginIsStoped_addsScriptToPlugin() {
        Script script = ObjectBuilders.script().build();
        Plugin plugin = ObjectBuilders.plugin().build();

        plugin.addScript(script);

        Collection<Script> expected = Arrays.asList(script);
        Collection<Script> actual = plugin.scripts();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void addScript_nullScript_throwsException() {
        expectedException.expect(NullPointerException.class);

        Plugin plugin = createDefaultPluginWith();

        plugin.addScript(null);
    }

    @Test
    public void addScript_whenPluginIsStarted_throwsException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Scripts cannot be added when a plugin is started.");

        Script script = ObjectBuilders.script().build();
        Plugin plugin = createDefaultPluginWith(script);

        plugin.start();
        plugin.addScript(script);
    }

    @Test
    public void removeScript_whenPluginIsStopped_removesPlugin() {
        Script script = ObjectBuilders.script().build();
        Plugin plugin = ObjectBuilders.plugin().build();

        plugin.addScript(script);
        plugin.removeScript(script);

        Collection<Script> expected = Collections.emptyList();
        Collection<Script> actual = plugin.scripts();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void removeScript_nullScript_throwsException() {
        expectedException.expect(NullPointerException.class);

        Plugin plugin = createDefaultPluginWith();

        plugin.removeScript(null);
    }

    @Test
    public void removeScript_whenPluginIsStarted_throwsException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Scripts cannot be removed when a plugin is started.");

        Script script = ObjectBuilders.script().build();
        Plugin plugin = createDefaultPluginWith(script);

        plugin.start();
        plugin.removeScript(script);
    }

    @Test
    public void equals_sameObjects_returnsTrue() {
        Plugin plugin1 = ObjectBuilders.plugin().build();
        Plugin plugin2 = ObjectBuilders.plugin().build();

        boolean result = plugin1.equals(plugin2);
        assertTrue(result);
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        Plugin plugin1 = ObjectBuilders.plugin().build();
        Plugin plugin2 = null;

        boolean result = plugin1.equals(plugin2);
        assertFalse(result);
    }

    @Test
    public void equals_differentId_returnsFalse() {
        Plugin plugin1 = ObjectBuilders.plugin().withPluginInformation(ObjectBuilders.pluginInformation().withId("id1").build()).build();
        Plugin plugin2 = ObjectBuilders.plugin().withPluginInformation(ObjectBuilders.pluginInformation().withId("id2").build()).build();

        boolean result = plugin1.equals(plugin2);
        assertFalse(result);
    }

    @Test
    public void hashCode_sameObjects_returnsSameHashCode() {
        Plugin plugin1 = ObjectBuilders.plugin().build();
        Plugin plugin2 = ObjectBuilders.plugin().build();

        boolean result = plugin1.hashCode() == plugin2.hashCode();
        assertTrue(result);
    }

    @Test
    public void hashCode_differentObjects_returnsDifferentHashCode() {
        Plugin plugin1 = ObjectBuilders.plugin().withPluginInformation(ObjectBuilders.pluginInformation().withId("id1").build()).build();
        Plugin plugin2 = ObjectBuilders.plugin().withPluginInformation(ObjectBuilders.pluginInformation().withId("id2").build()).build();

        boolean result = plugin1.hashCode() == plugin2.hashCode();
        assertFalse(result);
    }


    @Test
    public void scripts_byDefault_returnsEmptyCollection() {
        Plugin plugin = createDefaultPluginWith();

        Collection<Script> expected = Collections.emptyList();
        Collection<Script> actual = plugin.scripts();
        assertArrayEquals(expected.toArray(), actual.toArray());
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

    private Plugin createDefaultPluginWith(Script... scripts) {
        Plugin plugin = ObjectBuilders.plugin().build();

        Arrays.asList(scripts).forEach(plugin::addScript);

        return plugin;
    }

}
