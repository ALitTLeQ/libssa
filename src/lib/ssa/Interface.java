package lib.ssa;

import factory.SsaFactory;
import lib.Entity;

/**
 * @author laki
 */
public class Interface extends Entity {

    public Interface(String name) {
        super(name);
        entityGroup = SsaFactory.createInterface(name);
    }

}
