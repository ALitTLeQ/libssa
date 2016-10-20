package impl.shared;

import core.lib.Transition;
import core.factory.TransitionFactory;
import core.lib.Entity;

/**
 * @author laki
 */
public class StraightLineTransition extends Transition {

    public StraightLineTransition(Entity entityFrom, Entity entityTo, String name) {
        super(entityFrom, entityTo, name);
        transitionView = TransitionFactory.factory.createTransition(entityFrom, entityTo, name, Line.STRAIGHT, Symbol.ARROW);
    }
    
}
