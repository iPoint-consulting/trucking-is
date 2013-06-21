package com.ipoint.cargo4me.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

public class IntegrationServiceEntryPoint implements EntryPoint {
	public final IntegrationServiceGinjector ginjector = GWT
			.create(IntegrationServiceGinjector.class);

	public void onModuleLoad() {
		DelayedBindRegistry.bind(ginjector);
		ginjector.getResources().style().ensureInjected();
		ginjector.getPlaceManager().revealDefaultPlace();
	}
}
