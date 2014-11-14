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
package org.polarsys.capella.core.business.queries.fa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public abstract class AbstractFunction_RealizedFunctions implements IBusinessQuery {

	/**
	 * @param element_p the abstract function
	 * @return list of Function
	 */
	private List<CapellaElement> getRule_MQRY_AbstractFunction_RealizedFunctions_11(AbstractFunction element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<EObject> allFunctions = new ArrayList<EObject>();

		BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element_p);
	  for (BlockArchitecture block : arch.getAllocatedArchitectures()) {
	    allFunctions.addAll(EObjectExt.getAll(block, FaPackage.Literals.ABSTRACT_FUNCTION));
	  }

		if (null != element_p) {
      EList<FunctionRealization> ownedFunctionRealisations = element_p.getOwnedFunctionRealizations();
      for (FunctionRealization ownedFunctionRealisation : ownedFunctionRealisations) {
        TraceableElement targetElement = ownedFunctionRealisation.getTargetElement();
        if (null != targetElement) {
          if (allFunctions.contains(targetElement)) {
            allFunctions.remove(targetElement);
          } 
        } 
      }
    }

		for (EObject function : allFunctions) {
		  availableElements.add((CapellaElement) function);
    }
		
		return availableElements;
	}

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    
		if (element_p instanceof AbstractFunction) {
		  AbstractFunction element = (AbstractFunction) element_p;
			availableElements.addAll(getRule_MQRY_AbstractFunction_RealizedFunctions_11(element));
		} 
		 
		return availableElements;
	}

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement, boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		if (element_p instanceof AbstractFunction) {
		  AbstractFunction fct = (AbstractFunction) element_p;
      EList<FunctionRealization> ownedFunctionRealisations = fct.getOwnedFunctionRealizations();
      for (FunctionRealization ownedFunctionRealisation : ownedFunctionRealisations) {
        TraceableElement targetElement = ownedFunctionRealisation.getTargetElement();
        if (null != targetElement) {
          if (targetElement instanceof AbstractFunction) {
            currentElements.add((AbstractFunction) targetElement);            
          }
        }
      }
		}

		return currentElements;
	}

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS);
	}
}
