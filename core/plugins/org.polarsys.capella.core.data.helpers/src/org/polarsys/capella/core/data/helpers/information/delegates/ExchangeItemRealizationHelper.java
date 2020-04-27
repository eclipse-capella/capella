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

package org.polarsys.capella.core.data.helpers.information.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.core.data.information.ExchangeItemRealization;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class ExchangeItemRealizationHelper {
	private static ExchangeItemRealizationHelper instance;

	private ExchangeItemRealizationHelper() {
    // do nothing
	}

	public static ExchangeItemRealizationHelper getInstance() {
		if (instance == null)
			instance = new ExchangeItemRealizationHelper();
		return instance;
	}

	public Object doSwitch(ExchangeItemRealization element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(InformationPackage.Literals.EXCHANGE_ITEM_REALIZATION__REALIZED_ITEM)) {
			ret = getRealizedItem(element);
		}
		else if (feature.equals(InformationPackage.Literals.EXCHANGE_ITEM_REALIZATION__REALIZING_OPERATION)) {
			ret = getRealizingOperation(element);
		} 

    // no helper found... searching in super classes...
    if(null == ret) {
      ret = AllocationHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}

	protected AbstractExchangeItem getRealizedItem(ExchangeItemRealization element) {
		TraceableElement ret = element.getTargetElement();
		if( ret instanceof AbstractExchangeItem)
			return (AbstractExchangeItem) ret;
		return null;
	}

	protected Operation getRealizingOperation(ExchangeItemRealization element) {
		TraceableElement ret = element.getSourceElement();
		if (ret instanceof Operation)
			return (Operation) ret;
		return null;
	}
}
