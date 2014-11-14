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
package org.polarsys.capella.core.transition.system.topdown.handlers.filter;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellacore.CapellacorePackage;

/**
 *
 */
public class AttributeStringValueFromSource extends org.polarsys.capella.core.transition.common.handlers.filter.AttributeStringValueFromSource {

  /**
   * Returns whether the difference from attribute value should be merged or not
   * @return
   */
  @Override
  public boolean isMergeableAttribute(EAttribute attribute_p, EObject source_p, EObject target_p, Object oldValue_p, Object newValue_p) {
    // Merge name of element if name is same as EClass of the element
    if (CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.equals(attribute_p)) {
      return true;
    }
    if (CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.equals(attribute_p)) {
      return true;
    }
    return false;
  }

  /**
   * Returns whether the difference from attribute value should be merged or not
   * @return
   */
  @Override
  public boolean isMergeableAttributeValue(EAttribute attribute_p, EObject source_p, EObject target_p, Object oldValue_p, Object newValue_p) {
    return super.isMergeableAttributeValue(attribute_p, source_p, target_p, oldValue_p, newValue_p);
  }

}
