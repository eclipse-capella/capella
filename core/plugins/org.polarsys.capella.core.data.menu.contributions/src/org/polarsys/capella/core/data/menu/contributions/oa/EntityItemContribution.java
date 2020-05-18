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
package org.polarsys.capella.core.data.menu.contributions.oa;

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
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;

public class EntityItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return !(modelElement instanceof Component && ((Component)modelElement).isHuman());
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain, ModelElement containerElement, final ModelElement createdElement,
      EStructuralFeature feature) {
    if (createdElement instanceof Entity) {
      if (((Component) createdElement).getRepresentingParts().size() == 0) {
        EObject partOwner = null;
        if (containerElement instanceof Entity || containerElement instanceof EntityPkg) {
          partOwner = containerElement;
        }
        if (partOwner == null) {
          EObject pkg = EcoreUtil2.getFirstContainer(containerElement, OaPackage.Literals.OPERATIONAL_ANALYSIS);
          if (pkg != null) {
            partOwner = ((OperationalAnalysis) pkg).getOwnedEntityPkg();
          }
        }

        if (partOwner != null) {
          CompoundCommand cmd = new CompoundCommand();
          
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

          return cmd;
        }
      }
    }
    return new IdentityCommand();
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return OaPackage.Literals.ENTITY;
  }
}
