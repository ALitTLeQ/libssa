package lib.shared.transition;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

/**
 * @author laki
 */
public class Triangle extends Group {

    public Triangle(Line line) {
        Polygon outter = new Polygon(new double[]{0, 0, 10, 20, -10, 20});
        Polygon inner = new Polygon(new double[]{1, 1, 9, 19, -9, 19});
        outter.setFill(Color.BLACK);
        inner.setFill(Color.WHITE);
        getChildren().addAll(outter, inner);

        setAngle(line.getStartX(), line.getEndX(), line.getStartY(), line.getEndY());
    }

    public void setAngle(double startX, double endX, double startY, double endY) {
        double angle = Math.atan2(endY - startY, endX - startX);
        angle = Math.toDegrees(angle);
        setRotate(angle + 90);
        setTranslateX((startX + endX) / 2);
        setTranslateY((startY + endY) / 2 - 10);
    }

}
