package com.ipoint.cargo4me.client.gin;

import javax.inject.Inject;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.ipoint.cargo4me.client.dispatch.AsyncCallbackImpl;
import com.ipoint.cargo4me.shared.dispatch.FetchCurrentUserAction;
import com.ipoint.cargo4me.shared.dispatch.FetchCurrentUserResult;
import com.ipoint.cargo4me.shared.dto.CurrentUserDto;

public class BootstrapperImpl implements Bootstrapper {
    private final PlaceManager placeManager;
    private final DispatchAsync dispatcher;
    private final CurrentUserDto currentUserDto;

    @Inject
    public BootstrapperImpl(final PlaceManager placeManager, final DispatchAsync dispatcher,
            final CurrentUserDto currentUserDto) {
        this.placeManager = placeManager;
        this.dispatcher = dispatcher;
        this.currentUserDto = currentUserDto;
    }

    @Override
    public void onBootstrap() {
        fetchCurrentUser();
    }

    private void fetchCurrentUser() {
        dispatcher.execute(new FetchCurrentUserAction(), new AsyncCallbackImpl<FetchCurrentUserResult>() {
            @Override
            public void onSuccess(FetchCurrentUserResult result) {
                onFetchCurrentUserSuccess(result.getCurrentUser());
            }

            @Override
            public void onFailure(Throwable caught) {
                super.onFailure(caught);
                placeManager.revealCurrentPlace();
            }
        });
    }

    private void onFetchCurrentUserSuccess(CurrentUserDto currentUser) {
        currentUserDto.copyFrom(currentUser);

        placeManager.revealCurrentPlace();
    }
}
