package nl.beehivejava.scriptj.loader;

import nl.beehivejava.scriptj.Plugin;
import nl.beehivejava.scriptj.PluginInformation;
import nl.beehivejava.scriptj.io.ResourceLoader;
import nl.beehivejava.scriptj.parser.PluginInformationParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * The {@code DefaultPluginLoader} loads a {@link Plugin} based on a given {@link ResourceLoader} and a given
 * {@link PluginInformationParser}.
 *
 * @author Lesley
 */
public final class DefaultPluginLoader implements PluginLoader {

    private final ResourceLoader loader;
    private final PluginInformationParser parser;

    /**
     * Initializes a new {@code DefaultPluginLoader} using the given arguments.
     *
     * @param loader The {@link ResourceLoader} to load the external {@link Plugin} from, should not be {@code null}.
     * @param parser The {@link PluginInformationParser} to parse {@link PluginInformation}, should not be {@code null}.
     * @throws NullPointerException If the given {@code loader} is {@code null},
     *                              if the given {@code parser} is {@code null}.
     */
    public DefaultPluginLoader(ResourceLoader loader, PluginInformationParser parser) {
        this.loader = Objects.requireNonNull(loader);
        this.parser = Objects.requireNonNull(parser);
    }

    @Override
    public Plugin load(String location) throws IOException {
        Objects.requireNonNull(location);

        try (InputStream is = loader.load(location)) {
            PluginInformation information = parser.parse(is);

            return new Plugin(location, information);
        }
    }
}
