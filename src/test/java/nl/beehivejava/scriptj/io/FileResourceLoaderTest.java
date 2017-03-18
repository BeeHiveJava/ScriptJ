package nl.beehivejava.scriptj.io;

import nl.beehivejava.scriptj.util.builder.ObjectBuilders;

/**
 * @author Lesley
 */
public final class FileResourceLoaderTest extends ResourceLoaderTest {

    @Override
    protected ResourceLoader makeDefaultResourceLoader() {
        return ObjectBuilders.fileResourceLoader().build();
    }
}
