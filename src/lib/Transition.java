package lib;

import javafx.scene.Group;

/**
 * @author laki
 */
public class Transition {

    private final Group transitionView;
    private final Group stateFrom;
    private final Group stateTo;

    public Transition(Group transitionView, Group stateFrom, Group stateTo) {
        this.transitionView = transitionView;
        this.stateFrom = stateFrom;
        this.stateTo = stateTo;
    }

    public Group getStateFrom() {
        return stateFrom;
    }

    public Group getStateTo() {
        return stateTo;
    }

    public Group getTransitionView() {
        return transitionView;
    }

}
