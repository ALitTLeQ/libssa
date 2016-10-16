package lib.shared.transition;

import factory.TransitionFactory;
import lib.shared.Entity;

/**
 * @author laki
 */
public class ImplementsTransition extends Transition {

    public ImplementsTransition(Entity entityFrom, Entity entityTo, String name) {
        super(entityFrom, entityTo, name);
        transitionView = TransitionFactory.createTransition(entityFrom, entityTo, name, Line.STRAIGHT, Symbol.TRIANGLE);
    }

}
