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
  public String getDescription(IDifference difference) {
    return "Propagation, target attribute is not set";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference iDifference1, Role role1, IContext iContext1) {
    FilterAction role = null;
    if (iDifference1 instanceof IAttributeValuePresence) {
      // We merge AttributeValuePresence if value is empty into target
      if (role1 == Role.REFERENCE) {
        IAttributeValuePresence avp = (IAttributeValuePresence) iDifference1;
        role = FilterAction.TARGET;
        EObject diffelt = avp.getElementMatch().get(Role.REFERENCE);
        if (diffelt != null) {
          for (IMergeableDifference mergeDiff : avp.getDirectImpliesDependencies(Role.TARGET)) {
            if (mergeDiff instanceof IAttributeValuePresence) {
              role = null;
              if (isMergeableAttribute(avp.getFeature(), diffelt, avp.getElementMatch().get(Role.TARGET), ((IAttributeValuePresence) mergeDiff).getValue(),
                  avp.getValue(), iContext1)) {
                if (isMergeableAttributeValue(avp.getFeature(), diffelt, avp.getElementMatch().get(Role.TARGET),
                    ((IAttributeValuePresence) mergeDiff).getValue(), avp.getValue(), iContext1)) {
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
   * @param feature
   * @param diffelt
   * @param eObject
   * @param value
   * @param value2
   * @param context
   * @return
   */
  public boolean isMergeableAttributeValue(EAttribute feature, EObject diffelt, EObject eObject, Object value, Object value2, IContext context) {
    return true;
  }

  /**
   * Returns whether the difference from attribute value should be merged or not
   * @return
   */
  public boolean isMergeableAttribute(EAttribute attribute, EObject source, EObject target, Object oldValue, Object newValue, IContext context) {
    return oldValue == null;
  }

}
