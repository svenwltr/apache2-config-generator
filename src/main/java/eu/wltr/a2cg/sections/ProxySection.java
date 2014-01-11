package eu.wltr.a2cg.sections;

import eu.wltr.a2cg.ConfigPrinter;
import eu.wltr.a2cg.ConfigRootSection;
import eu.wltr.a2cg.schema.VirtualHost;

public class ProxySection implements ConfigRootSection<String> {

	private ConfigPrinter printer;

	public ProxySection(ConfigPrinter printer) {
		this.printer = printer;

	}

	@Override
	public String getSlug() {
		return "proxy";

	}

	@Override
	public void print(String data, VirtualHost host) {
		printer.writeComment("Proxy to " + data);
		printer.writeDirective("ProxyPass", "/", data);
		printer.writeDirective("ProxyPassReverse", "/", data);
		printer.writeNewline();

	}

}
