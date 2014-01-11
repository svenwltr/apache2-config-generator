package eu.wltr.a2cg.sections;


import java.util.List;
import java.util.Map;

import eu.wltr.a2cg.ConfigPrinter;
import eu.wltr.a2cg.ConfigRootSection;
import eu.wltr.a2cg.ConfigSubSection;
import eu.wltr.a2cg.SectionFactory;
import eu.wltr.a2cg.Utils;
import eu.wltr.a2cg.schema.Location;
import eu.wltr.a2cg.schema.VirtualHost;


public class LocationSection implements ConfigRootSection<Location> {

	private final SectionFactory sf;

	public LocationSection(SectionFactory sf, ConfigPrinter printer) {
		this.sf = sf;

	}

	@Override
	public String getSlug() {
		return "location";

	}

	@Override
	public void print(Location location, VirtualHost host) {
		Map<String, Object> elements = Utils.getBeanProperties(location);

		for (ConfigSubSection section : sf.getSubSections()) {
			Object value = elements.get(section.getSlug());

			if (value == null)
				continue;

			if (List.class.isAssignableFrom(value.getClass()))
				for (Object item : (List<Object>) value)
					section.print(item, location);

			else
				section.print(value, location);

		}

	}

}
