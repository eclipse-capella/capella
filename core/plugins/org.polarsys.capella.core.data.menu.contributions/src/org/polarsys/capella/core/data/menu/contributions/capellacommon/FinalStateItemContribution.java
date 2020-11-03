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

import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;

public class FinalStateItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    if ((modelElement instanceof StateMachine) || (modelElement instanceof State)) {
      return false;
    }
    return true;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain, ModelElement containerElement, final ModelElement createdElement, EStructuralFeature feature) {
    if (createdElement instanceof FinalState) {
      CompoundCommand cmd = new CompoundCommand();

      // Creates the region.
      final Command createRegionCmd = CreateChildCommand.create(editingDomain, createdElement, new CommandParameter(createdElement,
        CapellacommonPackage.Literals.STATE__OWNED_REGIONS, CapellacommonFactory.eINSTANCE.createRegion("region")), Collections.EMPTY_LIST); //$NON-NLS-1$
      cmd.append(createRegionCmd);

      if (createdElement instanceof AbstractNamedElement) {
	      String name = ((AbstractNamedElement) createdElement).getName();
	      if ((name == null) || name.startsWith(createdElement.eClass().getName())) {
	        return CreationHelper.getNamingCommand(editingDomain, (AbstractNamedElement) createdElement, containerElement, feature,
	            NamingConstants.FinalState_Name);
	      }
      }
      
      EObject superContainer = containerElement.eContainer();
      if (superContainer instanceof State) {
		State superContainerState = (State) superContainer;
		cmd.append(new AddCommand(editingDomain, superContainerState, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES, createdElement));
	  }

      return cmd;
    }
	  return new IdentityCommand();
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return CapellacommonPackage.Literals.FINAL_STATE;
  }
}
