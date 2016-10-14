package lib.eer;

import factory.EerFactory;
import lib.Entity;
import lib.Rotated;

/**
 * @author laki
 */
public class Relation extends Entity implements Rotated {

    public Relation(String name) {
        super(name);
        entityGroup = EerFactory.createRelation(name);
    }
    
}