package lib.shared.transition.symbol;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * @author laki
 */
public class Triangle extends Symbol {

    public Triangle(double startX, Double controlX, double endX, double startY, Double controlY, double endY) {
        super();
        createShape(startX, controlX, endX, startY, controlY, endY);
    }
    
    public void createShape(double startX, Double controlX, double endX, double startY, Double controlY, double endY) {
        Polygon outter = new Polygon(new double[]{0, 0, 10, 20, -10, 20});
        Polygon inner = new Polygon(new double[]{1, 1, 9, 19, -9, 19});
        outter.setFill(Color.BLACK);
        inner.setFill(Color.WHITE);
        getChildren().addAll(outter, inner);
        setAngleAndPosition(startX, controlX, endX, startY, controlY, endY);
    }
}
