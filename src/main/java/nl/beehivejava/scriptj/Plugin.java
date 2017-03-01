package nl.beehivejava.scriptj;

import nl.beehivejava.scriptj.script.Script;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

/**
 * A {@code Plugin} represents a collection of {@link Script Scripts}, loaded or not. The {@code Plugin} class keeps track
 * of the {@code Scripts}, but is not responsible of loading them in any way. The {@code Plugin} class, however,
 * provides a gateway to the {@code Scripts}. They can be started via a {@code Plugin}.
 *
 * @author Lesley
 */
public final class Plugin {

    private final String location;
    private final PluginInformation information;

    private final Collection<Script> scripts = new HashSet<>();

    private PluginState state;

    /**
     * Initializes a new {@code Plugin} using the given arguments.
     *
     * @param location    The {@code location} where the information file of this {@code Plugin} is found,
     *                    should not be {@code null}.
     * @param information The parsed {@link PluginInformation} of this {@code Plugin}, should not be {@code null}.
     * @throws NullPointerException If the given {@code location} is {@code null}.
     *                              If the given {@code information} is {@code null}.
     */
    public Plugin(String location, PluginInformation information) {
        this.location = Objects.requireNonNull(location);
        this.information = Objects.requireNonNull(information);

        state = PluginState.STOPPED;
    }

    /**
     * Starts this {@code Plugin} and all its {@link Script Scripts}.
     *
     * @throws IllegalStateException If this {@code Plugin} is already started, that is if {@link #state()} returns
     *                               {@link PluginState#STARTED}.
     *                               If this {@code Plugin} does not contain any {@code Scripts}.
     */
    public void start() {
        if (state == PluginState.STARTED) {
            throw new IllegalStateException("This plugin is already started.");
        }

        if (scripts.size() < 1) {
            throw new IllegalStateException("This plugin has no scripts to start.");
        }

        scripts.forEach(Script::start);
        state = PluginState.STARTED;
    }

    /**
     * Stops this {@code Plugin}.
     *
     * @throws IllegalStateException If this {@code Plugin} is already stopped, that is if {@link #state()} returns
     *                               {@link PluginState#STOPPED}.
     */
    public void stop() {
        if (state == PluginState.STOPPED) {
            throw new IllegalStateException("This plugin is already stopped.");
        }

        // Note: the amount of scripts here can never get below 1 since there are constraints on
        // start() and removeScript(..).

        scripts.forEach(Script::stop);
        state = PluginState.STOPPED;
    }

    /**
     * Adds a loaded {@link Script} to this {@code Plugin}. Scripts can only be added if this {@code Plugin's} state
     * is {@link PluginState#STOPPED}.
     *
     * @param script The {@code Script} to add to this {@code Plugin}, should not be {@code null}.
     * @throws NullPointerException  If the given {@code script} is {@code null}.
     * @throws IllegalStateException If this {@code Plugin} is started.
     */
    public void addScript(Script script) {
        Objects.requireNonNull(script);

        if (state == PluginState.STARTED) {
            throw new IllegalStateException("Scripts cannot be added when a plugin is started.");
        }

        scripts.add(script);
    }

    /**
     * Removes a loaded {@link Script} from this {@code Plugin}. Scripts can only be removed if this {@code Plugin's} state
     * is {@link PluginState#STOPPED}.
     *
     * @param script The {@code Script} to remove from this {@code Plugin}, should not be {@code null}.
     * @throws NullPointerException  If the given {@code script} is {@code null}.
     * @throws IllegalStateException If this {@code Plugin} is started.
     */
    public void removeScript(Script script) {
        Objects.requireNonNull(script);

        if (state == PluginState.STARTED) {
            throw new IllegalStateException("Scripts cannot be removed when a plugin is started.");
        }

        scripts.remove(script);
    }

    /**
     * Retrieves all {@link Script Scripts} that were added to this {@code Plugin}.
     *
     * @return A {@link Collection} containing all {@code Scripts} added to this {@code Plugin}.
     */
    public Collection<Script> scripts() {
        return Collections.unmodifiableCollection(scripts);
    }

    /**
     * Retrieves the {@code state} this {@code Plugin} is in.
     *
     * @return The {@link PluginState} of this {@code Plugin}.
     */
    public PluginState state() {
        return state;
    }

    /**
     * Retrieves the {@code location} of the information file of this {@code Plugin}.
     *
     * @return A {@link String} representing the location of the information file of this {@code Plugin}.
     */
    public String location() {
        return location;
    }

    /**
     * Retrieves the parsed {@link PluginInformation} of this {@code Plugin}.
     *
     * @return The {@code information} of this {@code Plugin}.
     */
    public PluginInformation information() {
        return information;
    }

}
