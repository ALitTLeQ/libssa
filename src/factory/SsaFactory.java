package factory;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * @author laki
 */
public class SsaFactory {

    private static int interfaceCount = 0;

    public static Group createInterface(String text) {
        return createInterface(text, 0, 0);
    }
    
    public static Group createInterface(String name, int x, int y) {
        interfaceCount++;
        Rectangle rectOutter = new Rectangle(100, 60);
        rectOutter.setFill(Color.BLACK);
        Rectangle rectInner = new Rectangle(96, 56);
        rectInner.setFill(Color.WHITE);
        rectInner.setX(2);
        rectInner.setY(2);
        Text rectText = new Text(name);
        rectText.setWrappingWidth(80);
        rectText.setX(50);
        rectText.setY(30);
        int offset = -1 * (name.length() > 25 ? 1 : (name.length() > 15 ? 2 : 3));
        rectText.setTranslateX(name.length() * offset);
        Group g = new Group(rectOutter, rectInner, rectText);
        if (x == 0 && y == 0) {
            x = calcX();
            y = calcY();
        }
        g.setTranslateX(x);
        g.setTranslateY(y);
        g.setCursor(Cursor.CROSSHAIR);
        return g;
    }
    
    private static int calcX() {
        return interfaceCount * 150;
    }

    private static int calcY() {
        return interfaceCount * 150;
    }

}
