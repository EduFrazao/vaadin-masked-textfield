package org.vaadin.addons.maskedtextfield.client;

import org.vaadin.addons.maskedtextfield.MoneyTextField;

import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.textfield.TextFieldConnector;
import com.vaadin.shared.ui.Connect;

@Connect(MoneyTextField.class)
public class MoneyTextFieldConnector extends TextFieldConnector {

	private static final long serialVersionUID = 1L;
	
	@Override
	public MoneyTextFieldWidget getWidget() {
		return (MoneyTextFieldWidget) super.getWidget();
	}

	@Override
	public MoneyTextFieldState getState() {
		return (MoneyTextFieldState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
		if(stateChangeEvent.hasPropertyChanged("fieldConfig")) {
			getWidget().updateMask(getState().fieldConfig);
		}
	}
	
}
