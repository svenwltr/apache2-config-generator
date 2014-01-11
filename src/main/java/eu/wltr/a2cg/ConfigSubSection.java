package eu.wltr.a2cg;


import eu.wltr.a2cg.schema.Location;



public interface ConfigSubSection<T> extends ConfigSection {

	void print(T value, Location location);

}
