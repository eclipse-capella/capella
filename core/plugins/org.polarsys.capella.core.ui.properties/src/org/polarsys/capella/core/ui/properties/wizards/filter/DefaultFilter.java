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
package org.polarsys.capella.core.ui.properties.wizards.filter;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * The default capella property wizard filter. Currently only one special
 * case: A part maps to the part's type if that type is not a configuration item.
 * 
 */
public class DefaultFilter extends Filter<EObject, EObject> {
  @Override
  public EObject filter(EObject modelElement) {
    EObject element = modelElement;
    if (element instanceof Part) {
      boolean allowMultiplePart = TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven((Part) modelElement));
      if (!allowMultiplePart) {
        AbstractType abstractType = ((Part) element).getAbstractType();
        if ((abstractType != null) && !(abstractType instanceof ConfigurationItem)) {
          element = abstractType;
        }
      }
    }
    return element;
  }
}
