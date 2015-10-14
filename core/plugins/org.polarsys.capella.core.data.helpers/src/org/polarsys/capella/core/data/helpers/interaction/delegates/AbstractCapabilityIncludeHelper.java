/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.helpers.interaction.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

public class AbstractCapabilityIncludeHelper {
	
	private static AbstractCapabilityIncludeHelper instance;

	private AbstractCapabilityIncludeHelper() {
    // do nothing
	}

	public static AbstractCapabilityIncludeHelper getInstance() {
		if (instance == null)
			instance = new AbstractCapabilityIncludeHelper();
		return instance;
	}

	public Object doSwitch(AbstractCapabilityInclude element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY_INCLUDE__INCLUSION)) {
			ret = getInclusion(element);
		}
		
		// No helper found... searching in super classes...
	    if (null == ret) {
	      ret = RelationshipHelper.getInstance().doSwitch(element, feature);
	    }

		return ret;
	}

	protected AbstractCapability getInclusion(AbstractCapabilityInclude element) {
		return element.eContainer() instanceof AbstractCapability? (AbstractCapability)element.eContainer() : null;
	}
}
