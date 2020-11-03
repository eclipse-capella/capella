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
package org.polarsys.capella.core.data.menu.contributions.information;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.information.ElementKind;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.InformationPackage;

public class ExchangeItemElementItemContribution extends MultiplicityElementItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return true;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain, final ModelElement containerElement, final ModelElement createdElement,
      EStructuralFeature feature) {
    if (InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT.isInstance(createdElement)) {
      CompoundCommand cmd = new CompoundCommand();

      cmd.append(getCardinalitiesCommand(editingDomain, createdElement, _ONE_CARDINALITY, _ONE_CARDINALITY));

      // Set the kind of exchange item element according container
      Command setKindCmd = new CommandWrapper() {
        @Override
        public Command createCommand() {
          ElementKind kind = ElementKind.TYPE;
          EObject container = containerElement;
          if ((container != null) && (container instanceof ExchangeItem)) {
            ExchangeItem item = (ExchangeItem) container;
            if (item.getExchangeMechanism() == ExchangeMechanism.OPERATION) {
              kind = ElementKind.MEMBER;
            }
            return new SetCommand(editingDomain, createdElement, InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT__KIND, kind);
          }
          return null;
        }
      };
      cmd.append(setKindCmd);

      // Set the isComposite of exchange item element
      cmd.append(new SetCommand(editingDomain, createdElement, InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT__COMPOSITE, Boolean.TRUE));

      return cmd;
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT;
  }
}
