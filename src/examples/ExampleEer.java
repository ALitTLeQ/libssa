package examples;


import javafx.application.Application;
import javafx.stage.Stage;
import core.lib.Entity;
import impl.eer.EerModelCreator;

/**
 * example - how to use for EER(extended entity relationship model)
 * 
 * @author laki
 */
public class ExampleEer extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        EerModelCreator emc = EerModelCreator.instance();

        Entity student = emc.createClass("student");
        emc.createAttribute("broj indeksa", student);
        emc.createAttribute("ime i prezime", student);
        emc.createAttribute("semestar", student);
        emc.createAttribute("status", student);
        
        Entity sluzba = emc.createClass("studentska služba");
        Entity katedra = emc.createClass("katedra");
        Entity ispit = emc.createClass("ispit");
        emc.createAttribute("id", ispit);
        emc.createAttribute("espb", ispit);
        emc.createAttribute("naziv", ispit);
        
        Entity prijava = emc.createAggregation("prijava", student, ispit, "(0,M) šalje", "(0,M) ima");
        emc.createAttribute("status", prijava);
        emc.createAttribute("datum", prijava);
        emc.createAttribute("id", prijava);
        
        emc.createRelation("obrađuje", prijava, sluzba, "(1:1)", "(0:M)");
        emc.createRelation("pripada", ispit, katedra, "(1:1)", "(1:M)");
        
        emc.createSpecialisation(katedra, new Entity[]{
            emc.createClass("katedra za softversko inženjerstvo"),
            emc.createClass("katedra za inform. sisteme"),
            emc.createClass("katedra za elektronsko poslovanje"),
        }, "(1:1)");
        
        emc.createModel(primaryStage);
    }

}
