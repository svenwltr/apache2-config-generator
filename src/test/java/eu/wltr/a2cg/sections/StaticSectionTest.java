package eu.wltr.a2cg.sections;

import org.junit.Before;
import org.junit.Test;

import eu.wltr.a2cg.schema.ObjectFactory;
import eu.wltr.a2cg.schema.Static;

public class StaticSectionTest extends AbstractSectionTest {
	
	private StaticSection section;

	@Before
	public void setUp() {
		section = new StaticSection(printer);

	}

	private Static createStatic(String value, Boolean dav, Boolean index) {
		Static s = new ObjectFactory().createStatic();
		s.setDav(dav);
		s.setIndex(index);
		s.setValue(value);

		return s;

	}

	@Test
	public void testSimple() {
		host.setStatic(createStatic("/var/www/", null, null));
		section.print(host.getStatic(), host);

		assertOutputEquals("%s%n%n",
				"DocumentRoot /var/www/");

	}

	@Test
	public void testDav() {
		host.setStatic(createStatic("/var/www/", true, null));
		section.print(host.getStatic(), host);

		assertOutputEquals("%s%n%n%s%n%s%n%s%n%n",
				"DocumentRoot /var/www/",
				"<Directory /var/www/>",
				"    DAV On",
				"</Directory>");

	}

	@Test
	public void testIndex() {
		host.setStatic(createStatic("/var/www/", null, true));
		section.print(host.getStatic(), host);

		assertOutputEquals("%s%n%n%s%n%s%n%s%n%n",
				"DocumentRoot /var/www/",
				"<Directory /var/www/>",
				"    Options +Indexes +MultiViews",
				"</Directory>");

	}

}
