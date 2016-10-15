package factory;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import lib.shared.Rounded;
import lib.eer.Relation;

/**
 * creates eer(pmov) entities, calculates
 * initial positions
 * 
 * @author laki
 */
public class EerFactory {

    private static int entityCount = 0;

    public static Group createClass(String name) {
        return createClass(name, 0, 0);
    }

    public static Group createClass(String name, int x, int y) {
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

    public static Group createAttribute(String text) {
        return createAttribute(text, 0, 0);
    }

    public static Group createAttribute(String text, int x, int y) {
        entityCount++;
        Ellipse eOutter = new Ellipse(50, 30);
        eOutter.setFill(Color.BLACK);
        Ellipse eInner = new Ellipse(48, 28);
        eInner.setFill(Color.WHITE);
        Group g = new Group(eOutter, eInner, formatText(text, Rounded.class));
        if (x == 0 && y == 0) {
            x = calcX() + 50;
            y = calcY() + 30;
        }
        g.setTranslateX(x);
        g.setTranslateY(y);
        g.setCursor(Cursor.HAND);
        return g;
    }

    public static Group createSubClass(String name) {
        return createSubClass(name, 0, 0);
    }
    
    public static Group createSubClass(String name, int x, int y) {
        entityCount++;
        Rectangle rectOutter = new Rectangle(100, 60);
        rectOutter.setFill(Color.BLACK);
        Rectangle rectInner = new Rectangle(96, 56);
        rectInner.setFill(Color.WHITE);
        rectInner.setX(2);
        rectInner.setY(2);
        Rectangle rectOutter2 = new Rectangle(92, 52);
        rectOutter2.setFill(Color.BLACK);
        rectOutter2.setX(4);
        rectOutter2.setY(4);
        Rectangle rectInner2 = new Rectangle(88, 48);
        rectInner2.setFill(Color.WHITE);
        rectInner2.setX(6);
        rectInner2.setY(6);
        Group g = new Group(rectOutter, rectInner, rectOutter2, rectInner2, formatText(name, Rectangle.class));
        if (x == 0 && y == 0) {
            x = calcX();
            y = calcY();
        }
        g.setTranslateX(x);
        g.setTranslateY(y);
        g.setCursor(Cursor.CROSSHAIR);
        return g;
    }

    public static Group createSpecialisation() {
        return createRelation("S", 0, 0);
    }
    
    public static Group createRelation(String name) {
        return createRelation(name, 0, 0);
    }
    
    public static Group createRelation(String name, int x, int y) {
        entityCount++;
        Rectangle rectOutter = new Rectangle(60, 60);
        rectOutter.setFill(Color.BLACK);
        Rectangle rectInner = new Rectangle(56, 56);
        rectInner.setFill(Color.WHITE);
        rectInner.setX(2);
        rectInner.setY(2);
        rectOutter.setRotate(45);
        rectInner.setRotate(45);
        Group g = new Group(rectOutter, rectInner, formatText(name, Relation.class));
        if (x == 0 && y == 0) {
            x = calcX() + 20;
            y = calcY();
        }
        g.setTranslateX(x);
        g.setTranslateY(y);
        g.setCursor(Cursor.CROSSHAIR);
        return g;
    }

    public static Group createAggregation(String name) {
        return createAggregation(name, 0, 0);
    }
    
    public static Group createAggregation(String name, int x, int y) {
        entityCount++;
        Rectangle rectOutter = new Rectangle(100, 60);
        rectOutter.setFill(Color.BLACK);
        Rectangle rectInner = new Rectangle(96, 56);
        rectInner.setFill(Color.WHITE);
        rectInner.setX(2);
        rectInner.setY(2);
        Rectangle rhombOutter = new Rectangle(40, 40);
        rhombOutter.setFill(Color.BLACK);
        rhombOutter.setX(30);
        rhombOutter.setY(10);
        Rectangle rhombInner = new Rectangle(38, 38);
        rhombInner.setFill(Color.WHITE);
        rhombInner.setX(31);
        rhombInner.setY(11);
        rhombOutter.setRotate(45);
        rhombInner.setRotate(45);
        Group g = new Group(rectOutter, rectInner, rhombOutter, rhombInner, formatText(name, Rectangle.class));
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
            if (c.isAssignableFrom(Relation.class)) text.setX(-10);
            else text.setX(10);
            text.setY(t.length() < 25 ? 30 : 15);
        } else {
            text.setX(-50);
        }
        return text;
    }
    
}
