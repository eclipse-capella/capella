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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.TypeHelper;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemRealization;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class ExchangeItemHelper {
	private static ExchangeItemHelper instance;
	
	private ExchangeItemHelper() {
	  //
	}
	
	public static ExchangeItemHelper getInstance(){
		if(instance == null)
			instance = new ExchangeItemHelper();
		return instance;
	}
	
	public Object doSwitch(ExchangeItem element_p, EStructuralFeature feature_p){
		Object ret = null;

		if (feature_p.equals(InformationPackage.Literals.EXCHANGE_ITEM__REALIZED_EXCHANGE_ITEMS)) {
      ret = getRealizedExchangeItems(element_p);
    } else if (feature_p.equals(InformationPackage.Literals.EXCHANGE_ITEM__REALIZING_EXCHANGE_ITEMS)) {
      ret = getRealizingExchangeItems(element_p);
    } else if (feature_p.equals(InformationPackage.Literals.EXCHANGE_ITEM__REALIZING_OPERATIONS)) {
      ret = getRealizingOperations(element_p);
    } else if (feature_p.equals(InformationPackage.Literals.EXCHANGE_ITEM__ALLOCATOR_INTERFACES)) {
      ret = getAllocatorInterfaces(element_p);
    }

    // no helper found... searching in super classes...
		if(null == ret) {
			ret = TypeHelper.getInstance().doSwitch(element_p, feature_p);
		}
		return ret;
	}

  /**
   *
   */
  protected List<ExchangeItem> getRealizedExchangeItems(ExchangeItem element_p) {
    List <ExchangeItem> ret = new ArrayList <ExchangeItem>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof InformationRealization) {
        TraceableElement elt = trace.getTargetElement();
        if (elt instanceof ExchangeItem) {
          ret.add((ExchangeItem) elt);
        }
      }
    }
    return ret;
  }

  /**
   *
   */
  protected List<ExchangeItem> getRealizingExchangeItems(ExchangeItem element_p) {
    List <ExchangeItem> ret = new ArrayList <ExchangeItem>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof InformationRealization) {
        TraceableElement elt = trace.getSourceElement();
        if (elt instanceof ExchangeItem) {
          ret.add((ExchangeItem) elt);
        }
      }
    }
    return ret;
  }

  /**
   *
   */
  protected List<Operation> getRealizingOperations(ExchangeItem element_p) {
    List <Operation> ret = new ArrayList <Operation>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof ExchangeItemRealization) {
        Operation op = ((ExchangeItemRealization) trace).getRealizingOperation();
        if (null != op) {
          ret.add(op);
        }
      }
    }
    return ret;
  }

  /**
   *
   */
  protected List<Interface> getAllocatorInterfaces(ExchangeItem element_p) {
    List<Interface> ret = new ArrayList<Interface>();
    for (EObject obj : EObjectExt.getReferencers(element_p, CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM)) {
      if (obj instanceof ExchangeItemAllocation) {
        Interface itf = ((ExchangeItemAllocation) obj).getAllocatingInterface();
        if (null != itf) {
          ret.add(itf);
        }
      }
    }
    return ret;
  }
}
