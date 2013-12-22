package eu.wltr.a2cg;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Stack;

public class ConfigPrinter {

	private final PrintStream writer;
	private final Stack<String> scopeStack;

	public static final String INDENT = "    ";

	public ConfigPrinter(OutputStream out) {
		this.writer = new PrintStream(out);
		scopeStack = new Stack<String>();

	}

	public void writeIndent() {
		for (int i = 0; i < scopeStack.size(); i++)
			writer.print(INDENT);

	}

	private String escape(String args) {
		if (args.contains(" "))
			args = "\"" + args + "\"";

		return args;

	}

	public void beginScope(String name, String args) {
		writeIndent();
		writer.print("<");
		writer.print(name);
		writer.print(" ");
		writer.print(escape(args));
		writer.print(">");
		writer.println();

		scopeStack.push(name);

	}

	public void endScope(String name) {
		if (!scopeStack.peek().equals(name))
			throw new IllegalArgumentException("Attempt to close '" + name
					+ "' scope, but current scope would be "
					+ scopeStack.peek() + ".");

		scopeStack.pop();

		writeIndent();
		writer.print("</");
		writer.print(name);
		writer.print(">");
		writer.println();
		writer.println();

	}

	public void writeComment(String comment) {

		if (comment.contains("\n") || comment.contains("\r"))
			throw new IllegalArgumentException(
					"Multiline comments are not allowed.");

		writeIndent();
		writer.print("# ");
		writer.print(comment);
		writer.println();

	}

	public void writeDirective(String name, String... args) {
		writeIndent();

		writer.print(name);

		for (String arg : args) {
			writer.print(" ");
			writer.print(escape(arg));
		}

		writer.println();

	}

	public void writeNewline() {
		writer.println();

	}

}
