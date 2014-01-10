package eu.wltr.a2cg.sections;

import java.util.List;

import eu.wltr.a2cg.ConfigPrinter;
import eu.wltr.a2cg.ConfigSection;
import eu.wltr.a2cg.SectionFactory;
import eu.wltr.a2cg.schema.Location;
import eu.wltr.a2cg.schema.VirtualHost;

public class LocationSection implements ConfigSection<List<Location>> {

	private final ConfigPrinter printer;
	private final SectionFactory sf;

	public LocationSection(SectionFactory sf,
			ConfigPrinter printer) {
		this.sf = sf;
		this.printer = printer;

	}

	@Override
	public void print(List<Location> location, VirtualHost host) {

	}

}
