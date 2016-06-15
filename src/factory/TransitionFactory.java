package factory;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * creates transition with line, text and arrow
 * 
 * @author laki
 */
public class TransitionFactory {

    public static Group createTransition(Group from, Group to, String text) {
        // verovatno jos 2 manje linije sa strana i spojim onim shape union da budu kao strelica, 
        // ali onda u handleru posmatram kao shape ne kao line
        Line line = new Line(from.getTranslateX(), from.getTranslateY(), to.getTranslateX(), to.getTranslateY());
        Text lineText = new Text(text);
        lineText.setTranslateX((from.getTranslateX() + to.getTranslateX()) / 2);
        lineText.setTranslateY((from.getTranslateY() + to.getTranslateY()) / 2);
        return new Group(line, lineText);
    }

}
