package exception;

import lib.DataWarehouse;
import lib.Entity;
import lib.Interface;
import lib.Transition;

/**
 * @author laki
 */
public class RuleChecker {

    public static String ILLEGAL_CONNECTION = "Connecting 2 DataWarehouses, 2 Interfaces or Interface and DataWareouse is illegal";
    public static String ILLEGAL_TRANSITION_NAME = "Transition must have a name";
    public static String ILLEGAL_ENTITY_NAME = "Entity must have a name";
    public static String ILLEGAL_TRANSITION = "Transition must connect 2 non null entities";

    public static void checkTransitionRules(Transition t) {
        Entity entityFrom = t.getEntityFrom();
        Entity entityTo = t.getEntityTo();
        String name = t.getName();
        if ((entityFrom instanceof DataWarehouse && entityTo instanceof DataWarehouse)
                || (entityFrom instanceof DataWarehouse && entityTo instanceof Interface)
                || (entityFrom instanceof Interface && entityTo instanceof DataWarehouse)
                || (entityFrom instanceof Interface && entityTo instanceof Interface)) {
            throw new IllegalTransitionException(ILLEGAL_CONNECTION);
        }
        if (entityFrom == null || entityTo == null) {
            throw new IllegalTransitionException(ILLEGAL_TRANSITION);
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalTransitionException(ILLEGAL_TRANSITION_NAME); // mozda ne mora ako je ka ili od skladista ???
        }
    }

    public static void checkEntityRules(Entity e) {
        String name = e.getName();
        if (name == null || name.isEmpty()) {
            if (!(e instanceof DataWarehouse)) { // proveri ovo !!!
                throw new IllegalTransitionException(ILLEGAL_ENTITY_NAME);
            }
        }
    }

}
