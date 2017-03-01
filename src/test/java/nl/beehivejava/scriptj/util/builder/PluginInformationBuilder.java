package nl.beehivejava.scriptj.util.builder;

import nl.beehivejava.scriptj.PluginInformation;

/**
 * @author Lesley
 */
public final class PluginInformationBuilder {

    private String id;
    private String author;

    PluginInformationBuilder() {
        id = "id";
        author = "author";
    }

    public PluginInformationBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public PluginInformationBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public PluginInformation build() {
        return new PluginInformation(id, author);
    }

}
