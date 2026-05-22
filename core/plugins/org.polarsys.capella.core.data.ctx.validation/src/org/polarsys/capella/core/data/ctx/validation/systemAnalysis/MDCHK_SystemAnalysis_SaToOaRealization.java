/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.ctx.validation.systemAnalysis;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * 
 * TJ_SA_04 - This rule ensures the realization consistency between System Analysis and Operational Analysis.
 *
 */
public class MDCHK_SystemAnalysis_SaToOaRealization extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof SystemAnalysis) {
        SystemAnalysis sa = (SystemAnalysis) eObj;
        if (sa.getOwnedOperationalAnalysisRealizations().isEmpty()) {
          Project project = ProjectExt.getProject(sa);
          BlockArchitecture architecture = BlockArchitectureExt
              .getBlockArchitecture(OaPackage.Literals.OPERATIONAL_ANALYSIS, project);
          OperationalAnalysis operationalAnalysis = (OperationalAnalysis) architecture;
          if (operationalAnalysis != null) {
            String targetArchitecture = CapellaElementExt.getValidationRuleMessagePrefix(operationalAnalysis);
            return createFailureStatus(ctx, new Object[] { NLS.bind(Messages.MDCHK_SystemAnalysis_SaToOaRealization_0, CapellaElementExt.getValidationRuleMessagePrefix(sa),
                targetArchitecture.substring(0, targetArchitecture.length() - 1))});
          } else {
            return createFailureStatus(ctx, new Object[] {
                NLS.bind(Messages.MDCHK_SystemAnalysis_SaToOaRealization_1, CapellaElementExt.getValidationRuleMessagePrefix(sa))});
          }
        }
      }
    }

    return ctx.createSuccessStatus();
  }

}
