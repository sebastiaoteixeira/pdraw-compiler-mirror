package symbols;

import java.util.List;

import symbols.ParameterList;

public class Symbol {
    private String identifier;
    private IType type;
    private CodeLocation codeLocation;

    public Symbol(String identifier, IType type) {
        this.identifier = identifier;
        if (type == null)
            throw new IllegalArgumentException("Type of " + identifier + " cannot be null");
        this.type = type;
        codeLocation = null;
    }

    public Symbol(String Identifier, IType type, int startLine, int startColumn, int endLine, int endColumn) {
        this.identifier = Identifier;
        if (type == null)
            throw new IllegalArgumentException("Type of " + Identifier + " cannot be null");
        this.type = type;
        codeLocation = new CodeLocation(startLine, startColumn, endLine, endColumn);
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

    public CodeLocation getCodeLocation() {
        return codeLocation;
    }
}