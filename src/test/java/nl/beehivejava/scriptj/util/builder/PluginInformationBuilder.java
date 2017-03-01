package nl.beehivejava.scriptj.util.builder;

import nl.beehivejava.scriptj.PluginInformation;
import nl.beehivejava.scriptj.script.ScriptInformation;

/**
 * @author Lesley
 */
public final class PluginInformationBuilder {

    private String id;
    private String author;
    private ScriptInformation[] scripts;

    PluginInformationBuilder() {
        id = "id";
        author = "author";
        scripts = new ScriptInformation[]{ObjectBuilders.scriptInformation().build()};
    }

    public PluginInformationBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public PluginInformationBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public PluginInformationBuilder withScripts(ScriptInformation... scripts) {
        this.scripts = scripts;
        return this;
    }

    public PluginInformation build() {
        return new PluginInformation(id, author, scripts);
    }

}
