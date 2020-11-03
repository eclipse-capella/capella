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
package org.polarsys.capella.core.data.selection.queries.information;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.selection.ILinkSelection;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;

public class PortAllocationSelection implements ILinkSelection {

	/**
	   * @see org.polarsys.capella.core.data.core.utils.selection.ILinkSelection#getAvailableTargetEClass()
	   */
	  public List<EClass> getAvailableTargetEClass() {
		  return null;
	  }

	  /**
	   * @see org.polarsys.capella.core.data.core.utils.selection.ILinkSelection#getDisplayedTarget(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	   */
	  public EObject getDisplayedTarget(EObject object, EObject context) {
		 if ((object instanceof PortAllocation) && (context instanceof Port)) {
			 PortAllocation currentAllocation = (PortAllocation) object;
			 if (currentAllocation.getAllocatedPort().equals(context)) {
				 return currentAllocation.getAllocatingPort();
			 } else if (currentAllocation.getAllocatingPort().equals(context)) {
				 return currentAllocation.getAllocatedPort();
			 }
 	     }
		 return context;
	  }

	  /**
	   * @see org.polarsys.capella.core.data.core.utils.selection.ILinkSelection#getEClass()
	   */
	  public EClass getEClass() {
	    return InformationPackage.Literals.PORT_ALLOCATION;
	  }


}
