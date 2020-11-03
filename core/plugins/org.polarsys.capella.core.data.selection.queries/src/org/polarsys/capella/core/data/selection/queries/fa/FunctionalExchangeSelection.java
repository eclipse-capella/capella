/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
  public EObject getDisplayedTarget(EObject object, EObject context) {
    if (object instanceof FunctionalExchange) {
      FunctionalExchange currentExchange = (FunctionalExchange) object;
      if (context == null) {
        return getLinkedFunction(currentExchange.getTarget());
        
      } else if (context instanceof AbstractFunction) {
        AbstractFunction returnedFunction = getLinkedFunction(currentExchange.getSource());
        //if returnFunction equals to context or if one of its parent is context
        // return target
        List<AbstractFunction> parentFunctions = FunctionExt.getParentFunctions(returnedFunction);
        if (returnedFunction.equals(context) || parentFunctions.contains(context)) {
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

  public AbstractFunction getLinkedFunction(ActivityNode activityNode) {
    if (activityNode instanceof AbstractFunction) {
      return (AbstractFunction) activityNode;
    }
    return (AbstractFunction) activityNode.eContainer();
  }

}
