package nl.beehivejava.scriptj.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Lesley
 */
public abstract class ResourceLoaderTest {

    @Rule
    public final ExpectedException internalExpectedException = ExpectedException.none();

    @Test
    public final void load_nullLocation_throwsException() throws Exception {
        internalExpectedException.expect(NullPointerException.class);

        ResourceLoader loader = makeDefaultResourceLoader();

        loader.load(null);
    }

    protected abstract ResourceLoader makeDefaultResourceLoader();

}
