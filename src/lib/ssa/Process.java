package lib.ssa;

import factory.SsaFactory;
import lib.shared.Entity;
import lib.shared.Rounded;

/**
 * @author laki
 */
public class Process extends Entity implements Rounded {

    public Process(String name) {
        super(name);
        entityGroup = SsaFactory.createProcess(name);
    }

}
