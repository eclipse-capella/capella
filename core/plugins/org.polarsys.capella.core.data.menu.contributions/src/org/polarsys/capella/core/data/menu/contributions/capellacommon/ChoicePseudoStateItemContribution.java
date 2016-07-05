/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.ChoicePseudoState;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;

public class ChoicePseudoStateItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement_p, EClass cls_p, EStructuralFeature feature_p) {
    if ((modelElement_p instanceof StateMachine) || (modelElement_p instanceof State)) {
      return false;
    }
    return true;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain_p, ModelElement containerElement_p, final ModelElement createdElement_p, EStructuralFeature feature_p) {
    if (createdElement_p instanceof ChoicePseudoState) {
    	CompoundCommand cmd = new CompoundCommand();
    	if (createdElement_p instanceof AbstractNamedElement) {
  	      String name = ((AbstractNamedElement) createdElement_p).getName();
  	      if ((name == null) || name.startsWith(createdElement_p.eClass().getName())) {
  	        return CreationHelper.getNamingCommand(editingDomain_p, (AbstractNamedElement) createdElement_p, containerElement_p, feature_p,
  	            NamingConstants.ChoicePseudoState_Name);
  	      }
        }
        EObject superContainer = containerElement_p.eContainer();
        if (superContainer instanceof State) {
  		  State superContainerState = (State) superContainer;
  		  cmd.append(new AddCommand(editingDomain_p, superContainerState, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES, createdElement_p));
  	    }
        return cmd;
      }
	  return new IdentityCommand();
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return CapellacommonPackage.Literals.CHOICE_PSEUDO_STATE;
  }
}
