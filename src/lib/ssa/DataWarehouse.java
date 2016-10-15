package lib.ssa;

import factory.SsaFactory;
import lib.shared.Entity;

/**
 * @author laki
 */
public class DataWarehouse extends Entity {

    public DataWarehouse(String name) {
        super(name);
        entityGroup = SsaFactory.createDataWarehouse(name);
    }
    
}
