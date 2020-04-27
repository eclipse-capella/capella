/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.detachment.propertyvalues.ui.page;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.model.label.LabelRetriever;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;

public class PropertyValueLabelProvider extends MDEAdapterFactoryLabelProvider {

  @Override
  public String getColumnText(Object element, int columnIndex) {

    if (columnIndex == 0) {
      return LabelRetriever.getLabel((EObject) element);
    }
    if (columnIndex == 1) {
      return LabelRetriever.getFullLabel((EObject) element);
    }
    return null;
  }
}