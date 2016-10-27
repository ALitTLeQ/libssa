package examples;

import java.util.Collection;
import java.util.HashSet;
import javafx.application.Application;
import javafx.stage.Stage;
import core.factory.DiagramFactory;
import core.lib.Entity;
import core.lib.Transition;
import core.util.DefaultExportManager;
import impl.shared.FullLineTransition;
import impl.uml.state_diagram.State;

/**
 * example - how to use
 *
 * @author laki
 */
public class ExampleUmlStateDiagram extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // create collections
        Collection<Entity> states = new HashSet<>();
        Collection<Transition> transitions = new HashSet<>();

        // create states
        Entity initialState = new State(State.StateType.INITIAL, null);
        Entity popunjena = new State(State.StateType.TRANSITIONAL, "popunjena");
        Entity predata = new State(State.StateType.TRANSITIONAL, "predata");
        Entity dostavljena = new State(State.StateType.TRANSITIONAL, "dostavljena");
        Entity pregledana = new State(State.StateType.TRANSITIONAL, "pregledana");
        Entity odobrena = new State(State.StateType.TRANSITIONAL, "odobrena");
        Entity odbijena = new State(State.StateType.TRANSITIONAL, "odbijena");
        Entity poslataKatedri = new State(State.StateType.TRANSITIONAL, "poslata katedri");
        Entity pregledanaNaKatedri = new State(State.StateType.TRANSITIONAL, "pregledana na katedri");
        Entity rezultovana = new State(State.StateType.TRANSITIONAL, "rezultovana");
        Entity finalState = new State(State.StateType.FINAL, null);

        // add them to collection
        states.add(initialState);
        states.add(finalState);
        states.add(popunjena);
        states.add(predata);
        states.add(dostavljena);
        states.add(pregledana);
        states.add(odobrena);
        states.add(odbijena);
        states.add(poslataKatedri);
        states.add(pregledanaNaKatedri);
        states.add(rezultovana);

        // create transitions and add to collection
        transitions.add(new FullLineTransition(initialState, popunjena, "popunjavanje"));
        transitions.add(new FullLineTransition(popunjena, predata, "predavanje"));
        transitions.add(new FullLineTransition(predata, dostavljena, "slanje"));
        transitions.add(new FullLineTransition(dostavljena, pregledana, "pregledanje"));
        transitions.add(new FullLineTransition(pregledana, odobrena, "odobravanje"));
        transitions.add(new FullLineTransition(pregledana, odbijena, "odbijanje"));
        transitions.add(new FullLineTransition(odbijena, rezultovana, "rezultovanje"));
        transitions.add(new FullLineTransition(odobrena, poslataKatedri, "slanje katedri"));
        transitions.add(new FullLineTransition(poslataKatedri, pregledanaNaKatedri, "pregledanje na katedri"));
        transitions.add(new FullLineTransition(pregledanaNaKatedri, odobrena, "odobravanje"));
        transitions.add(new FullLineTransition(pregledanaNaKatedri, odbijena, "odbijanje"));
        transitions.add(new FullLineTransition(odobrena, rezultovana, "rezultovanje"));
        transitions.add(new FullLineTransition(rezultovana, finalState, "slanje"));

        // create stage priview
        DiagramFactory.exportManager = new DefaultExportManager(true, true, false);
        DiagramFactory.createStage(primaryStage, states, transitions);
        primaryStage.setTitle("Example");
        primaryStage.show();
    }

}
