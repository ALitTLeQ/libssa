package lib;

import factory.SsaFactory;

/**
 * @author laki
 */
public class Interface extends Entity {

    public Interface(String name) {
        super(name);
        entityGroup = SsaFactory.createInterface(name);
    }

}
