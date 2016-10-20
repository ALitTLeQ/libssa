package core.util;

import core.lib.Transition;
import core.lib.Entity;

/**
 *
 * @author laki
 */
public interface RuleChecker {
    public void checkTransitionRules(Transition t);
    public void checkEntityRules(Entity e);
}
