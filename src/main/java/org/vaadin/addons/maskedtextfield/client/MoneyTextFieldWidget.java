package org.vaadin.addons.maskedtextfield.client;

import org.vaadin.addons.maskedtextfield.shared.MoneyMaskConfig;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.vaadin.client.ui.VTextField;

public class MoneyTextFieldWidget extends VTextField {

	private boolean wasFocused = false;
	
	@Override
	public void onBlur(BlurEvent event) {
		try {
			super.onBlur(event);
		} finally {
			wasFocused = false;
		}
	}
	
	@Override
	public void onFocus(FocusEvent event) {
		try {
			if(!wasFocused) {
				updateCarret();
			}
			super.onFocus(event);
		} finally {
			wasFocused = true;
		}
	}
	
	private void updateCarret() {
		String value = getText();
		if(value != null && !value.trim().isEmpty()) {
			setCursorPos(value.length());
		}
	}
	
	public void initMask(MoneyMaskConfig config) {
		updateMask(config);
	}
	
	public void updateMask(final MoneyMaskConfig config) {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				configureMaskToNativeField(toOverLay(config), getElement());
			}
		});
	}
	
	private native void configureMaskToNativeField(MoneyMaskConfigOverlay overlay, Element fieldElement) /*-{
		$wnd.SimpleMaskMoney.setMask(fieldElement, overlay);
	}-*/;
	
	public MoneyMaskConfigOverlay toOverLay(MoneyMaskConfig config) {
		MoneyMaskConfigOverlay overlay = MoneyMaskConfigOverlay.createObject().cast();
		
		/* fixed for now */
		overlay.setAllowNegative(true);
		overlay.setNegativeSignAfter(false);
		overlay.setPrefix("");
		overlay.setSuffix("");
		
		if(config.cursor != null) {
			overlay.setCursor(config.cursor.toLowerCase());
		}
		
		overlay.setFixed(config.fixed);
		overlay.setFractionDigits(config.fractionDigits);
		overlay.setDecimalSeparator(config.decimalSeparator);
		overlay.setThousandsSeparator(config.gropupingSeparator);
		overlay.setAutoCompleteDecimal(true);
		
		return overlay;
	}
	
}