package lib.ssa;

import factory.SsaFactory;
import lib.Entity;
import lib.Rounded;

/**
 * @author laki
 */
public class Process extends Entity implements Rounded {

    public Process(String name) {
        super(name);
        entityGroup = SsaFactory.createProcess(name);
    }

}
