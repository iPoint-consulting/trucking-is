package com.ipoint.cargo4me.server.dao.objectify;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.ipoint.cargo4me.shared.domain.Task;
import com.ipoint.cargo4me.shared.domain.User;

/**
 * For use of: import static com.sceneverse.shozon.server.dao.objectify.OfyService.ofy;
 */
public class OfyService {
    static {
        factory().register(Task.class);
        factory().register(User.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
