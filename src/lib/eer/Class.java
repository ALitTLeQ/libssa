package lib.eer;

import factory.EerFactory;
import lib.Entity;

/**
 * @author laki
 */
public class Class extends Entity {

    public Class(String name) {
        super(name);
        entityGroup = EerFactory.createClass(name);
    }
    
}
