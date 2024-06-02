import org.antlr.v4.runtime.*;

public class ErrorHandlingListener extends BaseErrorListener {
    @Override 
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, 
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {
        if (recognizer instanceof Parser) {
            // Get the input stream as a CharStream of the token before the error
            CharStream input = ((Parser) recognizer).getInputStream().getTokenSource().getInputStream();

            ErrorHandler.error(
                msg,
                line,
                charPositionInLine,
                input.toString().split("\n")[line - 1],
                ((Parser) recognizer).getInputStream().LT(1).getText().length() - 1
            );
        } else if (recognizer instanceof Lexer) {
            // Get the input stream as a CharStream
            CharStream input = ((Lexer) recognizer).getInputStream();

            ErrorHandler.error(
                msg,
                line,
                charPositionInLine,
                input.toString().split("\n")[line - 1],
                0
            );
        }

    }
}