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
  public EObject filter(EObject modelElement_p) {
    EObject modelElement = modelElement_p;
    if (modelElement instanceof Part) {
      boolean allowMultiplePart = TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven((Part) modelElement_p));
      if (!allowMultiplePart) {
        AbstractType abstractType = ((Part) modelElement).getAbstractType();
        if ((abstractType != null) && !(abstractType instanceof ConfigurationItem)) {
          modelElement = abstractType;
        }
      }
    }
    return modelElement;
  }
}
