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

import java.util.Collection;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class AttributeStringValueFromSource extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription(IDifference difference) {
    String attribute = "attribute";
    if (difference != null) {
      if (difference instanceof IAttributeValuePresence) {
        IAttributeValuePresence o = (IAttributeValuePresence) difference;
        if (o.getFeature() != null) {
          attribute = o.getFeature().getName();
        }
      }
    }
    return NLS.bind("Propagation of ''{0}'' only if target's value is empty or default value", attribute);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference, Role role1, IContext iContext1) {
    FilterAction role = null;
    if (difference instanceof IAttributeValuePresence) {
      // We merge AttributeValuePresence if value is empty into target
      if (role1 == Role.REFERENCE) {
        IAttributeValuePresence avp = (IAttributeValuePresence) difference;
        EObject diffelt = avp.getElementMatch().get(Role.REFERENCE);
        if (diffelt != null) {
          Collection<IMergeableDifference> diffs = avp.getDirectImpliesDependencies(Role.TARGET);
          if (diffs.isEmpty()) {
            if (isMergeableAttribute(avp.getFeature(), diffelt, avp.getElementMatch().get(Role.TARGET), null, avp.getValue())) {
              if (isMergeableAttributeValue(avp.getFeature(), diffelt, avp.getElementMatch().get(Role.TARGET), null, avp.getValue())) {
                role = FilterAction.TARGET;
              } else {
                role = FilterAction.NO_ACTION;
              }
            }
          } else {
            for (IMergeableDifference mergeDiff : avp.getDirectImpliesDependencies(Role.TARGET)) {
              if (mergeDiff instanceof IAttributeValuePresence) {
                role = null;
                if (isMergeableAttribute(avp.getFeature(), diffelt, avp.getElementMatch().get(Role.TARGET), ((IAttributeValuePresence) mergeDiff).getValue(),
                    avp.getValue())) {
                  if (isMergeableAttributeValue(avp.getFeature(), diffelt, avp.getElementMatch().get(Role.TARGET),
                      ((IAttributeValuePresence) mergeDiff).getValue(), avp.getValue())) {
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
    }
    return role;
  }

  /**
   * Returns whether the difference from attribute value should be merged or not
   * @return
   */
  public boolean isMergeableAttribute(EAttribute attribute, EObject source, EObject target, Object oldValue, Object newValue) {
    return false;
  }

  /**
   * Returns whether the difference from attribute value should be merged or not
   * @return
   */
  public boolean isMergeableAttributeValue(EAttribute attribute, EObject source, EObject target, Object oldValue, Object newValue) {
    // Merge name of element if name is same as EClass of the element
    if ((oldValue == null) || ((oldValue instanceof String) && (((String) oldValue).length() == 0))) {
      return true;
    }
    return false;
  }

}
