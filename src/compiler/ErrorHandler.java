package compiler;

public class ErrorHandler {
	public static boolean success = true;

	public static void error(String file, String msg, int line, int column) {
		success = false;
		System.out.println("\033[31m" + file + ":" + line + ":" + column + ": error: " + msg + "\033[0m");
	}

	public static void warning(String file, String msg, int line, int column) {
		System.out.println("\033[33m" + file + ":" + line + ":" + column + ": warning: " + msg + "\033[0m");

	}

	public static void info(String file, String msg, int line, int column) {
		System.out.println("\033[37m" + file + ":" + line + ":" + column + ": info: " + msg + "\033[0m");
	}

	public static void execute() {
		if (!success) {
			System.exit(1);
		}
	}
}
