package eu.wltr.a2cg.sections;

import java.util.List;

import eu.wltr.a2cg.ConfigPrinter;
import eu.wltr.a2cg.ConfigSection;
import eu.wltr.a2cg.schema.ServerAlias;
import eu.wltr.a2cg.schema.VirtualHost;

public class AliasSection implements ConfigSection<List<ServerAlias>> {

	private ConfigPrinter printer;

	public AliasSection(ConfigPrinter printer) {
		this.printer = printer;

	}

	@Override
	public void print(List<ServerAlias> aliases, VirtualHost host) {

		if (aliases.size() <= 0)
			return;

		for (ServerAlias alias : aliases)
			if (alias.getValue() != null)
				printer.writeDirective("ServerAlias", alias.getValue());

		printer.writeNewline();

		for (ServerAlias alias : aliases)
			if (alias.isRedirect() != null && alias.isRedirect()) {
				printer.writeComment("Redirect " + alias.getValue() + " to "
						+ host.getName() + ".");
				printer.writeDirective("RewriteCond", "%{HTTP_HOST}", "^"
						+ alias.getValue() + "$");
				printer.writeDirective("RewriteRule", "^",
						"http://" + host.getName() + "%{REQUEST_URI}",
						"[R=301,L]");
				printer.writeNewline();

			}

	}

}
