package examples;

import factory.DiagramFactory;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import javafx.application.Application;
import javafx.stage.Stage;
import lib.shared.Entity;
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

        Class handler = new Class("MouseEventHandler", 0,
                new Variable[]{
                    new Variable(Modifier.PUBLIC, "\nEventHandler<MouseEvent>", "onMousePressedEventHandler"),
                    new Variable(Modifier.PUBLIC, "\nEventHandler<MouseEvent>", "onEntityDraggedEventHandler"),
                    new Variable(Modifier.PUBLIC, "\nEventHandler<MouseEvent>", "onTransitionDraggedEventHandler"),
                    new Variable(Modifier.PUBLIC, "\nEventHandler<MouseEvent>", "onMouseScrolledEventHandler"),}, new Method[]{new Method(Modifier.PRIVATE, "void", "moveNode", "node:Node,\n\tnewTranslateX:double,\n\tnewTranslateY:double")});
        classes.add(handler);

        Class diagFactory = new Class("DiagramFactory", 0,
                new Variable[]{
                    new Variable(Modifier.PUBLIC, "boolean", "SHOW_EXPORT_BUTTONS"),
                    new Variable(Modifier.PUBLIC, "boolean", "CHECK_SSA_RULES"),},
                new Method[]{
                    new Method(Modifier.PUBLIC, "void", "createStage", "primaryStage:Stage,\n\ttransitions:Collection<Transition>,\n\tentities:Collection<Entity>"),
                    new Method(Modifier.PRIVATE, "Button", "createPngButton", "scene:Scene"),
                    new Method(Modifier.PRIVATE, "Button", "createPdfButton", "scene:Scene"),
                    new Method(Modifier.PRIVATE, "ScrollPane", "createScrollPane", "root:Group"),});
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

        Class entityFact = new Class("EntityFactory", Modifier.INTERFACE,
                new Variable[]{
                    new Variable(Modifier.PROTECTED, "int", "entityCount"),},
                new Method[]{
                    new Method(Modifier.PUBLIC, "Entity", "createEntity", "\n    name:String"),
                    new Method(Modifier.PRIVATE, "Text", "formatText", "t:String,\n    c:Class"),
                    new Method(Modifier.PRIVATE, "int", "calcX", null),
                    new Method(Modifier.PRIVATE, "int", "calcY", null),});
        classes.add(entityFact);

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
                    new Method(Modifier.PRIVATE, "Group", "createTransition", "fromEntity:Entity,\n\ttoEntity:Entity, text:String,\n\tlineType:Transition.Line,\n\ttype:Transition.Symbol"),
                    new Method(Modifier.PRIVATE, "double", "calcTranslate", "e:Entity,\n\twidth:boolean"),});
        classes.add(transitionFact);

        Class group = new Class("Group", 0,
                new Variable[]{}, new Method[]{
                    new Method(Modifier.PUBLIC, "Symbol", "new", "line:Line")});
        classes.add(group);

        Class symbol = new Class("Symbol", Modifier.ABSTRACT,
                new Variable[]{}, new Method[]{
                    new Method(Modifier.PRIVATE, "Symbol", "new", "line:Line"),
                    new Method(Modifier.PRIVATE, "Symbol", "new", "curve:QuadCurve"),
                    new Method(Modifier.PRIVATE, "void", "setRotate", "value:double"),});
        classes.add(symbol);

        transitions.add(new StraightLineTransition(diagFactory, handler, "1"));
        transitions.add(new StraightLineTransition(diagFactory, entity, "*"));
        transitions.add(new StraightLineTransition(entity, entityFact, "1"));
        transitions.add(new StraightLineTransition(diagFactory, transition, "*"));
        transitions.add(new StraightLineTransition(transition, transitionFact, "1"));
        transitions.add(new StraightLineTransition(transition, symbol, "0..1"));
        transitions.add(new ImplementsTransition(symbol, group, null));

        // create stage priview
        DiagramFactory.createStage(primaryStage, classes, transitions);
        primaryStage.setTitle("Example Uml Class Diagram");
        primaryStage.show();
    }

}
