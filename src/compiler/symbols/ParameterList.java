package symbols;

import java.util.List;

public class ParameterList extends IType{
    private List<Symbol> parameterSymbols;

    public ParameterList() {
        setType(Type.TUPLE);

    }

    public List<Symbol> getParameterSymbols() {
        return parameterSymbols;
    }

    public List<Symbol> add(Symbol parameterSymbol) {
        parameterSymbols.add(parameterSymbol);
        return parameterSymbols;
    }


}
