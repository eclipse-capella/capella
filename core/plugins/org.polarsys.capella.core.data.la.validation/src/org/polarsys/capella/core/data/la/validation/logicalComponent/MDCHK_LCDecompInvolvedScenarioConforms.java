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
package org.polarsys.capella.core.data.la.validation.logicalComponent;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check refinement needed for Logical component decomposed
 */
public class MDCHK_LCDecompInvolvedScenarioConforms extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof LogicalComponent) {
        LogicalComponent lcpnt = (LogicalComponent) eObj;

        if (ComponentExt.isComposite(lcpnt)) {
          // Check if current LogicalComponent's instance is involved into the upper scenario
          boolean flagInvolving = false;
          AbstractCapabilityPkg upperAspectPkg = null;
          List<Scenario> listScenarioUpper = null;

          Component parentContainer = LogicalComponentExt.getParentContainer(lcpnt);
          if (parentContainer instanceof SystemEngineering) {
            SystemEngineering systemEngineering = (SystemEngineering) parentContainer;
            upperAspectPkg = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering).getOwnedAbstractCapabilityPkg();
          } else if (parentContainer instanceof LogicalComponent)
            upperAspectPkg = ((LogicalComponent) parentContainer).getOwnedAbstractCapabilityPkg();

          if (upperAspectPkg != null) {
            listScenarioUpper = AbstractCapabilityPkgExt.getAllScenarios(upperAspectPkg);
            for (Scenario scenario : listScenarioUpper) {
              for (InstanceRole instRole : scenario.getOwnedInstanceRoles()) {
                AbstractInstance cpntInstance = instRole.getRepresentedInstance();
                if ((cpntInstance != null) && (cpntInstance.getType().equals(lcpnt))) {
                  flagInvolving = true;
                }
              }
            }
          }
          if (flagInvolving) {
            // Check if one upper scenario is refined toward to current logical component's sub scenario
            boolean flagSubScenarioFound = false;
            List<Scenario> tgtScenarioList = new ArrayList<Scenario>();
            List<Scenario> scenarioListComponent = new ArrayList<Scenario>();
            AbstractCapabilityPkg aspectPkgCurrentLC = lcpnt.getOwnedAbstractCapabilityPkg();

            if (aspectPkgCurrentLC != null)
              scenarioListComponent = AbstractCapabilityPkgExt.getAllScenarios(aspectPkgCurrentLC);
            for (Scenario scenarioUpper : listScenarioUpper) {
              for (CapellaElement capellaElt : RefinementLinkExt.getRefinementRelatedSourceElements(scenarioUpper, InteractionPackage.Literals.SCENARIO)) {
                if (capellaElt instanceof Scenario)
                  tgtScenarioList.add((Scenario) capellaElt);
              }
            }

            for (Scenario scenarioComponent : scenarioListComponent) {
              if (tgtScenarioList.contains(scenarioComponent))
                flagSubScenarioFound = true;
            }

            if (!flagSubScenarioFound) {
              return createFailureStatus(ctx, new Object[] { upperAspectPkg.getName(), lcpnt.getName() });
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
