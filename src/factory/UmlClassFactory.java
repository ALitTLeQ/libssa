package factory;

import java.util.Collection;
import java.util.LinkedList;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lib.shared.Entity;
import lib.shared.transition.Arrow;
import lib.shared.transition.Triangle;
import lib.uml.class_diagram.Method;
import lib.uml.class_diagram.Variable;

/**
 * @author laki
 */
public class UmlClassFactory {

    private static int classCount = 0;
    
    public static Group createClass(String name, String modifier, Variable[] vars, Method[] methods) {
        return createClass(name, modifier, vars, methods, 0, 0);
    }

    public static Group createClass(String className, String modifier, Variable[] vars, Method[] methods, int x, int y) {
        classCount++;
        int width = calculateMaxWidth(className, vars, methods);
        Text text = createClassText(width, modifier, className, vars, methods);
        
        Rectangle rectOutter = new Rectangle(text.getLayoutBounds().getWidth() + 4, text.getLayoutBounds().getHeight() - 11);
        rectOutter.setFill(Color.BLACK);
        Rectangle rectInner = new Rectangle(text.getLayoutBounds().getWidth(), text.getLayoutBounds().getHeight() - 15);
        rectInner.setFill(Color.WHITE);
        rectInner.setX(2);
        rectInner.setY(2);
        
        Group g = new Group(rectOutter, rectInner, text);
        if (x == 0 && y == 0) {
            x = calcX();
            y = calcY();
        }
        g.setTranslateX(x);
        g.setTranslateY(y);
        g.setCursor(Cursor.HAND);
        return g;
    }

    private static int calcX() {
        return ((classCount - 1) % 3 + 1) * 1350;
    }

    private static int calcY() {
        return ((classCount - 1) / 3 + 1) * 1350;
    }

    private static int calculateMaxWidth(String name, Variable[] vars, Method[] methods) {
        int max = name.length();
        for (Variable var : vars) {
            max = max < var.toString().length() ? var.toString().length() : max;
        }
        for (Method method : methods) {
            max = max < method.toString().length() ? method.toString().length() : max;
        }
        return max;
    }

    private static Text createClassText(int maxWidth, String modifier, String className, Variable[] vars, Method[] methods) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < (maxWidth - modifier.length()) / 2; i++) {
            sb.append(" ");
        }
        sb.append(modifier).append("\n");
        for (int i = 0; i < (maxWidth - className.length()) / 2; i++) {
            sb.append(" ");
        }
        sb.append(className).append("\n");
        for (int i = 0; i <= maxWidth; i++) {
            sb.append("_");
        }
        sb.append("\n\n");
        for (Variable var : vars) {
            sb.append(" ").append(var.toString()).append("\n");
        }
        for (int i = 0; i <= maxWidth; i++) {
            sb.append("_");
        }
        sb.append("\n\n");
        for (Method method : methods) {
            sb.append(" ").append(method.toString()).append("\n");
        }
        Text t = new Text(sb.toString());
        t.setFont(Font.font("Monospaced"));
        return t;
    }
    
    public static enum ConnectionType { IMPLEMENTS, CREATES, REFERENCES, DOUBLE_REFERENCES }
    
    public static Group createConnection(Entity fromEntity, Entity toEntity, String cardinalityFrom, String cardinalityTo, ConnectionType connType) {
        Collection<Node> nodes = new LinkedList<>();
        
        Group from = fromEntity.getEntityGroup();
        Group to = toEntity.getEntityGroup();

        double fromX = from.getTranslateX() + from.getLayoutBounds().getWidth() / 2;
        double fromY = from.getTranslateY() + from.getLayoutBounds().getHeight() / 2;
        double toX = to.getTranslateX() + to.getLayoutBounds().getWidth() / 2;
        double toY = to.getTranslateY() + to.getLayoutBounds().getHeight() / 2;

        Line line = new Line(fromX, fromY, toX, toY);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(1);
        line.setFill(null);
        if (connType == ConnectionType.CREATES) line.getStrokeDashArray().addAll(5d);
        nodes.add(line);
        
        if (cardinalityFrom != null && !cardinalityFrom.isEmpty()) {
            Text lineText = new Text(cardinalityFrom);
            lineText.setTranslateX((fromX + ((fromX + toX) / 2)) / 2);
            lineText.setTranslateY((fromY + ((fromY + toY) / 2)) / 2);
            lineText.setCursor(Cursor.MOVE);
            nodes.add(lineText);
        }
        
        if (cardinalityTo != null && !cardinalityTo.isEmpty()) {
            Text lineText = new Text(cardinalityTo);
            lineText.setTranslateX((toX + ((fromX + toX) / 2)) / 2);
            lineText.setTranslateY((toY + ((fromY + toY) / 2)) / 2);
            lineText.setCursor(Cursor.MOVE);
            nodes.add(lineText);
        }
        
        if (connType != ConnectionType.IMPLEMENTS) {
            Arrow arrow = new Arrow(line);
            arrow.setCursor(Cursor.MOVE);
            nodes.add(arrow);
        } else {
            Triangle triangle = new Triangle(line);
            triangle.setCursor(Cursor.MOVE);
            nodes.add(triangle);
        }

        return new Group(nodes);
    }

}
