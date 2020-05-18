/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class AttributeSummaryValueFromSource extends CategoryFilter {

  public AttributeSummaryValueFromSource(IContext context) {
    super(context, Messages.AttributeSummaryValueFromSource, Messages.AttributeSummaryValueFromSource_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setActive(true);
    setInFocusMode(false);
    setVisible(true);
  }

  @Override
  public boolean covers(IDifference difference) {
    if (difference instanceof IAttributeValuePresence) {
      EObject source = ((IAttributeValuePresence) difference).getElementMatch().get(Role.REFERENCE);
      EObject target = ((IAttributeValuePresence) difference).getElementMatch().get(Role.TARGET);

      if (CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY
          .equals(((IAttributeValuePresence) difference).getFeature())) {
        if (source instanceof CapellaElement) {
          return !isUpdatableValue(source, target, ((CapellaElement) target).getSummary(),
              ((CapellaElement) source).getSummary());
        }
      }
    }
    return false;
  }

  public boolean isUpdatableValue(EObject source, EObject target, Object oldValue, Object newValue) {
    // Merge name of element if name is same as EClass of the element
    if ((oldValue == null) || ((oldValue instanceof String) && (((String) oldValue).length() == 0))) {
      return true;
    }
    return false;
  }

}
