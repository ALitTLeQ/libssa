package impl.uml.class_diagram;

import java.lang.reflect.Modifier;

/**
 * @author laki
 */
public class Variable {
    
    private final int modifier;
    private final String name;
    private final String type;

    public Variable(int modifier, String type, String name) {
        this.modifier = modifier;
        this.name = name == null ? "" : name;
        this.type = type == null ? "" : type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name.length() + type.length() + 4);
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
        sb.append(": ");
        sb.append(type);
        return sb.toString();
    }

}
