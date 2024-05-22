package symbols;

public class FunctionType extends GenericType {
    private GenericType returnType;
    private ParameterList parameterList;

    public FunctionType(GenericType returnType, ParameterList parameterList) {
        setType(Type.FUNCTION);
        setReturnType(returnType);
        setParameterList(parameterList);
    }

    public GenericType getReturnType() {
        return returnType;
    }

    private void setReturnType(GenericType returnType) {
        this.returnType = returnType;
    }

    public ParameterList getParameterList() {
        return parameterList;
    }

    private void setParameterList(ParameterList parameterList) {
        this.parameterList = parameterList;
    }
}
