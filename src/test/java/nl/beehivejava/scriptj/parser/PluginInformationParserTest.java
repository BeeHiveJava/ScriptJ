package nl.beehivejava.scriptj.parser;

import nl.beehivejava.scriptj.PluginInformation;
import nl.beehivejava.scriptj.util.builder.ObjectBuilders;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public abstract class PluginInformationParserTest {

    @Rule
    public final ExpectedException internalExpectedException = ExpectedException.none();

    @Test
    public final void parse_nullInputStream_throwsException() throws Exception {
        internalExpectedException.expect(NullPointerException.class);

        PluginInformationParser parser = makeDefaultPluginInformationParser();

        parser.parse(null);
    }

    @Test
    public final void parse_validInputStream_returnsCorrectPluginInformation() throws Exception {
        InputStream is = makeDefaultInputStream();
        PluginInformationParser parser = makeDefaultPluginInformationParser();

        PluginInformation expected = ObjectBuilders.pluginInformation().build();
        PluginInformation actual = parser.parse(is);
        assertEquals(expected, actual);
    }

    @Test
    public final void parse_missingIdInputStream_throwsException() throws Exception {
        internalExpectedException.expect(NullPointerException.class);
        internalExpectedException.expectMessage("Missing id value.");

        InputStream is = makeMissingIdInputStream();
        PluginInformationParser parser = makeDefaultPluginInformationParser();

        parser.parse(is);
    }

    @Test
    public final void parse_invalidIdInputStream_throwsException() throws Exception {
        internalExpectedException.expect(IllegalArgumentException.class);
        internalExpectedException.expectMessage("Invalid id value, should be a String.");

        InputStream is = makeInvalidIdInputStream();
        PluginInformationParser parser = makeDefaultPluginInformationParser();

        parser.parse(is);
    }

    @Test
    public final void parse_missingAuthorInputStream_throwsException() throws Exception {
        internalExpectedException.expect(NullPointerException.class);
        internalExpectedException.expectMessage("Missing author value.");

        InputStream is = makeMissingAuthorInputStream();
        PluginInformationParser parser = makeDefaultPluginInformationParser();

        parser.parse(is);
    }

    @Test
    public final void parse_invalidAuthorInputStream_throwsException() throws Exception {
        internalExpectedException.expect(IllegalArgumentException.class);
        internalExpectedException.expectMessage("Invalid author value, should be a String.");

        InputStream is = makeInvalidAuthorInputStream();
        PluginInformationParser parser = makeDefaultPluginInformationParser();

        parser.parse(is);
    }

    @Test
    public final void parse_missingScriptsInputStream_throwsException() throws Exception {
        internalExpectedException.expect(NullPointerException.class);
        internalExpectedException.expectMessage("Missing scripts value.");

        InputStream is = makeMissingScriptsInputStream();
        PluginInformationParser parser = makeDefaultPluginInformationParser();

        parser.parse(is);
    }

    @Test
    public final void parse_invalidScriptsInputStream_throwsException() throws Exception {
        internalExpectedException.expect(IllegalArgumentException.class);
        internalExpectedException.expectMessage("Invalid scripts value, should be an array.");

        InputStream is = makeInvalidScriptsInputStream();
        PluginInformationParser parser = makeDefaultPluginInformationParser();

        parser.parse(is);
    }

    @Test
    public final void parse_invalidScriptInputStream_throwsException() throws Exception {
        internalExpectedException.expect(IllegalArgumentException.class);
        internalExpectedException.expectMessage("Invalid script value, should be an Object.");

        InputStream is = makeInvalidScriptInputStream();
        PluginInformationParser parser = makeDefaultPluginInformationParser();

        parser.parse(is);
    }

    @Test
    public final void parse_missingScriptNameInputStream_throwsException() throws Exception {
        internalExpectedException.expect(NullPointerException.class);
        internalExpectedException.expectMessage("Missing script name value.");

        InputStream is = makeMissingScriptNameInputStream();
        PluginInformationParser parser = makeDefaultPluginInformationParser();

        parser.parse(is);
    }

    @Test
    public final void parse_invalidScriptNameInputStream_throwsException() throws Exception {
        internalExpectedException.expect(IllegalArgumentException.class);
        internalExpectedException.expectMessage("Invalid script name value, should be a String.");

        InputStream is = makeInvalidScriptNameInputStream();
        PluginInformationParser parser = makeDefaultPluginInformationParser();

        parser.parse(is);
    }

    @Test
    public final void parse_missingScriptTypeInputStream_throwsException() throws Exception {
        internalExpectedException.expect(NullPointerException.class);
        internalExpectedException.expectMessage("Missing script type value.");

        InputStream is = makeMissingScriptTypeInputStream();
        PluginInformationParser parser = makeDefaultPluginInformationParser();

        parser.parse(is);
    }

    @Test
    public final void parse_invalidScriptTypeInputStream_throwsException() throws Exception {
        internalExpectedException.expect(IllegalArgumentException.class);
        internalExpectedException.expectMessage("Invalid script type value, should be a String.");

        InputStream is = makeInvalidScriptTypeInputStream();
        PluginInformationParser parser = makeDefaultPluginInformationParser();

        parser.parse(is);
    }

    protected abstract InputStream makeDefaultInputStream();

    protected abstract InputStream makeMissingIdInputStream();

    protected abstract InputStream makeInvalidIdInputStream();

    protected abstract InputStream makeMissingAuthorInputStream();

    protected abstract InputStream makeInvalidAuthorInputStream();

    protected abstract InputStream makeMissingScriptsInputStream();

    protected abstract InputStream makeInvalidScriptsInputStream();

    protected abstract InputStream makeInvalidScriptInputStream();

    protected abstract InputStream makeMissingScriptNameInputStream();

    protected abstract InputStream makeInvalidScriptNameInputStream();

    protected abstract InputStream makeMissingScriptTypeInputStream();

    protected abstract InputStream makeInvalidScriptTypeInputStream();

    protected abstract PluginInformationParser makeDefaultPluginInformationParser();

}
