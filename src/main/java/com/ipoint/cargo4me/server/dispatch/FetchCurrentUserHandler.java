package com.ipoint.cargo4me.server.dispatch;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;
import com.ipoint.cargo4me.server.authentication.CurrentUserDtoProvider;
import com.ipoint.cargo4me.shared.dispatch.FetchCurrentUserAction;
import com.ipoint.cargo4me.shared.dispatch.FetchCurrentUserResult;
import com.ipoint.cargo4me.shared.dto.CurrentUserDto;

public class FetchCurrentUserHandler extends AbstractAction<FetchCurrentUserAction, FetchCurrentUserResult> {
    private CurrentUserDtoProvider currentUserDtoProvider;

    @Inject
    public FetchCurrentUserHandler(CurrentUserDtoProvider currentUserDtoProvider) {
        super(FetchCurrentUserAction.class);

        this.currentUserDtoProvider = currentUserDtoProvider;
    }

    @Override
    public FetchCurrentUserResult execute(FetchCurrentUserAction action, ExecutionContext context)
            throws ActionException {
        CurrentUserDto currentUser = currentUserDtoProvider.get();

        return new FetchCurrentUserResult(currentUser);
    }
}
