/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
	 * Find the last occurrence of "find_p" in the "source_p" String and replace it by "replace_p".
	 * 
	 * @param find_p String to find
	 * @param replace_p String to replace
	 * @param source_p the source String to process.
	 * @return the replace result.
	 */
	public static String replaceLast(String find_p, String replace_p, String source_p) {
		String source = ""; //$NON-NLS-1$
		String result = ""; //$NON-NLS-1$

		if (source_p.indexOf(find_p) >= 0) {
			result += source_p.substring(0, source_p.lastIndexOf(find_p)) + replace_p;
			source = source_p.substring(source_p.lastIndexOf(find_p) + find_p.length());
		}
		result += source;

		return result;
	}
}
