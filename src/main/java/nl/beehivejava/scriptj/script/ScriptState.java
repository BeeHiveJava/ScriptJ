package nl.beehivejava.scriptj.script;

/**
 * The {@code ScriptState} of a {@code Script} defines the state of the {@code Script}. Only certain operations can be
 * performed on {@code Scripts} when they are in certain states, for example: a started {@code Script} may not be
 * started again.
 *
 * @author Lesley
 */
public enum ScriptState {

    /**
     * Defines that a {@code Script} was loaded and started successfully.
     */
    STARTED,

    /**
     * Defines that a {@code Script} was loaded, but not started.
     */
    STOPPED

}
