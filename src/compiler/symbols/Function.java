package symbols;

import java.util.List;

public class Function extends GenericType {
    private Type returnType;
    private List<Type> parameterTypes;

    public Function(Type returnType, List<Type> parameterTypes) {
        setType(Type.FUNCTION);
        setReturnType(returnType);
        setParameterTypes(parameterTypes);
    }

    public Type getReturnType() {
        return returnType;
    }

    private void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public List<Type> getParameterTypes() {
        return parameterTypes;
    }

    private void setParameterTypes(List<Type> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
}
