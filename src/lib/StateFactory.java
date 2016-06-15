package lib;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

/**
 * creates states which can be initial, transitional and final
 *
 * @author laki
 */
public class StateFactory {

    private static int stateCount = 0;

    public static Group createInitialState() {
        Circle circle = new Circle(15, Color.BLACK);
        Group g = new Group(circle);
        g.setTranslateX(50);
        g.setTranslateY(50);
        g.setCursor(Cursor.HAND);
        return g;
    }

    public static Group createFinalState() {
        Circle outter = new Circle(25, Color.BLACK);
        Circle middle = new Circle(20, Color.WHITE);
        Circle inner = new Circle(15, Color.BLACK);
        Group g = new Group(outter, middle, inner);
        g.setTranslateX(570);
        g.setTranslateY(570);
        g.setCursor(Cursor.HAND);
        return g;
    }

    public static Group createTransitionalState(String text) {
        return createTransitionalState(text, 0, 0);
    }

    // too much hardcoded stuff, will change later
    private static Group createTransitionalState(String text, int x, int y) {
        stateCount++;
        Ellipse eOutter = new Ellipse(50, 30);
        eOutter.setFill(Color.BLACK);
        Ellipse eInner = new Ellipse(48, 28);
        eInner.setFill(Color.WHITE);
        Text ellipseText = new Text(text);
        ellipseText.setWrappingWidth(80);
        int offset = -1 * (text.length() > 25 ? 1 : (text.length() > 15 ? 2 : 3));
        ellipseText.setTranslateX(text.length() * offset);
        Group g = new Group(eOutter, eInner, ellipseText);
        if (x == 0 && y == 0) {
            x = calcX();
            y = calcY();
        }
        g.setTranslateX(x);
        g.setTranslateY(y);
        g.setCursor(Cursor.HAND);
        return g;
    }

    // izracunaj x koordinatu novog state-a - srediti ovaj algoritam !!!
    private static int calcX() {
        return stateCount * 100;
    }

    private static int calcY() {
        return stateCount * 100;
    }

}
