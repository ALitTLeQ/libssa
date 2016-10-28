package impl.ssa;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import core.lib.Rounded;

/**
 * creates ssa entities (interface, process and data warehouse), calculates
 * initial positions
 *
 * @author laki
 */
public class SsaFactory {

    private static int entityCount = 0;
    
    public static Group createEntity(String text, Class clazz) {
        return createEntity(text, 0, 0, clazz);
    }

    public static Group createEntity(String name, int x, int y, Class clazz) {
        if (clazz.equals(Interface.class)) return createInterface(name, x, y);
        if (clazz.equals(Process.class)) return createProcess(name, x, y);
        if (clazz.equals(DataWarehouse.class)) return createDataWarehouse(name, x, y);
        return null;
    }

    private static Group createInterface(String name, int x, int y) {
        entityCount++;
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

    private static Group createProcess(String text, int x, int y) {
        entityCount++;
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
            x = calcX() + 50;
            y = calcY() + 30;
        }
        g.setTranslateX(x);
        g.setTranslateY(y);
        g.setCursor(Cursor.HAND);
        return g;
    }

    private static Group createDataWarehouse(String name, int x, int y) {
        entityCount++;
        Rectangle rectOutter = new Rectangle(100, 60);
        rectOutter.setFill(Color.BLACK);
        Rectangle rectInner = new Rectangle(100, 56);
        rectInner.setFill(Color.WHITE);
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
        return ((entityCount - 1) / 3 + 1) * 200;
    }

    private static int calcY() {
        return ((entityCount - 1) % 3 + 1) * 200;
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
