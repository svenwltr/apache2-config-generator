package eu.wltr.a2cg.sections;

import eu.wltr.a2cg.ConfigPrinter;
import eu.wltr.a2cg.ConfigRootSection;
import eu.wltr.a2cg.ConfigSubSection;
import eu.wltr.a2cg.schema.Location;
import eu.wltr.a2cg.schema.Static;
import eu.wltr.a2cg.schema.VirtualHost;


public class StaticSection implements ConfigRootSection<Static>,
		ConfigSubSection<Static> {

	private ConfigPrinter printer;

	public StaticSection(ConfigPrinter printer) {
		this.printer = printer;

	}

	@Override
	public String getSlug() {
		return "static";

	}

	@Override
	public void print(Static list, VirtualHost host) {
		printer.writeDirective("DocumentRoot", list.getValue());
		printer.writeNewline();

		if (list.isIndex() != null && list.isIndex() || list.isDav() != null
				&& list.isDav()) {
			printer.beginScope("Directory", list.getValue());

			if (list.isIndex() != null && list.isIndex())
				printer.writeDirective("Options", "+Indexes", "+MultiViews");

			if (list.isDav() != null && list.isDav())
				printer.writeDirective("DAV", "On");

			printer.endScope("Directory");

		}

	}

	@Override
	public void print(Static static_, Location location) {
		printer.writeDirective("Alias", location.getPath(), static_.getValue());
		printer.writeNewline();

	}
}
