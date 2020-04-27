/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ComponentPort) {
        ComponentPort port = (ComponentPort) eObj;
        // check if no component exchange as target
        if ((null == port.getComponentExchanges()) || (port.getComponentExchanges().size() == 0)) {
          if ((null == port.getProvidedInterfaces()) || (port.getProvidedInterfaces().size() == 0)) {
            if ((null == port.getRequiredInterfaces()) || (port.getRequiredInterfaces().size() == 0)) {
              return createFailureStatus(ctx, new Object[] { port.getName() });
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
