package nl.beehivejava.scriptj.script;

import nl.beehivejava.scriptj.Plugin;

import java.util.Objects;

/**
 * A {@code Script} represents the actual code of a {@link Plugin}. It contains some metadata and the location of the
 * the actual resource to be loaded in order to execute this {@code Script}.
 *
 * @author Lesley
 */
public abstract class Script {

    private final String location;
    private final ScriptInformation information;

    private ScriptState state;

    /**
     * Initializes a new {@code Script} using the given arguments.
     *
     * @param location    The {@code location} of this {@code Script}, should not be {@code null}.
     * @param information The {@link ScriptInformation} of this {@code Script}, should not be {@code null}.
     * @throws NullPointerException If the given {@code location} is {@code null}.
     *                              If the given {@code information} is {@code null}.
     */
    public Script(String location, ScriptInformation information) {
        this.location = Objects.requireNonNull(location);
        this.information = Objects.requireNonNull(information);

        state = ScriptState.STOPPED;
    }

    /**
     * The {@link #onStart()} method gets called whenever this {@code Script} gets started.
     * This method call should be delegated to the actual loaded script-resource.
     */
    protected abstract void onStart();

    /**
     * Starts this {@code Script}.
     *
     * @throws IllegalStateException If this {@code Script} is already started, that is if {@link #state()} returns
     *                               {@link ScriptState#STARTED}.
     */
    public void start() {
        if (state == ScriptState.STARTED) {
            throw new IllegalStateException("This script is already started.");
        }

        onStart();
        state = ScriptState.STARTED;
    }

    /**
     * The {@link #onStop()} method gets called whenever this {@code Script} gets stopped.
     * This method call should be delegated to the actual loaded script-resource.
     */
    protected abstract void onStop();

    /**
     * Stops this {@code Script}.
     *
     * @throws IllegalStateException If this {@code Script} is already stopped, that is if {@link #state()} returns
     *                               {@link ScriptState#STOPPED}.
     */
    public void stop() {
        if (state == ScriptState.STOPPED) {
            throw new IllegalStateException("This script is already stopped.");
        }

        onStop();
        state = ScriptState.STOPPED;
    }

    /**
     * Retrieves the {@code state} this {@code Script} is in.
     *
     * @return The {@link ScriptState} of this {@code Script}.
     */
    public ScriptState state() {
        return state;
    }

    /**
     * Retrieves the {@code location} of loaded script-resource.
     *
     * @return A {@link String} representing the location of the loaded script-resource of this {@code Script}.
     */
    public String location() {
        return location;
    }

    /**
     * Retrieves the parsed {@link ScriptInformation} of this {@code Script}.
     *
     * @return The {@code information} of this {@code Script}.
     */
    public ScriptInformation information() {
        return information;
    }

}
