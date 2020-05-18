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
package org.polarsys.capella.core.data.cs.validation.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule allows to ensure that a component cannot implement the same interface several times or use the same interface several times.
 */
public class MDCHK_Component_MultipleUseOrImplementationOfSameItfCheck extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Component) {
        Collection<IStatus> statuses = new ArrayList<IStatus>();
        Component cpnt = (Component) eObj;
        // This list will store the used interfaces
        List<Interface> usedInterfaces = new ArrayList<Interface>();
        // This one will store the interfaces used several times
        List<Interface> interfacesUsedSeveralTimes = new ArrayList<Interface>();
        // This one will store the implemented interfaces
        List<Interface> implementedInterfaces = new ArrayList<Interface>();
        // This one will store the interfaces implemented several times
        List<Interface> interfacesImplementedSeveralTimes = new ArrayList<Interface>();
        //        
        // First checks the used interfaces
        for (Interface itf : cpnt.getUsedInterfaces()) {
          if (!usedInterfaces.contains(itf)) {
            // adds the interface to the used ones list
            usedInterfaces.add(itf);
          } else {
            // It is already in the used ones list
            if (!interfacesUsedSeveralTimes.contains(itf)) {
              // If it not already in the list of the interfaces used several times, adds it to the list and creates a failure status
              interfacesUsedSeveralTimes.add(itf);
              IStatus failureStatus =
                  ctx.createFailureStatus(new Object[] { CapellaElementExt.getValidationRuleMessagePrefix(cpnt),
                      Messages.getString("MDCHK_Component_MultipleUseOrImplementationOfSameItfCheck.use"), //$NON-NLS-1$
                      itf.getName() });
              statuses.add(failureStatus);
            }
          }
        }
        // Now checks the implemented interfaces
        for (Interface itf : cpnt.getImplementedInterfaces()) {
          if (!implementedInterfaces.contains(itf)) {
            // adds the interface to the implemented ones list
            implementedInterfaces.add(itf);
          } else {
            // It is already in the implemented ones list
            if (!interfacesImplementedSeveralTimes.contains(itf)) {
              // If it not already in the list of the interfaces implemented several times, adds it to the list and creates a failure status
              interfacesImplementedSeveralTimes.add(itf);
              IStatus failureStatus =
                  ctx.createFailureStatus(new Object[] { CapellaElementExt.getValidationRuleMessagePrefix(cpnt),
                      Messages.getString("MDCHK_Component_MultipleUseOrImplementationOfSameItfCheck.implement"), //$NON-NLS-1$
                      itf.getName() });
              statuses.add(failureStatus);
            }
          }
        }
        if (statuses.size() > 0) {
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
