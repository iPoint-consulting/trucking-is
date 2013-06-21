package com.ipoint.cargo4me.server.dao.objectify;

import static com.ipoint.cargo4me.server.dao.objectify.OfyService.ofy;

import com.googlecode.objectify.ObjectifyFactory;

public class OfyFactory extends ObjectifyFactory {
    public OfyFactory() {
    }

    @Override
    public Ofy begin() {
        return new Ofy(ofy());
    }
}
