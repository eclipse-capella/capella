/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TargetAttributeFilterItem extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMergeable(IDifference difference, Role role, IContext context) {

    // By default, don't show an attribute difference on target (only shown on source)
    if (role == Role.TARGET) {
      if (difference instanceof IAttributeValuePresence) {
        return getDestinationRole(difference, role, context) == FilterAction.TARGET;
      }
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription(IDifference difference) {
    return "Don't merge an attribute from target, except if it's an unsetted enumeration";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference, Role role1, IContext context) {
    FilterAction role = null;
    if (difference instanceof IAttributeValuePresence) {
      // We merge AttributeValuePresence if source is an unsetted enum
      if (role1 == Role.TARGET) {
        IAttributeValuePresence avp = (IAttributeValuePresence) difference;
        EObject diffelt = avp.getElementMatch().get(Role.TARGET);
        if (avp.getDirectImpliesDependencies(Role.REFERENCE).isEmpty()) {
          if (isMergeableAttribute(avp.getFeature(), diffelt, avp.getElementMatch().get(Role.TARGET), null, avp.getValue(), context)) {
            if (isMergeableAttributeValue(avp.getFeature(), diffelt, avp.getElementMatch().get(Role.TARGET), null, avp.getValue(), context)) {
              role = FilterAction.TARGET;
            }
          }
        }
      }
    }
    return role;
  }

  public boolean isMergeableAttributeValue(EAttribute feature, EObject diffelt, EObject eObject, Object value, Object value2, IContext context) {
    return true;
  }

  public boolean isMergeableAttribute(EAttribute attribute, EObject source, EObject target, Object oldValue, Object newValue, IContext context) {
    return (oldValue == null) && (attribute.getEType() instanceof EEnum);
  }

}
