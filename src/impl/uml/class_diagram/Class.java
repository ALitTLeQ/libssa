package impl.uml.class_diagram;

import java.lang.reflect.Modifier;
import core.lib.Entity;

/**
 * @author laki
 */
public class Class extends Entity {

    private final int modifier;
    private final Variable[] vars;
    private final Method[] methods;

    public Class(String name, int modifier, Variable[] variables, Method[] methods) {
        super(name);
        this.vars = variables;
        this.methods = methods;
        this.modifier = modifier;
        String mod;
        switch(modifier) {
            case Modifier.INTERFACE:
                mod = "<< Interface >>";
                break;
            case Modifier.ABSTRACT:
                mod = "<< Abstract Class >>";
                break;
            default:
                mod = "<< Class >>";
        }
        entityGroup = UmlClassFactory.createClass(name, mod, variables, methods);
    }
    
}
