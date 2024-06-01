import org.antlr.v4.runtime.*;


public class ErrorHandlingListener extends BaseErrorListener {
    @Override public void syntaxError(Recognizer<?, ?> recognizer,
          Object offendingSymbol,
          int line, int charPositionInLine,
          String msg,
          RecognitionException e)
    {
       ErrorHandler.error(msg, line, charPositionInLine);
    }
 }