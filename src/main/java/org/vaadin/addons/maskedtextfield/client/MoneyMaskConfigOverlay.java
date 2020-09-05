package org.vaadin.addons.maskedtextfield.client;

import com.google.gwt.core.client.JavaScriptObject;

public class MoneyMaskConfigOverlay extends JavaScriptObject {

	protected MoneyMaskConfigOverlay() {
		super();
	}
	
	public final native void setAllowNegative(boolean allowNegative) /*-{
		this.allowNegative = allowNegative;
	}-*/;
	
	public final native boolean isAllowNegative() /*-{
		return this.allowNegative;
	}-*/;
	
	public final native void setNegativeSignAfter(boolean negativeSignAfter) /*-{
		this.negativeSignAfter = negativeSignAfter;
	}-*/;
	
	public final native boolean isNegativeSignAfter() /*-{
		return this.negativeSignAfter;
	}-*/;
	
	public final native void setPrefix(String prefix) /*-{
		this.prefix = prefix;
	}-*/;
	
	public final native String getPrefix() /*-{
		return this.prefix;
	}-*/;

	public final native void setSuffix(String suffix) /*-{
		this.suffix = suffix;
	}-*/;
	
	public final native String getSuffix() /*-{
		return this.suffix;
	}-*/;
	
	public final native void setDecimalSeparator(String decimalSeparator) /*-{
		this.decimalSeparator = decimalSeparator;
	}-*/;
	
	public final native String getDecimalSeparator() /*-{
		return this.decimalSeparator;
	}-*/;
	
	public final native void setThousandsSeparator(String thousandsSeparator) /*-{
		this.thousandsSeparator = thousandsSeparator;
	}-*/;
	
	public final native String getThousandsSeparator() /*-{
		return this.thousandsSeparator;
	}-*/;
	
	public final native void setFixed(boolean fixed) /*-{
		this.fixed = fixed;
	}-*/;
	
	public final native boolean isFixed() /*-{
		return this.fixed;
	}-*/;
	
	public final native void setFractionDigits(int fractionDigits) /*-{
		this.fractionDigits = fractionDigits;
	}-*/;
	
	public final native int getFractionDigits() /*-{
		return this.fractionDigits;
	}-*/;
	
	public final native void setCursor(String cursor) /*-{
		this.cursor = cursor;
	}-*/;
	
	public final native String getCursor() /*-{
		return this.cursor;
	}-*/;
	
	public final native void setAutoCompleteDecimal(boolean autoCompleteDecimal) /*-{
		this.autoCompleteDecimal = autoCompleteDecimal;
	}-*/;
	
	public final native boolean isAutoCompleteDecimal() /*-{
		return this.autoCompleteDecimal;
	}-*/;
	
}
