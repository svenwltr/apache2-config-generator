package eu.wltr.a2cg.sections;

import eu.wltr.a2cg.ConfigPrinter;
import eu.wltr.a2cg.ConfigSection;
import eu.wltr.a2cg.schema.DirList;
import eu.wltr.a2cg.schema.VirtualHost;

public class DirListSection implements ConfigSection<DirList> {

	private ConfigPrinter printer;

	public DirListSection(ConfigPrinter printer) {
		this.printer = printer;

	}

	@Override
	public void print(DirList list, VirtualHost host) {
		printer.writeDirective("DocumentRoot", list.getValue());
		printer.writeNewline();

		printer.beginScope("Directory", list.getValue());
		printer.writeDirective("Options", "+Indexes", "+MultiViews");

		if (list.isDav() != null && list.isDav())
			printer.writeDirective("DAV", "On");

		printer.endScope("Directory");

	}
}
