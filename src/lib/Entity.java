package lib;

import lib.transition.Transition;
import java.util.Collection;
import java.util.HashSet;
import javafx.scene.Group;

/**
 * @author laki
 */
public abstract class Entity {

    protected Group entityGroup;
    private final String name;
    private final Collection<Transition> transitionsTo;
    private final Collection<Transition> transitionsFrom;

    public Entity(String name) {
        this.name = name;
        transitionsTo = new HashSet<>();
        transitionsFrom = new HashSet<>();
    }

    public Group getEntityGroup() {
        return entityGroup;
    }

    public String getName() {
        return name;
    }

    public Collection<Transition> getTransitionsFrom() {
        return transitionsFrom;
    }

    public Collection<Transition> getTransitionsTo() {
        return transitionsTo;
    }

    public void newTransition(Transition t, boolean from) {
        if (from) {
            transitionsFrom.add(t);
        } else {
            transitionsTo.add(t);
        }
    }

}
