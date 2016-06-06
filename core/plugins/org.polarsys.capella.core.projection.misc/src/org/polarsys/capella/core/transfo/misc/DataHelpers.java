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
package org.polarsys.capella.core.transfo.misc;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

public class DataHelpers {

	private DataHelpers(){
	}
	/**
	 *  Check if elt2 element is belong to Data package and same layer than elt1 
	 */
	public static boolean isBelongToSameDataPkgLayer(ModelElement elt1, ModelElement elt2) {
		if (elt1 instanceof CapellaElement && elt2 instanceof CapellaElement) {
			if (EcoreUtil2.isContainedBy(elt2, InformationPackage.Literals.DATA_PKG) 
				&& CapellaLayerCheckingExt.getLayersName((CapellaElement) elt1) == CapellaLayerCheckingExt.getLayersName((CapellaElement)elt2)) {
				return true;
			}
		}
		return false;
	}
}
