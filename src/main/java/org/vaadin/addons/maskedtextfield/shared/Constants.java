package org.vaadin.addons.maskedtextfield.shared;

import java.util.Arrays;

/**
 * Some constants that can be used for any widget
 * @author eduardo
 *
 */
public class Constants {
	
	/**
	 * For a fixed GWT i18n formatter
	 */
	public static final char FIXED_LOCALE_DECIMAL_SEPARATOR = '.';
	
	/**
	 * For a fixed GWT i18n formatter
	 */
	public static final char FIXED_LOCALE_GROUPING_SEPARATOR = ',';
	
	/**
	 * Empty char representation
	 */
	public static final char EMPTY_CHAR ='\0';
	
	/**
	 * Supported masks representations
	 */
	public static final char[] MASK_REPRESENTATIONS = {'#', 'U', 'L', '?', 'A', '*', 'H', '~'};

	static {
		Arrays.sort(MASK_REPRESENTATIONS);
	}
	
}
