/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Type;

public class AssociationSelection implements ILinkSelection {

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
		 if ((object instanceof Association) && (context instanceof Classifier)) {
			 Association currentAssociation = (Association) object;
			 for (Property property : currentAssociation.getOwnedMembers()) {
				 Type type = property.getType();
				if (type != null && !type.equals(context)) {
					//opposite element of the context via association
					return type;
				}
			 }
			 for (Property property : currentAssociation.getNavigableMembers()) {
				 Type type = property.getType();
				if (type != null && !type.equals(context)) {
					//opposite element of the context via association
					return type;
				}
			 }
			 // self association
			 return context;
 	    }
	    return null;
	  }

	  /**
	   * @see org.polarsys.capella.core.data.core.utils.selection.ILinkSelection#getEClass()
	   */
	  public EClass getEClass() {
	    return InformationPackage.Literals.ASSOCIATION;
	  }


}
