package com.ipoint.cargo4me.client.application.authorization;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.ipoint.cargo4me.client.application.ApplicationPresenter;
import com.ipoint.cargo4me.client.place.NameTokens;

public class AuthorizationPresenter extends Presenter<AuthorizationPresenter.MyView, AuthorizationPresenter.MyProxy> {
    public interface MyView extends View {
    }
    
    @ProxyCodeSplit
    @NameToken(NameTokens.authorization)
    public interface MyProxy extends ProxyPlace<AuthorizationPresenter> {
    }

    @Inject
    public AuthorizationPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
            final DispatchAsync dispatcher) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);
    }
    
}