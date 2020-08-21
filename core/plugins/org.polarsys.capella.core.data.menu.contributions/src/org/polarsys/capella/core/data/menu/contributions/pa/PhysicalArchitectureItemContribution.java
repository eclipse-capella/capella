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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreatePhysicalArchiCmd;

public class PhysicalArchitectureItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  @Override
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    if ((modelElement instanceof SystemEngineering) && PaPackage.Literals.PHYSICAL_ARCHITECTURE.equals(cls)
        && CapellacorePackage.Literals.ABSTRACT_MODELLING_STRUCTURE__OWNED_ARCHITECTURES.equals(feature)) {
      return ((SystemEngineering) modelElement).getContainedPhysicalArchitectures().isEmpty();
    }
    return false;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  @Override
  public Command executionContribution(EditingDomain editingDomain, ModelElement containerElement,
      ModelElement createdElement, EStructuralFeature feature) {
    if ((createdElement instanceof PhysicalArchitecture) && (containerElement instanceof SystemEngineering)) {
      SystemEngineering engineering = (SystemEngineering) containerElement;
      LogicalArchitecture architecture = SystemEngineeringExt.getLogicalArchitecture(engineering);
      return new RecordingCommand((TransactionalEditingDomain) editingDomain) {
        @Override
        protected void doExecute() {
          new CreatePhysicalArchiCmd(engineering, NamingConstants.CreatePhysicalArchCmd_name, architecture,
              (LogicalComponent) architecture.getSystem(),
              (LogicalFunction) BlockArchitectureExt.getRootFunction(architecture, false),
              (PhysicalArchitecture) createdElement).run();
        }
      };
    }
    return new IdentityCommand();
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  @Override
  public EClass getMetaclass() {
    return PaPackage.Literals.PHYSICAL_ARCHITECTURE;
  }
}
