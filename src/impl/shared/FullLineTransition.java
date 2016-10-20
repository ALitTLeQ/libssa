package impl.shared;

import core.lib.Transition;
import core.factory.TransitionFactory;
import core.lib.Entity;

/**
 * @author laki
 */
public class FullLineTransition extends Transition {

    public FullLineTransition(Entity entityFrom, Entity entityTo, String name) {
        super(entityFrom, entityTo, name);
        transitionView = TransitionFactory.factory.createTransition(entityFrom, entityTo, name, Line.CURVED, Symbol.ARROW);
    }
    
}
