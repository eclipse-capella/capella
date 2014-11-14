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
package org.polarsys.capella.core.data.fa.validation.componentPort;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that any Flow Port is neither used by any Component Exchange nor providing/requiring any Interface.
 */
public class MDCHK_ComponentPort_Connections extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ComponentPort) {
        ComponentPort port = (ComponentPort) eObj;
        // check if no component exchange as target
        if ((null == port.getComponentExchanges()) || (port.getComponentExchanges().size() == 0)) {
          if ((null == port.getProvidedInterfaces()) || (port.getProvidedInterfaces().size() == 0)) {
            if ((null == port.getRequiredInterfaces()) || (port.getRequiredInterfaces().size() == 0)) {
              return createFailureStatus(ctx_p, new Object[] { port.getName() });
            }
          }
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }
}
