import java.util.Collection;
import java.util.HashSet;
import javafx.application.Application;
import javafx.stage.Stage;
import factory.DiagramFactory;
import lib.Transition;
import lib.State;

/**
 * example - how to use
 *
 * @author laki
 */
public class Example extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // create collections
        Collection<State> states = new HashSet<>();
        Collection<Transition> transitions = new HashSet<>();

        // create states
        State initialState = new State(State.Type.INITIAL, null);
        State finalState = new State(State.Type.FINAL, null);
        State transState1 = new State(State.Type.TRANSITIONAL, "t1");
        State transState2 = new State(State.Type.TRANSITIONAL, "t2");

        // add them to collection
        states.add(initialState);
        states.add(finalState);
        states.add(transState1);
        states.add(transState2);

        // create transitions and add to collection
        transitions.add(new Transition(initialState, transState1, "tr1"));
        transitions.add(new Transition(transState1, transState2, "tr21"));
        transitions.add(new Transition(transState1, transState2, "tr22"));
        transitions.add(new Transition(transState1, transState2, "tr23"));
        transitions.add(new Transition(transState2, finalState, "tr3"));

        // create stage priview
        DiagramFactory.createStage(primaryStage, states, transitions);
        primaryStage.show();
    }

}
