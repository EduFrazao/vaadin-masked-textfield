package org.vaadin.addons.maskedtextfield.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MoneyMaskConfig implements Serializable {

	public String prefix;
	public String suffix;
	public boolean fixed = true;
	public int fractionDigits = 2;
	public String decimalSeparator;
	public String gropupingSeparator;
	public String cursor;

}
