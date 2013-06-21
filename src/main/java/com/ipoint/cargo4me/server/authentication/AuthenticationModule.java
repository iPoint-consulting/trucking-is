package com.ipoint.cargo4me.server.authentication;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.RequestScoped;
import com.ipoint.cargo4me.shared.dto.CurrentUserDto;

public class AuthenticationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CurrentUserDtoProvider.class).in(Singleton.class);
        bind(CurrentUserDto.class).toProvider(CurrentUserDtoProvider.class).in(RequestScoped.class);
    }
}
