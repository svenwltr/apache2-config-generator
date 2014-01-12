package eu.wltr.a2cg.schema;


import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class ConfigUnmarshaller {

	private static final ConfigUnmarshaller singleton = new ConfigUnmarshaller();

	public static Configuration load(InputStream in) throws JAXBException {
		return singleton.unmarshall(in);

	}

	private final Unmarshaller unmarshaller;

	private ConfigUnmarshaller() {
		Unmarshaller unmarshaller = null;

		try {
			String ctxPath = this.getClass().getPackage().getName();
			JAXBContext jc = JAXBContext.newInstance(ctxPath);
			unmarshaller = jc.createUnmarshaller();

		} catch (JAXBException e) {
			e.printStackTrace();
			System.exit(255);

		} finally {
			this.unmarshaller = unmarshaller;

		}
	}

	private Configuration unmarshall(InputStream in) throws JAXBException {
		@SuppressWarnings("unchecked")
		JAXBElement<Configuration> el = (JAXBElement<Configuration>) unmarshaller
				.unmarshal(in);
		return el.getValue();

	}

}
