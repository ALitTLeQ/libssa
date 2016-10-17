package lib.shared.transition.symbol;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;

/**
 * @author laki
 */
public class Arrow extends Symbol {

    public Arrow(QuadCurve curve) {
        super();
        createShape(curve.getStartX(), curve.getEndX(), curve.getStartY(), curve.getEndY());
    }
    
    public Arrow(Line line) {
        super();
        createShape(line.getStartX(), line.getEndX(), line.getStartY(), line.getEndY());
    }
    
    public void createShape(double startX, double endX, double startY, double endY) {
        Polygon arrow = new Polygon(new double[]{0, 0, 5, 20, -5, 20}); // arrow shape
        arrow.setFill(Color.BLACK);
        getChildren().add(arrow);
        setAngle(startX, endX, startY, endY);
    }
}
