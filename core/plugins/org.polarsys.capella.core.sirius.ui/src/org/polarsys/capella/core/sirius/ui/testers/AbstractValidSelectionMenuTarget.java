/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.testers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

public abstract class AbstractValidSelectionMenuTarget extends PropertyTester {

  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
    if (property.equals(getPropertyId())) {
      if (receiver instanceof IStructuredSelection) {
        IStructuredSelection selection = (IStructuredSelection) receiver;

        // Selection shall not be empty
        if (selection.isEmpty())
          return false;

        List<EObject> resolvedElements = new ArrayList<EObject>();
        Iterator<?> iterator = selection.iterator();
        Object selectedElement = null;
        while (iterator.hasNext()) {
          selectedElement = iterator.next();
          EObject element = (selectedElement instanceof EObject) ? (EObject) selectedElement
              : Adapters.adapt(selectedElement, EObject.class, true);
          if (element instanceof DSemanticDecorator) {
            element = ((DSemanticDecorator) element).getTarget();
            if (isValid(element)) {
              resolvedElements.add(element);
            }
          }
        }
        return resolvedElements.size() == selection.size();
      }
    }
    return false;
  }

  protected abstract String getPropertyId();

  protected abstract boolean isValid(EObject element);
}
