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
package org.polarsys.capella.core.data.ctx.validation.system;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that an interface implemented by System is implemented by at least one Logical Component.
 *
 */
public class MDCHK_SystemUsedInterfacesDelegation extends AbstractValidationRule {
 
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof SystemComponent && !(ComponentExt.isActor(eObj))) {
        SystemComponent system = (SystemComponent) eObj;
        boolean localFlag = false;
        // This collection will store the conflicts statuses
        Collection<IStatus> statuses = new ArrayList<IStatus>();
        // get all implemented interfaces
        EList<Interface> implementedInterfaces = system.getImplementedInterfaces();
        for (Interface inter : implementedInterfaces) {
          // check if any LogicalComponenet Implement the 'inter'
          if (InterfaceExt.getImplementerLogicalComponents(inter).isEmpty()) {
            localFlag = true;
            // Creates a conflict status
            IStatus failureStatus = createFailureStatus(ctx, new Object[] {system.getName(), inter.getName()});
            statuses.add(failureStatus);
          }
        }
        
        if (localFlag) {
          // There are conflicts
          // Returns them as a multiStatuses 
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
