/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
