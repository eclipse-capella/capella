/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.shared.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.kitalpha.massactions.shared.helper.MASelectionHelper;

/**
 * A selection helper, providing some utility methods.
 * 
 * @author Sandu Postaru
 *
 */
public class CapellaMASelectionHelper extends MASelectionHelper {

  @Override
  public Collection<EObject> getElementsFromSelection(ISelection selection) {
    if (selection.isEmpty() || !(selection instanceof IStructuredSelection)) {
      return Collections.emptyList();
    }

    List<?> selectedElements = ((IStructuredSelection) selection).toList();

    List<EObject> elementsFromSelection = new ArrayList<>();

    for (Object selectedElement : selectedElements) {
      if (selectedElement instanceof DRepresentationDescriptor) {
        elementsFromSelection.add((DRepresentationDescriptor) selectedElement);
      } else {
        EObject semanticElement = CapellaAdapterHelper.resolveBusinessObject(selectedElement);
        if (semanticElement != null) {
          elementsFromSelection.add(semanticElement);
        }
      }
    }
    return elementsFromSelection;
  }
}
