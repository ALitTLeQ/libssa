package lib;

import factory.TransitionFactory;
import javafx.scene.Group;

/**
 * @author laki
 */
public class Transition {

    private final Group transitionView;
    private final State stateFrom;
    private final State stateTo;

    public Transition(State stateFrom, State stateTo, String name) {
        this.stateFrom = stateFrom;
        this.stateTo = stateTo;
        transitionView = TransitionFactory.createTransition(stateFrom, stateTo, name);
    }

    public Group getStateFromGroup() {
        return stateFrom.getStateGroup();
    }

    public Group getStateToGroup() {
        return stateTo.getStateGroup();
    }

    public Group getTransitionView() {
        return transitionView;
    }

}
