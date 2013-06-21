package com.ipoint.cargo4me.client.application.progressdialog;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ProgressDialogUiHandlers extends UiHandlers {

	public void closeModal();
	
	void setCompletion(int completion);
	
	public boolean isCancelImport();

	public void setCancelImport(boolean cancelImport);
}
