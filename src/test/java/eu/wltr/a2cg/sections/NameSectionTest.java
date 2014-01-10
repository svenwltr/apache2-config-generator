package eu.wltr.a2cg.sections;


import org.junit.Before;
import org.junit.Test;


public class NameSectionTest extends AbstractSectionTest {
	
	private NameSection section;

	@Before
	public void setUp() {
		section = new NameSection(printer);

	}

	@Test
	public void testSimple() {
		section.print(host.getName(), host);

		verifyDirective("ServerName", host.getName());

	}

}
