package eu.wltr.a2cg.sections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

public class WsgiSectionTest extends AbstractSectionTest {
	
	private WsgiSection section;

	@Before
	public void setUp() {
		section = new WsgiSection(printer);

	}

	@Test
	public void testSimple() {
		host.setWsgi("/var/www/website.wsgi");
		section.print(host.getWsgi(), host);

		verifyDirective("WSGIScriptAlias", "/", "/var/www/website.wsgi");

		InOrder inOrder = verifyBeginScope("Directory", "/var/www");
		verifyDirective(inOrder, "Options", "+ExecCGI");
		verifyDirective(inOrder, "AddHandler", "wsgi-script", ".wsgi");
		verifyEndScope(inOrder, "Directory");

	}

}
