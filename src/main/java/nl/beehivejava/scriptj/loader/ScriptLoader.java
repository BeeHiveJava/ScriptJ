package nl.beehivejava.scriptj.loader;

import nl.beehivejava.scriptj.script.Script;
import nl.beehivejava.scriptj.script.ScriptType;

import java.io.IOException;

/**
 * A {@code ScriptLoader} can load a {@link Script} from an external location, for example, a {@code Script} existing
 * on the filesystem.
 *
 * @author Lesley
 */
public interface ScriptLoader {

    /**
     * Loads a {@link Script} on the given {@code location}. The {@code location} represents
     * the {@code Script} file.
     *
     * @param location The {@code location} of the {@code Script} file, should not be {@code null}.
     * @return The loaded {@code Script}.
     * @throws IOException              If something failed while loading the {@code Script} from the
     *                                  given {@code location}.
     * @throws NullPointerException     If the given {@code location} is {@code null}.
     * @throws IllegalArgumentException If the loaded {@code Script}'s type is not
     *                                  compatible (See {@link #compatibleScriptType()}.
     */
    Script load(String location) throws IOException;

    /**
     * Gets this {@code ScriptLoader}'s compatible {@link ScriptType}. It represents the type of {@code Script} that
     * it can load.
     *
     * @return The compatible {@code ScriptType}.
     */
    ScriptType compatibleScriptType();

}
