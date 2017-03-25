package nl.beehivejava.scriptj.util.builder;

/**
 * @author Lesley
 */
public final class ObjectBuilders {

    private ObjectBuilders() {
        throw new UnsupportedOperationException("Should not be instantiated.");
    }

    public static DefaultPluginLoaderBuilder defaultPluginLoader() {
        return new DefaultPluginLoaderBuilder();
    }


    public static PluginBuilder plugin() {
        return new PluginBuilder();
    }

    public static ScriptBuilder script() {
        return new ScriptBuilder();
    }

    public static PluginInformationBuilder pluginInformation() {
        return new PluginInformationBuilder();
    }

    public static JsonPluginInformationParserBuilder jsonPluginInformationParser() {
        return new JsonPluginInformationParserBuilder();
    }

    public static ScriptInformationBuilder scriptInformation() {
        return new ScriptInformationBuilder();
    }

    public static FileResourceLoaderBuilder fileResourceLoader() {
        return new FileResourceLoaderBuilder();
    }

}
