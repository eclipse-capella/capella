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
package org.polarsys.capella.core.data.selection.queries.fa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.selection.ILinkSelection;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.common.data.activity.ActivityNode;

/**
 */
public class FunctionalExchangeSelection implements ILinkSelection {

  /**
   * @see org.polarsys.capella.core.data.core.utils.selection.ILinkSelection#getAvailableTargetEClass()
   */
  public List<EClass> getAvailableTargetEClass() {
    List<EClass> returnedList = new ArrayList<EClass>();
    returnedList.add(FaPackage.Literals.ABSTRACT_FUNCTION);
    return null;
  }

  /**
   * @see org.polarsys.capella.core.data.core.utils.selection.ILinkSelection#getDisplayedTarget(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
   */
  public EObject getDisplayedTarget(EObject object_p, EObject context_p) {
    if (object_p instanceof FunctionalExchange) {
      FunctionalExchange currentExchange = (FunctionalExchange) object_p;
      if (context_p == null) {
        return getLinkedFunction(currentExchange.getTarget());
        
      } else if (context_p instanceof AbstractFunction) {
        AbstractFunction returnedFunction = getLinkedFunction(currentExchange.getSource());
        //if returnFunction equals to context_p or if one of its parent is context_p
        // return target
        List<AbstractFunction> parentFunctions = FunctionExt.getParentFunctions(returnedFunction);
        if (returnedFunction.equals(context_p) || parentFunctions.contains(context_p)) {
          returnedFunction = getLinkedFunction(currentExchange.getTarget());
        }
        return returnedFunction;
      }
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.core.data.core.utils.selection.ILinkSelection#getEClass()
   */
  public EClass getEClass() {
    return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
  }

  public AbstractFunction getLinkedFunction(ActivityNode activityNode_p) {
    if (activityNode_p instanceof AbstractFunction) {
      return (AbstractFunction) activityNode_p;
    }
    return (AbstractFunction) activityNode_p.eContainer();
  }

}
