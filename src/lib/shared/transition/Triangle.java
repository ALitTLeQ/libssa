package lib.shared.transition;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

/**
 * @author laki
 */
public class Triangle  extends Polygon {

    private final Rotate rotate;

    public Triangle(Line line) {
        super(new double[]{0, 0, 10, 20, -10, 20});
        setFill(Color.BLACK);

        rotate = new Rotate();
        rotate.setAxis(Rotate.Z_AXIS);

        getTransforms().add(rotate);
        setAngle(line.getEndX() - line.getStartX(), line.getEndY() - line.getStartY() );

        setTranslateX(((line.getStartX() + line.getEndX()) / 2));
        setTranslateY((line.getStartY() + line.getEndY()) / 2);
    }

    public void setAngle(double endX, double endY) {
        double angle = Math.atan2(endY, endX);
        angle = Math.toDegrees(angle);
        rotate.setAngle(angle + 90);
    }

}
