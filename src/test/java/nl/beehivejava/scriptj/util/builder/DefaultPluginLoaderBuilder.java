package nl.beehivejava.scriptj.util.builder;

import nl.beehivejava.scriptj.io.ResourceLoader;
import nl.beehivejava.scriptj.loader.DefaultPluginLoader;
import nl.beehivejava.scriptj.parser.PluginInformationParser;

/**
 * @author Lesley
 */
public final class DefaultPluginLoaderBuilder {

    private ResourceLoader loader;
    private PluginInformationParser parser;

    DefaultPluginLoaderBuilder() {
        loader = ObjectBuilders.fileResourceLoader().build();
        parser = ObjectBuilders.jsonPluginInformationParser().build();

    }

    public DefaultPluginLoaderBuilder withResourceLoader(ResourceLoader loader) {
        this.loader = loader;
        return this;
    }

    public DefaultPluginLoaderBuilder withParser(PluginInformationParser parser) {
        this.parser = parser;
        return this;
    }

    public DefaultPluginLoader build() {
        return new DefaultPluginLoader(loader, parser);
    }

}
