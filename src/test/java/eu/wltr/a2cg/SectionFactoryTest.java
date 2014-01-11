package eu.wltr.a2cg;


import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import eu.wltr.a2cg.sections.AliasSection;
import eu.wltr.a2cg.sections.LocationSection;
import eu.wltr.a2cg.sections.NameSection;
import eu.wltr.a2cg.sections.PhpSection;
import eu.wltr.a2cg.sections.ProxySection;
import eu.wltr.a2cg.sections.StaticSection;
import eu.wltr.a2cg.sections.WsgiSection;


public class SectionFactoryTest {

	public SectionFactory sf;

	@Before
	public void setUp() {
		ConfigPrinter printer = Mockito.mock(ConfigPrinter.class);
		sf = new SectionFactory(printer);

	}

	@Test
	public void testKeys() {
		Iterable<String> keys = sf.getKeys();
		@SuppressWarnings("unchecked")
		List<String> list = IteratorUtils.toList(keys.iterator());

		Assert.assertEquals(list.size(), 7);
		Assert.assertEquals(list.get(0), "name");
		Assert.assertEquals(list.get(1), "alias");
		Assert.assertEquals(list.get(6), "location");

	}

	@Test
	public void testSections() {
		Assert.assertEquals(sf.get("name").getClass(), NameSection.class);
		Assert.assertEquals(sf.get("alias").getClass(), AliasSection.class);
		Assert.assertEquals(sf.get("php").getClass(), PhpSection.class);
		Assert.assertEquals(sf.get("wsgi").getClass(), WsgiSection.class);
		Assert.assertEquals(sf.get("proxy").getClass(), ProxySection.class);
		Assert.assertEquals(sf.get("static").getClass(), StaticSection.class);
		Assert.assertEquals(sf.get("location").getClass(),
				LocationSection.class);

	}

}
