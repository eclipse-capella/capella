/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.validation.property;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Collection;

/**
 * This check ensures that properties are of type primitive if not bound to an Association.
 * And does nothing if parent of a property is primitive
 */
public class PropertyTypeRule extends AbstractPropertyTypeRule {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isParentPrimitiveCheckApplied(EObject eContainer) {
		// if container primitive: the rule will always return success status
		if (null != eContainer) {
			if (eContainer instanceof Class) {
				Class cls = (Class) eContainer;
				if(cls.isIsPrimitive()) return true;
			}else if (eContainer instanceof Collection) {
				Collection cls = (Collection) eContainer;
				if(cls.isIsPrimitive()) return true;
			}
		}
		return false;
	}
 
}
