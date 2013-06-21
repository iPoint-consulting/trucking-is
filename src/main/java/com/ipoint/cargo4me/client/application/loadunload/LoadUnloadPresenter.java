package com.ipoint.cargo4me.client.application.loadunload;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.ipoint.cargo4me.client.application.ApplicationPresenter;
import com.ipoint.cargo4me.client.application.progressdialog.ProgressDialogPresenter;
import com.ipoint.cargo4me.client.application.userstable.UsersTablePresenter;
import com.ipoint.cargo4me.client.place.NameTokens;
import com.ipoint.cargo4me.client.security.AuthorizationCallback;
import com.ipoint.cargo4me.client.security.OAuth2Manager;
import com.ipoint.cargo4me.shared.dto.UserDTO;

public class LoadUnloadPresenter extends
		Presenter<LoadUnloadPresenter.MyView, LoadUnloadPresenter.MyProxy>
		implements LoadUnloadUiHandlers {
	public interface MyView extends View, HasUiHandlers<LoadUnloadUiHandlers> {
		void startImport();
	}

	private ProgressDialogPresenter progressDialogPresenter;

	private UsersTablePresenter usersTablePresenter;

	private int countUsers;

	private int currentUser = 0;

	private String requestsUrl;

	private List<UserDTO> listUsers;

	@ContentSlot
	public static Type<RevealContentHandler<?>> slot = new Type<RevealContentHandler<?>>();

	@ProxyCodeSplit
	@NameToken(NameTokens.loadunload)
	public interface MyProxy extends ProxyPlace<LoadUnloadPresenter> {
	}

	@Inject
	public LoadUnloadPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher,
			ProgressDialogPresenter progressDialogPresenter,
			UsersTablePresenter usersTablePresenter) {
		super(eventBus, view, proxy);
		this.progressDialogPresenter = progressDialogPresenter;
		this.usersTablePresenter = usersTablePresenter;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, ApplicationPresenter.TYPE_SetMainContent,
				this);

	}

	@Override
	protected void onReveal() {
		super.onReveal();
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().setUiHandlers(this);
	}

	@Override
	public void showProgressDialog() {
		addToPopupSlot(progressDialogPresenter, true);
	}

	@Override
	public void showUsersTable(List<UserDTO> carriers) {
		setInSlot(slot, usersTablePresenter);
		usersTablePresenter.showUserTable(carriers);
	}

	@Override
	public List<UserDTO> getSelectedUsers() {
		return usersTablePresenter.getSelectedUsers();
	}

	@Override
	public void sendToTruckingIntegrationServer() {
		if (listUsers.size() == 0) {
			return;
		}
		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST,
				OAuth2Manager.addAccessTokenToURL(requestsUrl));
		requestBuilder.setHeader("Content-Type",
				"application/x-www-form-urlencoded");
		final UserDTO user = listUsers.get(currentUser);
		listUsers.get(currentUser).getJSON();
		currentUser++;
		requestBuilder.setCallback(new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {
				logError("onResponseReceived" + user.getContactPhone());
				if ((currentUser < countUsers)
						& !progressDialogPresenter.isCancelImport()) {
					progressDialogPresenter
							.setCompletion((int) ((currentUser / ((float) countUsers)) * 100));
					sendToTruckingIntegrationServer();
				} else {
					removeFromPopupSlot(progressDialogPresenter);
					usersTablePresenter.clearUsersTable();
				}
			}

			@Override
			public void onError(Request request, Throwable exception) {
				Window.alert("Ошибка добавления пользователей.");
			}
		});
		StringBuffer postData = new StringBuffer();
		postData.append("data").append("=")
				.append(user.getJSON() == null ? "" : user.getJSON());
		requestBuilder.setRequestData(postData.toString());
		try {
			requestBuilder.send().isPending();

		} catch (RequestException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void sendUsersCount(int count) {
		this.countUsers = count;
	}

	@Override
	public void setRequestUrl(String url) {
		this.requestsUrl = url;
	}

	public void setListUsers(List<UserDTO> listUsers) {
		this.listUsers = new ArrayList<UserDTO>(listUsers);
	}

	public static void logError(String msg) {
		logAtJSConsole(msg);
	};

	private static native void logAtJSConsole(String msg) /*-{
		$wnd.console.log(msg);
	}-*/;

	public void cancelImport(boolean value) {
		progressDialogPresenter.setCancelImport(value);
	}

	public void resetCurrentUser() {
		this.currentUser = 0;
	}

	@Override
	public void authorize() {
		if (!OAuth2Manager.isAuthorized()) {
			OAuth2Manager.autorize(new AuthorizationCallback() {

				@Override
				public void onError(String error) {
					Window.alert("Error:\n" + error);
				}

				@Override
				public void onAuthorize() {
					getView().startImport();
				}
			});
		}
	}

}
