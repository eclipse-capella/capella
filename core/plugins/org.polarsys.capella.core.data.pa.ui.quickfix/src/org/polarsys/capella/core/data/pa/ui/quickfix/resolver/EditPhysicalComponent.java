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
package org.polarsys.capella.core.data.pa.ui.quickfix.resolver;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.validation.ui.ide.quickfix.CapellaElementEditResolver;

/**
 * Open capella element editor to edit respected values
 * 
 */
public class EditPhysicalComponent extends CapellaElementEditResolver {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEditableInstance(EObject value) {

		if (value instanceof PhysicalComponent) {
			return true;
		}
		return false;
	}

	@Override
	protected String[] getResolvableRuleIds() {
		return noRuleIds;
	}

}
