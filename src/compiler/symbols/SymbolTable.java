package symbols;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

class ScopeLevel {
    private int level;
    private Symbol function;
    public Map<String, Symbol> symbols = new HashMap<String, Symbol>();

    public ScopeLevel(int level) {
        this.level = level;
        this.function = null;
    }

    ScopeLevel(int level, Symbol function) {
        this.level = level;
        this.function = function;
    }

    public void addSymbol(Symbol symbol) {
        symbols.put(symbol.getIdentifier(), symbol);
    }

    public Symbol getSymbol(String identifier) {
        return symbols.get(identifier);
    }

    public int getLevel() {
        return level;
    }

    public Symbol getFunction() {
        return function;
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

    public void enterScope(Symbol function) {
        stack.push(new ScopeLevel(currentLevel, function));
        currentLevel++;
    }

    public void exitScope() {
        stack.pop();
        currentLevel--;
    }

    public void addSymbol(Symbol symbol) {
        stack.peek().addSymbol(symbol);
    }

    public Symbol getSymbol(String identifier) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            Symbol symbol = stack.get(i).getSymbol(identifier);
            if (symbol != null) {
                return symbol;
            }
        }
        return null;
    }

    public Symbol getCurrentFunction() {
        for (int i = stack.size() - 1; i >= 0; i--) {
            Symbol function = stack.get(i).getFunction();
            if (function != null) {
                return function;
            }
        }
        return null;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
}
