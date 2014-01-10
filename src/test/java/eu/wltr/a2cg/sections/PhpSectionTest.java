package eu.wltr.a2cg.sections;


import org.junit.Before;
import org.junit.Test;


public class PhpSectionTest extends AbstractSectionTest {
	
	private PhpSection section;

	@Before
	public void setUp() {
		section = new PhpSection(printer);
	}

	@Test
	public void testSimple() {
		host.setPhp("/var/www/");

		section.print(host.getPhp(), host);

		verifyDirective("DocumentRoot", "/var/www/");;

	}

}
