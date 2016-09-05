package lib;

import factory.TransitionFactory;
import javafx.scene.Group;

/**
 * @author laki
 */
public class Transition {

    private final Group transitionView;
    private final Entity entityFrom;
    private final Entity entityTo;

    public Transition(Entity entityFrom, Entity entityTo, String name) {
        this.entityFrom = entityFrom;
        this.entityTo = entityTo;
        entityFrom.newTransition(this, true);
        entityTo.newTransition(this, false);
        transitionView = TransitionFactory.createTransition(entityFrom, entityTo, name);
    }

    public Group getEntityFromGroup() {
        return entityFrom.getEntityGroup();
    }

    public Group getEntityToGroup() {
        return entityTo.getEntityGroup();
    }

    public Group getTransitionView() {
        return transitionView;
    }

}
