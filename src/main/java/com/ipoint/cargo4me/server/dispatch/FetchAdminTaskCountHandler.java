package com.ipoint.cargo4me.server.dispatch;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;
import com.ipoint.cargo4me.server.dao.TaskDao;
import com.ipoint.cargo4me.shared.dispatch.FetchAdminTaskCountAction;
import com.ipoint.cargo4me.shared.dispatch.FetchAdminTaskCountResult;

public class FetchAdminTaskCountHandler extends AbstractAction<FetchAdminTaskCountAction, FetchAdminTaskCountResult> {
    private final TaskDao taskDao;

    @Inject
    public FetchAdminTaskCountHandler(TaskDao taskDao) {
        super(FetchAdminTaskCountAction.class);

        this.taskDao = taskDao;
    }

    @Override
    public FetchAdminTaskCountResult execute(FetchAdminTaskCountAction action, ExecutionContext context)
            throws ActionException {
        Integer totalCount = taskDao.findTotalCount();

        return new FetchAdminTaskCountResult(totalCount);
    }
}
