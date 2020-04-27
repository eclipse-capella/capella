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
package org.polarsys.capella.core.data.pa.validation.physicalComponent;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_Component_Deployment extends AbstractValidationRule {

  public boolean isMultipleDeploymentAllowed() {
    return CapellaModelPreferencesPlugin.getDefault().isMultipleDeploymentAllowed();
  }

  @Override
  public IStatus validate(IValidationContext ctx) {
    PhysicalComponent pc = (PhysicalComponent) ctx.getTarget();

    // Obvious case
    if (isMultipleDeploymentAllowed())
      return ctx.createSuccessStatus();

    // We are interested to part
    int nbDeploy = 0;
    for (AbstractTypedElement ate : pc.getAbstractTypedElements()) {
      if (ate instanceof Part) {
        Part part = (Part) ate;
        nbDeploy += part.getDeployingLinks().size();
      }

    }
    if (nbDeploy > 1) {
      return ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(pc));
    }

    return ctx.createSuccessStatus();
  }
}
