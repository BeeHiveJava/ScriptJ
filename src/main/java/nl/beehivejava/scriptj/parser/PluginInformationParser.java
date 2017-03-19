package nl.beehivejava.scriptj.parser;

import nl.beehivejava.scriptj.PluginInformation;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Lesley
 */
public interface PluginInformationParser {

    PluginInformation parse(InputStream is) throws IOException;

}
