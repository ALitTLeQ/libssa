package impl.ssa;

import core.lib.Entity;
import core.lib.Rounded;

/**
 * @author laki
 */
public class Process extends Entity implements Rounded {

    public Process(String name) {
        super(name);
        entityGroup = SsaFactory.createProcess(name);
    }

}
