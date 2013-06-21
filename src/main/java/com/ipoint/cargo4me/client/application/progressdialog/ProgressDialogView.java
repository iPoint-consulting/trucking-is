package com.ipoint.cargo4me.client.application.progressdialog;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.ProgressBar;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;

public class ProgressDialogView
		extends
			PopupViewWithUiHandlers<ProgressDialogUiHandlers>
		implements
			ProgressDialogPresenter.MyView {

	private final Widget widget;

	private boolean cancelImport = false;

	public interface Binder extends UiBinder<Widget, ProgressDialogView> {
	}

	@UiField
	Modal importDialog;

	@UiField
	Button cancelButton;

	@UiField
	ProgressBar loadProgress;

	@UiField
	Label progressIndicator;

	@Inject
	public ProgressDialogView(final EventBus eventBus, final Binder binder) {
		super(eventBus);
		setUpDialog();
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	private void setUpDialog() {
		importDialog = new Modal() {

			@Override
			protected void onUnload() {
				// TODO Auto-generated method stub
				super.onUnload();
				ProgressDialogView.this.hide();
			}
		};

	}

	@Override
	public final void hide() {
		importDialog.hide();
	}

	@UiHandler("cancelButton")
	public void onCancelButtonClick(ClickEvent event) {
		setCancelImport(true);
		this.hide();
	}

	@Override
	public void showModal() {

	}

	@Override
	public void show() {
		loadProgress.setPercent(0);
		progressIndicator.setText("Выполнено 0%");
		importDialog.show();
	}

	@Override
	public void setCompletion(int completion) {
		loadProgress.setPercent(completion);
		progressIndicator.setText("Выполнено " + String.valueOf(completion)
				+ "%");
	}

	public boolean isCancelImport() {
		return cancelImport;
	}

	public void setCancelImport(boolean cancelImport) {
		this.cancelImport = cancelImport;
	}

}