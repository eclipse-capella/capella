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
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class AttributeValueFromSource extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription(IDifference difference_p) {
    return "Propagation, target attribute is not set";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference_p, Role role_p, IContext context_p) {
    FilterAction role = null;
    if (difference_p instanceof IAttributeValuePresence) {
      // We merge AttributeValuePresence if value is empty into target
      if (role_p == Role.REFERENCE) {
        IAttributeValuePresence avp = (IAttributeValuePresence) difference_p;
        role = FilterAction.TARGET;
        EObject diffelt = avp.getElementMatch().get(Role.REFERENCE);
        if (diffelt != null) {
          for (IMergeableDifference mergeDiff : avp.getDirectImpliesDependencies(Role.TARGET)) {
            if (mergeDiff instanceof IAttributeValuePresence) {
              role = null;
              if (isMergeableAttribute(avp.getFeature(), diffelt, avp.getElementMatch().get(Role.TARGET), ((IAttributeValuePresence) mergeDiff).getValue(),
                  avp.getValue(), context_p)) {
                if (isMergeableAttributeValue(avp.getFeature(), diffelt, avp.getElementMatch().get(Role.TARGET),
                    ((IAttributeValuePresence) mergeDiff).getValue(), avp.getValue(), context_p)) {
                  role = FilterAction.TARGET;
                } else {
                  role = FilterAction.NO_ACTION;
                }
              }
            }
          }
        }
      }
    }
    return role;
  }

  /**
   * @param feature_p
   * @param diffelt_p
   * @param eObject_p
   * @param value_p
   * @param value2_p
   * @param context_p
   * @return
   */
  public boolean isMergeableAttributeValue(EAttribute feature_p, EObject diffelt_p, EObject eObject_p, Object value_p, Object value2_p, IContext context_p) {
    return true;
  }

  /**
   * Returns whether the difference from attribute value should be merged or not
   * @return
   */
  public boolean isMergeableAttribute(EAttribute attribute_p, EObject source_p, EObject target_p, Object oldValue_p, Object newValue_p, IContext context_p) {
    return oldValue_p == null;
  }

}
