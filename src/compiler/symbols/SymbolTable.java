package compiler.symbols;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

class ScopeLevel {
    private int level;
    public Map<StringType, Symbol> symbols = new HashMap<StringType, Symbol>();

    ScopeLevel(int level) {
        this.level = level;
    }

    public void addSymbol(Symbol symbol) {
        symbols.put(symbol.getIdentifier(), symbol);
    }

    public Symbol getSymbol(StringType identifier) {
        return symbols.get(identifier);
    }

    public int getLevel() {
        return level;
    }
}

public class SymbolTable {
    Stack<ScopeLevel> stack = new Stack<ScopeLevel>();
    private int currentLevel = 0;

    public SymbolTable() {
        enterScope(); // global scope
    }

    public void enterScope() {
        stack.push(new ScopeLevel(currentLevel));
        currentLevel++;
    }

    public void exitScope() {
        stack.pop();
        currentLevel--;
    }

    public void addSymbol(Symbol symbol) {
        stack.peek().addSymbol(symbol);
    }

    public Symbol getSymbol(StringType identifier) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            Symbol symbol = stack.get(i).getSymbol(identifier);
            if (symbol != null) {
                return symbol;
            }
        }
        return null;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
}
