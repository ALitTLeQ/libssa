package lib.uml;

import factory.StateFactory;
import lib.Entity;
import lib.Rounded;

/**
 * @author laki
 */
public class State extends Entity implements Rounded {

    private StateType type;
    
    public enum StateType {
        INITIAL, TRANSITIONAL, FINAL
    }
    
    public State(StateType type, String name) {
        super(type == StateType.TRANSITIONAL ? name : "default");
        this.type = type;
        switch (type) {
            case INITIAL:
                entityGroup = StateFactory.createInitialState();
                break;
            case FINAL:
                entityGroup = StateFactory.createFinalState();
                break;
            case TRANSITIONAL:
                entityGroup = StateFactory.createTransitionalState(name);
        }
    }

    public StateType getType() {
        return type;
    }
    
}
