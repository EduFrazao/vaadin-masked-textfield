package org.vaadin.addons.maskedtextfield;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.vaadin.addons.maskedtextfield.client.MoneyTextFieldState;
import org.vaadin.addons.maskedtextfield.server.Utils;

import com.vaadin.annotations.JavaScript;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.AbstractStringToNumberConverter;
import com.vaadin.ui.TextField;

/**
 * Server side component for the VMaskedTextField widget.
 */
@JavaScript("smm-3.0.js")
public class MoneyTextField extends TextField {

	private static final long serialVersionUID = 1L;

	private MaskNumberConverter localConverter = null;
	
	private String formatMask = "##,##0.00";
	
	public MoneyTextField() {
		super();
		initConverter();
	}

	public MoneyTextField(Property<?> dataSource) {
		super(dataSource);
		initConverter();
	}

	public MoneyTextField(String caption, Property<?> dataSource) {
		super(caption, dataSource);
		initConverter();
	}

	public MoneyTextField(String caption, String value) {
		super(caption, value);
		initConverter();
	}

	public MoneyTextField(String caption) {
		super(caption);
		initConverter();
	}
	
	public MoneyTextField(String mask, char decimalSeparator, char groupingSeparator) {
		super();
		setMask(mask);
		setDecimalSeparator(decimalSeparator);
		setGroupingSeparator(groupingSeparator);
		initConverter();
	}

	private void initConverter() {
		localConverter = new MaskNumberConverter();
		setConverter(localConverter);
		configureClientMaskOptions();
	}
	
	public void setFixed(boolean fixed) {
		getState().fieldConfig.fixed = fixed;
	}
	
	public boolean isFixed() {
		return getState().fieldConfig.fixed;
	}
	
	@Override
	public void setValue(String string) {
		super.setValue(string);
	}
	
	public void setValue(Number number) {
		if(number != null) {
			if(getConverter() != null) {
				String v = getConverter().convertToPresentation(number, String.class, getLocale());
				setValue(v);
			} else {
				setValue( (String) null);
			}
		} else {
			setValue( (String) null);
		}
	}
	
	public void setMask(String mask) {
		if(mask == null) {
			throw new NullPointerException("The format mask cannot be null");
		}
		if(mask.trim().isEmpty()) {
			throw new IllegalStateException("The format mask cannot be empty");
		}
		formatMask = mask;
		if(localConverter != null) {
			localConverter.refreshFormatter();
			configureClientMaskOptions();
		}
	}
	
	private void configureClientMaskOptions() {
		getState().fieldConfig.fractionDigits = localConverter.formatter.getMaximumFractionDigits();
	}
	
	public String getMask() {
		return formatMask;
	}
	
	public void setDecimalSeparator(char decimalSeparator) {
		getState().fieldConfig.decimalSeparator = Character.toString(decimalSeparator);
	}
	
	public char getDecimalSeparator() {
		return StringUtils.isNotBlank(getState().fieldConfig.decimalSeparator)  
				? getState().fieldConfig.decimalSeparator.charAt(0)
				: Character.MIN_VALUE;
	}
	
	public void setGroupingSeparator(char groupingSeparator) {
		getState().fieldConfig.gropupingSeparator =Character.toString(groupingSeparator);
	}
	
	public char getGroupingSeparator() {
		return StringUtils.isNotBlank(getState().fieldConfig.gropupingSeparator)  
				? getState().fieldConfig.gropupingSeparator.charAt(0)
				: Character.MIN_VALUE;
	}
	
	public void setCursorAutoPosition(CursorPosition cursorPosition) {
		getState().fieldConfig.cursor = cursorPosition != null ? cursorPosition.toString() : null;
	}
	
	public CursorPosition getCursorAutoPosition() {
		return getState().fieldConfig.cursor != null
				? CursorPosition.valueOf(getState().fieldConfig.cursor)
				: null;
	}
	
	@Override
	public MoneyTextFieldState getState() {
		return (MoneyTextFieldState) super.getState();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		if(newDataSource != null) {
			if(!Number.class.isAssignableFrom(newDataSource.getType())) {
				throw new IllegalArgumentException("This field is compatible with number datasources only");
			}
			super.setPropertyDataSource(newDataSource);
		}
	}
	
	public static enum CursorPosition {
		MOVE,
		START,
		END
	}

	/**
	 * Custom converter to handle custom separators
	 * @author eduardo
	 *
	 */
	private class MaskNumberConverter extends AbstractStringToNumberConverter<Number> {

		private static final long serialVersionUID = 1L;

		private DecimalFormat formatter;
		
		public MaskNumberConverter() {
			refreshFormatter();
		}
		
		public void refreshFormatter() {
			if(formatter == null || 
					(	formatter.getDecimalFormatSymbols().getGroupingSeparator() != getGroupingSeparator()
					||  formatter.getDecimalFormatSymbols().getDecimalSeparator() != getDecimalSeparator()
					)
			) 
			{
				DecimalFormatSymbols decimalSymbols = new DecimalFormatSymbols();
				decimalSymbols.setGroupingSeparator(getGroupingSeparator());
				decimalSymbols.setDecimalSeparator(getDecimalSeparator());
				formatter = new DecimalFormat(getMask());
				formatter.setDecimalFormatSymbols(decimalSymbols);
				formatter.setMinimumFractionDigits(formatter.getMaximumFractionDigits());
			}
		}
		
		
		@Override
		public Number convertToModel(String value, Class<? extends Number> targetType, Locale locale) throws ConversionException {
			refreshFormatter();
			try {
				if(value == null || value.trim().isEmpty()) {
					return null;
				}
				Number number = formatter.parse(value);
				if(getPropertyDataSource() != null) {
					return Utils.convertToDataSource(number, getPropertyDataSource());
				}
				return number;
			} catch (ParseException e) {
				return Utils.convertToDataSource(new Double(0.0), getPropertyDataSource());
			}
		}

		@Override
		public String convertToPresentation(Number value, Class<? extends String> targetType, Locale locale) throws ConversionException {
			if(value != null) {
				refreshFormatter();
				return formatter.format(value);
			}
			return null;
		}

		@Override
		public Class<Number> getModelType() {
			return Number.class;
		}

	}


}