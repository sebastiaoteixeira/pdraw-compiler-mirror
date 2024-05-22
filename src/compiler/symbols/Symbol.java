package symbols;

import java.util.List;

import symbols.ParameterList;

public class Symbol {
    private String identifier;
    private IType type;

    public Symbol(String identifier, IType type) {
        this.identifier = identifier;
        if (type == null)
            throw new IllegalArgumentException("Type of " + identifier + " cannot be null");
        this.type = type;
    }

    public IType getIType() {
        return type;
    }

    public Type getType() {
        return type.getType();
    }

    public IType getReturnType() {
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