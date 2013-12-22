package eu.wltr.a2cg.schema;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ConfigUnmarshaller {

	private static final ConfigUnmarshaller singleton = new ConfigUnmarshaller();

	public static Configuration load(File file) throws JAXBException {
		return singleton.unmarshall(file);

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

	private Configuration unmarshall(File file) throws JAXBException {
		@SuppressWarnings("unchecked")
		JAXBElement<Configuration> el = (JAXBElement<Configuration>) unmarshaller.unmarshal(file);
		return el.getValue();

	}

}
