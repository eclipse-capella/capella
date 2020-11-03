/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.compare;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.sirius.SiriusDiffPolicy;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;


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
   * @see org.eclipse.emf.diffmerge.sirius.SiriusDiffPolicy#coverValue(java.lang.Object, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  public boolean coverValue(Object value, Object attribute, ITreeDataScope<EObject> scope_p) {
    boolean result;
    if (IGNORING_EMPTY_STRING_ATTRIBUTES.contains(attribute)
        && ((String)value).length() == 0)
      result = false;
    else
      result = super.coverValue(value, attribute, scope_p);
    return result;
  }
  
}
