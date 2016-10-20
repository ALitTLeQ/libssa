package factory;

import java.util.Collection;
import java.util.LinkedList;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;
import lib.shared.Entity;
import lib.shared.Rotated;
import lib.shared.Rounded;
import lib.shared.transition.Transition;
import lib.shared.transition.symbol.Arrow;
import lib.shared.transition.symbol.Triangle;

/**
 * @author laki
 */
public class DefaultTransitionFactory implements TransitionFactory {
    
    @Override
    public Group createTransition(Entity fromEntity, Entity toEntity, String text, Transition.Line lineType, Transition.Symbol type) {
        Collection<Node> nodes = new LinkedList<>();
        Group from = fromEntity.getEntityGroup();
        Group to = toEntity.getEntityGroup();

        // calculates entity`s central coordinates
        double fromX = from.getTranslateX() + calcTranslate(fromEntity, true);
        double fromY = from.getTranslateY() + calcTranslate(fromEntity, false);
        double toX = to.getTranslateX() + calcTranslate(toEntity, true);
        double toY = to.getTranslateY() + calcTranslate(toEntity, false);

        Line line = null;
        QuadCurve curve = null;
        Double curveControlX = null;
        Double curveControlY = null;
        
        if (lineType == Transition.Line.STRAIGHT || lineType == Transition.Line.STRAIGHT_INTERRUPTED) {
            line = new Line(fromX, fromY, toX, toY);
            line.setStroke(Color.BLACK);
            line.setStrokeWidth(1);
            line.setFill(null);
            if (lineType == Transition.Line.STRAIGHT_INTERRUPTED) line.getStrokeDashArray().addAll(5d);
            nodes.add(line);
        } else {
            int transFrom = fromEntity.getTransitionsFrom().size();
            curveControlX = (fromX + toX) / 2 + (transFrom % 2 == 0 ? transFrom : -transFrom) * 30;
            curveControlY = (fromY + toY) / 2;
            curve = new QuadCurve(fromX, fromY, curveControlX, curveControlY, toX, toY);
            curve.setStroke(Color.BLACK);
            curve.setStrokeWidth(1);
            curve.setFill(null);
            if (lineType == Transition.Line.CURVED_INTERRUPTED) curve.getStrokeDashArray().addAll(5d);
            nodes.add(curve);
        }
        
        if (text != null && !text.isEmpty()) {
            Text lineText = new Text(text);
            if (curveControlX != null && curveControlY != null) {
                lineText.setTranslateX((curveControlX + ((fromX + toX) / 2)) / 2);
                lineText.setTranslateY((curveControlY + ((fromY + toY) / 2)) / 2);
            } else {
                lineText.setTranslateX((fromX + toX) / 2);
                lineText.setTranslateY((fromY + toY) / 2);
            }
            lineText.setCursor(Cursor.MOVE);
            nodes.add(lineText);
        }
        
        if (type == Transition.Symbol.ARROW) {
            Arrow arrow = new Arrow(fromX, curveControlX, toX, fromY, curveControlY, toY);
            arrow.setCursor(Cursor.MOVE);
            nodes.add(arrow);
        } else if (type == Transition.Symbol.TRIANGLE) {
            Triangle triangle = new Triangle(fromX, curveControlX, toX, fromY, curveControlY, toY);
            triangle.setCursor(Cursor.MOVE);
            nodes.add(triangle);
        }

        return new Group(nodes);
    }

    /**
     * because width/height of rotated is hardcoded to 60
     */
    private static double calcTranslate(Entity e, boolean width) {
        if (e instanceof Rounded) return 0;
        if (e instanceof Rotated) return 30;
        if (width) {
            return e.getEntityGroup().getLayoutBounds().getWidth() / 2;
        } else {
            return e.getEntityGroup().getLayoutBounds().getHeight()/ 2;
        }
    }

    // @todo - fix
    private static double calcTextX(double fromX, double toX, Group to) {
        if(fromX == toX) return toX + 10;
        if(fromX < toX) {
            return toX - (to.getLayoutBounds().getWidth() / 2) - 10;
        } else {
            return toX + (to.getLayoutBounds().getWidth() / 2) + 10;
        }
    }
    
    private static double calcTextY(double fromY, double toY, Group to) {
        if(fromY == toY) return toY + 10;
        if(fromY < toY) {
            return toY - (to.getLayoutBounds().getHeight() / 2) - 10;
        } else {
            return toY + (to.getLayoutBounds().getHeight() / 2) + 10;
        }
    }
}
