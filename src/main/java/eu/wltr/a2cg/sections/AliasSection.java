package eu.wltr.a2cg.sections;

import eu.wltr.a2cg.ConfigPrinter;
import eu.wltr.a2cg.ConfigRootSection;
import eu.wltr.a2cg.schema.ServerAlias;
import eu.wltr.a2cg.schema.VirtualHost;


public class AliasSection implements ConfigRootSection<ServerAlias> {

	private ConfigPrinter printer;

	public AliasSection(ConfigPrinter printer) {
		this.printer = printer;

	}

	@Override
	public String getSlug() {
		return "alias";

	}

	@Override
	public void print(ServerAlias alias, VirtualHost host) {
		if (alias.getValue() != null)
			printer.writeDirective("ServerAlias", alias.getValue());

		printer.writeNewline();

		if (alias.isRedirect() != null && alias.isRedirect()) {
			printer.writeComment("Redirect " + alias.getValue() + " to "
					+ host.getName() + ".");
			printer.writeDirective("RewriteCond", "%{HTTP_HOST}",
					"^" + alias.getValue() + "$");
			printer.writeDirective("RewriteRule", "^",
					"http://" + host.getName() + "%{REQUEST_URI}", "[R=301,L]");
			printer.writeNewline();

		}

	}

}
