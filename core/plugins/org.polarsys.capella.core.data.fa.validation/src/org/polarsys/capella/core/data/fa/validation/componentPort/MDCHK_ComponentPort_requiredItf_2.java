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

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that interfaces requires by standard port is used by its owner Component.
 */
public class MDCHK_ComponentPort_requiredItf_2 extends AbstractValidationRule {
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

        PartitionableElement block = (PartitionableElement) port.eContainer();
        if (block instanceof Component) {
          Component cpnt = (Component) block;
          for (Interface itf : port.getRequiredInterfaces()) {
            if (!cpnt.getUsedInterfaces().contains(itf)) {
              return createFailureStatus(ctx, new Object[] { port.getName(), itf.getName() });
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
