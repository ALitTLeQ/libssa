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
import lib.transition.Arrow;
import lib.Entity;
import lib.Rotated;
import lib.Rounded;

/**
 * creates transition with line, text and arrow
 *
 * @author laki
 */
public class TransitionFactory {

    public static Group createTransition(Entity fromEntity, Entity toEntity, String text) {
        return createTransition(fromEntity, toEntity, text, true, QuadCurve.class);
    }

    public static Group createStraightLine(Entity fromEntity, Entity toEntity, String text) {
        return createTransition(fromEntity, toEntity, text, false, Line.class);
    }

    public static Group createStraightLineTransition(Entity fromEntity, Entity toEntity, String text) {
        return createTransition(fromEntity, toEntity, text, true, Line.class);
    }
    
    public static Group createTransition(Entity fromEntity, Entity toEntity, String text, boolean addArrow, Class c) {
        Collection<Node> nodes = new LinkedList<>();
        int translateFromX = calcTranslateX(fromEntity);
        int translateFromY = calcTranslateY(fromEntity);
        int translateToX = calcTranslateX(toEntity);
        int translateToY = calcTranslateY(toEntity);
        
        Group from = fromEntity.getEntityGroup();
        Group to = toEntity.getEntityGroup();

        double fromX = from.getTranslateX() + translateFromX;
        double fromY = from.getTranslateY() + translateFromY;
        double toX = to.getTranslateX() + translateToX;
        double toY = to.getTranslateY() + translateToY;

        int transFrom = fromEntity.getTransitionsFrom().size();
        double curveControlX = (fromX + toX) / 2 + (transFrom % 2 == 0 ? transFrom : -transFrom) * 30;
        double curveControlY = (fromY + toY) / 2;

        Line line = null;
        QuadCurve curve = null;
        
        if (c.equals(Line.class)) {
            line = new Line(fromX, fromY, toX, toY);
            line.setStroke(Color.BLACK);
            line.setStrokeWidth(1);
            line.setFill(null);
            nodes.add(line);
        } else if (c.equals(QuadCurve.class)) {
            curve = new QuadCurve(fromX, fromY, curveControlX, curveControlY, toX, toY);
            curve.setStroke(Color.BLACK);
            curve.setStrokeWidth(1);
            curve.setFill(null);
            nodes.add(curve);
        }
        
        if (text != null && !text.isEmpty()) {
            Text lineText = new Text(text);
            lineText.setTranslateX((fromX + toX) / 2);
            lineText.setTranslateY((fromY + toY) / 2);
            lineText.setCursor(Cursor.MOVE);
            nodes.add(lineText);
        }
        
        if (addArrow) {
            Arrow arrow = line != null ? new Arrow(line) : new Arrow(curve);
            arrow.setCursor(Cursor.MOVE);
            nodes.add(arrow);
        }

        return new Group(nodes);
    }

    private static int calcTranslateX(Entity e) {
        return e instanceof Rounded ? 0 : (e instanceof Rotated ? 30 : 50);
    }

    private static int calcTranslateY(Entity e) {
        return e instanceof Rounded ? 0 : 30;
    }
    
}
