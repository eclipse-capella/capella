/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.menu.contributions.pa;

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
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;

public class PhysicalComponentItemContribution implements IMDEMenuItemContribution {
  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  @Override
  public Command executionContribution(final EditingDomain editingDomain, ModelElement container,
      final ModelElement createdElement, EStructuralFeature feature) {

    if (createdElement instanceof PhysicalComponent) {
      PhysicalComponent createdComponent = (PhysicalComponent) createdElement;

      EObject partOwner = getPartOwner(container);

      if (partOwner != null) {
        CompoundCommand wrappingCommand = new CompoundCommand();

        if (createdComponent.getRepresentingParts().isEmpty()) {
          EStructuralFeature ownedPartFeature = CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES;
          if (partOwner instanceof ComponentPkg) {
            ownedPartFeature = CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS;
          }
          // Creates the part.
          final Command createPartCmd = CreateChildCommand.create(editingDomain, partOwner,
              new CommandParameter(createdComponent, ownedPartFeature, CsFactory.eINSTANCE.createPart()),
              Collections.emptyList());

          wrappingCommand.append(createPartCmd);

          // Sets the part name.
          final Command setPartNameCmd = new CommandWrapper() {
            @Override
            public Command createCommand() {
              Collection<?> res = createPartCmd.getResult();
              if (res.size() == 1) {
                Object createdPart = res.iterator().next();
                if (createdPart instanceof EObject) {
                  return new SetCommand(editingDomain, (EObject) createdPart,
                      ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, createdComponent.getName());
                }
              }
              return new IdentityCommand();
            }
          };
          wrappingCommand.append(setPartNameCmd);

          // Sets the part type.
          Command setPartTypeCmd = new CommandWrapper() {
            @Override
            public Command createCommand() {
              Collection<?> res = setPartNameCmd.getResult();
              if (res.size() == 1) {
                Object createdPart = res.iterator().next();
                if (createdPart instanceof EObject) {
                  return new SetCommand(editingDomain, (EObject) createdPart,
                      ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, createdComponent);
                }
              }
              return null;
            }
          };
          wrappingCommand.append(setPartTypeCmd);
        }

        return wrappingCommand;
      }
    }
    return new IdentityCommand();
  }

  private EObject getPartOwner(ModelElement container) {
    EObject partOwner = null;
    if (container instanceof PhysicalComponent || container instanceof PhysicalComponentPkg) {
      partOwner = container;
    }
    if (partOwner == null) {
      EObject arch = EcoreUtil2.getFirstContainer(container, PaPackage.Literals.PHYSICAL_ARCHITECTURE);
      if (arch != null) {
        partOwner = ((PhysicalArchitecture) arch).getOwnedPhysicalComponentPkg();
      }
    }
    return partOwner;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  @Override
  public EClass getMetaclass() {
    return PaPackage.Literals.PHYSICAL_COMPONENT;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  @Override
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return true;
  }
}
