package com.ipoint.cargo4me.server.dao;

import com.ipoint.cargo4me.shared.domain.Task;

public class TaskDao extends BaseDao<Task> {
    protected TaskDao() {
        super(Task.class);
    }

    public Integer findTotalCount() {
        return ofy().query(Task.class).count();
    }
}
