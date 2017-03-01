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
