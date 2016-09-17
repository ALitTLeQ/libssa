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
        Entity interface4 = new Interface("4");
        Entity interface5 = new Interface("5");
        Entity interface6 = new Interface("6");
        Entity interface7 = new Interface("777777777777");
        
        // create processes
        Entity process1 = new Process("process1");
        Entity process2 = new Process("process2");
        Entity process3 = new Process("process3");
        Entity process4 = new Process("process4");
        
        // create data-warehouses
        Entity dw1 = new DataWarehouse("dw1");
        Entity dw2 = new DataWarehouse("dw2");
        Entity dw3 = new DataWarehouse("data warehouse number 3");

        // add them to collection
        entities.add(interface1);
        entities.add(interface2);
        entities.add(interface3);
        entities.add(interface4);
        entities.add(interface5);
        entities.add(interface6);
        entities.add(interface7);
        entities.add(process1);
        entities.add(process2);
        entities.add(process3);
        entities.add(process4);
        entities.add(dw1);
        entities.add(dw2);
        entities.add(dw3);

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
        transitions.add(new Transition(process2, dw2, "tr19"));
        transitions.add(new Transition(process3, dw3, "tr19"));
        transitions.add(new Transition(process4, dw2, "tr19"));
        transitions.add(new Transition(interface4, process3, "tr19"));
        transitions.add(new Transition(interface5, process2, "tr19"));
        transitions.add(new Transition(interface6, process4, "tr19"));
        transitions.add(new Transition(interface7, process4, "tr19"));
        
        // create stage priview
        DiagramFactory.createStage(primaryStage, entities, transitions);
        primaryStage.setTitle("Example for ssa");
        primaryStage.show();
    }

}
