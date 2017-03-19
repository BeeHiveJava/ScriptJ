package nl.beehivejava.scriptj.script;

import java.util.Objects;

/**
 * This class provides general information about a {@code Script}. It is the result of parsing a subsection in a
 * plugin-information file.
 *
 * @author Lesley
 */
public final class ScriptInformation {

    private final String name;
    private final ScriptType type;

    /**
     * Initializes a new {@code ScriptInformation} using the given arguments.
     *
     * @param name The {@code name} of the {@code Script}, should not be {@code null}.
     * @param type The {@code type} of the {@code Script}, should not be {@code null}.
     * @throws NullPointerException If the given {@code name} is {@code null}.
     *                              If the given {@code type} is {@code null}.
     */
    public ScriptInformation(String name, ScriptType type) {
        this.name = Objects.requireNonNull(name);
        this.type = Objects.requireNonNull(type);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ScriptInformation)) {
            return false;
        }

        ScriptInformation other = (ScriptInformation) o;
        return name.equals(other.name) && type.equals(other.type);
    }

    @Override
    public int hashCode() {
        int result = 19;
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    /**
     * Retrieves the name of this {@code Script}.
     *
     * @return A {@link String} containing the name of this {@code Script}.
     */
    public String name() {
        return name;
    }

    /**
     * Retrieves the {@link ScriptType} of this {@code Script}.
     *
     * @return The {@code ScriptType} of this {@code Script}.
     */
    public ScriptType type() {
        return type;
    }

}
