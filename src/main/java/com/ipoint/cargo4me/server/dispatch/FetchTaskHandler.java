package com.ipoint.cargo4me.server.dispatch;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;
import com.ipoint.cargo4me.server.dao.TaskDao;
import com.ipoint.cargo4me.shared.dispatch.FetchTaskAction;
import com.ipoint.cargo4me.shared.dispatch.FetchTaskResult;
import com.ipoint.cargo4me.shared.domain.Task;

public class FetchTaskHandler extends AbstractAction<FetchTaskAction, FetchTaskResult> {
    private final TaskDao taskDao;

    @Inject
    public FetchTaskHandler(TaskDao taskDao) {
        super(FetchTaskAction.class);

        this.taskDao = taskDao;
    }

    @Override
    public FetchTaskResult execute(FetchTaskAction action, ExecutionContext context) throws ActionException {
        Task task = taskDao.get(action.getTaskId());

        return new FetchTaskResult(task);
    }
}
