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

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;

public class PhysicalComponentItemContribution implements IMDEMenuItemContribution {
  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain_p, ModelElement containerElement_p, final ModelElement createdElement_p,
      EStructuralFeature feature_p) {
    if (createdElement_p instanceof PhysicalComponent) {
      EObject partOwner =
          (containerElement_p instanceof PhysicalComponent) ? containerElement_p : EcoreUtil2.getFirstContainer(containerElement_p,
              PaPackage.Literals.PHYSICAL_COMPONENT);
      if (partOwner == null) {
        EObject arch = EcoreUtil2.getFirstContainer(containerElement_p, PaPackage.Literals.PHYSICAL_ARCHITECTURE);
        if (arch != null) {
          partOwner = ((PhysicalArchitecture) arch).getOwnedPhysicalContext();
        }
      }

      if (partOwner != null) {
        CompoundCommand cmd = new CompoundCommand();

        if (((PartitionableElement) createdElement_p).getRepresentingPartitions().size() == 0) {

          // Creates the part.
          final Command createPartCmd =
              CreateChildCommand.create(editingDomain_p, partOwner, new CommandParameter(createdElement_p,
                  CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES, CsFactory.eINSTANCE.createPart()), Collections.EMPTY_LIST);
          cmd.append(createPartCmd);

          // Sets the part name.
          final Command setPartNameCmd = new CommandWrapper() {
            @Override
            public Command createCommand() {
              Collection<?> res = createPartCmd.getResult();
              if (res.size() == 1) {
                Object createdPart = res.iterator().next();
                if (createdPart instanceof EObject) {
                  return new SetCommand(editingDomain_p, (EObject) createdPart, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
                      ((AbstractNamedElement) createdElement_p).getName());
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
                  return new SetCommand(editingDomain_p, (EObject) createdPart, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE,
                      createdElement_p);
                }
              }
              return null;
            }
          };
          cmd.append(setPartTypeCmd);

        }

        if (partOwner instanceof PhysicalComponent){

          // don't initialize nature if the current one has already been set to a non-default.
          if (((PhysicalComponent) createdElement_p).getNature() == null ||
              ((PhysicalComponent) createdElement_p).getNature() == PaPackage.Literals.PHYSICAL_COMPONENT_NATURE.getDefaultValue()) {
            PhysicalComponent pc = (PhysicalComponent) partOwner;
            if (pc.getNature().equals(PhysicalComponentNature.UNSET) || pc.getNature().equals(PhysicalComponentNature.NODE)) {
              cmd.append(new SetCommand(editingDomain_p, createdElement_p, PaPackage.Literals.ABSTRACT_PHYSICAL_COMPONENT__NATURE, PhysicalComponentNature.NODE));
            } else if (pc.getNature().equals(PhysicalComponentNature.BEHAVIOR)) {
              cmd.append(new SetCommand(editingDomain_p, createdElement_p, PaPackage.Literals.ABSTRACT_PHYSICAL_COMPONENT__NATURE,
                  PhysicalComponentNature.BEHAVIOR));
            }
          }
        }

        return cmd;
      }
    }
    return new IdentityCommand();
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return PaPackage.Literals.PHYSICAL_COMPONENT;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement_p, EClass cls_p, EStructuralFeature feature_p) {
    return true;
  }
}
