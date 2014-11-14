/******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package org.polarsys.capella.core.commands.preferences.util;

import java.net.URL;

import org.eclipse.core.runtime.IConfigurationElement;

/**
 * A custom implementation of the Eclipse configuration element API which is
 * parsed from the <tt>&lt;constraints&gt;</tt> XML element.  This unifies the
 * representation of constraint data obtained from "included" XML files with
 * constraint data parsed by Eclipse from the <tt>plugin.xml</tt>.
 * 
 */
public class ItemsConfigurationElement extends XmlConfigurationElement {
	/**
	 * Initializes me as a copy of <CODE>original</CODE>, without any
	 * <TT>&lt;include&gt;</TT> children.
	 * 
	 * @param original the original <TT>&lt;constraints&gt;</TT> element
	 * @param url the URL from which the element was loaded, originally
	 */
	public ItemsConfigurationElement(IConfigurationElement original, URL url) {
		super(original.getName(), original.getDeclaringExtension(), url);
		
		setValue(original.getValue());

		String[] names = original.getAttributeNames();

		for (int i = 0; i < names.length; i++) {
			String name = names[i];

			putAttribute(name, original.getAttribute(name));
		}

		IConfigurationElement[] originalChildren = original.getChildren();

		for (int i = 0; i < originalChildren.length; i++) {
			IConfigurationElement child = originalChildren[i];

			if (!XmlPreferencesConfig.E_INCLUDE.equals(child.getName())) {
				// "<include>" elements will be resolved afterwards to
				// insert the referenced constraints
				addChild(child);
			}
		}
	}
}
