package factory;

import exception.RuleChecker;
import handler.MouseEventHandler;
import java.util.Collection;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import lib.Entity;
import lib.transition.Transition;

/**
 * @author laki
 */
public class DiagramFactory {

    public static void createStage(Stage primaryStage, Collection<Entity> entities, Collection<Transition> transitions) {
        Group root = new Group();

        for (Transition transition : transitions) {
            RuleChecker.checkTransitionRules(transition);
            root.getChildren().add(transition.getTransitionView());
        }
        MouseEventHandler.setTransitions(transitions);

        for (Entity entity : entities) {
            RuleChecker.checkEntityRules(entity);
            entity.getEntityGroup().setOnMousePressed(MouseEventHandler.onMousePressedEventHandler);
            entity.getEntityGroup().setOnMouseDragged(MouseEventHandler.onMouseEntityDraggedEventHandler);
            root.getChildren().add(entity.getEntityGroup());
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setOnScroll(MouseEventHandler.onMouseScrolledEventHandler);
        
        flowPane.getChildren().add(root);
        
        scrollPane.setContent(flowPane);
        
        primaryStage.setScene(new Scene(scrollPane, 600, 600));
    }

}
