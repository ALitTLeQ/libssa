package impl.eer;

import core.lib.Entity;

/**
 * @author laki
 */
public class Aggregation extends Entity {

    public Aggregation(String name) {
        super(name);
        entityGroup = EerFactory.createAggregation(name);
    }
    
}