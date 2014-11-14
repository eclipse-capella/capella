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
package org.polarsys.capella.core.commands.preferences.xml;

import org.eclipse.core.runtime.IConfigurationElement;

import org.polarsys.capella.core.commands.preferences.service.IItemDescriptor;

/**
 * Interface provided by constraint descriptors that are parsed from XML
 * configuration data (such as is implemented in plug-in manifests).
 *
 */

public interface IExtensionPointItemDescriptor extends IItemDescriptor { 
	/**
	 * Obtains the Eclipse configuration element from which I was initialized.
	 * 
	 * @return my Eclipse extension configuration data
	 */
	public IConfigurationElement getConfig();
	
	
}
