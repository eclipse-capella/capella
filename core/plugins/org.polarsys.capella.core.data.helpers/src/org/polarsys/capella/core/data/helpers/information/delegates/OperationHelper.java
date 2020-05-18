/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.FeatureHelper;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemRealization;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.information.OperationAllocation;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.AbstractTypeHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class OperationHelper {
	private static OperationHelper instance;

	private OperationHelper() {
    // do nothing
	}

	public static OperationHelper getInstance() {
		if (instance == null)
			instance = new OperationHelper();
		return instance;
	}

	public Object doSwitch(Operation element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(InformationPackage.Literals.OPERATION__ALLOCATED_OPERATIONS)) {
			return ret = getAllocatedOperations(element);
		} else if (feature.equals(InformationPackage.Literals.OPERATION__ALLOCATING_OPERATIONS)) {
			return ret = getAllocatingOperations(element);
    } else if (feature.equals(InformationPackage.Literals.OPERATION__REALIZED_EXCHANGE_ITEMS)) {
      ret = getRealizedExchangeItems(element);
		} 

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = FeatureHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = AbstractTypeHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = AbstractEventOperationHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}

  /**
   *
   */
	protected List<Operation> getAllocatedOperations(Operation element) {
		List <Operation> ret = new ArrayList<>();
		for (AbstractTrace trace : element.getOutgoingTraces()) {	
			if (trace instanceof OperationAllocation) {
			  Operation op = ((OperationAllocation) trace).getAllocatedOperation();
				if (null != op) ret.add(op);
			}
		}
		return ret;
	}

  /**
   *
   */
	protected List<Operation> getAllocatingOperations(Operation element) {
		List <Operation> ret = new ArrayList<>();
		for (AbstractTrace trace : element.getIncomingTraces()) {	
			if (trace instanceof OperationAllocation) {
        Operation op = ((OperationAllocation) trace).getAllocatingOperation();
        if (null != op) ret.add(op);
			}
		}
		return ret;
	}

  /**
   *
   */
  protected List<ExchangeItem> getRealizedExchangeItems(Operation element) {
    List <ExchangeItem> ret = new ArrayList <>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof ExchangeItemRealization) {
        AbstractExchangeItem item = ((ExchangeItemRealization) trace).getRealizedItem();
        if (item instanceof ExchangeItem) {
          ret.add((ExchangeItem) item);
        }
      }
    }
    return ret;
  }
}
