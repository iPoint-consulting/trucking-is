package com.ipoint.cargo4me.client;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.ipoint.cargo4me.client.application.ApplicationPresenter;
import com.ipoint.cargo4me.client.application.authorization.AuthorizationPresenter;
import com.ipoint.cargo4me.client.application.loadunload.LoadUnloadPresenter;
import com.ipoint.cargo4me.client.application.progressdialog.ProgressDialogPresenter;
import com.ipoint.cargo4me.client.application.userstable.UsersTablePresenter;
import com.ipoint.cargo4me.client.gin.ClientModule;
import com.ipoint.cargo4me.client.resources.Resources;

@GinModules({DispatchAsyncModule.class, ClientModule.class})
public interface IntegrationServiceGinjector extends Ginjector {

	Resources getResources();
	
	PlaceManager getPlaceManager();

	EventBus getEventBus();
	
	AsyncProvider<LoadUnloadPresenter> getLoadUnloadPresenter();
	
	AsyncProvider<ProgressDialogPresenter> getProgressDialogPresenter();
	
	AsyncProvider<UsersTablePresenter> getUsersTablePresenter();
	
	AsyncProvider<AuthorizationPresenter> getAuthorizationPresenter();
	
	AsyncProvider<ApplicationPresenter> getApplicationPresenter();

}
