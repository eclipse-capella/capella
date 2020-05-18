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
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateCtxArchiCmd;

/**
 */
public class SystemAnalysisItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    if ((modelElement instanceof SystemEngineering) && CtxPackage.Literals.SYSTEM_ANALYSIS.equals(cls)
        && CapellacorePackage.Literals.ABSTRACT_MODELLING_STRUCTURE__OWNED_ARCHITECTURES.equals(feature)) {
      return ((SystemEngineering) modelElement).getContainedSystemAnalysis().isEmpty();
    }
    return false;
  }

  /**
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(EditingDomain editingDomain, ModelElement containerElement,
      ModelElement createdElement, EStructuralFeature feature) {
    if ((createdElement instanceof SystemAnalysis) && (containerElement instanceof SystemEngineering)) {
      SystemEngineering engineering = (SystemEngineering) containerElement;
      OperationalAnalysis architecture = SystemEngineeringExt.getOperationalAnalysis(engineering);
      return new RecordingCommand((TransactionalEditingDomain) editingDomain) {
        @Override
        protected void doExecute() {
          new CreateCtxArchiCmd(engineering, NamingConstants.CreateSysAnalysisCmd_name, architecture,
              (OperationalActivity) BlockArchitectureExt.getRootFunction(architecture, false),
              (SystemAnalysis) createdElement).run();
        }
      };
    }
    return new IdentityCommand();
  }

  /**
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return CtxPackage.Literals.SYSTEM_ANALYSIS;
  }
}
