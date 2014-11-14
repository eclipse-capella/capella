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

	public Object doSwitch(ExchangeItemAllocation element_p, EStructuralFeature feature_p) {
		Object ret = null;
    
    if (feature_p.equals(CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE)) {
      ret = getAllocatingInterface(element_p);
    }

    // no helper found... searching in super classes...
    if(null == ret) {
      ret = RelationshipHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if(null == ret) {
      ret = AbstractEventOperationHelper.getInstance().doSwitch(element_p, feature_p);
    }

		return ret;
	}

  protected Interface getAllocatingInterface(ExchangeItemAllocation element_p) {
    if (null != element_p) {
      EObject owner = element_p.eContainer();
      if (owner instanceof Interface) {
        return (Interface) owner;
      }
    }
    return null;
  }
}
