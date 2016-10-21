package impl.eer;

import core.lib.Entity;

/**
 * @author laki
 */
public class SubClass extends Entity {

    public SubClass(String name) {
        super(name);
        entityGroup = EerFactory.createSubClass(name);
    }
    
}