package lib.shared.transition;

import factory.TransitionFactory;
import lib.shared.Entity;

/**
 * @author laki
 */
public class StraightLine extends Transition {

    public StraightLine(Entity entityFrom, Entity entityTo, String text) {
        super(entityFrom, entityTo, text);
        transitionView = TransitionFactory.factory.createTransition(entityFrom, entityTo, text, Line.STRAIGHT, Symbol.NONE);
    }
    
}
