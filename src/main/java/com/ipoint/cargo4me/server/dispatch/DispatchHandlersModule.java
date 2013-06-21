package com.ipoint.cargo4me.server.dispatch;

import com.gwtplatform.dispatch.server.guice.HandlerModule;
import com.ipoint.cargo4me.server.dispatch.validators.AdminActionValidator;
import com.ipoint.cargo4me.shared.dispatch.FetchAdminTaskCountAction;
import com.ipoint.cargo4me.shared.dispatch.FetchCurrentUserAction;
import com.ipoint.cargo4me.shared.dispatch.FetchTaskAction;

public class DispatchHandlersModule extends HandlerModule {
    @Override
    protected void configureHandlers() {
        bindHandler(FetchTaskAction.class, FetchTaskHandler.class);
        bindHandler(FetchCurrentUserAction.class, FetchCurrentUserHandler.class);

        // This fetch has a Validator which only lets App Admins fetch it.
        bindHandler(FetchAdminTaskCountAction.class, FetchAdminTaskCountHandler.class, AdminActionValidator.class);
    }
}
