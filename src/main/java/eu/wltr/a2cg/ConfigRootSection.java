package eu.wltr.a2cg;

import eu.wltr.a2cg.schema.VirtualHost;


public interface ConfigRootSection<T> extends ConfigSection {

	void print(T value, VirtualHost host);

}
