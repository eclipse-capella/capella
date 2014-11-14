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
