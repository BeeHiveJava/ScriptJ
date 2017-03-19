package nl.beehivejava.scriptj.script;

import nl.beehivejava.scriptj.util.builder.ObjectBuilders;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void equals_sameObject_returnsTrue() {
        ScriptInformation script1 = ObjectBuilders.scriptInformation().build();
        ScriptInformation script2 = ObjectBuilders.scriptInformation().build();

        boolean result = script1.equals(script2);
        assertTrue(result);
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        ScriptInformation script1 = ObjectBuilders.scriptInformation().build();
        ScriptInformation script2 = null;

        boolean result = script1.equals(script2);
        assertFalse(result);
    }

    @Test
    public void equals_otherName_returnsFalse() {
        ScriptInformation script1 = ObjectBuilders.scriptInformation().withName("name1").build();
        ScriptInformation script2 = ObjectBuilders.scriptInformation().withName("name2").build();

        boolean result = script1.equals(script2);
        assertFalse(result);
    }

    @Test
    public void equals_otherScriptType_returnsFalse() {
        ScriptInformation script1 = ObjectBuilders.scriptInformation().withScriptType(ScriptType.JAVASCRIPT).build();
        ScriptInformation script2 = ObjectBuilders.scriptInformation().withScriptType(ScriptType.RUBY).build();

        boolean result = script1.equals(script2);
        assertFalse(result);
    }

    @Test
    public void hashCode_sameObjects_returnSameHashCode() {
        ScriptInformation script1 = ObjectBuilders.scriptInformation().build();
        ScriptInformation script2 = ObjectBuilders.scriptInformation().build();

        boolean result = script1.hashCode() == script2.hashCode();
        assertTrue(result);
    }

    @Test
    public void hashCode_otherObjects_returnSameHashCode() {
        ScriptInformation script1 = ObjectBuilders.scriptInformation().withName("name1").build();
        ScriptInformation script2 = ObjectBuilders.scriptInformation().withName("name2").build();

        boolean result = script1.hashCode() == script2.hashCode();
        assertFalse(result);
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
