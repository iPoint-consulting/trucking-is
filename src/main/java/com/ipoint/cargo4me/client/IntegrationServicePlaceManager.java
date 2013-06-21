package com.ipoint.cargo4me.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;
import com.ipoint.cargo4me.client.place.NameTokens;

@Singleton
public class IntegrationServicePlaceManager extends PlaceManagerImpl {

	@Inject
	public IntegrationServicePlaceManager(EventBus eventBus,
			TokenFormatter tokenFormatter) {
		super(eventBus, tokenFormatter);
	}

	@Override
	public void revealDefaultPlace() {
		this.revealPlace(new PlaceRequest(NameTokens.loadunload));
	}

	@Override
	public void revealUnauthorizedPlace(String unauthorizedHistoryToken) {
		PlaceRequest currentPlaceRequest = getCurrentPlaceRequest();
		revealPlace(currentPlaceRequest, false);
	}

}
