/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class AttributeNameValueFromSource extends CategoryFilter {

  public AttributeNameValueFromSource(IContext context) {
    super(context, Messages.AttributeNameValueFromSource, Messages.AttributeNameValueFromSource_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setActive(true);
    setInFocusMode(false);
    setVisible(true);
  }

  @Override
  public boolean covers(IDifference<EObject> difference) {
    if (difference instanceof EAttributeValuePresence) {
      EObject source = ((EAttributeValuePresence) difference).getElementMatch().get(Role.REFERENCE);
      EObject target = ((EAttributeValuePresence) difference).getElementMatch().get(Role.TARGET);

      if (ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME
          .equals(((EAttributeValuePresence) difference).getFeature())) {
        if (source instanceof AbstractNamedElement) {
          return !isUpdatableValue(source, target, ((AbstractNamedElement) target).getName(),
              ((AbstractNamedElement) source).getName());
        }
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

    if (oldValue.equals(NamingConstants.CreateSysAnalysisCmd_system_statemachine_name)) {
      return true;
    }
    if (oldValue.equals(NamingConstants.Region_DefaultRegion)) {
      return true;
    }
    return false;
  }

}
