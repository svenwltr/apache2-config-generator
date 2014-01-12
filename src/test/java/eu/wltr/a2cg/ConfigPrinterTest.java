package eu.wltr.a2cg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConfigPrinterTest {

	private ByteArrayOutputStream out;
	private ConfigPrinter printer;

	@Before
	public void setUp() {
		out = new ByteArrayOutputStream();
		PrintStream writer = new PrintStream(out, true);
		printer = new ConfigPrinter(writer);

	}

	@Test
	public void testScopeSimple() throws IOException {
		printer.beginScope("VirtualHost", "*:80");
		printer.endScope("VirtualHost");

		String expected = String.format("%s%n%s%n%n",
				"<VirtualHost *:80>",
				"</VirtualHost>");
		Assert.assertEquals(expected, out.toString());

	}

	@Test
	public void testScopeEscape() {
		printer.beginScope("Directory", "/tmp/path with spaces/");
		printer.endScope("Directory");

		String expected = String.format("%s%n%s%n%n",
				"<Directory \"/tmp/path with spaces/\">",
				"</Directory>");
		Assert.assertEquals(expected, out.toString());

	}

	@Test
	public void testScopeStacked() {
		printer.beginScope("VirtualHost", "*:80");
		printer.beginScope("Directory", "/tmp/");
		printer.endScope("Directory");
		printer.endScope("VirtualHost");

		String expected = String.format("%s%n%s%n%s%n%n%s%n%n",
				"<VirtualHost *:80>",
				"    <Directory /tmp/>",
				"    </Directory>",
				"</VirtualHost>");
		Assert.assertEquals(expected, out.toString());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testScopeWrongOrder() {
		printer.beginScope("VirtualHost", "*:80");
		printer.beginScope("Directory", "/tmp/");
		printer.endScope("VirtualHost");
		printer.endScope("Directory");

	}

	@Test
	public void testComment() {
		printer.writeComment("This is a test.");

		String expected = String.format("%s%n",
				"# This is a test.");
		Assert.assertEquals(expected, out.toString());

	}

	@Test
	public void testCommentIndent() {
		printer.beginScope("VirtualHost", "*:80");
		printer.writeComment("This is a test.");
		printer.endScope("VirtualHost");

		String expected = String.format("%s%n%s%n%s%n%n",
				"<VirtualHost *:80>",
				"    # This is a test.",
				"</VirtualHost>");
		Assert.assertEquals(expected, out.toString());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testCommentMultiline() {
		printer.writeComment(String.format("%s%n%s", "Line 1", "Line 2"));

	}

	@Test
	public void testDirectiveSimple() {
		printer.writeDirective("ServerName", "example.com");

		String expected = String.format("%s%n",
				"ServerName example.com");
		Assert.assertEquals(expected, out.toString());

	}

	@Test
	public void testDirectiveEscape() {
		printer.writeDirective("DocumentRoot", "/var/www/my site/");

		String expected = String.format("%s%n",
				"DocumentRoot \"/var/www/my site/\"");
		Assert.assertEquals(expected, out.toString());

	}

	@Test
	public void testDirectiveIndent() {
		printer.beginScope("VirtualHost", "*:80");
		printer.writeDirective("ServerAlias", "www.example.com");
		printer.endScope("VirtualHost");

		String expected = String.format("%s%n%s%n%s%n%n",
				"<VirtualHost *:80>",
				"    ServerAlias www.example.com",
				"</VirtualHost>");
		Assert.assertEquals(expected, out.toString());

	}

	@Test
	public void testDirectiveMultiArgs() {
		printer.writeDirective("DirectoryIndex", "index.html", "index.php");

		String expected = String.format("%s%n",
				"DirectoryIndex index.html index.php");
		Assert.assertEquals(expected, out.toString());

	}

	@Test
	public void testDirectiveMultiArgsEscape() {
		printer.writeDirective("CustomLog", "/var/log/my site/access.log",
				"combined");

		String expected = String.format("%s%n",
				"CustomLog \"/var/log/my site/access.log\" combined");
		Assert.assertEquals(expected, out.toString());

	}
}