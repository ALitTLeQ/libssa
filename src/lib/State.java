package lib;

import factory.StateFactory;
import javafx.scene.Group;

/**
 * @author laki
 */
public class State {

    private Group stateGroup;

    public enum Type {
        INITIAL, TRANSITIONAL, FINAL
    }

    public State(Type type, String name) {
        switch (type) {
            case INITIAL:
                stateGroup = StateFactory.createInitialState();
                break;
            case FINAL:
                stateGroup = StateFactory.createFinalState();
                break;
            case TRANSITIONAL:
                stateGroup = StateFactory.createTransitionalState(name);
        }
    }

    public Group getStateGroup() {
        return stateGroup;
    }
    
}
