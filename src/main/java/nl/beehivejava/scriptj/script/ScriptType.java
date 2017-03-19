package nl.beehivejava.scriptj.script;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

/**
 * The {@code ScriptType} defines what kind of {@code Scripts} can be run inside the system.
 * Usually a single {@code ScriptType} represents a single language, such as 'JavaScript' or 'Ruby'.
 *
 * @author Lesley
 */
public enum ScriptType {

    /**
     * Defines the JavaScript-language. Scripts with a {@link ScriptType#JAVASCRIPT} type are written in JavaScript and
     * can be ran inside JavaScript-supporting {@code PluginEnvironments}.
     */
    JAVASCRIPT("js", "javascript"),

    /**
     * Defines the Ruby-language. Scripts with a {@link ScriptType#RUBY} type are written in Ruby and can be ran inside
     * Ruby-supporting {@code PluginEnvironments}.
     */
    RUBY("rb", "ruby");

    private static final Collection<ScriptType> SCRIPT_TYPE_CACHE = EnumSet.allOf(ScriptType.class);

    private final Collection<String> identifiers = new HashSet<>();

    /**
     * Initializes a new {@code ScriptType} using the given {@code identifiers}. Each identifier should be unique
     * to one {@code ScriptType}. A {@code ScriptType} can be retrieved by its {@code identifier}.
     *
     * @param identifiers An array of {@link String Strings} containing all {@code identifiers}.
     */
    ScriptType(String... identifiers) {
        this.identifiers.addAll(Arrays.asList(identifiers));
    }

    /**
     * Searches all {@code ScriptTypes} for the given {@code identifier}, and returns a {@code ScriptType} with a
     * matching {@code identifier}.
     *
     * @param identifier The identifier that identifies the {@code ScriptType} to search for, should not be {@code null}.
     * @return A {@code ScriptType} with a {@code identifier} that matches the given {@code identifier}.
     * @throws NullPointerException If the given {@code identifier} is {@code null}.
     *                              If no {@code ScriptType} with matching {@code identifier} was found.
     */
    public static ScriptType parse(String identifier) {
        Objects.requireNonNull(identifier);

        Optional<ScriptType> optional = SCRIPT_TYPE_CACHE.stream()
                .filter(s -> s.identifiers.contains(identifier))
                .findAny();
        return optional.orElseThrow(NullPointerException::new);
    }

    /**
     * Retrieves all the {@code identifiers} of this {@code ScriptType}.
     *
     * @return A {@link Collection} containing all the {@code identifiers} of this {@code ScriptType}.
     */
    public Collection<String> identifiers() {
        return Collections.unmodifiableCollection(identifiers);
    }

}
