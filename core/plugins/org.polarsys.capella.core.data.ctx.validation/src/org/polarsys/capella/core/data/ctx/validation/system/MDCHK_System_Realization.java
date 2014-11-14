/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.ctx.validation.system;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.SystemRealization;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public class MDCHK_System_Realization extends AbstractValidationRule {
  /**
   * 
   */
  private static final String UNNAMED_ELEMENT = "Unnamed Element"; //$NON-NLS-1$

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof System) {
        System system = (System) eObj;

        for (AbstractTrace trace : system.getIncomingTraces()) {
          if (trace instanceof SystemRealization) {
            TraceableElement sourceElement = trace.getSourceElement();
            TraceableElement targetElement = trace.getTargetElement();
            if (!((sourceElement instanceof LogicalComponent) && (targetElement instanceof System))) {
              String sourceName = UNNAMED_ELEMENT;
              if (sourceElement instanceof NamedElement) {
                sourceName = ((NamedElement) sourceElement).getName();
              }
              String sourceType = sourceElement.eClass().getName();
              String targetName = UNNAMED_ELEMENT;
              if (targetElement instanceof NamedElement) {
                targetName = ((NamedElement) targetElement).getName();
              }
              String targetType = targetElement.eClass().getName();
              return createFailureStatus(ctx_p, new Object[] { sourceName, sourceType, targetName, targetType });
            }
          }
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }
}
