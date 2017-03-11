package nl.beehivejava.scriptj.load;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Lesley
 */
public interface PluginLoader {

    InputStream load(String location) throws IOException;

}
