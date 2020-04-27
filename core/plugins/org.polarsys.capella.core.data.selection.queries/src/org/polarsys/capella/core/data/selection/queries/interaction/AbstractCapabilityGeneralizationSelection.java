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
package org.polarsys.capella.core.data.selection.queries.interaction;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.selection.ILinkSelection;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

public class AbstractCapabilityGeneralizationSelection implements ILinkSelection {

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
    if ((object instanceof AbstractCapabilityGeneralization) && (context instanceof AbstractCapability)) {
      AbstractCapabilityGeneralization currentGeneralization = (AbstractCapabilityGeneralization) object;
      if (currentGeneralization.getSuper().equals(context)) {
        return currentGeneralization.getSub();
      }
      return currentGeneralization.getSuper();
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.core.data.core.utils.selection.ILinkSelection#getEClass()
   */
  public EClass getEClass() {
    return InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION;
  }

}
