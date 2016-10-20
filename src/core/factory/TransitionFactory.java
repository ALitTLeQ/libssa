package core.factory;

import core.lib.Transition;
import core.lib.Entity;
import javafx.scene.Group;

/**
 * @author laki
 */
public interface TransitionFactory {

    public static TransitionFactory factory = new DefaultTransitionFactory();
    public Group createTransition(Entity fromEntity, Entity toEntity, String text, Transition.Line lineType, Transition.Symbol type);
    
}
