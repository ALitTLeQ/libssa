package lib.transition;

import factory.TransitionFactory;
import lib.Entity;

/**
 * @author laki
 */
public class FullLineTransition extends Transition {

    public FullLineTransition(Entity entityFrom, Entity entityTo, String name) {
        super(entityFrom, entityTo, name);
        transitionView = TransitionFactory.createTransition(entityFrom, entityTo, name);
    }
    
}
