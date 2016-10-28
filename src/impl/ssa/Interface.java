package impl.ssa;

import core.lib.Entity;

/**
 * @author laki
 */
public class Interface extends Entity {

    public Interface(String name) {
        super(name);
        entityGroup = SsaFactory.createEntity(name, Interface.class);
    }

}
