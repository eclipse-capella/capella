/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

/**
 * PhysicalArchitecturePkg helpers
 * 
 */
public class PhysicalArchitecturePkgExt {

	/**
	 * This method retrieves all the physical components from the model.
	 *
	 * @param currentElement
	 * @return List<PhysicalComponent>
	 */
	public static List<PhysicalComponent> getAllPhysicalComponents(PhysicalArchitecturePkg currentElement) {
		Set<EObject> pcSet = EObjectExt.getAll(currentElement, PaPackage.Literals.PHYSICAL_COMPONENT);
		List<PhysicalComponent> pcList = new ArrayList<PhysicalComponent>();
		for (EObject obj : pcSet) {
			pcList.add((PhysicalComponent)obj);
		}
		return pcList;
	}
}
