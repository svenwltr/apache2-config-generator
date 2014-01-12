package eu.wltr.a2cg;


import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import eu.wltr.a2cg.schema.Configuration;
import eu.wltr.a2cg.schema.VirtualHost;


public class ApacheConfigGeneratorTest {

	private ConfigPrinter printer;
	private Configuration config;
	private SectionFactory sf;
	private ApacheConfigGenerator a2cg;
	private VirtualHost host;
	private ConfigRootSection<String> rootSection;
	private List<ConfigRootSection<?>> sections;

	@Before
	@SuppressWarnings("unchecked")
	public void setUp() {
		printer = mock(ConfigPrinter.class);
		config = mock(Configuration.class);
		sf = mock(SectionFactory.class);

		a2cg = new ApacheConfigGenerator(printer, config, sf);

		host = mock(VirtualHost.class);
		rootSection = mock(ConfigRootSection.class);
		sections = new ArrayList<ConfigRootSection<?>>();
		
	}

	@Test
	public void testEmptyConfig() {
		a2cg.generate();

		verify(printer, never()).writeDirective(any(String.class),
				any(String.class));
		verify(printer, never()).beginScope(any(String.class),
				any(String.class));

	}

	private void setEmptyHost() {
		when(host.getName()).thenReturn("www.example.com");
		when(config.getHost()).thenReturn(Arrays.asList(host));
		when(sf.getRootSections()).thenReturn(sections);

	}

	@Test
	public void testEmptyHost() {
		setEmptyHost();

		a2cg.generate();

		verify(printer).beginScope("VirtualHost", "*:80");
		verify(printer, never()).writeDirective(any(String.class),
				any(String.class));

	}

	private void setEmptySection() {
		setEmptySection("name");

	}

	private void setEmptySection(String sectionName) {
		sections.add(rootSection);
		setEmptyHost();

		when(rootSection.getSlug()).thenReturn(sectionName);

	}

	@Test
	public void testEmptySection() {
		setEmptySection();

		a2cg.generate();

		verify(printer).beginScope("VirtualHost", "*:80");
		verify(printer, never()).writeDirective(any(String.class),
				any(String.class));

	}

	@Test
	public void testInvalidSection() {
		setEmptySection("invalid");

		a2cg.generate();

		verify(printer).beginScope("VirtualHost", "*:80");
		verify(printer, never()).writeDirective(any(String.class),
				any(String.class));
		verify(rootSection, never()).print(any(String.class),
				any(VirtualHost.class));

	}

	@Test
	public void testRegularSection() {
		setEmptySection();

		when(host.getName()).thenReturn("www.example.com");

		a2cg.generate();

		verify(printer).beginScope("VirtualHost", "*:80");
		verify(rootSection).print(any(String.class),
				any(VirtualHost.class));

	}

}
