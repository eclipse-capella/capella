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
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagramElement;

public abstract class AbstractValidCategoryMenuTargetTester extends PropertyTester {

  public AbstractValidCategoryMenuTargetTester() {
  }

  /**
   * Tests that all selected elements are edges, and that each model element is valid
   */
  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
    if (getPropertyId().equals(property)) {
      if(receiver instanceof IStructuredSelection) {
        IStructuredSelection selection = (IStructuredSelection) receiver;
        List<AbstractGraphicalEditPart> edgeEditParts = new ArrayList<AbstractGraphicalEditPart>();
        for (Object elem : selection.toList()) {
          if (elem instanceof AbstractGraphicalEditPart) {
            edgeEditParts.add((AbstractGraphicalEditPart) elem);
          }
        }

        if (edgeEditParts.size() != selection.size())
          return false;

        List<EObject> candidates = edgeEditParts.stream().map(edgeEditPart -> edgeEditPart.getModel())
            .map(View.class::cast).map(model -> model.getElement()).map(DDiagramElement.class::cast)
            .map(element -> element.getTarget()).collect(Collectors.toList());

        List<EObject> validCandidates = candidates.stream().filter(modelElement -> isValidContext(modelElement))
            .map(getTargetClass()::cast)
            .collect(Collectors.toList());

        if (candidates.size() != validCandidates.size())
          return false;

        return true;
      }
    }
    return false;
  }

  protected boolean isValidContext(EObject context) {
    return false;
  }

  protected Class<? extends EObject> getTargetClass() {
    return null;
  }

  protected String getPropertyId() {
    return "";
  }
}
