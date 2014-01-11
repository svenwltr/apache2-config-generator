package eu.wltr.a2cg;


import java.util.ArrayList;
import java.util.List;

import eu.wltr.a2cg.sections.AliasSection;
import eu.wltr.a2cg.sections.LocationSection;
import eu.wltr.a2cg.sections.NameSection;
import eu.wltr.a2cg.sections.PhpSection;
import eu.wltr.a2cg.sections.ProxySection;
import eu.wltr.a2cg.sections.StaticSection;
import eu.wltr.a2cg.sections.WsgiSection;

public class SectionFactory {

	private final List<ConfigSection> sections;

	public SectionFactory(ConfigPrinter printer) {
		sections = new ArrayList<ConfigSection>();

		sections.add(new NameSection(printer));
		sections.add(new AliasSection(printer));
		sections.add(new PhpSection(printer));
		sections.add(new WsgiSection(printer));
		sections.add(new ProxySection(printer));
		sections.add(new StaticSection(printer));
		sections.add(new LocationSection(this, printer));

	}

	public Iterable<ConfigRootSection<?>> getRootSections() {
		List<ConfigRootSection<?>> list = new ArrayList<ConfigRootSection<?>>();

		for (ConfigSection section : sections)
			if (section instanceof ConfigRootSection<?>)
				list.add((ConfigRootSection<?>) section);

		return list;

	}

	public Iterable<ConfigSubSection<?>> getSubSections() {
		List<ConfigSubSection<?>> list = new ArrayList<ConfigSubSection<?>>();

		for (ConfigSection section : sections)
			if (section instanceof ConfigSubSection<?>)
				list.add((ConfigSubSection<?>) section);

		return list;

	}

}
