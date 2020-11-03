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

package org.polarsys.capella.common.ui.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.kitalpha.emde.model.Element;

/**
 * The Capella adaptation.
 */
@Deprecated
public class ModelAdaptation {

  /**
   * Adapt a Sirius element into a Capella element.
   * And return the first element, if multiple selection.
   * @param element The Sirius element.
   * @return The Capella element.
   */
  public static ModelElement adaptToCapella(Object element) {
    if (element instanceof IStructuredSelection) {
      IStructuredSelection structuredSelection = (IStructuredSelection) element;
      List<ModelElement> adapted = adaptFromStructuredSelection(structuredSelection);
      return adapted.isEmpty()? null: adapted.get(0);
    }
    return adaptFromObject(element);
  }

  /**
   * Adapt a Sirius elements into a Capella elements.
   * @param element the selected element (could be the list of elements)
   * @return The Capella element.
   */
  public static List<ModelElement> adaptToCapellaElements(Object element) {
    List<ModelElement> result = new ArrayList<ModelElement>();
    if (element instanceof IStructuredSelection) {
      IStructuredSelection structuredSelection = (IStructuredSelection) element;
      return adaptFromStructuredSelection(structuredSelection);
    }
    result.add(adaptFromObject(element));
    return result;
  }

  private static ModelElement adaptFromObject(Object element) {
    ModelElement result = null;
    if (element instanceof ModelElement) {
      result = (ModelElement) element;
    } else {
      if (element instanceof IAdaptable) {
        // FIXME why not also ask the adapter manager?
        // GMF level adaptation
        Object dnodeElement = ((IAdaptable) element).getAdapter(EObject.class);
        if (dnodeElement != null) {
          EObject obj = (EObject) Platform.getAdapterManager().getAdapter(dnodeElement, Element.class);
          // Business level adaptation
          if (obj == null) {
            obj = (EObject) Platform.getAdapterManager().loadAdapter(dnodeElement, Element.class.getName());
          }
          if (obj instanceof ModelElement) {
            result = (ModelElement) obj;
          }
        }
      }
    }
    return result;
  }

  private static List<ModelElement> adaptFromStructuredSelection(IStructuredSelection structuredSelection) {
    List<ModelElement> result = new ArrayList<ModelElement>(0);
    Iterator<?> it = structuredSelection.iterator();
    while (it.hasNext()) {
      Object nextSelected = it.next();
      result.add(adaptFromObject(nextSelected));
    }

    return result;
  }

}
