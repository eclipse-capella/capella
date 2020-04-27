/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.helpers;

/**
 * String helpers
 * 
 */
public class StringExt {

	/**
	 * Find the last occurrence of "find" in the "source" String and replace it by "replace".
	 * 
	 * @param find String to find
	 * @param replace String to replace
	 * @param source the source String to process.
	 * @return the replace result.
	 */
	public static String replaceLast(String find, String replace, String source) {
		String src = ""; //$NON-NLS-1$
		String result = ""; //$NON-NLS-1$

		if (source.indexOf(find) >= 0) {
			result += source.substring(0, source.lastIndexOf(find)) + replace;
			src = source.substring(source.lastIndexOf(find) + find.length());
		}
		result += src;

		return result;
	}
}
