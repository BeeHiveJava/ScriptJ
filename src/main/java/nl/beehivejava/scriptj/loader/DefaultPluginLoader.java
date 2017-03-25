package nl.beehivejava.scriptj.loader;

import nl.beehivejava.scriptj.Plugin;
import nl.beehivejava.scriptj.PluginInformation;
import nl.beehivejava.scriptj.io.ResourceLoader;
import nl.beehivejava.scriptj.parser.PluginInformationParser;
import nl.beehivejava.scriptj.script.Script;
import nl.beehivejava.scriptj.script.ScriptInformation;
import nl.beehivejava.scriptj.script.ScriptType;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * The {@code DefaultPluginLoader} loads a {@link Plugin} based on a given {@link ResourceLoader} and a given
 * {@link PluginInformationParser}.
 *
 * @author Lesley
 */
public final class DefaultPluginLoader implements PluginLoader {

    private final Map<ScriptType, ScriptLoader> scriptLoaders = new EnumMap<>(ScriptType.class);

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
            Plugin plugin = new Plugin(location, information);

            Path path = getPath(location);
            for (ScriptInformation scriptInformation : information.scripts()) {
                ScriptLoader scriptLoader = scriptLoaders.get(scriptInformation.type());
                if (scriptLoader == null) {
                    throw new IllegalArgumentException("No ScriptLoader found for Script.");
                }

                String scriptLocation = path.resolve(scriptInformation.name()).toString();
                Script script = scriptLoader.load(scriptLocation);
                plugin.addScript(script);
            }

            return plugin;
        }
    }

    @Override
    public void addScriptLoader(ScriptLoader loader) {
        Objects.requireNonNull(loader);

        scriptLoaders.put(loader.compatibleScriptType(), loader);
    }

    private Path getPath(String location) {
        Path path = Paths.get(location);
        Path parent = path.getParent();
        path = parent == null ? Paths.get("/") : parent;

        return path;
    }
}
