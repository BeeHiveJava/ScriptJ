package nl.beehivejava.scriptj;

/**
 * @author Lesley
 */
public final class DefaultPluginManagerTest extends PluginManagerTest {

    @Override
    protected PluginManager createPluginManager() {
        return new DefaultPluginManager();
    }
}
