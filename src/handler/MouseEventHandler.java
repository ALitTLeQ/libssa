package handler;

import java.util.Collection;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;
import lib.Arrow;
import lib.Transition;

/**
 * class handles entity and transition dragging
 *
 * @author laki
 */
public class MouseEventHandler {

    enum Dragged { ENTITY_FROM, ENTITY_TO, TRANSITION }
    
    private static Collection<Transition> transitions;
    static double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY; // for entity drag control
    static double csX, csY, ccX, ccY, ceX, ceY; // curve start, control and end coordinates

    public static void setTransitions(Collection<Transition> transitions) {
        for (Transition transition : transitions) {
            transition.getTransitionView().setOnMouseDragged(onMouseTransitionDraggedEventHandler);
        }
        MouseEventHandler.transitions = transitions;
    }

    public static EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Group) (t.getSource())).getTranslateX();
            orgTranslateY = ((Group) (t.getSource())).getTranslateY();
        }
    };

    public static EventHandler<MouseEvent> onMouseEntityDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            Group draggedEntity = (Group) (t.getSource());
            draggedEntity.setTranslateX(newTranslateX);
            draggedEntity.setTranslateY(newTranslateY);

            for (Transition transition : transitions) {
                if (draggedEntity.equals(transition.getEntityFromGroup())) {
                    for (Node node : transition.getTransitionView().getChildren()) {
                        moveNode(node, newTranslateX, newTranslateY, Dragged.ENTITY_FROM);
                    }
                }
                if (draggedEntity.equals(transition.getEntityToGroup())) {
                    for (Node node : transition.getTransitionView().getChildren()) {
                        moveNode(node, newTranslateX, newTranslateY, Dragged.ENTITY_TO);
                    }
                }
            }
        }
    };

    public static EventHandler<MouseEvent> onMouseTransitionDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            Group draggedTransitionGroup = (Group) (t.getSource());

            for (Node node : draggedTransitionGroup.getChildren()) {
                moveNode(node, newTranslateX, newTranslateY, Dragged.TRANSITION);
            }
        }
    };

    private static void moveNode(Node node, double newTranslateX, double newTranslateY, Dragged dragged) {
        if (node instanceof QuadCurve) {
            QuadCurve curve = (QuadCurve) node;
            switch (dragged) {
                case ENTITY_FROM:
                    curve.setStartX(newTranslateX);
                    curve.setStartY(newTranslateY);
                    break;
                case ENTITY_TO:
                    curve.setEndX(newTranslateX);
                    curve.setEndY(newTranslateY);
                    break;
                case TRANSITION:
                    curve.setControlX(newTranslateX);
                    curve.setControlY(newTranslateY);
            }
            csX = curve.getStartX();
            csY = curve.getStartY();
            ceX = curve.getEndX();
            ceY = curve.getEndY();
            ccX = curve.getControlX();
            ccY = curve.getControlY();
        }
        if (node instanceof Text) {
            Text lineText = (Text) node;
            lineText.setTranslateX((ccX + (csX + ceX) / 2) / 2);
            lineText.setTranslateY((ccY + (csY + ceY) / 2) / 2);
        }
        if (node instanceof Arrow) {
            Arrow arrow = (Arrow) node;
            arrow.setTranslateX((ccX + (csX + ceX) / 2) / 2);
            arrow.setTranslateY((ccY + (csY + ceY) / 2) / 2);
            arrow.setAngle(ceX - csX, ceY - csY);
        }
    }

}
