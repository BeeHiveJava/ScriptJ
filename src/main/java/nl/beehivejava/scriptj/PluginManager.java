package nl.beehivejava.scriptj;

/**
 * @author Lesley
 */
public interface PluginManager {

    Plugin load(String location);

    void addEnvironment(PluginEnvironment environment);

    void removeEnvironment(PluginEnvironment environment);

}
