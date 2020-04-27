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
package org.polarsys.capella.core.data.menu.contributions.ctx;

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
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class CapabilityItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain, ModelElement containerElement,
      final ModelElement createdElement, EStructuralFeature feature) {
    if (createdElement instanceof Capability) {
      // Links the capability to the System
      SystemEngineering sysEng = (SystemEngineering) EcoreUtil2.getFirstContainer(containerElement,
          CapellamodellerPackage.Literals.SYSTEM_ENGINEERING);
      if (sysEng != null) {
        SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
        if (ca != null) {
          Component sys = ca.getSystem();
          if (sys != null) {
            CompoundCommand cmd = new CompoundCommand();

            // Creates the capability supplier link.
            final Command createLinkCmd = CreateChildCommand.create(editingDomain, createdElement,
                new CommandParameter(createdElement, CtxPackage.Literals.CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS,
                    CtxFactory.eINSTANCE.createCapabilityInvolvement()),
                Collections.EMPTY_LIST);
            cmd.append(createLinkCmd);

            // Sets the linked system.
            Command setLinkedSystemCmd = new CommandWrapper() {
              @Override
              public Command createCommand() {
                Collection<?> res = createLinkCmd.getResult();
                if (res.size() == 1) {
                  Object createdObj = res.iterator().next();
                  if (createdObj instanceof EObject) {
                    return new SetCommand(editingDomain, (EObject) createdObj,
                        CapellacorePackage.Literals.INVOLVEMENT__INVOLVED, sys);
                  }
                }
                return null;
              }
            };
            cmd.append(setLinkedSystemCmd);

            return cmd;
          }
        }
      }
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return CtxPackage.Literals.CAPABILITY;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return (!EcoreUtil2.isContainedBy(modelElement, LaPackage.Literals.LOGICAL_ARCHITECTURE)
        && !EcoreUtil2.isContainedBy(modelElement, PaPackage.Literals.PHYSICAL_ARCHITECTURE)
        && !EcoreUtil2.isContainedBy(modelElement, EpbsPackage.Literals.EPBS_ARCHITECTURE)
        && !EcoreUtil2.isContainedBy(modelElement, FaPackage.Literals.FUNCTION_PKG));
  }
}
