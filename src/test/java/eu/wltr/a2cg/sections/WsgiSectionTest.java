package eu.wltr.a2cg.sections;

import org.junit.Before;
import org.junit.Test;

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

		assertOutputEquals("%s%n%s%n%s%n%s%n%s%n%n%n",
				"WSGIScriptAlias / /var/www/website.wsgi",
				"<Directory /var/www>",
				"    Options +ExecCGI",
				"    AddHandler wsgi-script .wsgi",
				"</Directory>");

	}

}
