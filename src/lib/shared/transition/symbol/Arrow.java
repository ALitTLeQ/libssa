package lib.shared.transition.symbol;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * @author laki
 */
public class Arrow extends Symbol {

    public Arrow(double startX, Double controlX, double endX, double startY, Double controlY, double endY) {
        super();
        createShape(startX, controlX, endX, startY, controlY, endY);
    }
    
    public void createShape(double startX, Double controlX, double endX, double startY, Double controlY, double endY) {
        Polygon arrow = new Polygon(new double[]{0, 0, 5, 20, -5, 20}); // arrow shape
        arrow.setFill(Color.BLACK);
        getChildren().add(arrow);
        setAngleAndPosition(startX, controlX, endX, startY, controlY, endY);
    }
}
