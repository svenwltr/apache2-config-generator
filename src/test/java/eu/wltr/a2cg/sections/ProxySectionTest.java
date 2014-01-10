package eu.wltr.a2cg.sections;


import org.junit.Before;
import org.junit.Test;


public class ProxySectionTest extends AbstractSectionTest {
	
	private ProxySection section;

	@Before
	public void setUp() {
		section = new ProxySection(printer);
	}

	@Test
	public void testSimple() {
		host.setProxy("www.example.net");
		section.print(host.getProxy(), host);

		verifyDirective("ProxyPass", "/", "www.example.net");
		verifyDirective("ProxyPassReverse", "/", "www.example.net");

	}

}
