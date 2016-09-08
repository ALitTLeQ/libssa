package factory;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;
import lib.Arrow;
import lib.Entity;
import lib.Rounded;

/**
 * creates transition with line, text and arrow
 *
 * @author laki
 */
public class TransitionFactory {

    public static Group createTransition(Entity fromEntity, Entity toEntity, String text) {
        int translateFromX = fromEntity instanceof Rounded ? 0 : 50;
        int translateFromY = fromEntity instanceof Rounded ? 0 : 30;
        int translateToX = toEntity instanceof Rounded ? 0 : 50;
        int translateToY = toEntity instanceof Rounded ? 0 : 30;
        
        Group from = fromEntity.getEntityGroup();
        Group to = toEntity.getEntityGroup();

        double fromX = from.getTranslateX() + translateFromX;
        double fromY = from.getTranslateY() + translateFromY;
        double toX = to.getTranslateX() + translateToX;
        double toY = to.getTranslateY() + translateToY;

        int transFrom = fromEntity.getTransitionsFrom().size();
        double curveControlX = (fromX + toX) / 2 + (transFrom % 2 == 0 ? transFrom : -transFrom) * 30;
        double curveControlY = (fromY + toY) / 2;

        QuadCurve curve = new QuadCurve(fromX, fromY, curveControlX, curveControlY, toX, toY);
        curve.setStroke(Color.BLACK);
        curve.setStrokeWidth(1);
        curve.setFill(null);

        Text lineText = new Text(text);
        lineText.setTranslateX(curveControlX);
        lineText.setTranslateY(curveControlY);
        lineText.setCursor(Cursor.MOVE);

        Arrow arrow = new Arrow(curve);
        arrow.setCursor(Cursor.MOVE);
        return new Group(curve, lineText, arrow);
    }

}
