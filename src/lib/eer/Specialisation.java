package lib.eer;

import factory.EerFactory;
import lib.shared.Entity;
import lib.shared.Rotated;

/**
 * @author laki
 */
public class Specialisation extends Entity implements Rotated {

    public Specialisation() {
        super("S");
        entityGroup = EerFactory.createSpecialisation();
    }
    
}