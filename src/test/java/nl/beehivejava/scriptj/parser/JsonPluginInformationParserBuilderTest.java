package nl.beehivejava.scriptj.parser;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import nl.beehivejava.scriptj.util.builder.ObjectBuilders;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author Lesley
 */
public final class JsonPluginInformationParserBuilderTest extends PluginInformationParserTest {

    private static final String DEFAULT_JSON_INFORMATION = "{\n" +
            "  \"id\": \"id\",\n" +
            "  \"author\": \"author\",\n" +
            "  \"scripts\": [\n" +
            "    {\n" +
            "      \"name\": \"name\",\n" +
            "      \"type\": \"js\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Override
    protected InputStream makeDefaultInputStream() {
        return new ByteArrayInputStream(DEFAULT_JSON_INFORMATION.getBytes());
    }

    @Override
    protected InputStream makeMissingIdInputStream() {
        JsonObject json = Json.parse(DEFAULT_JSON_INFORMATION).asObject();
        json.remove("id");

        return new ByteArrayInputStream(json.toString().getBytes());
    }

    @Override
    protected InputStream makeInvalidIdInputStream() {
        JsonObject json = Json.parse(DEFAULT_JSON_INFORMATION).asObject();
        json.set("id", false); // Id should be a String.

        return new ByteArrayInputStream(json.toString().getBytes());
    }

    @Override
    protected InputStream makeMissingAuthorInputStream() {
        JsonObject json = Json.parse(DEFAULT_JSON_INFORMATION).asObject();
        json.remove("author");

        return new ByteArrayInputStream(json.toString().getBytes());
    }

    @Override
    protected InputStream makeInvalidAuthorInputStream() {
        JsonObject json = Json.parse(DEFAULT_JSON_INFORMATION).asObject();
        json.set("author", false); // author should be a String.

        return new ByteArrayInputStream(json.toString().getBytes());
    }

    @Override
    protected InputStream makeMissingScriptsInputStream() {
        JsonObject json = Json.parse(DEFAULT_JSON_INFORMATION).asObject();
        json.remove("scripts");

        return new ByteArrayInputStream(json.toString().getBytes());
    }


    @Override
    protected InputStream makeInvalidScriptsInputStream() {
        JsonObject json = Json.parse(DEFAULT_JSON_INFORMATION).asObject();
        json.set("scripts", false); // scripts should be an array.

        return new ByteArrayInputStream(json.toString().getBytes());
    }

    @Override
    protected InputStream makeInvalidScriptInputStream() {
        JsonObject json = Json.parse(DEFAULT_JSON_INFORMATION).asObject();
        json.get("scripts").asArray().set(0, false); // script should be an Object.

        return new ByteArrayInputStream(json.toString().getBytes());
    }

    @Override
    protected InputStream makeMissingScriptNameInputStream() {
        JsonObject json = Json.parse(DEFAULT_JSON_INFORMATION).asObject();
        JsonObject script = json.get("scripts").asArray().get(0).asObject();
        script.remove("name");

        return new ByteArrayInputStream(json.toString().getBytes());
    }

    @Override
    protected InputStream makeInvalidScriptNameInputStream() {
        JsonObject json = Json.parse(DEFAULT_JSON_INFORMATION).asObject();
        JsonObject script = json.get("scripts").asArray().get(0).asObject();
        script.set("name", false); // script name should be a String.

        return new ByteArrayInputStream(json.toString().getBytes());
    }

    @Override
    protected InputStream makeMissingScriptTypeInputStream() {
        JsonObject json = Json.parse(DEFAULT_JSON_INFORMATION).asObject();
        JsonObject script = json.get("scripts").asArray().get(0).asObject();
        script.remove("type");

        return new ByteArrayInputStream(json.toString().getBytes());
    }

    @Override
    protected InputStream makeInvalidScriptTypeInputStream() {
        JsonObject json = Json.parse(DEFAULT_JSON_INFORMATION).asObject();
        JsonObject script = json.get("scripts").asArray().get(0).asObject();
        script.set("type", false); // script type should be a String.

        return new ByteArrayInputStream(json.toString().getBytes());
    }


    @Override
    protected PluginInformationParser makeDefaultPluginInformationParser() {
        return ObjectBuilders.jsonPluginInformationParser().build();
    }
}
