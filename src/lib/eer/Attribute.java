package lib.eer;

import factory.EerFactory;
import lib.shared.Entity;
import lib.shared.Rounded;

/**
 * @author laki
 */
public class Attribute extends Entity implements Rounded {
    
    public Attribute(String name) {
        super(name);
        entityGroup = EerFactory.createAttribute(name);
    }
    
}