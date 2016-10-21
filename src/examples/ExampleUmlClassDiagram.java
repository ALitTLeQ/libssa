package examples;

import core.factory.DiagramFactory;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import javafx.application.Application;
import javafx.stage.Stage;
import core.lib.Entity;
import impl.shared.ImplementsTransition;
import impl.shared.StraightLineTransition;
import core.lib.Transition;
import impl.shared.CreatesTransition;
import impl.uml.class_diagram.Class;
import impl.uml.class_diagram.Method;
import impl.uml.class_diagram.Variable;

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

        Class handler = new Class("MouseEventHandler", Modifier.INTERFACE,
                new Variable[]{},
                new Method[]{
                    new Method(Modifier.PUBLIC, "\nEventHandler<MouseEvent>", "getOnMousePressedEventHandler", null),
                    new Method(Modifier.PUBLIC, "\nEventHandler<MouseEvent>", "onEntityDraggedEventHandler", null),
                    new Method(Modifier.PUBLIC, "\nEventHandler<MouseEvent>", "onTransitionDraggedEventHandler", null),
                    new Method(Modifier.PUBLIC, "\nEventHandler<MouseEvent>", "onMouseScrolledEventHandler", null),
                    
                });
        classes.add(handler);
        Class defhandler = new Class("DefaultMouseEventHandler", 0,
                new Variable[]{
                    new Variable(Modifier.PRIVATE, "DefaultMouseEventHandler", "INSTANCE"),
                },
                new Method[]{
                    new Method(Modifier.PUBLIC, "MouseEventHandler", "instance", null),
                });
        classes.add(defhandler);

        Class diagFactory = new Class("DiagramFactory", 0,
                new Variable[]{
                    new Variable(Modifier.PUBLIC, "boolean", "SHOW_EXPORT_BUTTONS")},
                new Method[]{
                    new Method(Modifier.PUBLIC, "void", "createStage", "primaryStage:Stage,\n\ttransitions:Collection<Transition>,\n\tentities:Collection<Entity>"),
                    new Method(Modifier.PUBLIC, "ScrollPane", "createScrollPane", "root:Group")
                });
        classes.add(diagFactory);

        Class entity = new Class("Entity", Modifier.ABSTRACT,
                new Variable[]{
                    new Variable(Modifier.PROTECTED, "Group", "entityGroup"),
                    new Variable(Modifier.PRIVATE, "String", "name"),
                    new Variable(Modifier.PRIVATE, "Collection<Transition>", "transitionsTo"),
                    new Variable(Modifier.PRIVATE, "Collection<Transition>", "transitionsFrom")
                },
                new Method[]{
                    new Method(Modifier.PUBLIC, "Entity", "new", "name:String"),
                    new Method(Modifier.PUBLIC, "Group", "getEntityGroup", null),
                    new Method(Modifier.PUBLIC, "String", "getName", null),
                    new Method(Modifier.PUBLIC, "Collection<Transition>", "getTransitionsFrom", null),
                    new Method(Modifier.PUBLIC, "Collection<Transition>", "getTransitionsTo", null),
                    new Method(Modifier.PUBLIC, "void", "newTransition", "t:Transition,\n\tfrom:boolean"),});
        classes.add(entity);

        Class transition = new Class("Transition", Modifier.ABSTRACT,
                new Variable[]{
                    new Variable(Modifier.PROTECTED, "Group", "transitionView"),
                    new Variable(Modifier.PRIVATE, "String", "name"),
                    new Variable(Modifier.PRIVATE, "Entity", "entityFrom"),
                    new Variable(Modifier.PRIVATE, "Entity", "entityTo"),},
                new Method[]{
                    new Method(Modifier.PUBLIC, "Transition", "new", "entityFrom:Entity,\n    entityTo:Entity,\n    name:String"),
                    new Method(Modifier.PUBLIC, "Group", "getTransitionView", null),
                    new Method(Modifier.PUBLIC, "String", "getName", null),
                    new Method(Modifier.PUBLIC, "Entity", "getEntityTo", null),
                    new Method(Modifier.PUBLIC, "Entity", "getEntityFrom", null),});
        classes.add(transition);

        Class transitionFact = new Class("TransitionFactory", Modifier.INTERFACE,
                new Variable[]{},
                new Method[]{
                    new Method(Modifier.PUBLIC, "Group", "createTransition", "fromEntity:Entity,\n\ttoEntity:Entity, text:String,\n\tlineType:Transition.Line,\n\ttype:Transition.Symbol"),
                });
        classes.add(transitionFact);
        
        Class deftransitionFact = new Class("DefaultTransitionFactory", Modifier.INTERFACE,
                new Variable[]{},
                new Method[]{
                    new Method(Modifier.PUBLIC, "TransitionFactory", "new", ""),
                });
        classes.add(deftransitionFact);

        Class group = new Class("Group", 0,
                new Variable[]{}, new Method[]{
                    new Method(Modifier.PUBLIC, "Group", "new", "")});
        classes.add(group);

        Class symbol = new Class("Symbol", Modifier.ABSTRACT,
                new Variable[]{}, new Method[]{
                    new Method(Modifier.PUBLIC, "Symbol", "new", ""),
                    new Method(Modifier.PUBLIC, "void", "setAngleAndPosition", "\n  coordinates:double[]"),});
        classes.add(symbol);
        
        Class rulecheck = new Class("RuleChecker", Modifier.INTERFACE,
                new Variable[]{}, new Method[]{
                    new Method(Modifier.PUBLIC, "void", "checkTransitionRules", "\n   t:Transition"),
                    new Method(Modifier.PUBLIC, "void", "checkEntityRules", "\n   e:Entity"),});
        classes.add(rulecheck);

        transitions.add(new StraightLineTransition(diagFactory, handler, "1"));
        transitions.add(new ImplementsTransition(defhandler, handler, ""));
        transitions.add(new StraightLineTransition(diagFactory, rulecheck, "0..1"));
        transitions.add(new StraightLineTransition(diagFactory, entity, "*"));
        transitions.add(new StraightLineTransition(diagFactory, transition, "*"));
        transitions.add(new StraightLineTransition(transition, transitionFact, "1"));
        transitions.add(new ImplementsTransition(deftransitionFact, transitionFact, null));
        transitions.add(new CreatesTransition(deftransitionFact, symbol, "<<creates>>"));
        transitions.add(new ImplementsTransition(symbol, group, null));

        // create stage priview
        DiagramFactory.createStage(primaryStage, classes, transitions);
        primaryStage.setTitle("Example Uml Class Diagram");
        primaryStage.show();
    }

}
