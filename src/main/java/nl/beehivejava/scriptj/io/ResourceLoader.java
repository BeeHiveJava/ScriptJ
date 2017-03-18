package nl.beehivejava.scriptj.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * A {@code ResourceLoader} can convert a location (as a {@link String}) to an {@link InputStream}. For example: a
 * {@code UrlResourceLoader} could be implemented, loading an URL (as a {@code String}) to an {@code InputStream}.
 *
 * @author Lesley
 */
public interface ResourceLoader {

    /**
     * Converts the given {@code location} to an {@link InputStream}.
     *
     * @param location A {@link String} representing the location of an external resource, should not be {@code null}.
     * @return An {@code InputStream} containing the resource on the given {@code location}.
     * @throws NullPointerException If the given {@code location} is {@code null}.
     * @throws IOException          If something failed while loading the resource.
     */
    InputStream load(String location) throws IOException;

}
