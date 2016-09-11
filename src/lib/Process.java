package lib;

import factory.SsaFactory;

/**
 * @author laki
 */
public class Process extends Entity implements Rounded {

    public Process(String name) {
        super(name);
        entityGroup = SsaFactory.createProcess(name);
    }

}
