package eu.wltr.a2cg;

import eu.wltr.a2cg.schema.VirtualHost;

public interface ConfigSection<T> {

	void print(T value, VirtualHost host);

}
