package factory;

import handler.MouseEventHandler;
import java.util.Collection;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lib.State;
import lib.Transition;

/**
 * @author laki
 */
public class DiagramFactory {

    public static void createStage(Stage primaryStage, Collection<State> states, Collection<Transition> transitions) {
        Group root = new Group();

        for (Transition transition : transitions) {
            root.getChildren().add(transition.getTransitionView());
        }
        MouseEventHandler.transitions = transitions;

        for (State state : states) {
            state.getStateGroup().setOnMousePressed(MouseEventHandler.onMousePressedEventHandler);
            state.getStateGroup().setOnMouseDragged(MouseEventHandler.onMouseDraggedEventHandler);
            root.getChildren().add(state.getStateGroup());
        }

        primaryStage.setScene(new Scene(root, 600, 600));
    }

}
