package symbols;

import java.util.List;
import java.util.ArrayList;

public class ParameterList extends IType{
    private List<Symbol> parameterSymbols;

    public ParameterList() {
        setType(Type.TUPLE);
        parameterSymbols = new ArrayList<>();
    }

    public List<Symbol> getParameterSymbols() {
        return parameterSymbols;
    }

    public void add(Symbol parameterSymbol) {
        parameterSymbols.add(parameterSymbol);
    }


}
