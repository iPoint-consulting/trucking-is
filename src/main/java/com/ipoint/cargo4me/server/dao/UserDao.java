package com.ipoint.cargo4me.server.dao;

import com.ipoint.cargo4me.shared.domain.User;

public class UserDao extends BaseDao<User> {
    public UserDao() {
        super(User.class);
    }

    public User findByGoogleId(String googleId) {
        return ofy().query(User.class).filter("googleId =", googleId).first().get();
    }
}
