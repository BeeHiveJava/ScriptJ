package nl.beehivejava.scriptj;

import java.util.Objects;

/**
 * This class provides information about a {@code Plugin}. It is the result of parsing a plugin-information file.s
 *
 * @author Lesley
 */
public final class PluginInformation {

    private final String id;
    private final String author;

    /**
     * Initializes a new {@code PluginInformation} using the given arguments.
     *
     * @param id     The {@code id} of the {@code Plugin}, should not be {@code null}.
     * @param author The {@code author} of the {@code Plugin}, should not be {@code null}.
     * @throws NullPointerException If the given {@code id} is {@code null}.
     *                              If the given {@code author} is {@code null}.
     */
    public PluginInformation(String id, String author) {
        this.id = Objects.requireNonNull(id);
        this.author = Objects.requireNonNull(author);
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

}
