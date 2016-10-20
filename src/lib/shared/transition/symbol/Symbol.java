package lib.shared.transition.symbol;

import javafx.scene.Group;

/**
 * @author laki
 */
public abstract class Symbol extends Group {

    public Symbol() {
    }

    public void setAngleAndPosition(double startX, Double controlX, double endX, double startY, Double controlY, double endY) {
        double angle = Math.atan2(endY - startY, endX - startX);
        angle = Math.toDegrees(angle);
        setRotate(angle + 90);
        if (controlX != null && controlY != null) {
            setTranslateX((controlX + ((startX + endX) / 2)) / 2);
            setTranslateY((controlY + ((startY + endY) / 2)) / 2 - 10);
        } else {
            setTranslateX((startX + endX) / 2);
            setTranslateY((startY + endY) / 2 - 10);
        }
    }
    
}
