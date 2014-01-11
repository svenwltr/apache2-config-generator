package eu.wltr.a2cg.sections;

import eu.wltr.a2cg.ConfigPrinter;
import eu.wltr.a2cg.ConfigRootSection;
import eu.wltr.a2cg.schema.VirtualHost;

public class PhpSection implements ConfigRootSection<String> {

	private ConfigPrinter printer;

	public PhpSection(ConfigPrinter printer) {
		this.printer = printer;

	}

	@Override
	public String getSlug() {
		return "php";

	}

	@Override
	public void print(String path, VirtualHost host) {
		printer.writeDirective("DocumentRoot", path);
		printer.writeNewline();

	}
}
