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

package org.polarsys.capella.core.data.helpers.interaction.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

public class AbstractCapabilityExtendHelper {
	
	private static AbstractCapabilityExtendHelper instance;

	private AbstractCapabilityExtendHelper() {
    // do nothing
	}

	public static AbstractCapabilityExtendHelper getInstance() {
		if (instance == null)
			instance = new AbstractCapabilityExtendHelper();
		return instance;
	}

	public Object doSwitch(AbstractCapabilityExtend element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENSION)) {
			ret = getExtension(element);
		}
		
		// No helper found... searching in super classes...
	    if (null == ret) {
	      ret = RelationshipHelper.getInstance().doSwitch(element, feature);
	    }

		return ret;
	}

	protected AbstractCapability getExtension(AbstractCapabilityExtend element) {
		return element.eContainer() instanceof AbstractCapability ? (AbstractCapability)element.eContainer() : null;
	}
}
