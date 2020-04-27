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
package org.polarsys.capella.core.data.selection.queries.cs;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.selection.ILinkSelection;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;

/**
 *
 */
public class PhysicalLinkSelection implements ILinkSelection {

  /**
   * @see org.polarsys.capella.common.helpers.selection.ILinkSelection#getAvailableTargetEClass()
   */
  public List<EClass> getAvailableTargetEClass() {
    return null;
  }

  /**
   * @see org.polarsys.capella.common.helpers.selection.ILinkSelection#getDisplayedTarget(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
   */
  public EObject getDisplayedTarget(EObject object, EObject context) {
    PhysicalLink currentLink = (PhysicalLink) object;
    Part end1 = PhysicalLinkExt.getSourcePart(currentLink);
    Part end2 = PhysicalLinkExt.getTargetPart(currentLink);
    
    Part contextPart = null;
    if (context instanceof Component){
      Component contextComponent = (Component) context;
      if (contextComponent.getRepresentingParts().isEmpty()){
        return null;
      }
      contextPart = (Part) contextComponent.getRepresentingParts().get(0);
    }
    else {
      contextPart = (Part) context;
    }
    EObject result;
    if (end1.equals(contextPart)){
      result = end2;
    }
    else {
      result = end1;
    }
    if (!TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(contextPart))){
      // mode mono part
      return ((Part) result).getType();
    }
    return result;
  }

  /**
   * @see org.polarsys.capella.common.helpers.selection.ILinkSelection#getEClass()
   */
  public EClass getEClass() {
    return CsPackage.Literals.PHYSICAL_LINK;
  }

}
