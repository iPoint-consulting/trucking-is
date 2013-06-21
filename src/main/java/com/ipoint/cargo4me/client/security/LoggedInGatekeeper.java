package com.ipoint.cargo4me.client.security;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;
import com.ipoint.cargo4me.shared.dto.CurrentUserDto;

@Singleton
public class LoggedInGatekeeper implements Gatekeeper {
    private final CurrentUserDto currentUser;

    @Inject
    public LoggedInGatekeeper(CurrentUserDto currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public boolean canReveal() {
        return currentUser.isLoggedIn();
    }
}
