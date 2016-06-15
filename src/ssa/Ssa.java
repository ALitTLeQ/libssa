package ssa;

import java.util.Collection;
import java.util.HashSet;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;
import lib.DiagramFactory;
import lib.StateFactory;
import lib.Transition;
import lib.TransitionFactory;

/**
 * example - how to use
 *
 * @author laki
 */
public class Ssa extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // create empty collections
        Collection<Group> states = new HashSet<>();
        Collection<Transition> transitions = new HashSet<>();

        // create states
        Group initialState = StateFactory.createInitialState();
        Group finalState = StateFactory.createFinalState();
        Group transState1 = StateFactory.createTransitionalState("t1");
        Group transState2 = StateFactory.createTransitionalState("t2");

        // add them to collection
        states.add(initialState);
        states.add(finalState);
        states.add(transState1);
        states.add(transState2);

        // create transitions and add to collection
        transitions.add(TransitionFactory.createTransition(initialState, transState1, "tr1"));
        transitions.add(TransitionFactory.createTransition(transState1, transState2, "tr2"));
        transitions.add(TransitionFactory.createTransition(transState2, finalState, "tr3"));

        // create stage priview
        DiagramFactory.createStage(primaryStage, states, transitions);
        primaryStage.show();
    }

}
