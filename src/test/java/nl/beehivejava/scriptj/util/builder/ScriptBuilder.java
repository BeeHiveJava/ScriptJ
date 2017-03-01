package nl.beehivejava.scriptj.util.builder;

import nl.beehivejava.scriptj.script.ScriptInformation;
import nl.beehivejava.scriptj.util.fake.FakeScript;

/**
 * @author Lesley
 */
public final class ScriptBuilder {

    private String location;
    private ScriptInformation information;

    ScriptBuilder() {
        location = "location";
        information = ObjectBuilders.scriptInformation().build();
    }

    public ScriptBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public ScriptBuilder withScriptInformation(ScriptInformation information) {
        this.information = information;
        return this;
    }

    public FakeScript build() {
        return new FakeScript(location, information);
    }

}
