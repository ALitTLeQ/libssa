
import factory.DiagramFactory;
import java.util.Collection;
import java.util.HashSet;
import javafx.application.Application;
import javafx.stage.Stage;
import lib.Entity;
import lib.eer.Aggregation;
import lib.eer.Class;
import lib.eer.Attribute;
import lib.eer.Relation;
import lib.eer.Specialisation;
import lib.eer.SubClass;
import lib.transition.StraightLine;
import lib.transition.StraightLineTransition;
import lib.transition.Transition;

/**
 * example - how to use for EER(extended entity relationship model)
 * 
 * @author laki
 */
public class ExampleEer extends Application {

    Collection<Entity> entities;
    Collection<Transition> transitions;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // create collections
        entities = new HashSet<>();
        transitions = new HashSet<>();

        Entity c1 = createClass("first class");
        createAttribute("atr11", c1);
        createAttribute("atr12", c1);
        createAttribute("atr13", c1);
        
        Entity c2 = createClass("second class");
        createAttribute("atr21", c2);
        
        Entity c3 = createClass("third class");
        createAttribute("atr31", c3);
        createAttribute("atr32", c3);
        
        Entity sc1 = createSubClass("sub class", c1, "(0:M)");
        createAttribute("atr sc11", sc1);
        createAttribute("atr sc12", sc1);
        
        Entity sc2 = createSubClass("sub class 2", c2, "(0:1)");
        createAttribute("atr sc21", sc2);
        
        createRelation("relation name", c1, sc2, "(0:1)", "(1:1)");
        createAggregation("aggregation name", c1, c2, "(0:M)", "(1:M)");
        createSpecialisation(c1, new Entity[]{c2, c3}, "(0:M)");
        
        // create stage priview
        DiagramFactory.createStage(primaryStage, entities, transitions);
        primaryStage.setTitle("Example for eer");
        primaryStage.show();
    }

    private Entity createClass(String name) {
        Entity clazz = new Class(name);
        entities.add(clazz);
        return clazz;
    }

    private void createAttribute(String name, Entity clazz) {
        Entity atr = new Attribute(name);
        entities.add(atr);
        transitions.add(new StraightLine(clazz, atr, ""));
    }

    private Entity createSubClass(String name, Entity parent, String cardinality) {
        Entity sub = new SubClass(name);
        entities.add(sub);
        transitions.add(new StraightLineTransition(parent, sub, cardinality));
        return sub;
    }

    private void createRelation(String name, Entity from, Entity to, String fromCardinality, String toCardinality) {
        Entity rel = new Relation(name);
        entities.add(rel);
        transitions.add(new StraightLine(from, rel, fromCardinality));
        transitions.add(new StraightLine(to, rel, toCardinality));
    }
    
    private void createAggregation(String name, Entity from, Entity to, String fromCardinality, String toCardinality) {
        Entity agr = new Aggregation(name);
        entities.add(agr);
        transitions.add(new StraightLineTransition(from, agr, fromCardinality));
        transitions.add(new StraightLineTransition(to, agr, toCardinality));
    }

    private void createSpecialisation(Entity from, Entity[] to, String cardinality) {
        Entity spec = new Specialisation();
        entities.add(spec);
        transitions.add(new StraightLine(from, spec, cardinality));
        for (Entity entityTo : to) {
            transitions.add(new StraightLineTransition(spec, entityTo, ""));
        }
    }

}
