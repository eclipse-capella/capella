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
package org.polarsys.capella.core.transfo.misc;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

public class DataHelpers {

	/**
	 *  Check if elt2_p element is belong to Data package and same layer than elt1_p 
	 */
	public static boolean isBelongToSameDataPkgLayer(ModelElement elt1_p, ModelElement elt2_p) {
		if (elt1_p instanceof CapellaElement && elt2_p instanceof CapellaElement) {
			if (null != elt2_p && EcoreUtil2.isContainedBy(elt2_p, InformationPackage.Literals.DATA_PKG) 
				&& CapellaLayerCheckingExt.getLayersName((CapellaElement) elt1_p) == CapellaLayerCheckingExt.getLayersName((CapellaElement)elt2_p)) {
				return true;
			}
		}
		return false;
	}
}
