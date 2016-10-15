package lib.eer;

import factory.EerFactory;
import lib.shared.Entity;
import lib.shared.Rotated;

/**
 * @author laki
 */
public class Relation extends Entity implements Rotated {

    public Relation(String name) {
        super(name);
        entityGroup = EerFactory.createRelation(name);
    }
    
}