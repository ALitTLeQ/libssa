import java.util.Collection;
import java.util.HashSet;
import javafx.application.Application;
import javafx.stage.Stage;
import factory.DiagramFactory;
import lib.Transition;
import lib.Entity;
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
        Collection<Entity> states = new HashSet<>();
        Collection<Transition> transitions = new HashSet<>();

        // create states
        Entity initialState = new State(State.StateType.INITIAL, null);
        Entity finalState = new State(State.StateType.FINAL, null);
        Entity transState1 = new State(State.StateType.TRANSITIONAL, "t1");
        Entity transState2 = new State(State.StateType.TRANSITIONAL, "t2");
        Entity transState3 = new State(State.StateType.TRANSITIONAL, "t3");
        Entity transState4 = new State(State.StateType.TRANSITIONAL, "t4");

        // add them to collection
        states.add(initialState);
        states.add(finalState);
        states.add(transState1);
        states.add(transState2);
        states.add(transState3);
        states.add(transState4);

        // create transitions and add to collection
        transitions.add(new Transition(initialState, transState1, "tr1"));
        transitions.add(new Transition(transState1, transState2, "tr21"));
        transitions.add(new Transition(transState1, transState2, "tr22"));
        transitions.add(new Transition(transState1, transState2, "tr23"));
        transitions.add(new Transition(transState2, transState3, "tr23"));
        transitions.add(new Transition(transState2, transState3, "tr23"));
        transitions.add(new Transition(transState1, transState3, "tr23"));
        transitions.add(new Transition(transState3, transState4, "tr23"));
        transitions.add(new Transition(transState4, transState2, "tr23"));
        transitions.add(new Transition(transState2, finalState, "tr3"));

        // create stage priview
        DiagramFactory.createStage(primaryStage, states, transitions);
        primaryStage.setTitle("Example");
        primaryStage.show();
    }

}
