package nl.beehivejava.scriptj.util.builder;

import nl.beehivejava.scriptj.parser.JsonPluginInformationParser;
import nl.beehivejava.scriptj.parser.PluginInformationParser;

/**
 * @author Lesley
 */
public final class JsonPluginInformationParserBuilder {

    JsonPluginInformationParserBuilder() {
    }

    public PluginInformationParser build() {
        return new JsonPluginInformationParser();
    }

}
