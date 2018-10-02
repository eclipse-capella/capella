/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;

/**
 * A selection helper, providing some utility methods.
 * 
 * @author Sandu Postaru
 *
 */
public class CapellaMASelectionHelper {

  private CapellaMASelectionHelper() {
    // Exists only to defeat instantiation.
  }

  public static Collection<EObject> getElementsFromSelection(ISelection selection) {

    if (selection.isEmpty() || !(selection instanceof IStructuredSelection)) {
      return Collections.emptyList();
    }

    List<?> selectedElements = ((IStructuredSelection) selection).toList();
    List<EObject> eObjects = new ArrayList<>();

    for (Object selectedElement : selectedElements) {
      EObject eObject = CapellaAdapterHelper.resolveSemanticObject(selectedElement, false);

      if (eObject != null) {
        eObjects.add(eObject);
      }
    }
    return eObjects;
  }

}
