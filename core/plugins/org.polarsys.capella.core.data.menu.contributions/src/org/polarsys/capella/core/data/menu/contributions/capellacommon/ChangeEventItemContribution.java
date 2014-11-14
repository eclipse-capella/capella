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
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.ChangeEvent;
import org.polarsys.capella.core.data.capellacommon.ChangeEventKind;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;

public class ChangeEventItemContribution implements IMDEMenuItemContribution {

  @Override
  public EClass getMetaclass() {
    return CapellacommonPackage.Literals.CHANGE_EVENT;
  }

  @Override
  public Command executionContribution(EditingDomain editingDomain_p, ModelElement containerElement_p, ModelElement createdElement_p,
      EStructuralFeature feature_p) {
    if (createdElement_p instanceof ChangeEvent) {
      CompoundCommand cmd = new CompoundCommand();
      if (containerElement_p instanceof DataPkg) {
        // Sets the container region involved states.
        ((ChangeEvent) createdElement_p).setKind(ChangeEventKind.WHEN);
        cmd.append(new AddCommand(editingDomain_p, containerElement_p, InformationPackage.Literals.DATA_PKG__OWNED_STATE_EVENTS, createdElement_p));
      }
      return cmd;
    }
    return new IdentityCommand();
  }

  @Override
  public boolean selectionContribution(ModelElement modelElement_p, EClass cls_p, EStructuralFeature feature_p) {
    return true;
  }

}
