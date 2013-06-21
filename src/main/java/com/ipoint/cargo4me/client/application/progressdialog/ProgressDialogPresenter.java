package com.ipoint.cargo4me.client.application.progressdialog;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;

public class ProgressDialogPresenter
		extends
			PresenterWidget<ProgressDialogPresenter.MyView>
		implements
			ProgressDialogUiHandlers {

	public interface MyView
			extends
				PopupView,
				HasUiHandlers<ProgressDialogUiHandlers> {

		public void showModal();

		void setCompletion(int completion);

		public boolean isCancelImport();

		public void setCancelImport(boolean cancelImport);

	}

	@Inject
	public ProgressDialogPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
		getView().setUiHandlers(this);
	}

	@Override
	public void closeModal() {

	}

	@Override
	public void setCompletion(int completion) {
		getView().setCompletion(completion);
	}

	@Override
	public boolean isCancelImport() {
		return getView().isCancelImport();
	}

	@Override
	public void setCancelImport(boolean cancelImport) {
		getView().setCancelImport(cancelImport);
	}

}
