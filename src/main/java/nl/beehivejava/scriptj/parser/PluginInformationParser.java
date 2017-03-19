package nl.beehivejava.scriptj.parser;

import nl.beehivejava.scriptj.Plugin;
import nl.beehivejava.scriptj.PluginInformation;

import java.io.IOException;
import java.io.InputStream;

/**
 * A {@code PluginInformationParser} can convert/parse an {@link InputStream} to a {@link PluginInformation}.
 *
 * @author Lesley
 */
@FunctionalInterface
public interface PluginInformationParser {

    /**
     * Parses the given {@link InputStream} to a {@link PluginInformation}.
     *
     * @param is The {@code InputStream} that holds the data of the {@code PluginInformation}, should not be
     *           {@code null}.
     * @return A {@code PluginInformation} holding the data of a {@link Plugin}.
     * @throws IOException          If some error occurs while reading from the {@code InputStream}.
     * @throws NullPointerException If the given {@code InputStream} is {@code null}.
     */
    PluginInformation parse(InputStream is) throws IOException;

}
