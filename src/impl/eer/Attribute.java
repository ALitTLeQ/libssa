package impl.eer;

import core.lib.Entity;
import core.lib.Rounded;

/**
 * @author laki
 */
public class Attribute extends Entity implements Rounded {
    
    public Attribute(String name) {
        super(name);
        entityGroup = EerFactory.createAttribute(name);
    }
    
}