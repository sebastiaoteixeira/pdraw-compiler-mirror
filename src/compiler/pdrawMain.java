import java.io.IOException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class pdrawMain {
   public static void main(String[] args) {
      try {
         String inputFile = null;
         String outputFile = null;

         for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-o")) {
               if (i + 1 < args.length) {
                  outputFile = args[i + 1];
                  i++;
               }
            } else {
               inputFile = args[i];
            }
         }

         // create a CharStream that reads from standard input:
         CharStream input;
         if (inputFile != null) {
            input = CharStreams.fromFileName(args[0]);
            ErrorHandler.setFile(args[0]);
         } else {
            input = CharStreams.fromStream(System.in);
            ErrorHandler.setFile("stdin");
         }
         // create a lexer that feeds off of input CharStream:
         pdrawLexer lexer = new pdrawLexer(input);
         // replace error listener:
         lexer.removeErrorListeners(); // remove ConsoleErrorListener
         lexer.addErrorListener(new ErrorHandlingListener());
         // create a buffer of tokens pulled from the lexer:
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         // create a parser that feeds off the tokens buffer:
         pdrawParser parser = new pdrawParser(tokens);
         // replace error listener:
         parser.removeErrorListeners(); // remove ConsoleErrorListener
         parser.addErrorListener(new ErrorHandlingListener());
         // begin parsing at program rule:
         ParseTree tree = parser.program();
         // print LISP-style tree:
         // System.out.println(tree.toStringTree(parser));
         semanticVerifier visitor0 = new semanticVerifier();
         // analyze the semantic until be impossible to continue due to errors
         try {
            visitor0.visit(tree);
         } catch (Exception e) {
            // do nothing
         } finally {
            // always execute the error handler
            ErrorHandler.execute();
         }
         if (outputFile == null)
            outputFile = "run.py";
         codeGen visitor1 = new codeGen(outputFile);
         visitor1.visit(tree);
      }
      catch(IOException e) {
         e.printStackTrace();
         System.exit(1);
      }
      catch(RecognitionException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }
}
