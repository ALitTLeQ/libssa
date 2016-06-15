package lib;

import handler.MouseEventHandler;
import java.util.Collection;
import java.util.HashSet;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author laki
 */
public class DiagramFactory {

    private static final Collection<Group> states = new HashSet<>();
    private static final Collection<Transition> transitions = new HashSet<>();

    public static void createStage(Stage primaryStage, Collection<Group> stateCollection, Collection<Transition> transitionCollection) {
        Group root = new Group();
        states.addAll(stateCollection);
        transitions.addAll(transitionCollection);

        for (Transition transition : transitions) {
            root.getChildren().addAll(transition.getTransitionView());
        }
        MouseEventHandler.transitions = transitions;

        for (Group group : states) {
            group.setOnMousePressed(MouseEventHandler.onMousePressedEventHandler);
            group.setOnMouseDragged(MouseEventHandler.onMouseDraggedEventHandler);
        }
        root.getChildren().addAll(states);

        primaryStage.setScene(new Scene(root, 600, 600));
    }

}
