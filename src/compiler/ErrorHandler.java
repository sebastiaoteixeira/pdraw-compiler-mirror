import java.io.PrintStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Interval;
import symbols.Symbol;

public class ErrorHandler {
	private static boolean success = true;
	private static String file = "";
	private static Symbol lastScope = null;
	private static PrintStream out = System.out;

	private static final String RESET = "\033[0m";

	private static int counters[] = {0, 0, 0};

	private static final String colors[] = {
		"\033[37m",
		"\033[33m",
		"\033[31m"
	};

	private static final String[] messageTypes = {
		colors[0] + "info: " + RESET,
		colors[1] + "warning: " + RESET,
		colors[2] + "error: " + RESET
	};

	public static void error(String msg, int line, int column) {
		success = false;
		printMessage(2, msg, line, column);
	}

	public static void warning(String msg, int line, int column) {
		printMessage(1, msg, line, column);
	}

	public static void info(String msg, int line, int column) {
		printMessage(0, msg, line, column);
	}

	public static void error(String msg, int lineN, int column, String line, int length) {
		success = false;
		printExtendedMessage(2, msg, lineN, column, line, length);
	}

	public static void warning(String msg, int lineN, int column, String line, int length) {
		printExtendedMessage(1, msg, lineN, column, line, length);
	}

	public static void info(String msg, int lineN, int column, String line, int length) {
		printExtendedMessage(0, msg, lineN, column, line, length);
	}

	public static void error(String msg, ParserRuleContext ctx) {
		error(
			msg,
			ctx.getStart().getLine(),
			ctx.getStart().getCharPositionInLine(),
			extractLineFromContext(ctx),
			ctx.getStop().getCharPositionInLine() - ctx.getStart().getCharPositionInLine()
			);
	}

	public static void warning(String msg, ParserRuleContext ctx) {
		warning(
			msg,
			ctx.getStart().getLine(),
			ctx.getStart().getCharPositionInLine(),
			extractLineFromContext(ctx),
			ctx.getStop().getCharPositionInLine() - ctx.getStart().getCharPositionInLine()
		);
	}

	public static void info(String msg, ParserRuleContext ctx) {
		info(
			msg,
			ctx.getStart().getLine(),
			ctx.getStart().getCharPositionInLine(),
			extractLineFromContext(ctx),
			ctx.getStop().getCharPositionInLine() - ctx.getStart().getCharPositionInLine()
		);
	}

	public static void error(String msg, ParserRuleContext ctx, Symbol scope) {
		if (scope != null) {
			printScope(ctx, scope);
		}
		error(
			msg,
			ctx.getStart().getLine(),
			ctx.getStart().getCharPositionInLine(),
			extractLineFromContext(ctx),
			ctx.getStop().getCharPositionInLine() - ctx.getStart().getCharPositionInLine()
			);
	}

	public static void warning(String msg, ParserRuleContext ctx, Symbol scope) {
		if (scope != null) {
			printScope(ctx, scope);
		}
		warning(
			msg,
			ctx.getStart().getLine(),
			ctx.getStart().getCharPositionInLine(),
			extractLineFromContext(ctx),
			ctx.getStop().getCharPositionInLine() - ctx.getStart().getCharPositionInLine()
		);
	}

	public static void info(String msg, ParserRuleContext ctx, Symbol scope) {
		if (scope != null) {
			printScope(ctx, scope);
		}
		info(
			msg,
			ctx.getStart().getLine(),
			ctx.getStart().getCharPositionInLine(),
			extractLineFromContext(ctx),
			ctx.getStop().getCharPositionInLine() - ctx.getStart().getCharPositionInLine()
		);
	}

	public static void error(String msg, ParserRuleContext ctx, Symbol scope, Symbol symbolToPrint) {
		if (scope != null) {
			printScope(ctx, scope);
		}

		int line = symbolToPrint.getCodeLocation().getStartLine();
		int column = symbolToPrint.getCodeLocation().getStartColumn();
		int length = symbolToPrint.getCodeLocation().getEndColumn() - column + 2;
		
		error(
			msg,
			line,
			column,
			extractLineFromContext(ctx, line),
			length
			);
	}

	public static void warning(String msg, ParserRuleContext ctx, Symbol scope, Symbol symbolToPrint) {
		if (scope != null) {
			printScope(ctx, scope);
		}

		int line = symbolToPrint.getCodeLocation().getStartLine();
		int column = symbolToPrint.getCodeLocation().getStartColumn();
		int length = symbolToPrint.getCodeLocation().getEndColumn() - column + 2;

		warning(
			msg,
			line,
			column,
			extractLineFromContext(ctx, line),
			length
		);
	}

	public static void info(String msg, ParserRuleContext ctx, Symbol scope, Symbol symbolToPrint) {
		if (scope != null) {
			printScope(ctx, scope);
		}

		int line = symbolToPrint.getCodeLocation().getStartLine();
		int column = symbolToPrint.getCodeLocation().getStartColumn();
		int length = symbolToPrint.getCodeLocation().getEndColumn() - column + 2;

		info(
			msg,
			line,
			column,
			extractLineFromContext(ctx, line),
			length
		);
	}

	public static void printScope(ParserRuleContext ctx, Symbol scope) {
		String functionName = scope.getIdentifier();

		if (lastScope != null && lastScope.getIdentifier().equals(functionName)) {
			return;
		}

		out.println("\033[1m" + file + RESET + " In function ‘\033[1m" + functionName + RESET + "’:");

		lastScope = scope;
	}

	public static void printMessage(int type, String msg, int line, int column) {
		counters[type]++;
		out.println("\033[1m" + file + ":" + line + ":" + column + ": " + messageTypes[type] + msg);
	}

	public static void printExtendedMessage(int type, String msg, int lineN, int column, String line, int length) {
		printMessage(type, msg, lineN, column);

		if (length > line.length() - column) {
			length = line.length() - column;
		}

		// print the line with the error with the line number
		out.printf("%5d | %s\n", lineN, line);
		// print the error position with a caret
		out.printf("      | %s\n", " ".repeat(column) + colors[type] + "^" + "~".repeat(length) + RESET);
	}

	public static String extractLineFromContext(ParserRuleContext ctx) {
        // Get the entire current line from the input stream
		String result = ctx.getStart().getInputStream().toString().split("\n")[ctx.getStart().getLine() - 1];
		return result;
    }

	public static String extractLineFromContext(ParserRuleContext ctx, int line) {
        // Get the entire current line from the input stream
		String result = ctx.getStart().getInputStream().toString().split("\n")[line - 1];
		return result;
    }

	public static void execute() {
		for (int i = 2; i >= 0; i--) {
			if (counters[i] > 0) {
				out.println(messageTypes[i] + counters[i]);
			}
		}
		
		if (!success) {
			System.exit(1);
		}
	}

	public static void setFile(String file) {
		ErrorHandler.file = file;
	}
}
