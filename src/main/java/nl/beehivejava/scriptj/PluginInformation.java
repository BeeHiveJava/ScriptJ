package nl.beehivejava.scriptj;

import nl.beehivejava.scriptj.script.ScriptInformation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

/**
 * This class provides information about a {@code Plugin}. It is the result of parsing a plugin-information file.
 *
 * @author Lesley
 */
public final class PluginInformation {

    private final String id;
    private final String author;

    private final Collection<ScriptInformation> scripts = new HashSet<>();

    /**
     * Initializes a new {@code PluginInformation} using the given arguments.
     *
     * @param id      The {@code id} of the {@code Plugin}, should not be {@code null}.
     * @param author  The {@code author} of the {@code Plugin}, should not be {@code null}.
     * @param scripts The information about the {@code Scripts} that this {@code Plugin} contains,
     *                should not be {@code null} and should contain at least one {@code Script}.
     * @throws NullPointerException     If the given {@code id} is {@code null}.
     *                                  If the given {@code author} is {@code null}.
     * @throws IllegalArgumentException If there were no {@code Scripts} given.
     */
    public PluginInformation(String id, String author, ScriptInformation... scripts) {
        this.id = Objects.requireNonNull(id);
        this.author = Objects.requireNonNull(author);

        Objects.requireNonNull(scripts);
        if (scripts.length < 1) {
            throw new IllegalArgumentException("At least one script is required.");
        }

        this.scripts.addAll(Arrays.asList(scripts));
    }

    /**
     * Retrieves the {@code id} of a {@code Plugin}.
     *
     * @return A {@link String} representing the {@code id} of a {@code Plugin}.
     */
    public String id() {
        return id;
    }

    /**
     * Retrieves the {@code author} of a {@code Plugin}.
     *
     * @return A {@link String} representing the {@code author} of a {@code Plugin}.
     */
    public String author() {
        return author;
    }

    /**
     * Retrieves information about all the {@code Scripts} that this {@code Plugin} contains.
     *
     * @return A {@link Collection} of {@code Scripts} that this {@code Plugin} contains.
     */
    public Collection<ScriptInformation> scripts() {
        return Collections.unmodifiableCollection(scripts);
    }

}
