package nl.beehivejava.scriptj;

import nl.beehivejava.scriptj.script.ScriptInformation;
import nl.beehivejava.scriptj.util.builder.ObjectBuilders;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Lesley
 */
public final class PluginInformationTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullId_throwsException() {
        expectedException.expect(NullPointerException.class);

        PluginInformation information = ObjectBuilders.pluginInformation().withId(null).build();
    }

    @Test
    public void construct_nullAuthor_throwsException() {
        expectedException.expect(NullPointerException.class);

        PluginInformation information = ObjectBuilders.pluginInformation().withAuthor(null).build();
    }

    @Test
    public void construct_nullScripts_throwsException() {
        expectedException.expect(NullPointerException.class);

        PluginInformation information = ObjectBuilders.pluginInformation().withScripts(null).build();
    }

    @Test
    public void construct_noScripts_throwsException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("At least one script is required.");

        PluginInformation information = ObjectBuilders.pluginInformation().withScripts().build();
    }

    @Test
    public void equals_sameObjects_returnsTrue() {
        PluginInformation information1 = ObjectBuilders.pluginInformation().build();
        PluginInformation information2 = ObjectBuilders.pluginInformation().build();

        boolean result = information1.equals(information2);
        assertTrue(result);
    }

    @Test
    public void equals_nullObject_returnsTrue() {
        PluginInformation information1 = ObjectBuilders.pluginInformation().build();
        PluginInformation information2 = null;

        boolean result = information1.equals(information2);
        assertFalse(result);
    }

    @Test
    public void equals_otherId_returnsTrue() {
        PluginInformation information1 = ObjectBuilders.pluginInformation().withId("id1").build();
        PluginInformation information2 = ObjectBuilders.pluginInformation().withId("id2").build();

        boolean result = information1.equals(information2);
        assertFalse(result);
    }

    @Test
    public void equals_otherAuthor_returnsTrue() {
        PluginInformation information1 = ObjectBuilders.pluginInformation().withAuthor("author1").build();
        PluginInformation information2 = ObjectBuilders.pluginInformation().withAuthor("author2").build();

        boolean result = information1.equals(information2);
        assertFalse(result);
    }

    @Test
    public void equals_otherScripts_returnsTrue() {
        ScriptInformation script1 = ObjectBuilders.scriptInformation().withName("name1").build();
        ScriptInformation script2 = ObjectBuilders.scriptInformation().withName("name2").build();
        PluginInformation information1 = ObjectBuilders.pluginInformation().withScripts(script1).build();
        PluginInformation information2 = ObjectBuilders.pluginInformation().withScripts(script2).build();

        boolean result = information1.equals(information2);
        assertFalse(result);
    }

    @Test
    public void hashCode_sameObjects_returnsSameHashCode() {
        PluginInformation information1 = ObjectBuilders.pluginInformation().build();
        PluginInformation information2 = ObjectBuilders.pluginInformation().build();

        boolean result = information1.hashCode() == information2.hashCode();
        assertTrue(result);
    }

    @Test
    public void hashCode_differentObjects_returnsSameHashCode() {
        PluginInformation information1 = ObjectBuilders.pluginInformation().withId("id1").build();
        PluginInformation information2 = ObjectBuilders.pluginInformation().withId("id2").build();

        boolean result = information1.hashCode() == information2.hashCode();
        assertFalse(result);
    }

    @Test
    public void information_whenCalled_returnsId() {
        PluginInformation information = ObjectBuilders.pluginInformation().withId("id").build();

        String expected = "id";
        String actual = information.id();
        assertEquals(expected, actual);
    }

    @Test
    public void author_whenCalled_returnsAuthor() {
        PluginInformation information = ObjectBuilders.pluginInformation().withAuthor("author").build();

        String expected = "author";
        String actual = information.author();
        assertEquals(expected, actual);
    }

    @Test
    public void scripts_whenCalled_returnsScripts() {
        ScriptInformation script = ObjectBuilders.scriptInformation().build();
        PluginInformation information = ObjectBuilders.pluginInformation().withScripts(script).build();

        Collection<ScriptInformation> expected = Arrays.asList(script);
        Collection<ScriptInformation> actual = information.scripts();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

}
