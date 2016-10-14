package lib.eer;

import factory.EerFactory;
import lib.Entity;

/**
 * @author laki
 */
public class SubClass extends Entity {

    public SubClass(String name) {
        super(name);
        entityGroup = EerFactory.createSubClass(name);
    }
    
}