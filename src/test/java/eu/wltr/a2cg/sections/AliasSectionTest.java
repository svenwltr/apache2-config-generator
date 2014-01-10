package eu.wltr.a2cg.sections;


import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import eu.wltr.a2cg.schema.ObjectFactory;
import eu.wltr.a2cg.schema.ServerAlias;


public class AliasSectionTest extends AbstractSectionTest {

	private AliasSection section;

	@Before
	public void setUp() {
		section = new AliasSection(printer);

	}

	private ServerAlias alias(String value, Boolean redirect) {
		ServerAlias alias = new ObjectFactory().createServerAlias();
		alias.setRedirect(redirect);
		alias.setValue(value);

		return alias;

	}

	@Test
	public void testSimple() {
		host.getAlias().add(alias("www.example.com", null));
		section.print(host.getAlias(), host);

		verify(printer).writeDirective("ServerAlias", "www.example.com");

	}

	@Test
	public void testEmpty() {
		section.print(host.getAlias(), host);

		verifyNoPrint();

	}

	@Test
	public void testMissing() {
		host.getAlias().add(alias(null, null));
		section.print(host.getAlias(), host);

		verifyNoPrint();

	}

	@Test
	public void testRedirect() {
		host.getAlias().add(alias("www.example.com", true));
		section.print(host.getAlias(), host);

		verifyDirective("ServerAlias", "www.example.com");
		verifyDirective("RewriteCond", "%{HTTP_HOST}", "^www.example.com$");
		verifyDirective("RewriteRule", "^", "http://example.com%{REQUEST_URI}",
				"[R=301,L]");

	}

	@Test
	public void testNoRedirect() {
		host.getAlias().add(alias("www.example.com", false));
		section.print(host.getAlias(), host);

		verifyDirective("ServerAlias", "www.example.com");

	}

}
