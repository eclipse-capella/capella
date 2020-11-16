/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.ui.quickfix.resolver;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class TypedElementHasDifferentNameThanTypeResolver extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {
    for (EObject element : MarkerViewHelper.getModelElementsFromMarker(marker)) {
      if (element instanceof AbstractTypedElement) {
        AbstractTypedElement typedElement = (AbstractTypedElement) element;
        AbstractType type = typedElement.getAbstractType();
        String newName = type.getName();

        NamingHelper.synchronizeName(typedElement, newName);
      }
    }
  }

}
