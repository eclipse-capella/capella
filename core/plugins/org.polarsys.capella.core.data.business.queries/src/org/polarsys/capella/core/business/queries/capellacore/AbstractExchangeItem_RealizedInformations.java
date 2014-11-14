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
package org.polarsys.capella.core.business.queries.capellacore;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public abstract class AbstractExchangeItem_RealizedInformations implements IBusinessQuery {

	/**
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    BlockArchitecture currentBlockArchitecture = DataPkgExt.getRootBlockArchitecture(element_p);
    if (currentBlockArchitecture != null) {
      for (BlockArchitecture previousBlockArchitecture : BlockArchitectureExt.getPreviousBlockArchitectures(currentBlockArchitecture)) {
        for (EObject elt : EObjectExt.getAll(previousBlockArchitecture, ModellingcorePackage.Literals.ABSTRACT_EXCHANGE_ITEM)) {
          availableElements.add((CapellaElement) elt);
        }
      }
    }

    availableElements = ListExt.removeDuplicates(availableElements);
    availableElements.remove(element_p);

		return availableElements;
	}
}
