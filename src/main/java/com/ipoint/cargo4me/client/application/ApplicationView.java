package com.ipoint.cargo4me.client.application;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class ApplicationView extends ViewImpl implements
		ApplicationPresenter.MyView {
	public interface Binder extends UiBinder<Widget, ApplicationView> {
	}

	@UiField
	SimplePanel main;

	@Inject
	public ApplicationView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setInSlot(Object slot, IsWidget content) {
		if (ApplicationPresenter.centerSlot.equals(slot)) {
			if (content != null) {
				main.clear();
				main.add(content);
			}
		}
	}
}
