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
package org.polarsys.capella.core.data.menu.contributions.capellacommon;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;

/**
 */
public class StateMachineItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#executionContribution(org.eclipse.emf.edit.domain.EditingDomain,
   *      org.polarsys.capella.common.data.modellingcore.ModelElement, org.polarsys.capella.common.data.modellingcore.ModelElement,
   *      org.eclipse.emf.ecore.EStructuralFeature)
   */
  public Command executionContribution(EditingDomain editingDomain_p, ModelElement containerElement_p, ModelElement createdElement_p,
      EStructuralFeature feature_p) {
    Command command = null;
    // We'd rather check created element.
    if (createdElement_p instanceof StateMachine) {
      // Create a Region named Default Region if none
      StateMachine stateMachine = (StateMachine) createdElement_p;
      if (stateMachine.getOwnedRegions().isEmpty()) {
        Region defaultRegion = CapellacommonFactory.eINSTANCE.createRegion(); // Use classic EMF creation.
        defaultRegion.setName(NamingConstants.Region_DefaultRegion);
        command = AddCommand.create(editingDomain_p, createdElement_p, CapellacommonPackage.Literals.STATE_MACHINE__OWNED_REGIONS, defaultRegion);
      }
    }
    return command;
  }

  /**
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return CapellacommonPackage.Literals.STATE_MACHINE;
  }

  /**
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#selectionContribution(org.polarsys.capella.common.data.modellingcore.ModelElement,
   *      org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EStructuralFeature)
   */
  public boolean selectionContribution(ModelElement modelElement_p, EClass cls_p, EStructuralFeature feature_p) {
    return true;
  }
}
