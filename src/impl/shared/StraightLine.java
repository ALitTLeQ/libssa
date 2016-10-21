package impl.shared;

import core.lib.Transition;
import core.factory.TransitionFactory;
import core.lib.Entity;

/**
 * @author laki
 */
public class StraightLine extends Transition {

    public StraightLine(Entity entityFrom, Entity entityTo, String text) {
        super(entityFrom, entityTo, text);
        transitionView = TransitionFactory.factory.createTransition(entityFrom, entityTo, text, Line.STRAIGHT, Symbol.NONE);
    }
    
}
