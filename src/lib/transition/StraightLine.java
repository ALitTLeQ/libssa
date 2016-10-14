package lib.transition;

import factory.TransitionFactory;
import lib.Entity;

/**
 * @author laki
 */
public class StraightLine extends Transition {

    public StraightLine(Entity entityFrom, Entity entityTo, String text) {
        super(entityFrom, entityTo, text);
        transitionView = TransitionFactory.createStraightLine(entityFrom, entityTo, text);
    }
    
}
