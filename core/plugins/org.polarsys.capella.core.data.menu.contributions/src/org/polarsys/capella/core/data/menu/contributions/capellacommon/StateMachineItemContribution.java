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
  public Command executionContribution(EditingDomain editingDomain, ModelElement containerElement, ModelElement createdElement,
      EStructuralFeature feature) {
    Command command = null;
    // We'd rather check created element.
    if (createdElement instanceof StateMachine) {
      // Create a Region named Default Region if none
      StateMachine stateMachine = (StateMachine) createdElement;
      if (stateMachine.getOwnedRegions().isEmpty()) {
        Region defaultRegion = CapellacommonFactory.eINSTANCE.createRegion(); // Use classic EMF creation.
        defaultRegion.setName(NamingConstants.Region_DefaultRegion);
        command = AddCommand.create(editingDomain, createdElement, CapellacommonPackage.Literals.STATE_MACHINE__OWNED_REGIONS, defaultRegion);
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
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return true;
  }
}
