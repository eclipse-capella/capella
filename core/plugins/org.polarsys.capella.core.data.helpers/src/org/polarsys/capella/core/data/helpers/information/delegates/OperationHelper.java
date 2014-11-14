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

	public Object doSwitch(Operation element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(InformationPackage.Literals.OPERATION__ALLOCATED_OPERATIONS)) {
			return ret = getAllocatedOperations(element_p);
		} else if (feature_p.equals(InformationPackage.Literals.OPERATION__ALLOCATING_OPERATIONS)) {
			return ret = getAllocatingOperations(element_p);
    } else if (feature_p.equals(InformationPackage.Literals.OPERATION__REALIZED_EXCHANGE_ITEMS)) {
      ret = getRealizedExchangeItems(element_p);
		} 

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = FeatureHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = AbstractTypeHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = AbstractEventOperationHelper.getInstance().doSwitch(element_p, feature_p);
    }

		return ret;
	}

  /**
   *
   */
	protected List<Operation> getAllocatedOperations(Operation element_p) {
		List <Operation> ret = new ArrayList<Operation>();
		for (AbstractTrace trace : element_p.getOutgoingTraces()) {	
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
	protected List<Operation> getAllocatingOperations(Operation element_p) {
		List <Operation> ret = new ArrayList<Operation>();
		for (AbstractTrace trace : element_p.getIncomingTraces()) {	
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
  protected List<ExchangeItem> getRealizedExchangeItems(Operation element_p) {
    List <ExchangeItem> ret = new ArrayList <ExchangeItem>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
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
