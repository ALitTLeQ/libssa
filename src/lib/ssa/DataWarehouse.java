package lib.ssa;

import factory.SsaFactory;
import lib.Entity;

/**
 * @author laki
 */
public class DataWarehouse extends Entity {

    public DataWarehouse(String name) {
        super(name);
        entityGroup = SsaFactory.createDataWarehouse(name);
    }
    
}
