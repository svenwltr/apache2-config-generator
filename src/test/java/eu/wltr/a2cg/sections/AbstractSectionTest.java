package eu.wltr.a2cg.sections;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;

import eu.wltr.a2cg.ConfigPrinter;
import eu.wltr.a2cg.schema.ObjectFactory;
import eu.wltr.a2cg.schema.VirtualHost;

public class AbstractSectionTest {

	private ByteArrayOutputStream out;
	protected ConfigPrinter printer;
	protected VirtualHost host;

	public AbstractSectionTest() {
		out = new ByteArrayOutputStream();
		PrintStream writer = new PrintStream(out, true);
		printer = new ConfigPrinter(writer);
		host = new ObjectFactory().createVirtualHost();
		host.setName("example.com");

	}

	protected void assertOutputEquals(String format, Object... args) {
		String expected = String.format(format, args);
		Assert.assertEquals(expected, out.toString());

	}

}
