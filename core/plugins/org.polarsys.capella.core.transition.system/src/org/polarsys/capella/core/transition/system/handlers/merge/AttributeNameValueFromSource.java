/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class AttributeNameValueFromSource extends CategoryFilter {

  public AttributeNameValueFromSource(IContext context) {
    super(context, "Focus on Default or empty names", null);
    setActive(false);
    setInFocusMode(true);
    setVisible(true);
  }

  @Override
  public boolean covers(IDifference difference) {
    if (difference instanceof IAttributeValuePresence) {
      EObject source = ((IAttributeValuePresence) difference).getElementMatch().get(Role.REFERENCE);
      EObject target = ((IAttributeValuePresence) difference).getElementMatch().get(Role.TARGET);

      if (source instanceof AbstractNamedElement) {
        return isUpdatableValue(source, target, ((AbstractNamedElement) target).getName(),
            ((AbstractNamedElement) source).getName());
      }
    }
    return false;
  }

  public boolean isUpdatableValue(EObject source, EObject target, Object oldValue, Object newValue) {
    // Merge name of element if name is same as EClass of the element
    if ((oldValue == null) || ((oldValue instanceof String) && (((String) oldValue).length() == 0))) {
      return true;

    } else if (oldValue.equals(target.eClass().getName())) {
      return true;
    }
    if (oldValue.equals("System State Machine")) {
      return true;
    }
    if (oldValue.equals("Default Region")) {
      return true;
    }

    // Merge name of element if name is same as EClass of the element
    if ((oldValue == null) || ((oldValue instanceof String) && (((String) oldValue).length() == 0))) {
      return true;
    }
    return false;
  }

}
