package eu.wltr.a2cg.sections;


import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.mockito.InOrder;

import eu.wltr.a2cg.ConfigPrinter;
import eu.wltr.a2cg.schema.ObjectFactory;
import eu.wltr.a2cg.schema.VirtualHost;

public class AbstractSectionTest {

	protected final VirtualHost host;
	protected final ConfigPrinter printer;

	public AbstractSectionTest() {
		printer = mock(ConfigPrinter.class);

		host = new ObjectFactory().createVirtualHost();
		host.setName("example.com");

	}

	public void verifyNoPrint() {
		verify(printer, never()).writeDirective(any(String.class),
				any(String.class));
		verify(printer, never()).writeComment(any(String.class));

	}

	public void verifyDirective(String name, String... args) {
		verify(printer).writeDirective(name, args);

	}

	public void verifyDirective(InOrder inOrder, String name, String... args) {
		inOrder.verify(printer).writeDirective(name, args);

	}

	public InOrder verifyBeginScope(String name, String args) {
		InOrder inOrder = inOrder(printer);
		inOrder.verify(printer).beginScope(name, args);

		return inOrder;
	}

	public void verifyEndScope(InOrder inOrder, String name) {
		inOrder.verify(printer).endScope(name);

	}

}
