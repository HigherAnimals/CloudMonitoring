package com.rackspace.cloudmonitoring;

import java.util.Collections;
import java.util.List;

public class Service {
	public final String name;
	public final String type;
	public final List<Endpoint> serviceCatalog;
	
	public Service(String name, String type, List<Endpoint> serviceCatalog) {
		this.name = name;
		this.type = type;
		this.serviceCatalog = Collections.unmodifiableList(serviceCatalog);
	}
}
