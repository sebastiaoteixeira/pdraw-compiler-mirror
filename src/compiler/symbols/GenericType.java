package compiler.symbols;

public abstract class GenericType {
    private Type type;

    public Type getType() {
        return this.type;
    }

    protected void setType(Type type) {
        this.type = type;
    }
    
}
