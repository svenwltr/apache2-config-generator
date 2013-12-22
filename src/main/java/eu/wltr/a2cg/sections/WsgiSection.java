package eu.wltr.a2cg.sections;

import java.io.File;

import eu.wltr.a2cg.ConfigPrinter;
import eu.wltr.a2cg.ConfigSection;
import eu.wltr.a2cg.schema.VirtualHost;

public class WsgiSection implements ConfigSection<String> {

	private ConfigPrinter printer;

	public WsgiSection(ConfigPrinter printer) {
		this.printer = printer;

	}

	@Override
	public void print(String path, VirtualHost host) {
		File wsgi = new File(path);

		printer.writeDirective("WSGIScriptAlias", "/", wsgi.getAbsolutePath());

		printer.beginScope("Directory", wsgi.getParent());
		printer.writeDirective("Options", "+ExecCGI");
		printer.writeDirective("AddHandler", "wsgi-script", ".wsgi");
		printer.endScope("Directory");

		printer.writeNewline();

	}
}
