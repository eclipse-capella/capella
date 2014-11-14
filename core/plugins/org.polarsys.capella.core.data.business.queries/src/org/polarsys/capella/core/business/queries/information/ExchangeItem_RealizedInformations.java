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
package org.polarsys.capella.core.business.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.business.queries.capellacore.AbstractExchangeItem_RealizedInformations;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.InformationRealization;

/**
 */
public class ExchangeItem_RealizedInformations extends AbstractExchangeItem_RealizedInformations {

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement, boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		if (element_p instanceof ExchangeItem) {
		  for (InformationRealization elt : ((ExchangeItem) element_p).getOwnedInformationRealizations()) {
			  currentElements.add((CapellaElement) elt.getTargetElement());
		  }
		}

		return currentElements;
	}

	public EClass getEClass() {
		return InformationPackage.Literals.EXCHANGE_ITEM;
	}

	public List<EReference> getEStructuralFeatures() {
	  List<EReference> list = new ArrayList<EReference>(1);
	  list.add(InformationPackage.Literals.EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS);	  
    return list;
	}
}
