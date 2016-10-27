package core.util;

import core.lib.Entity;
import core.lib.Transition;
import java.util.Collection;
import javafx.scene.Node;
import javafx.scene.Scene;

/**
 * @author laki
 */
public interface ExportManager {
    public Node[] getExportButtons(Scene scene, Collection<Entity> entities, Collection<Transition> transitions);
}
