
import javafx.application.Application;
import javafx.stage.Stage;
import lib.Entity;
import util.EerModelCreator;

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
                
        Entity c1 = emc.createClass("first class");
        emc.createAttribute("atr11", c1);
        emc.createAttribute("atr12", c1);
        emc.createAttribute("atr13", c1);
        
        Entity c2 = emc.createClass("second class");
        emc.createAttribute("atr21", c2);
        
        Entity c3 = emc.createClass("third class");
        emc.createAttribute("atr31", c3);
        emc.createAttribute("atr32", c3);
        
        Entity sc1 = emc.createSubClass("sub class", c1, "(0:M)");
        emc.createAttribute("atr sc11", sc1);
        emc.createAttribute("atr sc12", sc1);
        
        Entity sc2 = emc.createSubClass("sub class 2", c2, "(0:1)");
        emc.createAttribute("atr sc21", sc2);
        
        emc.createRelation("relation name", c1, sc2, "(0:1)", "(1:1)");
        emc.createAggregation("aggregation name", c1, c2, "(0:M)", "(1:M)");
        emc.createSpecialisation(c1, new Entity[]{c2, c3}, "(0:M)");
        
        emc.createModel(primaryStage);
    }

}
