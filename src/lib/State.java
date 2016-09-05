package lib;

import factory.StateFactory;

/**
 * @author laki
 */
public class State extends Entity {

    private StateType type;
    
    public enum StateType {
        INITIAL, TRANSITIONAL, FINAL
    }
    
    public State(StateType type, String name) {
        super(name);
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
