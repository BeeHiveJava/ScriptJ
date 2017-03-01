package nl.beehivejava.scriptj;

/**
 * The {@code PluginState} of a {@link Plugin} defines the state of the {@code Plugin}. Only certain operations can be
 * performed on {@code Plugins} when they are in certain states, for example: a started {@code Plugin} may not be
 * started again.
 *
 * @author Lesley
 */
public enum PluginState {

    /**
     * Defines that a {@link Plugin} was loaded and started successfully.
     */
    STARTED,

    /**
     * Defines that a {@link Plugin} was loaded, but not started.
     */
    STOPPED

}
