package examples;

import java.util.Collection;
import java.util.HashSet;
import javafx.application.Application;
import javafx.stage.Stage;
import factory.DiagramFactory;
import lib.shared.Entity;
import lib.shared.transition.Transition;
import lib.shared.transition.FullLineTransition;
import lib.uml.state_diagram.State;

/**
 * example - how to use
 *
 * @author laki
 */
public class ExampleUmlStateDiagram extends Application {

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
        Entity transState1 = new State(State.StateType.TRANSITIONAL, "t1");
        Entity transState2 = new State(State.StateType.TRANSITIONAL, "t2");
        Entity transState3 = new State(State.StateType.TRANSITIONAL, "t3");
        Entity transState4 = new State(State.StateType.TRANSITIONAL, "t4");
        Entity finalState = new State(State.StateType.FINAL, null);

        // add them to collection
        states.add(initialState);
        states.add(finalState);
        states.add(transState1);
        states.add(transState2);
        states.add(transState3);
        states.add(transState4);

        // create transitions and add to collection
        transitions.add(new FullLineTransition(initialState, transState1, "tr1"));
        transitions.add(new FullLineTransition(transState1, transState2, "tr21"));
        transitions.add(new FullLineTransition(transState1, transState2, "tr22"));
        transitions.add(new FullLineTransition(transState1, transState2, "tr23"));
        transitions.add(new FullLineTransition(transState2, transState3, "tr23"));
        transitions.add(new FullLineTransition(transState2, transState3, "tr23"));
        transitions.add(new FullLineTransition(transState1, transState3, "tr23"));
        transitions.add(new FullLineTransition(transState3, transState4, "tr23"));
        transitions.add(new FullLineTransition(transState4, transState2, "tr23"));
        transitions.add(new FullLineTransition(transState2, finalState, "tr3"));

        // create stage priview
        DiagramFactory.SHOW_EXPORT_BUTTONS = true;
        DiagramFactory.createStage(primaryStage, states, transitions);
        primaryStage.setTitle("Example");
        primaryStage.show();
    }

}
