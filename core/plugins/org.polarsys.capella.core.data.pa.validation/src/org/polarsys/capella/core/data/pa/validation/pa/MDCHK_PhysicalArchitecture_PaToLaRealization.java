/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.pa.validation.pa;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * 
 * TJ_PA_12 - This rule ensures the realization consistency between Physical Architecture and Logical Architecture.
 *
 */
public class MDCHK_PhysicalArchitecture_PaToLaRealization extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof PhysicalArchitecture) {
        PhysicalArchitecture pa = (PhysicalArchitecture) eObj;
        if (pa.getOwnedLogicalArchitectureRealizations().isEmpty()) {
          Project project = ProjectExt.getProject(pa);
          BlockArchitecture architecture = BlockArchitectureExt.getBlockArchitecture(BlockArchitectureExt.Type.LA,
              project);
          LogicalArchitecture la = (LogicalArchitecture) architecture;
          String targetArchitecture = CapellaElementExt.getValidationRuleMessagePrefix(la);
          targetArchitecture = targetArchitecture.isEmpty() ? "Logical Architecture"
              : targetArchitecture.substring(0, targetArchitecture.length() - 1);
          return createFailureStatus(ctx, new Object[] {
              CapellaElementExt.getValidationRuleMessagePrefix(pa) + "does not realize " + targetArchitecture });
        }
      }
    }

    return ctx.createSuccessStatus();
  }

}
