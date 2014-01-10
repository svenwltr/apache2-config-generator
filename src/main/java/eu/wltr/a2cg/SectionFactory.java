package eu.wltr.a2cg;

import java.util.LinkedHashMap;

import eu.wltr.a2cg.sections.AliasSection;
import eu.wltr.a2cg.sections.LocationSection;
import eu.wltr.a2cg.sections.NameSection;
import eu.wltr.a2cg.sections.PhpSection;
import eu.wltr.a2cg.sections.ProxySection;
import eu.wltr.a2cg.sections.StaticSection;
import eu.wltr.a2cg.sections.WsgiSection;

public class SectionFactory {

	private final LinkedHashMap<String, ConfigSection<?>> sections;

	public SectionFactory(ConfigPrinter printer) {
		sections = new LinkedHashMap<String, ConfigSection<?>>();

		sections.put("name", new NameSection(printer));
		sections.put("alias", new AliasSection(printer));
		sections.put("php", new PhpSection(printer));
		sections.put("wsgi", new WsgiSection(printer));
		sections.put("proxy", new ProxySection(printer));
		sections.put("static", new StaticSection(printer));
		sections.put("location", new LocationSection(this, printer));

	}

	public Iterable<String> getKeys() {
		return sections.keySet();

	}

	public ConfigSection<?> get(String key) {
		return sections.get(key);

	}

}
