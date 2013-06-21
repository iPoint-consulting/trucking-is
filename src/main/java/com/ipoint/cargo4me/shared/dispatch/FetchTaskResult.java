package com.ipoint.cargo4me.shared.dispatch;

import com.gwtplatform.dispatch.shared.Result;
import com.ipoint.cargo4me.shared.domain.Task;

public class FetchTaskResult implements Result {
    private Task task;

    public FetchTaskResult() {
    }

    public FetchTaskResult(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
