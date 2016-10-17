package lib.shared.transition.symbol;

import javafx.scene.Group;

/**
 * @author laki
 */
public abstract class Symbol extends Group {

    public Symbol() {
    }

    public void setAngle(double startX, double endX, double startY, double endY) {
        double angle = Math.atan2(endY - startY, endX - startX);
        angle = Math.toDegrees(angle);
        setRotate(angle + 90);
        setTranslateX((startX + endX) / 2);
        setTranslateY((startY + endY) / 2 - 10);
    }
    
}
