package util;

import lib.shared.Entity;
import lib.shared.transition.Transition;

/**
 *
 * @author laki
 */
public interface RuleChecker {
    public void checkTransitionRules(Transition t);
    public void checkEntityRules(Entity e);
}
