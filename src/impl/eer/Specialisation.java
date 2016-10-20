package impl.eer;

import core.lib.Entity;
import core.lib.Rotated;

/**
 * @author laki
 */
public class Specialisation extends Entity implements Rotated {

    public Specialisation() {
        super("S");
        entityGroup = EerFactory.createSpecialisation();
    }
    
}