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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.helpers.information.delegates.AbstractEventOperationHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;

public class ExchangeItemAllocationHelper {
	private static ExchangeItemAllocationHelper instance;

	private ExchangeItemAllocationHelper() {
    // do nothing
	}

	public static ExchangeItemAllocationHelper getInstance() {
		if (instance == null)
			instance = new ExchangeItemAllocationHelper();
		return instance;
	}

	public Object doSwitch(ExchangeItemAllocation element, EStructuralFeature feature) {
		Object ret = null;
    
    if (feature.equals(CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE)) {
      ret = getAllocatingInterface(element);
    }

    // no helper found... searching in super classes...
    if(null == ret) {
      ret = RelationshipHelper.getInstance().doSwitch(element, feature);
    }
    if(null == ret) {
      ret = AbstractEventOperationHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}

  protected Interface getAllocatingInterface(ExchangeItemAllocation element) {
    if (null != element) {
      EObject owner = element.eContainer();
      if (owner instanceof Interface) {
        return (Interface) owner;
      }
    }
    return null;
  }
}
