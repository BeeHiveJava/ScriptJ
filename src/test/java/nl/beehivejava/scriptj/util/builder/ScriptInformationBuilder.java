package nl.beehivejava.scriptj.util.builder;

import nl.beehivejava.scriptj.script.ScriptInformation;
import nl.beehivejava.scriptj.script.ScriptType;

/**
 * @author Lesley
 */
public final class ScriptInformationBuilder {

    String name;
    ScriptType type;

    ScriptInformationBuilder() {
        name = "name";
        type = ScriptType.JAVASCRIPT;
    }

    public ScriptInformationBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ScriptInformationBuilder withScriptType(ScriptType type) {
        this.type = type;
        return this;
    }

    public ScriptInformation build() {
        return new ScriptInformation(name, type);
    }

}
