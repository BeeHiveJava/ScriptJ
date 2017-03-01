package nl.beehivejava.scriptj.util.fake;

import nl.beehivejava.scriptj.script.Script;
import nl.beehivejava.scriptj.script.ScriptInformation;

/**
 * @author Lesley
 */
public final class FakeScript extends Script {

    public boolean onStartCalled = false;
    public boolean onStopCalled = false;

    public FakeScript(String location, ScriptInformation information) {
        super(location, information);
    }

    @Override
    protected void onStart() {
        onStartCalled = true;
    }

    @Override
    protected void onStop() {
        onStopCalled = true;
    }

}
