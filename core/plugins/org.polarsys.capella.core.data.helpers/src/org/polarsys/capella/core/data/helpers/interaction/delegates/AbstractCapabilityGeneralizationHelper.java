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

package org.polarsys.capella.core.data.helpers.interaction.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

public class AbstractCapabilityGeneralizationHelper {
	
	private static AbstractCapabilityGeneralizationHelper instance;

	private AbstractCapabilityGeneralizationHelper() {
    // do nothing
	}

	public static AbstractCapabilityGeneralizationHelper getInstance() {
		if (instance == null)
			instance = new AbstractCapabilityGeneralizationHelper();
		return instance;
	}

	public Object doSwitch(AbstractCapabilityGeneralization element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION__SUB)) {
			ret = getSub(element);
		}
		
		// No helper found... searching in super classes...
	    if (null == ret) {
	      ret = RelationshipHelper.getInstance().doSwitch(element, feature);
	    }

		return ret;
	}

	protected AbstractCapability getSub(AbstractCapabilityGeneralization element) {
		return element.eContainer() instanceof AbstractCapability ? (AbstractCapability)element.eContainer() : null;
	}
}
