package eu.wltr.a2cg;


import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;


public class Utils {

	@SuppressWarnings({ "unchecked" })
	public static Map<String, Object> getBeanProperties(Object obj) {
		try {
			return PropertyUtils.describe(obj);

		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
			throw new AssertionError(e);

		}

	}

}
