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
package org.polarsys.capella.core.data.menu.contributions.epbs;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemKind;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsPackage;

public class ConfigurationItemItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain, ModelElement containerElement, final ModelElement createdElement,
      EStructuralFeature feature) {
    if (createdElement instanceof ConfigurationItem) {
      EObject partOwner = null;
      if (containerElement instanceof ConfigurationItem || containerElement instanceof ConfigurationItemPkg) {
        partOwner = containerElement;
      }
      if (partOwner == null) {
        EObject arch = EcoreUtil2.getFirstContainer(containerElement, EpbsPackage.Literals.EPBS_ARCHITECTURE);
        if (arch != null) {
          partOwner = ((EPBSArchitecture) arch).getOwnedConfigurationItemPkg();
        }
      }

      CompoundCommand cmd = new CompoundCommand();

      if (((Component) createdElement).getRepresentingParts().size() == 0) {

        if (partOwner != null) {
          EStructuralFeature ownedPartFeature = CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES;
          if (partOwner instanceof ComponentPkg) {
            ownedPartFeature = CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS;
          }
          // Creates the part.
          final Command createPartCmd =
              CreateChildCommand.create(editingDomain, partOwner, new CommandParameter(createdElement,
                  ownedPartFeature, CsFactory.eINSTANCE.createPart()), Collections.EMPTY_LIST);
          cmd.append(createPartCmd);

          // Sets the part name.
          final Command setPartNameCmd = new CommandWrapper() {
            @Override
            public Command createCommand() {
              Collection<?> res = createPartCmd.getResult();
              if (res.size() == 1) {
                Object createdPart = res.iterator().next();
                if (createdPart instanceof EObject) {
                  return new SetCommand(editingDomain, (EObject) createdPart, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
                      ((AbstractNamedElement) createdElement).getName());
                }
              }
              return new IdentityCommand();
            }
          };
          cmd.append(setPartNameCmd);

          // Sets the part type.
          Command setPartTypeCmd = new CommandWrapper() {
            @Override
            public Command createCommand() {
              Collection<?> res = setPartNameCmd.getResult();
              if (res.size() == 1) {
                Object createdPart = res.iterator().next();
                if (createdPart instanceof EObject) {
                  return new SetCommand(editingDomain, (EObject) createdPart, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE,
                      createdElement);
                }
              }
              return null;
            }
          };
          cmd.append(setPartTypeCmd);

        }
      }

      //Set the name if kind is already set
      ConfigurationItem item = (ConfigurationItem) createdElement;
      if (item.getKind() != ConfigurationItemKind.UNSET) {
        if (createdElement instanceof AbstractNamedElement) {
          String name = ((AbstractNamedElement) createdElement).getName();
          if ((name == null) || name.startsWith(createdElement.eClass().getName())) {
            cmd.append(CreationHelper.getNamingCommand(editingDomain, (AbstractNamedElement) createdElement, containerElement, feature, item.getKind()
                .getName()));
          }
        }
      }
      return cmd;
    }
    return new IdentityCommand();
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return true;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return EpbsPackage.Literals.CONFIGURATION_ITEM;
  }
}
