package util;

import lib.shared.Entity;
import lib.shared.transition.Transition;
import lib.ssa.DataWarehouse;
import lib.ssa.Interface;
import lib.ssa.Process;

/**
 * @author laki
 */
public class SSARuleChecker implements RuleChecker {

    public static final String ILLEGAL_CONNECTION = "Connecting 2 DataWarehouses, 2 Interfaces or Interface and DataWareouse is illegal";
    public static final String ILLEGAL_TRANSITION_NAME = "Transition must have a name";
    public static final String ILLEGAL_ENTITY_NAME = "Entity must have a name";
    public static final String ILLEGAL_TRANSITION = "Transition must connect 2 non null entities";
    public static final String ILLEGAL_PROCESS = "Process must have at least 1 input and 1 output";
    public static final String ILLEGAL_WAREHOUSE = "DataWarehouse must have at least 1 input or output";
    public static final String ILLEGAL_INTERFACE = "Interface must have at least 1 input or output";

    @Override
    public void checkTransitionRules(Transition t) {
        Entity entityFrom = t.getEntityFrom();
        Entity entityTo = t.getEntityTo();
        String name = t.getName();
        if (entityFrom == null || entityTo == null) {
            throw new IllegalTransitionException(ILLEGAL_TRANSITION);
        }
        if ((entityFrom instanceof DataWarehouse && entityTo instanceof DataWarehouse)
                || (entityFrom instanceof DataWarehouse && entityTo instanceof Interface)
                || (entityFrom instanceof Interface && entityTo instanceof DataWarehouse)
                || (entityFrom instanceof Interface && entityTo instanceof Interface)) {
            throw new IllegalTransitionException(ILLEGAL_CONNECTION);
        }
        if (name == null || name.isEmpty()) {
            if (!(entityFrom instanceof DataWarehouse) && !(entityTo instanceof DataWarehouse)) {
                throw new IllegalTransitionException(ILLEGAL_TRANSITION_NAME);
            }
        }
    }

    @Override
    public void checkEntityRules(Entity e) {
        String name = e.getName();
        if (name == null || name.isEmpty()) {
            throw new IllegalEntityException(ILLEGAL_ENTITY_NAME);
        }
        if (e instanceof Process && (e.getTransitionsFrom().isEmpty() || e.getTransitionsTo().isEmpty())) {
            throw new IllegalEntityException(ILLEGAL_PROCESS);
        }
        if (e instanceof DataWarehouse && (e.getTransitionsFrom().isEmpty() && e.getTransitionsTo().isEmpty())) {
            throw new IllegalEntityException(ILLEGAL_WAREHOUSE);
        }
        if (e instanceof Interface && (e.getTransitionsFrom().isEmpty() && e.getTransitionsTo().isEmpty())) {
            throw new IllegalEntityException(ILLEGAL_INTERFACE);
        }
    }

}
