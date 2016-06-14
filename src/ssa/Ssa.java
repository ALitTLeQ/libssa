package ssa;

import java.util.Collection;
import java.util.HashSet;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lib.StateFactory;
import handler.MouseEventHandler;
import lib.Transition;
import lib.TransitionFactory;

/**
 *
 * @author laki
 */
public class Ssa extends Application {

    Collection<Group> states = new HashSet<>();
    Collection<Transition> transitions = new HashSet<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        Group initialState = StateFactory.createInitialState();
        states.add(initialState);
        Group finalState = StateFactory.createFinalState();
        states.add(finalState);
        Group transState1 = StateFactory.createTransitionalState("t1", calcX(), calcY());
        states.add(transState1);
        Group transState2 = StateFactory.createTransitionalState("t2", calcX(), calcY());
        states.add(transState2);
        
        for (Group group : states) {
            group.setOnMousePressed(MouseEventHandler.onMousePressedEventHandler);
            group.setOnMouseDragged(MouseEventHandler.onMouseDraggedEventHandler);
        }
        root.getChildren().addAll(states);

        transitions.add(TransitionFactory.createTransition(initialState, transState1, "tr1"));
        transitions.add(TransitionFactory.createTransition(transState1, transState2, "tr2"));
        transitions.add(TransitionFactory.createTransition(transState2, finalState, "tr3"));
        for (Transition transition : transitions) {
            root.getChildren().addAll(transition.getTransitionView());
        }
        MouseEventHandler.transitions = transitions;
                
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    // izracunaj x koordinatu novog state-a - srediti ovaj algoritam !!!
    private int calcX() {
        return states.size() * 100;
    }

    private int calcY() {
        return states.size() * 100;
    }

}
