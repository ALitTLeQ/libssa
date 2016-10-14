package lib.transition;

import factory.TransitionFactory;
import lib.Entity;

/**
 * @author laki
 */
public class StraightLineTransition extends Transition {

    public StraightLineTransition(Entity entityFrom, Entity entityTo, String name) {
        super(entityFrom, entityTo, name);
        transitionView = TransitionFactory.createStraightLineTransition(entityFrom, entityTo, name);
    }
    
}
