package handler;

import java.util.Collection;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import lib.Transition;

/**
 * class handles state dragging, and moves transitions which from/to state moved
 * 
 * @author laki
 */
public class MouseEventHandler {

    public static Collection<Transition> transitions;
    static double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY;

    public static EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Group) (t.getSource())).getTranslateX();
            orgTranslateY = ((Group) (t.getSource())).getTranslateY();
        }
    };

    public static EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            Group draggedState = (Group) (t.getSource());
            draggedState.setTranslateX(newTranslateX);
            draggedState.setTranslateY(newTranslateY);

            for (Transition transition : transitions) {
                if (draggedState.equals(transition.getStateFrom())) {
                    for (Node node : transition.getTransitionView().getChildren()) {
                        if (node instanceof Line) {
                            Line line = (Line) node;
                            line.setStartX(newTranslateX);
                            line.setStartY(newTranslateY);
                        }
                        if (node instanceof Text) {
                            Text lineText = (Text) node;
                            lineText.setTranslateX((newTranslateX + transition.getStateTo().getTranslateX()) / 2);
                            lineText.setTranslateY((newTranslateY + transition.getStateTo().getTranslateY()) / 2);
                        }
                    }
                }
                if (draggedState.equals(transition.getStateTo())) {
                    for (Node node : transition.getTransitionView().getChildren()) {
                        if (node instanceof Line) {
                            Line line = (Line) node;
                            line.setEndX(newTranslateX);
                            line.setEndY(newTranslateY);
                        } 
                        if (node instanceof Text) {
                            Text lineText = (Text) node;
                            lineText.setTranslateX((newTranslateX + transition.getStateFrom().getTranslateX()) / 2);
                            lineText.setTranslateY((newTranslateY + transition.getStateFrom().getTranslateY()) / 2);
                        }
                    }
                }
            }
        }
    };

}
