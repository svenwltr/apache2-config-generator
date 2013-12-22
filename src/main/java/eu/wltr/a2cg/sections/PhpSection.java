package eu.wltr.a2cg.sections;

import eu.wltr.a2cg.ConfigPrinter;
import eu.wltr.a2cg.ConfigSection;
import eu.wltr.a2cg.schema.VirtualHost;

public class PhpSection implements ConfigSection<String> {

	private ConfigPrinter printer;

	public PhpSection(ConfigPrinter printer) {
		this.printer = printer;

	}

	@Override
	public void print(String path, VirtualHost host) {
		printer.writeDirective("DocumentRoot", path);
		printer.writeNewline();

	}
}
