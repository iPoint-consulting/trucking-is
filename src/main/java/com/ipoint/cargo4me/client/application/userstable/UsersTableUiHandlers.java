package com.ipoint.cargo4me.client.application.userstable;

import java.util.List;

import com.gwtplatform.mvp.client.UiHandlers;
import com.ipoint.cargo4me.shared.dto.UserDTO;

public interface UsersTableUiHandlers extends UiHandlers {

	public void showUserTable(List<UserDTO> users);

	public List<UserDTO> getSelectedUsers();

	public void clearUsersTable();
}
