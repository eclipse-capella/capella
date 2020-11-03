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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.PhysicalLink;

/**
 * Return allocating physical link of current component exchange
 * 
 *
 */
public class ComponentExchangeAllocatingPhysicalLink extends AbsComponentExchangeAllocatingElements {

	public ComponentExchangeAllocatingPhysicalLink() {
    // do nothing
	}

	@Override
	public boolean isValidInstanceOf(EObject element) {
		if (null != element && element instanceof PhysicalLink) {
			return true;
		}
		return false;
	}
	
}
