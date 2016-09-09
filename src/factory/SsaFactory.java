package factory;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import lib.Rounded;

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
        Group g = new Group(rectOutter, rectInner, formatText(name, Rectangle.class));
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
    
    private static Text formatText(String t, Class c) {
        Text text = new Text(t);
        text.setWrappingWidth(80);
        text.setTextAlignment(TextAlignment.CENTER);
        if (!c.isAssignableFrom(Rounded.class)) {
            text.setX(10);
            text.setY(t.length() < 25 ? 30 : 15);
        }
        return text;
    }

}
