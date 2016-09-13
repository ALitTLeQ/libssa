package lib;

import factory.TransitionFactory;
import javafx.scene.Group;

/**
 * @author laki
 */
public class Transition {

    private final String name;
    private final Group transitionView;
    private final Entity entityFrom;
    private final Entity entityTo;

    public Transition(Entity entityFrom, Entity entityTo, String name) {
        this.entityFrom = entityFrom;
        this.entityTo = entityTo;
        this.name = name;
        entityFrom.newTransition(this, true);
        entityTo.newTransition(this, false);
        transitionView = TransitionFactory.createTransition(entityFrom, entityTo, name);
    }

    public Entity getEntityFrom() {
        return entityFrom;
    }

    public Entity getEntityTo() {
        return entityTo;
    }

    public String getName() {
        return name;
    }

    public Group getTransitionView() {
        return transitionView;
    }

}
