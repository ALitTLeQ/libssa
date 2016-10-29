package core.util;

import core.lib.Entity;
import core.lib.Transition;
import java.util.Collection;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Scene;

/**
 * @author laki
 */
public interface ExportManager {
    public List<Node> getExportButtons(Scene scene, Collection<Entity> entities, Collection<Transition> transitions);
}
