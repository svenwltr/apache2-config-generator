package eu.wltr.a2cg.sections;

import eu.wltr.a2cg.ConfigPrinter;
import eu.wltr.a2cg.ConfigRootSection;
import eu.wltr.a2cg.schema.VirtualHost;

public class NameSection implements ConfigRootSection<String> {

	private ConfigPrinter printer;

	public NameSection(ConfigPrinter printer) {
		this.printer = printer;

	}

	@Override
	public String getSlug() {
		return "name";

	}

	@Override
	public void print(String data, VirtualHost host) {
		printer.writeDirective("ServerName", data);
		printer.writeNewline();

	}
}
