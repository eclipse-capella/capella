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
package org.polarsys.capella.core.compare;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.ecore.EAttribute;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.common.consonance.ui.sirius.SiriusDiffPolicy;


/**
 * A diff policy for Capella models.
 */
public class CapellaDiffPolicy extends SiriusDiffPolicy {
  
  /** The set of String attributes for which the empty string value must not be
      distinguished from the null value */
  private static final Collection<EAttribute> IGNORING_EMPTY_STRING_ATTRIBUTES =
    Arrays.asList(
        CapellacorePackage.eINSTANCE.getCapellaElement_Description(),
        CapellacorePackage.eINSTANCE.getCapellaElement_Summary()
    );
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.SiriusDiffPolicy#coverValue(java.lang.Object, org.eclipse.emf.ecore.EAttribute)
   */
  @Override
  public boolean coverValue(Object value_p, EAttribute attribute_p) {
    boolean result;
    if (IGNORING_EMPTY_STRING_ATTRIBUTES.contains(attribute_p)
        && ((String)value_p).length() == 0)
      result = false;
    else
      result = super.coverValue(value_p, attribute_p);
    return result;
  }
  
}
