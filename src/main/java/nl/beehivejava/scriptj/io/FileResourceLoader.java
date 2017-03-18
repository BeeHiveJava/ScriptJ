package nl.beehivejava.scriptj.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Converts a {@link File}, somewhere on the filesystem, to an {@link InputStream}.
 *
 * @author Lesley
 */
public final class FileResourceLoader implements ResourceLoader {

    private static void requirePathExists(Path path) throws IOException {
        if (!path.toFile().exists()) {
            throw new FileNotFoundException();
        }
    }

    private static void requirePathIsFile(Path path) {
        if (!path.toFile().isFile()) {
            throw new IllegalArgumentException();
        }
    }

    private static void requireValidPath(Path path) throws IOException {
        requirePathExists(path);
        requirePathIsFile(path);
    }

    @Override
    public InputStream load(String location) throws IOException {
        Objects.requireNonNull(location);

        Path path = Paths.get(location);
        requireValidPath(path);

        return Files.newInputStream(path);
    }

}
