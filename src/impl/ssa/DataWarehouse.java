package impl.ssa;

import core.lib.Entity;

/**
 * @author laki
 */
public class DataWarehouse extends Entity {

    public DataWarehouse(String name) {
        super(name);
        entityGroup = SsaFactory.createDataWarehouse(name);
    }
    
}
