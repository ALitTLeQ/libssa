package util;

import factory.DiagramFactory;
import java.util.Collection;
import java.util.HashSet;
import javafx.stage.Stage;
import lib.Entity;
import lib.eer.Aggregation;
import lib.eer.Attribute;
import lib.eer.Relation;
import lib.eer.Specialisation;
import lib.eer.SubClass;
import lib.transition.StraightLine;
import lib.transition.StraightLineTransition;
import lib.transition.Transition;

/**
 * @author laki
 */
public class EerModelCreator {

    private static EerModelCreator instance;
    private final Collection<Entity> entities;
    private final Collection<Transition> transitions;


    private EerModelCreator() {
        // init collections
        entities = new HashSet<>();
        transitions = new HashSet<>();
    }

    public static EerModelCreator instance() {
        if (instance == null) {
            instance = new EerModelCreator();
        }
        return instance;
    }

    public Entity createClass(String name) {
        Entity clazz = new lib.eer.Class(name);
        entities.add(clazz);
        return clazz;
    }

    public void createAttribute(String name, Entity clazz) {
        Entity atr = new Attribute(name);
        entities.add(atr);
        transitions.add(new StraightLine(clazz, atr, ""));
    }

    public Entity createSubClass(String name, Entity parent, String cardinality) {
        Entity sub = new SubClass(name);
        entities.add(sub);
        transitions.add(new StraightLineTransition(parent, sub, cardinality));
        return sub;
    }

    public void createRelation(String name, Entity from, Entity to, String fromCardinality, String toCardinality) {
        Entity rel = new Relation(name);
        entities.add(rel);
        transitions.add(new StraightLine(from, rel, fromCardinality));
        transitions.add(new StraightLine(to, rel, toCardinality));
    }

    public void createAggregation(String name, Entity from, Entity to, String fromCardinality, String toCardinality) {
        Entity agr = new Aggregation(name);
        entities.add(agr);
        transitions.add(new StraightLineTransition(from, agr, fromCardinality));
        transitions.add(new StraightLineTransition(to, agr, toCardinality));
    }

    public void createSpecialisation(Entity from, Entity[] to, String cardinality) {
        Entity spec = new Specialisation();
        entities.add(spec);
        transitions.add(new StraightLine(from, spec, cardinality));
        for (Entity entityTo : to) {
            transitions.add(new StraightLineTransition(spec, entityTo, ""));
        }
    }

    // creates stage priview
    public void createModel(Stage primaryStage) {
        DiagramFactory.createStage(primaryStage, entities, transitions);
        primaryStage.setTitle("Example for eer");
        primaryStage.show();
    }

}
