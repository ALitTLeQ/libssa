package impl.eer;

import core.lib.Entity;
import core.lib.Rotated;

/**
 * @author laki
 */
public class Relation extends Entity implements Rotated {

    public Relation(String name) {
        super(name);
        entityGroup = EerFactory.createRelation(name);
    }
    
}