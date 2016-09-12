package lib;

import factory.SsaFactory;

/**
 * @author laki
 */
public class DataWarehouse extends Entity {

    public DataWarehouse(String name) {
        super(name);
        entityGroup = SsaFactory.createDataWarehouse(name);
    }
    
}
