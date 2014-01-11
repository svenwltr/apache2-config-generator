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
		section.print(alias("www.example.com", null), host);

		verify(printer).writeDirective("ServerAlias", "www.example.com");

	}

	@Test
	public void testMissing() {
		section.print(alias(null, null), host);

		verifyNoPrint();

	}

	@Test
	public void testRedirect() {
		section.print(alias("www.example.com", true), host);

		verifyDirective("ServerAlias", "www.example.com");
		verifyDirective("RewriteCond", "%{HTTP_HOST}", "^www.example.com$");
		verifyDirective("RewriteRule", "^", "http://example.com%{REQUEST_URI}",
				"[R=301,L]");

	}

	@Test
	public void testNoRedirect() {
		section.print(alias("www.example.com", false), host);

		verifyDirective("ServerAlias", "www.example.com");

	}

}
