package com.ipoint.cargo4me.shared.dispatch;

import com.gwtplatform.dispatch.shared.Result;
import com.ipoint.cargo4me.shared.dto.CurrentUserDto;

public class FetchCurrentUserResult implements Result {
    private CurrentUserDto currentUser;

    public FetchCurrentUserResult() {
    }

    public FetchCurrentUserResult(CurrentUserDto currentUser) {
        this.currentUser = currentUser;
    }

    public CurrentUserDto getCurrentUser() {
        return currentUser;
    }
}
