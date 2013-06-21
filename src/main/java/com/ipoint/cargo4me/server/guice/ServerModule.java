package com.ipoint.cargo4me.server.guice;

import com.gwtplatform.dispatch.server.guice.HandlerModule;
import com.ipoint.cargo4me.server.authentication.AuthenticationModule;
import com.ipoint.cargo4me.server.dispatch.DispatchHandlersModule;

public class ServerModule extends HandlerModule {
    @Override
    protected void configureHandlers() {
        install(new DispatchHandlersModule());
        install(new AuthenticationModule());
    }
}
