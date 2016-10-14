package lib.eer;

import factory.EerFactory;
import lib.Entity;
import lib.Rotated;

/**
 * @author laki
 */
public class Specialisation extends Entity implements Rotated {

    public Specialisation() {
        super("S");
        entityGroup = EerFactory.createSpecialisation();
    }
    
}