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

    public static void createStage(Stage primaryStage, Collection<Group> states, Collection<Transition> transitions) {
        Group root = new Group();

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
