package impl.uml.class_diagram;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
        return ((classCount - 1) % 3 + 1) * 550;
    }

    private static int calcY() {
        return ((classCount - 1) / 3 + 1) * 550;
    }

    private static int calculateMaxWidth(String name, Variable[] vars, Method[] methods) {
        int max = name.length();
        for (Variable var : vars) {
            String varString = var.toString();
            for (String varLine : varString.split("\n")) {
                max = max < varLine.length() ? varLine.length() : max;
            }
        }
        for (Method method : methods) {
            String methodString = method.toString();
            for (String methodLine : methodString.split("\n")) {
                max = max < methodLine.length() ? methodLine.length() : max;
            }
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

}
