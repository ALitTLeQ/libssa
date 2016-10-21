package core.lib.symbol;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * @author laki
 */
public class Arrow extends Symbol {

    public Arrow(double startX, Double controlX, double endX, double startY, Double controlY, double endY) {
        super();
        setAngleAndPosition(startX, controlX, endX, startY, controlY, endY);
    }
    
    @Override
    void createShape() {
        Polygon arrow = new Polygon(new double[]{0, 0, 5, 20, -5, 20}); // arrow shape
        arrow.setFill(Color.BLACK);
        getChildren().add(arrow);
    }

}
