/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.osgi.util.NLS;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReferenceValueFromSource extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription(IDifference difference) {
    if (difference instanceof IReferenceValuePresence) {
      IReferenceValuePresence avp = (IReferenceValuePresence) difference;
      return NLS.bind("Propagation, reference ''{0}'' is updated automatically", avp.getFeature().getName());
    }
    return "Propagation, target reference is updated automatically";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference, Role role1, IContext context) {
    FilterAction role = null;
    if (difference instanceof IReferenceValuePresence) {
      // We merge AttributeValuePresence if value is empty into target
      if (role1 == Role.REFERENCE) {
        IReferenceValuePresence avp = (IReferenceValuePresence) difference;
        role = null;
        EObject diffelt = avp.getElementMatch().get(Role.REFERENCE);
        if (diffelt != null) {

          if (difference instanceof IReferenceValuePresence) {
            if (isMergeableReference(avp.getFeature(), diffelt, avp.getElementMatch().get(Role.TARGET), ((IReferenceValuePresence) difference).getValue(),
                avp.getValue(), context)) {
              role = FilterAction.TARGET;
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
  public boolean isMergeableReference(EReference reference, EObject source, EObject target, Object oldValue, Object newValue, IContext context) {
    return false;
  }

}
