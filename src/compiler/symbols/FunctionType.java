package symbols;

public class FunctionType extends IType {
    private IType returnType;
    private ParameterList parameterList;

    public FunctionType(IType returnType, ParameterList parameterList) {
        setType(Type.FUNCTION);
        setReturnType(returnType);
        setParameterList(parameterList);
    }

    public IType getReturnType() {
        return returnType;
    }

    private void setReturnType(IType returnType) {
        this.returnType = returnType;
    }

    public ParameterList getParameterList() {
        return parameterList;
    }

    private void setParameterList(ParameterList parameterList) {
        this.parameterList = parameterList;
    }
}
