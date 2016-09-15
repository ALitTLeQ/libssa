package lib.ssa;

import lib.Rounded;
import factory.SsaFactory;
import lib.Entity;

/**
 * @author laki
 */
public class Process extends Entity implements Rounded {

    public Process(String name) {
        super(name);
        entityGroup = SsaFactory.createProcess(name);
    }

}
