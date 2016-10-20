package impl.shared;

import core.lib.Transition;
import core.factory.TransitionFactory;
import core.lib.Entity;

/**
 * @author laki
 */
public class ImplementsTransition extends Transition {

    public ImplementsTransition(Entity entityFrom, Entity entityTo, String name) {
        super(entityFrom, entityTo, name);
        transitionView = TransitionFactory.factory.createTransition(entityFrom, entityTo, name, Line.STRAIGHT, Symbol.TRIANGLE);
    }

}
