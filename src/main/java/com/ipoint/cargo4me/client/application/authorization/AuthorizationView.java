package com.ipoint.cargo4me.client.application.authorization;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class AuthorizationView extends ViewImpl implements AuthorizationPresenter.MyView {
    public interface Binder extends UiBinder<Widget, AuthorizationView > {
    }

    @Inject
    public AuthorizationView (Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    } 

}
