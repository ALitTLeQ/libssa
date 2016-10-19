package lib.shared.transition;

import factory.TransitionFactory;
import lib.shared.Entity;

/**
 * @author laki
 */
public class CreatesTransition extends Transition {

    public CreatesTransition(Entity entityFrom, Entity entityTo, String name) {
        super(entityFrom, entityTo, name);
        transitionView = TransitionFactory.factory.createTransition(entityFrom, entityTo, name, Line.STRAIGHT_INTERRUPTED, Symbol.ARROW);
    }

}