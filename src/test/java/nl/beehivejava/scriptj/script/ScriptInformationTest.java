package nl.beehivejava.scriptj.script;

import nl.beehivejava.scriptj.util.builder.ObjectBuilders;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public final class ScriptInformationTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullName_throwsException() {
        expectedException.expect(NullPointerException.class);

        ScriptInformation information = ObjectBuilders.scriptInformation().withName(null).build();
    }

    @Test
    public void construct_nullScriptType_throwsException() {
        expectedException.expect(NullPointerException.class);

        ScriptInformation information = ObjectBuilders.scriptInformation().withScriptType(null).build();
    }

    @Test
    public void name_whenCalled_returnsName() {
        ScriptInformation information = ObjectBuilders.scriptInformation().withName("name").build();

        String expected = "name";
        String actual = information.name();
        assertEquals(expected, actual);
    }

    @Test
    public void type_whenCalled_returnsScriptType() {
        ScriptInformation information = ObjectBuilders.scriptInformation().withScriptType(ScriptType.JAVASCRIPT).build();

        ScriptType expected = ScriptType.JAVASCRIPT;
        ScriptType actual = information.type();
        assertEquals(expected, actual);
    }

}
