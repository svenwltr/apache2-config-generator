package eu.wltr.a2cg;


import org.junit.Before;
import org.mockito.Mockito;


public class SectionFactoryTest {

	public SectionFactory sf;

	@Before
	public void setUp() {
		ConfigPrinter printer = Mockito.mock(ConfigPrinter.class);
		sf = new SectionFactory(printer);

	}

}
