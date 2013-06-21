package com.ipoint.cargo4me.client.gin;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.impl.SchedulerImpl;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;
import com.ipoint.cargo4me.client.IntegrationServicePlaceManager;
import com.ipoint.cargo4me.client.application.ApplicationPresenter;
import com.ipoint.cargo4me.client.application.ApplicationView;
import com.ipoint.cargo4me.client.application.authorization.AuthorizationPresenter;
import com.ipoint.cargo4me.client.application.authorization.AuthorizationView;
import com.ipoint.cargo4me.client.application.loadunload.LoadUnloadPresenter;
import com.ipoint.cargo4me.client.application.loadunload.LoadUnloadView;
import com.ipoint.cargo4me.client.application.progressdialog.ProgressDialogPresenter;
import com.ipoint.cargo4me.client.application.progressdialog.ProgressDialogView;
import com.ipoint.cargo4me.client.application.userstable.UsersTablePresenter;
import com.ipoint.cargo4me.client.application.userstable.UsersTableView;
import com.ipoint.cargo4me.client.place.NameTokens;
import com.ipoint.cargo4me.client.resources.Resources;
import com.ipoint.cargo4me.shared.dto.CurrentUserDto;

/**
 * See more on setting up the PlaceManager on <a
 * href="// See more on: https://github.com/ArcBees/GWTP/wiki/PlaceManager"
 * >DefaultModule's > DefaultPlaceManager</a>
 */
public class ClientModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(
				Singleton.class);
		bind(RootPresenter.class).asEagerSingleton();
		bind(Scheduler.class).to(SchedulerImpl.class).in(Singleton.class);


		bind(CurrentUserDto.class).asEagerSingleton();

		bind(PlaceManager.class).to(IntegrationServicePlaceManager.class).in(
				Singleton.class);
		

		bind(Resources.class).in(Singleton.class);
		bindPresenter(LoadUnloadPresenter.class,
				LoadUnloadPresenter.MyView.class,LoadUnloadView.class,
				LoadUnloadPresenter.MyProxy.class);
		bindPresenterWidget(ProgressDialogPresenter.class,
				ProgressDialogPresenter.MyView.class,ProgressDialogView.class);
		bindPresenterWidget(UsersTablePresenter.class,
				UsersTablePresenter.MyView.class,UsersTableView.class);
		bindPresenter(AuthorizationPresenter.class,
				AuthorizationPresenter.MyView.class, AuthorizationView.class,
				AuthorizationPresenter.MyProxy.class);
		bindPresenter(ApplicationPresenter.class,
				ApplicationPresenter.MyView.class, ApplicationView.class,
				ApplicationPresenter.MyProxy.class);
	}
}
