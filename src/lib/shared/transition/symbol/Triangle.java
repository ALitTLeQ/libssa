package lib.shared.transition.symbol;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;

/**
 * @author laki
 */
public class Triangle extends Symbol {

    public Triangle(QuadCurve curve) {
        super();
        createShape(curve.getStartX(), curve.getEndX(), curve.getStartY(), curve.getEndY());
    }
    
    public Triangle(Line line) {
        super();
        createShape(line.getStartX(), line.getEndX(), line.getStartY(), line.getEndY());
    }
    
    public void createShape(double startX, double endX, double startY, double endY) {
        Polygon outter = new Polygon(new double[]{0, 0, 10, 20, -10, 20});
        Polygon inner = new Polygon(new double[]{1, 1, 9, 19, -9, 19});
        outter.setFill(Color.BLACK);
        inner.setFill(Color.WHITE);
        getChildren().addAll(outter, inner);
        setAngle(startX, endX, startY, endY);
    }
}
