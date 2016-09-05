package lib;

import java.util.Collection;
import java.util.HashSet;
import javafx.scene.Group;

/**
 * @author laki
 */
public abstract class Entity {

    String name;
    Group entityGroup;
    Collection<Transition> transitionsTo;
    Collection<Transition> transitionsFrom;

    public Entity(String name) {
        this.name = name;
        transitionsTo = new HashSet<>();
        transitionsFrom = new HashSet<>();
    }

    public Group getEntityGroup() {
        return entityGroup;
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
