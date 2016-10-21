package impl.shared;

import core.lib.Transition;
import core.factory.TransitionFactory;
import core.lib.Entity;

/**
 * @author laki
 */
public class CreatesTransition extends Transition {

    public CreatesTransition(Entity entityFrom, Entity entityTo, String name) {
        super(entityFrom, entityTo, name);
        transitionView = TransitionFactory.factory.createTransition(entityFrom, entityTo, name, Line.STRAIGHT_INTERRUPTED, Symbol.ARROW);
    }

}