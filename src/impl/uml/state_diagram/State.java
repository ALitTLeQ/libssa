package impl.uml.state_diagram;

import core.lib.Entity;
import core.lib.Rounded;

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
                entityGroup = UmlStateFactory.createInitialState();
                break;
            case FINAL:
                entityGroup = UmlStateFactory.createFinalState();
                break;
            case TRANSITIONAL:
                entityGroup = UmlStateFactory.createTransitionalState(name);
        }
    }

    public StateType getType() {
        return type;
    }
    
}
