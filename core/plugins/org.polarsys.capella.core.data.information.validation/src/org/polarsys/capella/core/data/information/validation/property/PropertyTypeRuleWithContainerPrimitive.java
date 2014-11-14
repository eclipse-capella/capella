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
package org.polarsys.capella.core.data.information.validation.property;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Collection;

/**
 * This check ensures that properties are of type primitive if not bound to an Association.
 * And does nothing if parent of a property is non primitive
 */
public class PropertyTypeRuleWithContainerPrimitive extends AbstractPropertyTypeRule {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isParentPrimitiveCheckApplied(EObject eContainer_p) {
		// if container non primitive: the rule will always return success status
		if (null != eContainer_p) {
			if (eContainer_p instanceof Class) {
				Class cls = (Class) eContainer_p;
				if(!cls.isIsPrimitive()) return true;
			}else if (eContainer_p instanceof Collection) {
				Collection cls = (Collection) eContainer_p;
				if(!cls.isIsPrimitive()) return true;
			}
		}
		
		return false;
	}

}
