package nl.beehivejava.scriptj.util.fake;

import nl.beehivejava.scriptj.loader.ScriptLoader;
import nl.beehivejava.scriptj.script.Script;
import nl.beehivejava.scriptj.script.ScriptType;

import java.io.IOException;

/**
 * @author Lesley
 */
public final class FakeScriptLoader implements ScriptLoader {

    public Script scriptToLoad;
    public ScriptType compatibleScriptType;

    @Override
    public Script load(String location) throws IOException {
        return scriptToLoad;
    }

    @Override
    public ScriptType compatibleScriptType() {
        return compatibleScriptType;
    }
}
