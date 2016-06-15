package factory;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;
import lib.State;

/**
 * creates transition with line, text and arrow
 *
 * @author laki
 */
public class TransitionFactory {

    // verovatno jos 2 manje linije sa strana i spojim onim shape union da budu kao strelica, 
    // ali onda u handleru posmatram kao shape ne kao Group
    public static Group createTransition(State fromState, State toState, String text) {
        Group from = fromState.getStateGroup();
        Group to = toState.getStateGroup();
        fromState.newTransition(true);
        toState.newTransition(false);

        double fromX = from.getTranslateX();
        double fromY = from.getTranslateY();
        double toX = to.getTranslateX();
        double toY = to.getTranslateY();

        int transFrom = fromState.getTransitionsFrom();
        double curveControlX = (fromX + toX) / 2 + (transFrom % 2 == 0 ? transFrom : -transFrom) * 30;
        double curveControlY = (fromY + toY) / 2;

        QuadCurve line = new QuadCurve(fromX, fromY, curveControlX, curveControlY, toX, toY);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(1);
        line.setFill(null);

        Text lineText = new Text(text);
        lineText.setTranslateX(curveControlX);
        lineText.setTranslateY(curveControlY);
        lineText.setCursor(Cursor.MOVE);
        return new Group(line, lineText);
    }

}
