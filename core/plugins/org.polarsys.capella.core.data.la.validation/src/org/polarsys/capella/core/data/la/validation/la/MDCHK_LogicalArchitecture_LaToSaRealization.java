/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.la.validation.la;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_LogicalArchitecture_LaToSaRealization extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof LogicalArchitecture) {
        LogicalArchitecture la = (LogicalArchitecture) eObj;
        if (la.getOwnedSystemAnalysisRealizations().isEmpty()) {
          return createFailureStatus(ctx, new Object[] { la.getName() });
        }
      }
    }

    return ctx.createSuccessStatus();
  }

}
