package symbols;

public class Symbol {
    private String identifier;
    private Type type;

    public Symbol(String identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }
}