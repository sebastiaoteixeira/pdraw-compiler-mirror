package symbols;

public class FunctionType extends GenericType {
    private GenericType returnType;
    private ParameterList parameterTypes;

    public FunctionType(GenericType returnType, ParameterList parameterTypes) {
        setType(Type.FUNCTION);
        setReturnType(returnType);
        setParameterTypes(parameterTypes);
    }

    public GenericType getReturnType() {
        return returnType;
    }

    private void setReturnType(GenericType returnType) {
        this.returnType = returnType;
    }

    public ParameterList getParameterTypes() {
        return parameterTypes;
    }

    private void setParameterTypes(ParameterList parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
}
