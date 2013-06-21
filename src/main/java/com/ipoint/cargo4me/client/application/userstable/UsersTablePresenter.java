package com.ipoint.cargo4me.client.application.userstable;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.ipoint.cargo4me.shared.dto.UserDTO;

public class UsersTablePresenter extends
		PresenterWidget<UsersTablePresenter.MyView> implements
		UsersTableUiHandlers {

	public interface MyView extends View, HasUiHandlers<UsersTableUiHandlers> {

		public void setUserTableData(List<UserDTO> users);

		public List<UserDTO> getSelectedUsers();

		public void clearUsersTable();

	}

	@Inject
	public UsersTablePresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().setUiHandlers(this);
	}

	public void showUserTable(List<UserDTO> users) {

		getView().setUserTableData(users);

	}

	@Override
	public List<UserDTO> getSelectedUsers() {
		return getView().getSelectedUsers();
	}

	public void clearUsersTable() {
		getView().clearUsersTable();
	}

}
