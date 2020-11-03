/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.commands.preferences.util;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;

import org.polarsys.capella.core.commands.preferences.internalization.l10n.PreferencesUIMessages;
import org.polarsys.capella.core.commands.preferences.model.CategoryPreferences;
import org.polarsys.capella.core.commands.preferences.model.IItemNode;

/**
 * Helper utilities for dealing with the display of constraint details.
 * 
 */
public class ItemDetailsHelper {
	
	private static final String BOLD_START = "<b>"; //$NON-NLS-1$
	private static final String BOLD_END = "</b>"; //$NON-NLS-1$

	static final String PLATFORM_NEWLINE = System
		.getProperty("line.separator"); //$NON-NLS-1$
	
	static final String ALSO_IN_CATEGORIES = 
		PLATFORM_NEWLINE + PLATFORM_NEWLINE +
		PreferencesUIMessages.prefs_constraints_also;
	
	static final String CONSTRAINT_DESCRIPTION_PATTERN = 
		PreferencesUIMessages.prefs_description_constraint;
	static final String ERROR_CONSTRAINT_DESCRIPTION_PATTERN = 
		PreferencesUIMessages.prefs_description_constraint_error;

	static final String NO_CONSTRAINT_DESCRIPTION =
		PreferencesUIMessages.prefs_no_description_constraint;
	
	/**
	 * Formats the description of the specified <code>constraint</code> and
	 * returns it.  The list of <code>styles</code> is populated with the
	 * {@link StyleRange}s implementing the bold-text highlights parsed from the
	 * description pattern.
	 * 
	 * @param constraint the constraint whose description is to be formatted
	 * @param selectedCategory the currently selected category.  This is used
	 *     to determine which categories other than the current selection the
	 *     <code>constraint</code> belongs to
	 * @param styles collects the style information to be passed to the text
	 *     widget
	 * @return the description text, formatted according to the pattern in the
	 *     localized resource file
	 */
	public static String formatItemDescription(
			IItemNode constraint,
			CategoryPreferences selectedCategory,
			List<? super StyleRange> styles) {
		
		String description = constraint.getDescription();
		
		if (description == null) {
			description = NO_CONSTRAINT_DESCRIPTION;
		}

		String messagePattern = CONSTRAINT_DESCRIPTION_PATTERN;
		
		
		String text = NLS.bind(messagePattern, new Object[] {constraint.getId(), description});
		
		Collection<CategoryPreferences> categories = constraint.getCategories();
		if (categories.size() > 1) {
			// also display the other categories that contain this constraint
			text = text + getOtherCategories(selectedCategory, categories);
		}
		
		if (constraint.isMandatory()) {
			text = MessageFormat.format(PreferencesUIMessages.prefs_mandatory_constraint, new Object[] {text});
		}
		
		return parseStyles(text, styles);
	}
	
	/**
	 * Obtains a string listing the other categories in which the currently
	 * selected constraint is a member that are not the currently selected
	 * category.
	 * 
	 * @param selected the currently selected category
	 * @param categories the categories which contain the constraint
	 * @return the other categories than the currently selected one
	 */
	private static String getOtherCategories(
			CategoryPreferences selected,
			Collection<? extends CategoryPreferences> categories) {
		
		StringBuffer result = new StringBuffer(64);
		result.append(ALSO_IN_CATEGORIES);
		
		for (CategoryPreferences next : categories) {
			if (next != selected) {
				result.append(PLATFORM_NEWLINE);
				result.append(next.getQualifiedName());
			}
		}
		
		return result.toString();
	}
	
	/**
	 * Parses out the <tt>&lt;b&gt;...&lt;/b&gt;</tt> style information from the
	 * specified <code>text</code> and adds corresponding {@link StyleRange}s
	 * to the <code>styles</code> collector parameter.  The result is the
	 * <code>text</code> minus the style markup.
	 * 
	 * @param text marked up text
	 * @param styles a list which collects the corresponding style ranges
	 * @return the original <code>text</code> minus the markup
	 */
	private static String parseStyles(String text, List<? super StyleRange> styles) {
		int pos = -1;
		int lastPos = 0;
		
		StringBuffer result = new StringBuffer(text.length());
		
		while (lastPos < text.length()) {
			pos = text.indexOf(BOLD_START, lastPos);  // known BMP characters
			
			if (pos < 0) {
				break;
			} else {
				result.append(text.substring(lastPos, pos));
				
				lastPos = pos + BOLD_START.length();
				
				pos = text.indexOf(BOLD_END, lastPos);  // known BMP characters
				
				if (pos < 0) {
					// implied </b> at end of input
					pos = text.length();
				}
				
				styles.add(new StyleRange(
								result.length(), pos - lastPos,
								null, null,
								SWT.BOLD));
				
				result.append(text.substring(lastPos, pos));
				
				lastPos = Math.min(pos + BOLD_END.length(), text.length()); 
			}
		}
		
		result.append(text.substring(lastPos, text.length()));
		
		return result.toString();
	}
}
