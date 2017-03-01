package nl.beehivejava.scriptj.util.builder;

import nl.beehivejava.scriptj.Plugin;
import nl.beehivejava.scriptj.PluginInformation;

/**
 * @author Lesley
 */
public final class PluginBuilder {

    String location;
    PluginInformation information;

    PluginBuilder() {
        location = "location";
        information = ObjectBuilders.pluginInformation().build();
    }

    public PluginBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public PluginBuilder withPluginInformation(PluginInformation information) {
        this.information = information;
        return this;
    }

    public Plugin build() {
        return new Plugin(location, information);
    }

}
