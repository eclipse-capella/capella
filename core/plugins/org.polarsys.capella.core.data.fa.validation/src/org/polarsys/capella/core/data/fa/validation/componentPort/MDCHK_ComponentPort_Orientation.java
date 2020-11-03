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
package org.polarsys.capella.core.data.fa.validation.componentPort;

import static org.polarsys.capella.core.data.fa.OrientationPortKind.IN;
import static org.polarsys.capella.core.data.fa.OrientationPortKind.OUT;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;

/**
 * Checks consistency of a ComponentExchange source and target ComponentPorts. - A source component port cannot have orientation "IN" - A target component port
 * cannot have orientation "OUT" [Exception Case : Component Exchange of kind Delegation can only have orientation IN/IN or OUT/OUT]
 * @see org.polarsys.capella.test.rules.ju.cases.ComponentPortOrientationConsistencyTest
 */
public class MDCHK_ComponentPort_Orientation extends AbstractModelConstraint {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    ComponentExchange exchange = (ComponentExchange) ctx.getTarget();
    ComponentPort sourceCompPort = null;
    ComponentPort targetCompPort = null;
    Port sourcePort = ComponentExchangeExt.getSourcePort(exchange);
    Port targetPort = ComponentExchangeExt.getTargetPort(exchange);
    if (sourcePort instanceof ComponentPort) {
      sourceCompPort = (ComponentPort) sourcePort;
    }
    if (targetPort instanceof ComponentPort) {
      targetCompPort = (ComponentPort) targetPort;
    }

    boolean delgation = false;
    ComponentExchangeKind kind = exchange.getKind();
    if (kind.equals(ComponentExchangeKind.DELEGATION)) {
      delgation = true;
    }

    byte result = 0;
    if (!delgation) {
      if ((sourceCompPort != null) && (sourceCompPort.getOrientation() == IN)) {
        result |= 1;
      }
      if ((targetCompPort != null) && (targetCompPort.getOrientation() == OUT)) {
        result |= 2;
      }
    } else {
      if ((sourceCompPort != null) && (targetCompPort != null)) {
        // Component Exchange of kind Delegation can only have orientation IN/IN or OUT/OUT
        if (((sourceCompPort.getOrientation() == IN) && (targetCompPort.getOrientation() == OUT))
            || ((sourceCompPort.getOrientation() == OUT) && (targetCompPort.getOrientation() == IN))) {
          result = 4;
        }
      }
    }

    switch (result) {
      case 1:
    	String sourceCompPortName = sourceCompPort != null ? sourceCompPort.getName() : "Unknown";
        return ctx.createFailureStatus("Orientation of source component port '" + sourceCompPortName + "' cannot be 'IN'"); //$NON-NLS-1$ //$NON-NLS-2$
      case 2:
    	  String targetCompPortName = targetCompPort != null ? targetCompPort.getName() : "Unknown";
        return ctx.createFailureStatus("Orientation of target component port '" + targetCompPortName + "' cannot be 'OUT'"); //$NON-NLS-1$ //$NON-NLS-2$
      case 3:
        return ctx.createFailureStatus("ComponentExchange '" + exchange.getName() + "' has inconsistent component port orientations"); //$NON-NLS-1$ //$NON-NLS-2$
      case 4:
        return ctx.createFailureStatus("ComponentExchange of kind Delegation '" + exchange.getName() + "' has inconsistent component port orientations"); //$NON-NLS-1$ //$NON-NLS-2$
      default:
        return ctx.createSuccessStatus();
    }
  }
}
