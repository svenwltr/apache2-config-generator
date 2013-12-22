package eu.wltr.a2cg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

public class ApacheConfigGeneratorTest {

	@Test
	public void testExample() throws IOException, JAXBException {
		InputStream in = this.getClass().getClassLoader()
				.getResource("example.xml").openStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		ApacheConfigGenerator a2cg = new ApacheConfigGenerator(in, out);
		a2cg.generate();

		InputStream expected = this.getClass().getClassLoader()
				.getResource("example.conf").openStream();
		StringWriter writer = new StringWriter();
		IOUtils.copy(expected, writer);

		Assert.assertEquals(out.toString(), writer.toString());

	}

}
