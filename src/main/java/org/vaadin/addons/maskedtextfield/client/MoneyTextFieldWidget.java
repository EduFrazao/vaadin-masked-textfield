package org.vaadin.addons.maskedtextfield.client;

import org.vaadin.addons.maskedtextfield.shared.MoneyMaskConfig;

import com.google.gwt.dom.client.Element;
import com.vaadin.client.ui.VTextField;

public class MoneyTextFieldWidget extends VTextField {

	public void initMask(MoneyMaskConfig config) {
		updateMask(config);
	}
	
	public void updateMask(MoneyMaskConfig config) {
		configureMaskToNativeField(MoneyMaskConfigOverlay.toOverLay(config), getElement());
	}
	
	private native void configureMaskToNativeField(MoneyMaskConfigOverlay overlay, Element fieldElement) /*-{
		$wnd.SimpleMaskMoney.setMask(fieldElement, overlay);
	}-*/;
	
}