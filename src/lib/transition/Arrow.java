package lib.transition;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.shape.QuadCurve;

/**
 * thanks to
 * http://stackoverflow.com/questions/26702519/javafx-line-curve-with-arrow-head
 *
 * @author laki
 */
public class Arrow extends Polygon {

    private final Rotate rotate;

    public Arrow(QuadCurve curve) {
        super(new double[]{0, 0, 5, 20, -5, 20}); // arrow shape
        setFill(Color.BLACK);

        rotate = new Rotate();
        rotate.setAxis(Rotate.Z_AXIS);

        getTransforms().add(rotate);
        setAngle(curve.getEndX() - curve.getStartX(), curve.getEndY() - curve.getStartY() );

        setTranslateX(((curve.getStartX() + curve.getEndX()) / 2 + curve.getControlX()) / 2);
        setTranslateY((curve.getStartY() + curve.getEndY()) / 2);
    }

    public Arrow(Line line) {
        super(new double[]{0, 0, 5, 20, -5, 20}); // arrow shape
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
