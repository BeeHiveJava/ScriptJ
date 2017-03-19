package nl.beehivejava.scriptj.parser;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import nl.beehivejava.scriptj.PluginInformation;
import nl.beehivejava.scriptj.script.ScriptInformation;
import nl.beehivejava.scriptj.script.ScriptType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Can parse an {@link InputStream} to a {@link PluginInformation}, given that the {@code InputStream} contains its
 * information in a JSON format.
 *
 * @author Lesley
 */
public final class JsonPluginInformationParser implements PluginInformationParser {

    public static final String VALUE_ID = "id";
    public static final String VALUE_AUTHOR = "author";

    public static final String VALUE_SCRIPTS = "scripts";
    public static final String VALUE_SCRIPT_NAME = "name";
    public static final String VALUE_SCRIPT_TYPE = "type";

    @Override
    public PluginInformation parse(InputStream is) throws IOException {
        Objects.requireNonNull(is);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            JsonValue json = Json.parse(reader);
            return parsePluginInformation(json);
        }
    }

    private PluginInformation parsePluginInformation(JsonValue json) {
        JsonObject object = json.asObject();

        String id = parseId(object);
        String author = parseAuthor(object);
        ScriptInformation[] scripts = parseScriptInformation(object);

        return new PluginInformation(id, author, scripts);
    }

    private String parseId(JsonObject json) {
        JsonValue id = json.get(VALUE_ID);
        requireValidId(id);

        return id.asString();
    }

    private void requireValidId(JsonValue id) {
        requireValueNotNull(id, VALUE_ID);
        requireValueIsString(id, VALUE_ID);
    }

    private String parseAuthor(JsonObject json) {
        JsonValue author = json.get(VALUE_AUTHOR);
        requireValidAuthor(author);

        return author.asString();
    }

    private void requireValidAuthor(JsonValue author) {
        requireValueNotNull(author, VALUE_AUTHOR);
        requireValueIsString(author, VALUE_AUTHOR);
    }

    private ScriptInformation[] parseScriptInformation(JsonObject json) {
        JsonValue value = json.get(VALUE_SCRIPTS);
        requireValidScripts(value);

        return parseScriptInformation(value.asArray());
    }

    private void requireValidScripts(JsonValue scripts) {
        requireValueNotNull(scripts, VALUE_SCRIPTS);
        requireValueIsArray(scripts, VALUE_SCRIPTS);
    }

    private ScriptInformation[] parseScriptInformation(JsonArray array) {
        List<ScriptInformation> scripts = new ArrayList<>();

        array.iterator().forEachRemaining(v -> scripts.add(parseScript(v)));

        return scripts.toArray(new ScriptInformation[0]);
    }

    private ScriptInformation parseScript(JsonValue value) {
        requireValueIsObject(value, "script");
        JsonObject script = value.asObject();

        JsonValue name = script.get(VALUE_SCRIPT_NAME);
        requireValidScriptName(name);

        JsonValue type = script.get(VALUE_SCRIPT_TYPE);
        requireValidScriptType(type);

        return new ScriptInformation(name.asString(), ScriptType.parse(type.asString()));
    }

    private void requireValidScriptName(JsonValue name) {
        String scriptName = "script name";

        requireValueNotNull(name, scriptName);
        requireValueIsString(name, scriptName);
    }

    private void requireValidScriptType(JsonValue type) {
        String scriptType = "script type";

        requireValueNotNull(type, scriptType);
        requireValueIsString(type, scriptType);
    }

    private void requireValueNotNull(JsonValue value, String name) {
        if (value == null) {
            throw new NullPointerException(String.format("Missing %s value.", name));
        }
    }

    private void requireValueIsString(JsonValue value, String name) {
        if (!value.isString()) {
            throw new IllegalArgumentException(String.format("Invalid %s value, should be a String.", name));
        }
    }

    private void requireValueIsObject(JsonValue value, String name) {
        if (!value.isObject()) {
            throw new IllegalArgumentException(String.format("Invalid %s value, should be an Object.", name));
        }
    }

    private void requireValueIsArray(JsonValue value, String name) {
        if (!value.isArray()) {
            throw new IllegalArgumentException(String.format("Invalid %s value, should be an array.", name));
        }
    }
}
