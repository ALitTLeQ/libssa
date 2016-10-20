package impl.uml.class_diagram;

import java.lang.reflect.Modifier;

/**
 * @author laki
 */
public class Method {

    private final int modifier;
    private final String args;
    private final String name;
    private final String returnType;

    public Method(int modifier, String returnType, String name, String args) {
        this.modifier = modifier;
        this.args = args == null ? "" : args;
        this.name = name == null ? "" : name;
        this.returnType = returnType == null ? "" : returnType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(args.length() + name.length() + returnType.length() + 6);
        switch(modifier) {
            case Modifier.PRIVATE:
                sb.append("- ");
                break;
            case Modifier.PROTECTED:
                sb.append("# ");
                break;
            default:
                sb.append("+ ");
        }
        sb.append(name);
        sb.append("(");
        sb.append(args);
        sb.append("): ");
        sb.append(returnType);
        return sb.toString();
    }
    
}
