package examples;

import core.factory.DiagramFactory;
import java.util.Collection;
import java.util.HashSet;
import javafx.application.Application;
import javafx.stage.Stage;
import core.lib.Entity;
import impl.ssa.DataWarehouse;
import impl.ssa.Interface;
import impl.ssa.Process;
import core.lib.Transition;
import impl.shared.FullLineTransition;
import impl.ssa.SSARuleChecker;

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
        Entity student = new Interface("student");
        Entity sluzba = new Interface("studentska služba");
        Entity katedra = new Interface("katedra za softversko inženjerstvo");
        
        // create processes
        Entity prijava = new Process("1. prijava");
        Entity provera = new Process("2. provera");
        
        // create data-warehouses
        Entity prijave = new DataWarehouse("prijave");
        Entity obrPrijave = new DataWarehouse("obrađene prijave");

        // add them to collection
        entities.add(student);
        entities.add(sluzba);
        entities.add(katedra);
        entities.add(prijava);
        entities.add(provera);
        entities.add(prijave);
        entities.add(obrPrijave);

        // create transitions and add to collection
        transitions.add(new FullLineTransition(student, prijava, "prijava ispita"));
        transitions.add(new FullLineTransition(prijava, student, "rezultat prijave ispita"));
        
        transitions.add(new FullLineTransition(prijava, prijave, ""));
        transitions.add(new FullLineTransition(prijave, provera, ""));
        transitions.add(new FullLineTransition(provera, sluzba, "prijava ispita"));
        transitions.add(new FullLineTransition(sluzba, provera, "odobrenje prijave ispita"));
        transitions.add(new FullLineTransition(sluzba, provera, "odbijanje prijave ispita"));
        
        transitions.add(new FullLineTransition(provera, katedra, "prijava ispita"));
        transitions.add(new FullLineTransition(katedra, provera, "angažovanje studenta"));
        
        transitions.add(new FullLineTransition(provera, obrPrijave, ""));
        transitions.add(new FullLineTransition(obrPrijave, prijava, ""));
        
        // create stage priview
        DiagramFactory.checker = new SSARuleChecker();
        DiagramFactory.addExportButtons = true;
        DiagramFactory.createStage(primaryStage, entities, transitions);
        primaryStage.setTitle("Example for ssa");
        primaryStage.show();
    }

}
