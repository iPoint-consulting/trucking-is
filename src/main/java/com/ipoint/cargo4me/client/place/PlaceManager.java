package com.ipoint.cargo4me.client.place;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

public class PlaceManager extends PlaceManagerImpl {
    private final PlaceRequest defaultPlaceRequest;

    @Inject
    public PlaceManager(final EventBus eventBus, final TokenFormatter tokenFormatter,
            @DefaultPlace final String defaultPlaceNameToken) {
        super(eventBus, tokenFormatter);

        this.defaultPlaceRequest = new PlaceRequest(defaultPlaceNameToken);
    }

    @Override
    public void revealDefaultPlace() {
        revealPlace(new PlaceRequest(NameTokens.loadunload), false);
    }
}
