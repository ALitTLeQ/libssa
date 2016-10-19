package lib.shared.transition;

import factory.TransitionFactory;
import lib.shared.Entity;

/**
 * @author laki
 */
public class FullLineTransition extends Transition {

    public FullLineTransition(Entity entityFrom, Entity entityTo, String name) {
        super(entityFrom, entityTo, name);
        transitionView = TransitionFactory.factory.createTransition(entityFrom, entityTo, name, Line.CURVED, Symbol.ARROW);
    }
    
}
