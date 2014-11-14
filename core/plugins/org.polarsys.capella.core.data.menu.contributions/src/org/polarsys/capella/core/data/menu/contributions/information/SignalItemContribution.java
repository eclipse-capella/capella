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
package org.polarsys.capella.core.data.menu.contributions.information;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.core.data.information.communication.CommunicationFactory;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.communication.Signal;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;

public class SignalItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement_p, EClass cls_p, EStructuralFeature feature_p) {
    return false;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain_p, ModelElement containerElement_p, final ModelElement createdElement_p, EStructuralFeature feature_p) {
    if (createdElement_p instanceof Signal) {
      CompoundCommand cmd = new CompoundCommand();

      // Creates the signal instance.
      final Command createSignalInstanceCmd = CreateChildCommand.create(editingDomain_p, createdElement_p, new CommandParameter(createdElement_p,
        CommunicationPackage.Literals.SIGNAL__SIGNAL_INSTANCES, CommunicationFactory.eINSTANCE.createSignalInstance(((AbstractNamedElement) createdElement_p).getName())), Collections.EMPTY_LIST);
      cmd.append(createSignalInstanceCmd);

      // Sets the signal instance type.
      Command setSignalInstanceTypeCmd = new CommandWrapper() {
        @Override
        public Command createCommand() {
          Collection<?> res = createSignalInstanceCmd.getResult();
          if (res.size() == 1) {
            Object createdObj = res.iterator().next();
            if (createdObj instanceof EObject) {
              return new SetCommand(editingDomain_p, (EObject) createdObj, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createdElement_p);
            }
          }
          return null;
        }
      };
      cmd.append(setSignalInstanceTypeCmd);

      return cmd;
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return CommunicationPackage.Literals.SIGNAL;
  }
}
