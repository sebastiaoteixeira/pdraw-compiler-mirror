package symbols;

public abstract class IType {
    private Type type;

    public Type getType() {
        return this.type;
    }

    protected void setType(Type type) {
        this.type = type;
    }
    
}
