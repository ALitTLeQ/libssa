package lib.shared.transition;

import javafx.scene.Group;
import lib.shared.Entity;

/**
 * @author laki
 */
public abstract class Transition {

    protected Group transitionView;
    private final String name;
    private final Entity entityFrom;
    private final Entity entityTo;

    public Transition(Entity entityFrom, Entity entityTo, String name) {
        this.entityFrom = entityFrom;
        this.entityTo = entityTo;
        this.name = name;
        entityFrom.newTransition(this, true);
        entityTo.newTransition(this, false);
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
