package nl.beehivejava.scriptj.util.builder;

import nl.beehivejava.scriptj.io.FileResourceLoader;

/**
 * @author Lesley
 */
public final class FileResourceLoaderBuilder {

    FileResourceLoaderBuilder() {

    }

    public FileResourceLoader build() {
        return new FileResourceLoader();
    }

}
