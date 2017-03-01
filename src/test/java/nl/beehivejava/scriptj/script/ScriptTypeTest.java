package nl.beehivejava.scriptj.script;

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
public final class ScriptTypeTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void identifiers_javaScript_returnsCorrectIdentifiers() {
        ScriptType type = ScriptType.JAVASCRIPT;

        Collection<String> expected = Arrays.asList("js", "javascript");
        Collection<String> actual = type.identifiers();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void parse_js_returnsJavaScript() {
        ScriptType expected = ScriptType.JAVASCRIPT;
        ScriptType actual = ScriptType.parse("js");
        assertEquals(expected, actual);
    }

    @Test
    public void parse_javaScript_returnsJavaScript() {
        ScriptType expected = ScriptType.JAVASCRIPT;
        ScriptType actual = ScriptType.parse("javascript");
        assertEquals(expected, actual);
    }

    @Test
    public void parse_nullIdentifier_throwsException() {
        expectedException.expect(NullPointerException.class);

        ScriptType type = ScriptType.parse(null);
    }

    @Test
    public void parse_unknownIdentifier_throwsException() {
        expectedException.expect(NullPointerException.class);

        ScriptType type = ScriptType.parse("some_unkown_identifier");
    }

}
