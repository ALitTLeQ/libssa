package examples;

import factory.DiagramFactory;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import javafx.application.Application;
import javafx.stage.Stage;
import lib.shared.Entity;
import lib.shared.transition.CreatesTransition;
import lib.shared.transition.ImplementsTransition;
import lib.shared.transition.StraightLineTransition;
import lib.shared.transition.Transition;
import lib.uml.class_diagram.Class;
import lib.uml.class_diagram.Method;
import lib.uml.class_diagram.Variable;

/**
 * @author laki
 */
public class ExampleUmlClassDiagram extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // create collections
        Collection<Entity> classes = new HashSet<>();
        Collection<Transition> transitions = new HashSet<>();

        Class c1 = new Class("c1", Modifier.ABSTRACT,
                new Variable[]{
                    new Variable(Modifier.PRIVATE, "String", "var1"),
                    new Variable(Modifier.PUBLIC, "int", "var2"),
                    new Variable(Modifier.PRIVATE, "long", "var3")
                }, new Method[]{
                    new Method(Modifier.PUBLIC, "void", "method1", null),
                    new Method(Modifier.PRIVATE, "String", "method2", "String a1, String a2"),
                    new Method(Modifier.PUBLIC, "int", "method3", ""),
                    new Method(Modifier.PRIVATE, "long", "method4", null),});
        classes.add(c1);
        
        Class c2 = new Class("c2", Modifier.INTERFACE,
                new Variable[]{new Variable(Modifier.PUBLIC, "int", "var2")},
                new Method[]{new Method(Modifier.PUBLIC, "void", "method1", null)});
        classes.add(c2);
        
        Class c3 = new Class("c3", 0,
                new Variable[]{new Variable(Modifier.PUBLIC, "int", "var2")},
                new Method[]{new Method(Modifier.PUBLIC, "void", "method1", null)});
        classes.add(c3);
        
        transitions.add(new StraightLineTransition(c1, c2, "asdasd"));
        transitions.add(new CreatesTransition(c1, c3, "from1"));
        transitions.add(new ImplementsTransition(c3, c2, "extends\n0..*"));

        // create stage priview
        DiagramFactory.createStage(primaryStage, classes, transitions);
        primaryStage.setTitle("Example Uml Class Diagram");
        primaryStage.show();
    }

}
