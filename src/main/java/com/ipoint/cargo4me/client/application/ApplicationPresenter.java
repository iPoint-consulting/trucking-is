package com.ipoint.cargo4me.client.application;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.ipoint.cargo4me.client.application.loadunload.LoadUnloadPresenter;

public class ApplicationPresenter extends
		Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> {
	public interface MyView extends View {
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();

	@ContentSlot
	public static Type<RevealContentHandler<?>> centerSlot = new Type<RevealContentHandler<?>>();

	private LoadUnloadPresenter loadUnloadPresenter;

	@ProxyCodeSplit
	public interface MyProxy extends Proxy<ApplicationPresenter> {
	}

	@Inject
	public ApplicationPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final LoadUnloadPresenter loadUnloadPresenter) {
		super(eventBus, view, proxy, RevealType.Root);
		this.loadUnloadPresenter = loadUnloadPresenter;
	}

	@Override
	protected void onBind() {
		super.onBind();

		setInSlot(centerSlot, loadUnloadPresenter);
	}
}
