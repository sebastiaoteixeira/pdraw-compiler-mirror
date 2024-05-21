package compiler.symbols;

import java.util.List;

public class Symbol {
    private StringType identifier;
    private GenericType type;

    public Symbol(StringType identifier, GenericType type) {
        this.identifier = identifier;
        this.type = type;
    }

    public Type getType() {
        return type.getType();
    }

    public Type getReturnType() {
        if (type.getType() == Type.FUNCTION)
            return ((Function) type).getReturnType();
        return null;
    }

    public List<Type> getParameterTypes() {
        if (type.getType() == Type.FUNCTION)
            return ((Function) type).getParameterTypes();
        return null;
    }

    public StringType getIdentifier() {
        return identifier;
    }
}