package org.vaadin.addons.maskedtextfield.client;

import org.vaadin.addons.maskedtextfield.shared.MoneyMaskConfig;

import com.vaadin.shared.ui.textfield.AbstractTextFieldState;

@SuppressWarnings("serial")
public class MoneyTextFieldState extends AbstractTextFieldState {
	
	public MoneyMaskConfig fieldConfig = new MoneyMaskConfig();
	
}
