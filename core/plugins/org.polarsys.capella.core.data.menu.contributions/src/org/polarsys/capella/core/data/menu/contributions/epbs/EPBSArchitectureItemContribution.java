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
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateEPBSArchiCmd;

public class EPBSArchitectureItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  @Override
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    if ((modelElement instanceof SystemEngineering) && EpbsPackage.Literals.EPBS_ARCHITECTURE.equals(cls)
        && CapellacorePackage.Literals.ABSTRACT_MODELLING_STRUCTURE__OWNED_ARCHITECTURES.equals(feature)) {
      return ((SystemEngineering) modelElement).getContainedEPBSArchitectures().isEmpty();
    }
    return false;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  @Override
  public Command executionContribution(EditingDomain editingDomain, ModelElement containerElement,
      ModelElement createdElement, EStructuralFeature feature) {
    if ((createdElement instanceof EPBSArchitecture) && (containerElement instanceof SystemEngineering)) {
      SystemEngineering engineering = (SystemEngineering) containerElement;
      PhysicalArchitecture architecture = SystemEngineeringExt.getPhysicalArchitecture(engineering);
      return new RecordingCommand((TransactionalEditingDomain) editingDomain) {
        @Override
        protected void doExecute() {
          new CreateEPBSArchiCmd(engineering, NamingConstants.CreateEPBSArchCmd_name, architecture,
              (PhysicalComponent) architecture.getSystem(), (EPBSArchitecture) createdElement).run();
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
    return EpbsPackage.Literals.EPBS_ARCHITECTURE;
  }
}
