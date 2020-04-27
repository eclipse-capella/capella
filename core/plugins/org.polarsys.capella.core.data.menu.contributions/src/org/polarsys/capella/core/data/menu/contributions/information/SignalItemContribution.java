/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return false;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain, ModelElement containerElement, final ModelElement createdElement, EStructuralFeature feature) {
    if (createdElement instanceof Signal) {
      CompoundCommand cmd = new CompoundCommand();

      // Creates the signal instance.
      final Command createSignalInstanceCmd = CreateChildCommand.create(editingDomain, createdElement, new CommandParameter(createdElement,
        CommunicationPackage.Literals.SIGNAL__SIGNAL_INSTANCES, CommunicationFactory.eINSTANCE.createSignalInstance(((AbstractNamedElement) createdElement).getName())), Collections.EMPTY_LIST);
      cmd.append(createSignalInstanceCmd);

      // Sets the signal instance type.
      Command setSignalInstanceTypeCmd = new CommandWrapper() {
        @Override
        public Command createCommand() {
          Collection<?> res = createSignalInstanceCmd.getResult();
          if (res.size() == 1) {
            Object createdObj = res.iterator().next();
            if (createdObj instanceof EObject) {
              return new SetCommand(editingDomain, (EObject) createdObj, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createdElement);
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
