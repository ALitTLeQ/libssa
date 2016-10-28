package core.factory;

import core.util.RuleChecker;
import core.handler.MouseEventHandler;
import core.handler.DefaultMouseEventHandler;
import core.lib.Transition;
import core.lib.Entity;
import core.util.ExportManager;
import java.util.Collection;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * @author laki
 */
public class DiagramFactory {

    public static ExportManager exportManager = null;
    public static RuleChecker checker = null;
    public static MouseEventHandler handler = DefaultMouseEventHandler.instance();
    
    public static void createStage(Stage primaryStage, Collection<Entity> entities, Collection<Transition> transitions) {
        Group root = new Group();

        // add transitions
        for (Transition transition : transitions) {
            if(checker != null) checker.checkTransitionRules(transition);
            root.getChildren().add(transition.getTransitionView());
        }
        handler.setTransitions(transitions);

        // add entities
        for (Entity entity : entities) {
            if(checker != null) checker.checkEntityRules(entity);
            entity.getEntityGroup().setOnMousePressed(handler.getOnMousePressedEventHandler());
            entity.getEntityGroup().setOnMouseDragged(handler.getOnEntityDraggedEventHandler());
            root.getChildren().add(entity.getEntityGroup());
        }

        Scene scene = new Scene(createScrollPane(root), 600, 600);
        if (exportManager != null) {
            root.getChildren().addAll(exportManager.getExportButtons(scene, entities, transitions));
        }
        primaryStage.setScene(scene);
    }

    private static ScrollPane createScrollPane(Group root) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setOnScroll(handler.getOnMouseScrolledEventHandler());

        flowPane.getChildren().add(root);

        scrollPane.setContent(flowPane);
        return scrollPane;
    }

}
