package handler;

import java.util.Collection;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;
import lib.shared.Rotated;
import lib.shared.Rounded;
import lib.shared.transition.Transition;
import lib.shared.transition.symbol.Symbol;

/**
 * class handles entity and transition dragging
 *
 * @author laki
 */
public class DefaultMouseEventHandler implements MouseEventHandler {

    private DefaultMouseEventHandler() {
    }

    private static MouseEventHandler INSTANCE;
    public static MouseEventHandler instance() {
        if (INSTANCE == null) {
            INSTANCE = new DefaultMouseEventHandler();
        }
        return INSTANCE;
    }

    enum Dragged { ENTITY_FROM, ENTITY_TO, TRANSITION }

    private Collection<Transition> transitions;
    private double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY; // for entity drag control
    private double csX, csY, ccX, ccY, ceX, ceY; // curve start, control and end coordinates

    @Override
    public void setTransitions(Collection<Transition> transitions) {
        for (Transition transition : transitions) {
            transition.getTransitionView().setOnMouseDragged(getOnTransitionDraggedEventHandler());
        }
        this.transitions = transitions;
    }

    @Override
    public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
                orgTranslateX = ((Group) (t.getSource())).getTranslateX();
                orgTranslateY = ((Group) (t.getSource())).getTranslateY();
            }
        };
    }

    @Override
    public EventHandler<MouseEvent> getOnEntityDraggedEventHandler() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;

                Group draggedEntity = (Group) (t.getSource());
                draggedEntity.setTranslateX(newTranslateX);
                draggedEntity.setTranslateY(newTranslateY);

                int translateX = 0;
                int translateY = 0;

                for (Transition transition : transitions) {
                    if (draggedEntity.equals(transition.getEntityFrom().getEntityGroup())) {
                        translateX = calculateTranslateX(transition, true);
                        translateY = calculateTranslateY(transition, true);
                        for (Node node : transition.getTransitionView().getChildren()) {
                            moveNode(node, newTranslateX + translateX, newTranslateY + translateY, Dragged.ENTITY_FROM);
                        }
                    }
                    if (draggedEntity.equals(transition.getEntityTo().getEntityGroup())) {
                        translateX = calculateTranslateX(transition, false);
                        translateY = calculateTranslateY(transition, false);
                        for (Node node : transition.getTransitionView().getChildren()) {
                            moveNode(node, newTranslateX + translateX, newTranslateY + translateY, Dragged.ENTITY_TO);
                        }
                    }
                }
            }

            private int calculateTranslateX(Transition transition, boolean draggedEntityFrom) {
                if (draggedEntityFrom) {
                    if (transition.getEntityFrom() instanceof Rounded) {
                        return 0;
                    }
                    if (transition.getEntityFrom() instanceof Rotated) {
                        return 30;
                    }
                    return (int) transition.getEntityFrom().getEntityGroup().getLayoutBounds().getWidth() / 2;
                } else {
                    if (transition.getEntityTo() instanceof Rounded) {
                        return 0;
                    }
                    if (transition.getEntityTo() instanceof Rotated) {
                        return 30;
                    }
                    return (int) transition.getEntityTo().getEntityGroup().getLayoutBounds().getWidth() / 2;
                }
            }

            private int calculateTranslateY(Transition transition, boolean draggedEntityFrom) {
                if (draggedEntityFrom) {
                    if (transition.getEntityFrom() instanceof Rounded) {
                        return 0;
                    }
                    if (transition.getEntityFrom() instanceof Rotated) {
                        return 30;
                    }
                    return (int) transition.getEntityFrom().getEntityGroup().getLayoutBounds().getHeight() / 2;
                } else {
                    if (transition.getEntityTo() instanceof Rounded) {
                        return 0;
                    }
                    if (transition.getEntityTo() instanceof Rotated) {
                        return 30;
                    }
                    return (int) transition.getEntityTo().getEntityGroup().getLayoutBounds().getHeight() / 2;
                }
            }
        };
    }

    @Override
    public EventHandler<MouseEvent> getOnTransitionDraggedEventHandler() {
        return new EventHandler<MouseEvent>() {
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
    }

    private void moveNode(Node node, double newTranslateX, double newTranslateY, Dragged dragged) {
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
        if (node instanceof Line) {
            Line line = (Line) node;
            switch (dragged) {
                case ENTITY_FROM:
                    line.setStartX(newTranslateX);
                    line.setStartY(newTranslateY);
                    break;
                case ENTITY_TO:
                    line.setEndX(newTranslateX);
                    line.setEndY(newTranslateY);
                    break;
            }
            csX = line.getStartX();
            csY = line.getStartY();
            ccX = (line.getStartX() + line.getEndX()) / 2;
            ccY = (line.getStartY() + line.getEndY()) / 2;
            ceX = line.getEndX();
            ceY = line.getEndY();
        }
        if (node instanceof Text) {
            Text lineText = (Text) node;
            lineText.setTranslateX((ccX + (csX + ceX) / 2) / 2);
            lineText.setTranslateY((ccY + (csY + ceY) / 2) / 2);
        }
        if (node instanceof Symbol) {
            Symbol symbol = (Symbol) node;
            symbol.setTranslateX((ccX + (csX + ceX) / 2) / 2);
            symbol.setTranslateY((ccY + (csY + ceY) / 2) / 2);
            symbol.setAngle(csX, ceX, csY, ceY);
        }
    }

    @Override
    public EventHandler<ScrollEvent> getOnMouseScrolledEventHandler() {
        return new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                event.consume();
                if (event.getDeltaY() == 0) {
                    return;
                }

                double scaleDelta = 1.1;
                double scaleFactor = (event.getDeltaY() > 0) ? scaleDelta : 1 / scaleDelta;

                Object source = event.getSource();
                if (source instanceof Pane) {
                    Pane pane = (Pane) source;
                    pane.setScaleX(pane.getScaleX() * scaleFactor);
                    pane.setScaleY(pane.getScaleY() * scaleFactor);
                }
            }
        };
    }

}
