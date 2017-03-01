package nl.beehivejava.scriptj.util.builder;

/**
 * @author Lesley
 */
public final class ObjectBuilders {

    private ObjectBuilders() {
        throw new UnsupportedOperationException("Should not be instantiated.");
    }

    public static PluginInformationBuilder pluginInformation() {
        return new PluginInformationBuilder();
    }

    public static ScriptInformationBuilder scriptInformation() {
        return new ScriptInformationBuilder();
    }

}
