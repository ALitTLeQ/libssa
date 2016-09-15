import factory.DiagramFactory;
import java.util.Collection;
import java.util.HashSet;
import javafx.application.Application;
import javafx.stage.Stage;
import lib.Entity;
import lib.Transition;
import lib.ssa.DataWarehouse;
import lib.ssa.Process;
import lib.ssa.Interface;

/**
 * example - how to use for ssa
 *
 * @author laki
 */
public class ExampleSSA extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // create collections
        Collection<Entity> entities = new HashSet<>();
        Collection<Transition> transitions = new HashSet<>();

        // create interfaces
        Entity interface1 = new Interface("first");
        Entity interface2 = new Interface("second");
        Entity interface3 = new Interface("third interface is this one");
        
        // create processes
        Entity process1 = new Process("process");
        
        // create data-warehouses
        Entity dw1 = new DataWarehouse("dw1");

        // add them to collection
        entities.add(interface1);
        entities.add(interface2);
        entities.add(interface3);
        entities.add(process1);
        entities.add(dw1);

        // create transitions and add to collection
        transitions.add(new Transition(process1, interface2, "tr11"));
        transitions.add(new Transition(interface3, process1, "tr12"));
        transitions.add(new Transition(process1, interface2, "tr13"));
        transitions.add(new Transition(process1, interface3, "tr14"));
        transitions.add(new Transition(process1, interface1, "tr15"));
        transitions.add(new Transition(interface2, process1, "tr16"));
        transitions.add(new Transition(process1, interface2, "tr17"));
        transitions.add(new Transition(interface1, process1, "tr18"));
        transitions.add(new Transition(process1, interface1, "tr19"));
        transitions.add(new Transition(dw1, process1, "tr19"));
        transitions.add(new Transition(process1, dw1, "tr19"));
        
        // create stage priview
        DiagramFactory.createStage(primaryStage, entities, transitions);
        primaryStage.setTitle("Example for ssa");
        primaryStage.show();
    }

}
