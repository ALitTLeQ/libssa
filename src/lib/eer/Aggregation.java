package lib.eer;

import factory.EerFactory;
import lib.Entity;

/**
 * @author laki
 */
public class Aggregation extends Entity {

    public Aggregation(String name) {
        super(name);
        entityGroup = EerFactory.createAggregation(name);
    }
    
}