package nl.beehivejava.scriptj.load;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Lesley
 */
public final class FilePluginLoader implements PluginLoader {

    @Override
    public InputStream load(String location) throws IOException {
        Path path = Paths.get(location);
        InputStream in = Files.newInputStream(path);

        return in;
    }
}
