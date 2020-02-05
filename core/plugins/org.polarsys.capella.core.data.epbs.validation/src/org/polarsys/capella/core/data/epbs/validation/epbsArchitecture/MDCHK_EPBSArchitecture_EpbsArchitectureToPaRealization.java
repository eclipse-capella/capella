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
package org.polarsys.capella.core.data.epbs.validation.epbsArchitecture;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * 
 * TJ_EPBS_04 - This rule ensures the realization consistency between EPBS Architecture and Physical Architecture.
 *
 */
public class MDCHK_EPBSArchitecture_EpbsArchitectureToPaRealization extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof EPBSArchitecture) {
        EPBSArchitecture epbsa = (EPBSArchitecture) eObj;
        if (epbsa.getOwnedPhysicalArchitectureRealizations().isEmpty()) {
          Project project = ProjectExt.getProject(epbsa);
          BlockArchitecture architecture = BlockArchitectureExt.getBlockArchitecture(BlockArchitectureExt.Type.PA,
              project);
          PhysicalArchitecture pa = (PhysicalArchitecture) architecture;
          String targetArchitecture = CapellaElementExt.getValidationRuleMessagePrefix(pa);
          return createFailureStatus(ctx, new Object[] { CapellaElementExt.getValidationRuleMessagePrefix(epbsa)
              + "does not realize " + targetArchitecture.substring(0, targetArchitecture.length() - 1) });
        }
      }
    }

    return ctx.createSuccessStatus();
  }

}
