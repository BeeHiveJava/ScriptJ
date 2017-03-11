package nl.beehivejava.scriptj;

import nl.beehivejava.scriptj.script.ScriptType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Lesley
 */
public final class DefaultPluginManager implements PluginManager {

    private final Map<ScriptType, PluginEnvironment> environments = new HashMap<>();

    @Override
    public Plugin load(String location) {
        Objects.requireNonNull(location);

        return null;
    }

    @Override
    public void addEnvironment(PluginEnvironment environment) {
        Objects.requireNonNull(environment);

        ScriptType type = environment.type();
        if (environments.containsKey(type)) {
            throw new IllegalStateException("This PluginManager already has an environment for the given ScriptType.");
        }

        environments.put(type, environment);
    }

    @Override
    public void removeEnvironment(PluginEnvironment environment) {
        Objects.requireNonNull(environment);

        environments.remove(environment.type(), environment);
    }
}
