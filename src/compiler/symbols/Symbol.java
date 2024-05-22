package symbols;

import java.util.List;

import symbols.ParameterList;

public class Symbol {
    private String identifier;
    private GenericType type;

    public Symbol(String identifier, GenericType type) {
        this.identifier = identifier;
        this.type = type;
    }

    public GenericType getGenericType() {
        return type;
    }

    public Type getType() {
        return type.getType();
    }

    public GenericType getReturnType() {
        if (type.getType() == Type.FUNCTION)
            return ((FunctionType) type).getReturnType();
        return null;
    }

    public ParameterList getParameterTypes() {
        if (type.getType() == Type.FUNCTION)
            return ((FunctionType) type).getParameterList();
        return null;
    }

    public String getIdentifier() {
        return identifier;
    }
}