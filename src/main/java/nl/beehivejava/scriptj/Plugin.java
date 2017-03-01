package nl.beehivejava.scriptj;

import java.util.Objects;

/**
 * A {@code Plugin} represents a collection of {@code Scripts}, loaded or not. The {@code Plugin} class keeps track
 * of the {@code Scripts}, but is not responsible of loading them in any way. The {@code Plugin} class, however,
 * provides a gateway to the {@code Scripts}. They can be started via a {@code Plugin}.
 *
 * @author Lesley
 */
public final class Plugin {

    private final String location;
    private final PluginInformation information;

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
     * Starts this {@code Plugin}.
     *
     * @throws IllegalStateException If this {@code Plugin} is already started, that is if {@link #state()} returns
     *                               {@link PluginState#STARTED}.
     */
    public void start() {
        if (state == PluginState.STARTED) {
            throw new IllegalStateException("This plugin is already started.");
        }

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

        state = PluginState.STOPPED;
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
     * @return A {@link String} representing the location of information file of this {@code Plugin}.
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
