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
package org.polarsys.capella.core.transition.common.handlers.filter;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReferenceValueFromSource extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription(IDifference difference_p) {
    return "Propagation, target reference is not set";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference_p, Role role_p, IContext context_p) {
    FilterAction role = null;
    if (difference_p instanceof IReferenceValuePresence) {
      // We merge AttributeValuePresence if value is empty into target
      if (role_p == Role.REFERENCE) {
        IReferenceValuePresence avp = (IReferenceValuePresence) difference_p;
        role = FilterAction.TARGET;
        EObject diffelt = avp.getElementMatch().get(Role.REFERENCE);
        if (diffelt != null) {
          for (IMergeableDifference mergeDiff : avp.getDirectImpliesDependencies(Role.TARGET)) {
            if (mergeDiff instanceof IReferenceValuePresence) {
              role = null;
              if (isMergeableReference(avp.getFeature(), diffelt, avp.getElementMatch().get(Role.TARGET), ((IReferenceValuePresence) mergeDiff).getValue(),
                  avp.getValue(), context_p)) {
                role = FilterAction.TARGET;
              }
            }
          }
        }
      }
    }
    return role;
  }

  /**
   * Returns whether the difference from reference value should be merged or not
   * @return
   */
  public boolean isMergeableReference(EReference reference_p, EObject source_p, EObject target_p, Object oldValue_p, Object newValue_p, IContext context_p) {
    return false;
  }

}
