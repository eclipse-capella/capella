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

package org.polarsys.capella.core.transition.system.handlers.filter;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.transition.common.handlers.filter.AttributeStringValueFromSource;

/**
 *
 */
public class AttributeNameValueFromSource extends AttributeStringValueFromSource {

  /**
   * Returns whether the difference from attribute value should be merged or not
   * @return
   */
  @Override
  public boolean isMergeableAttribute(EAttribute attribute, EObject source, EObject target, Object oldValue, Object newValue) {
    // Merge name of element if name is same as EClass of the element
    if (ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.equals(attribute)) {
      return true;
    }
    return false;
  }

  /**
   * Returns whether the difference from attribute value should be merged or not
   * @return
   */
  @Override
  public boolean isMergeableAttributeValue(EAttribute attribute, EObject source, EObject target, Object oldValue, Object newValue) {
    // Merge name of element if name is same as EClass of the element
    if (ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.equals(attribute)) {
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
    }
    return super.isMergeableAttributeValue(attribute, source, target, oldValue, newValue);
  }

}
