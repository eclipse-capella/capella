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
package org.polarsys.capella.core.model.obfuscator;

import org.eclipse.emf.ecore.resource.Resource;

public interface IResourceObfuscator {
	
	/**
	 * check if the obfuscator can be applied on this resource.
	 * typical implementation check the type of the resource or its extension
	 * @param ressource_p
	 * @return
	 */
	boolean isApplicableOn (Resource resource_p);
	
	/**
	 * apply the obfuscation on the resource. 
	 * @param ressource_p
	 */
	void obfuscate (Resource resource_p);
}
