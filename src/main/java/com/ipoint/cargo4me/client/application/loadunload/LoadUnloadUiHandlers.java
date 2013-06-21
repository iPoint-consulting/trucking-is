package com.ipoint.cargo4me.client.application.loadunload;

import java.util.List;

import com.gwtplatform.mvp.client.UiHandlers;
import com.ipoint.cargo4me.shared.dto.UserDTO;

public interface LoadUnloadUiHandlers extends UiHandlers {

	public void showProgressDialog();

	public List<UserDTO> getSelectedUsers();

	public void sendToTruckingIntegrationServer();

	public void sendUsersCount(int count);

	public void setRequestUrl(String url);

	public void setListUsers(List<UserDTO> users);

	public void cancelImport(boolean value);

	public void resetCurrentUser();

	public void authorize();

	public void showUsersTable(List<UserDTO> dtos);

}
