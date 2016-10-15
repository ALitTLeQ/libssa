package lib.uml.class_diagram;

import factory.UmlClassFactory;
import lib.shared.Entity;
import lib.shared.transition.Transition;

/**
 * @author laki
 */
public class Connection extends Transition {

    public Connection(Entity entityFrom, Entity entityTo, String cardinalityFrom, String cardinalityTo, UmlClassFactory.ConnectionType type) {
        super(entityFrom, entityTo, null);
        transitionView = UmlClassFactory.createConnection(entityFrom, entityTo, cardinalityFrom, cardinalityTo, type);
    }
    
}
