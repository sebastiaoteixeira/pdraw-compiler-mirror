import java.io.PrintStream;
import org.antlr.v4.runtime.ParserRuleContext;

public class ErrorHandler {
	private static boolean success = true;
	private static String file = "";

	public static void error(String msg, int line, int column) {
		success = false;
		System.out.println("\033[31m" + file + ":" + line + ":" + column + ": error: " + msg + "\033[0m");
	}

	public static void warning(String msg, int line, int column) {
		System.out.println("\033[33m" + file + ":" + line + ":" + column + ": warning: " + msg + "\033[0m");

	}

	public static void info(String msg, int line, int column) {
		System.out.println("\033[37m" + file + ":" + line + ":" + column + ": info: " + msg + "\033[0m");
	}

	public static void error(String msg, ParserRuleContext ctx) {
		error(msg, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
	}

	public static void warning(String msg, ParserRuleContext ctx) {
		warning(msg, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
	}

	public static void info(String msg, ParserRuleContext ctx) {
		info(msg, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
	}

	public static void execute() {
		if (!success) {
			System.exit(1);
		}
	}

	public static void setFile(String file) {
		ErrorHandler.file = file;
	}
}
