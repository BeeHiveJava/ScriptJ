package nl.beehivejava.scriptj.loader;

import nl.beehivejava.scriptj.Plugin;
import nl.beehivejava.scriptj.PluginInformation;
import nl.beehivejava.scriptj.script.Script;

import java.io.IOException;

/**
 * A {@code PluginLoader} can load a {@link Plugin} from an external location, for example, a {@code Plugin} existing
 * on the filesystem.
 *
 * @author Lesley
 */
public interface PluginLoader {

    /**
     * Loads a {@link Plugin} on the given {@code location}. The {@code location} represents
     * the {@link PluginInformation} file. Note that all {@link Script Scripts} will be searched in the root of the
     * {@code PluginInformation} file.
     *
     * @param location The {@code location} of the {@link PluginInformation} file, should not be {@code null}.
     * @return The loaded {@code Plugin}.
     * @throws IOException          If something failed while loading the {@code Plugin} from the given {@code location}.
     * @throws NullPointerException If the given {@code location} is {@code null}.
     */
    Plugin load(String location) throws IOException;

    /**
     * Adds a {@link ScriptLoader} to this {@code PluginLoader}.
     *
     * @param loader The {@code ScriptLoader} to add, should not be {@code null}.
     * @throws NullPointerException If the given {@code loader} is {@code null}.
     */
    void addScriptLoader(ScriptLoader loader);

}
