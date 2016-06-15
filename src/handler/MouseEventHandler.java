package handler;

import java.util.Collection;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;
import lib.Transition;

/**
 * class handles state and transition dragging
 *
 * @author laki
 */
public class MouseEventHandler {

    private static final int DRAGGED_STATE_FROM = 1;
    private static final int DRAGGED_STATE_TO = 2;
    private static final int DRAGGED_TRANSITION = 3;

    private static Collection<Transition> transitions;
    static double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY; // for state drag control
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

    public static EventHandler<MouseEvent> onMouseStateDraggedEventHandler = new EventHandler<MouseEvent>() {
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
                if (draggedState.equals(transition.getStateFromGroup())) {
                    for (Node node : transition.getTransitionView().getChildren()) {
                        moveNode(node, newTranslateX, newTranslateY, DRAGGED_STATE_FROM);
                    }
                }
                if (draggedState.equals(transition.getStateToGroup())) {
                    for (Node node : transition.getTransitionView().getChildren()) {
                        moveNode(node, newTranslateX, newTranslateY, DRAGGED_STATE_TO);
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
                moveNode(node, newTranslateX, newTranslateY, DRAGGED_TRANSITION);
            }
        }
    };

    private static void moveNode(Node node, double newTranslateX, double newTranslateY, int action) {
        if (node instanceof QuadCurve) {
            QuadCurve curve = (QuadCurve) node;
            switch (action) {
                case DRAGGED_STATE_FROM:
                    curve.setStartX(newTranslateX);
                    curve.setStartY(newTranslateY);
                    break;
                case DRAGGED_STATE_TO:
                    curve.setEndX(newTranslateX);
                    curve.setEndY(newTranslateY);
                    break;
                case DRAGGED_TRANSITION:
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
    }

}
