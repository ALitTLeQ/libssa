package factory;

import javafx.scene.Group;
import lib.shared.Entity;
import lib.shared.transition.Transition;

/**
 * @author laki
 */
public interface TransitionFactory {

    public static TransitionFactory factory = new DefaultTransitionFactory();
    public Group createTransition(Entity fromEntity, Entity toEntity, String text, Transition.Line lineType, Transition.Symbol type);
    
}
