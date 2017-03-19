package nl.beehivejava.scriptj.util.fake;

import nl.beehivejava.scriptj.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Lesley
 */
public final class FakeResourceLoader implements ResourceLoader {

    public InputStream inputStreamToReturnOnLoad;


    @Override
    public InputStream load(String location) throws IOException {
        return inputStreamToReturnOnLoad;
    }
}
