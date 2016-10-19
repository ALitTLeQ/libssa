package handler;

import java.util.Collection;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import lib.shared.transition.Transition;

/**
 *
 * @author laki
 */
public interface MouseEventHandler {
    
    public void setTransitions(Collection<Transition> transitions);
    public EventHandler<MouseEvent> getOnMousePressedEventHandler();
    public EventHandler<MouseEvent> getOnEntityDraggedEventHandler();
    public EventHandler<MouseEvent> getOnTransitionDraggedEventHandler();
    public EventHandler<ScrollEvent> getOnMouseScrolledEventHandler();
    
}
