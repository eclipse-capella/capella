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
package org.polarsys.capella.core.ui.semantic.browser.sirius.helpers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IWorkbenchPart;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.ui.semantic.browser.sirius.view.SiriusSemanticBrowserView;

/**
 */
public class SiriusSelectionHelper {
  /**
   * Handle selection for specified parameters.
   * @param part_p
   * @param selection_p
   * @param handleSemanticBrowserSelectionEvent_p <code>true</code> means selection coming from the semantic browser itself are handled.
   * @return <code>null</code> means nothing to select.
   */
  public static Object handleSelection(IWorkbenchPart part_p, ISelection selection_p, boolean handleSemanticBrowserSelectionEvent_p) {
    Object result = null;
    if (!selection_p.isEmpty() && (handleSemanticBrowserSelectionEvent_p || !(part_p instanceof SiriusSemanticBrowserView))) {
      if (selection_p instanceof IStructuredSelection) {
        IStructuredSelection selection_l = (IStructuredSelection) selection_p;
        Object firstElement = selection_l.getFirstElement();
        if (firstElement instanceof CapellaElement) {
          // Selection of a CapellaElement from a standard EMF editor for instance.
          result = firstElement;
        } else if (firstElement instanceof EditPart) {
          // Selection of a graphical element in a GMF-based diagram editor.
          EditPart selectedEditPart = (EditPart) firstElement;
          View view = (View) selectedEditPart.getModel();
          if (view.getElement() instanceof DSemanticDecorator) {
            DSemanticDecorator diagram = (DSemanticDecorator) view.getElement();
            if (null != diagram) {
              // diagram may be null when there is a note in the diagram
              // So here, there is a test to check whether this instance is null or not
              result = diagram.getTarget();
            }
          }
        } else if (firstElement instanceof DSemanticDecorator) {
          DSemanticDecorator vpe = (DSemanticDecorator) firstElement;
          result = vpe.getTarget();
        } else if (firstElement instanceof DRepresentationElement) {
          DRepresentationElement vpe = (DRepresentationElement) firstElement;
          List<EObject> elements = vpe.getSemanticElements();
          for (EObject element : elements) {
            result = element;
          }
        } else if (firstElement instanceof EObject) {
          result = firstElement;
        }
      }
    }

    if (result instanceof Part) {
      boolean allowMultiplePart = TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven((Part) result));
      if (!allowMultiplePart) {
        if (!(((Part) result).getAbstractType() instanceof ConfigurationItem)) {
          result = ((Part) result).getAbstractType();
        }
      }
    }

    return result;
  }

  /**
   * Handle selection for specified parameters.<br>
   * Default implementation ignores selection coming from the semantic browser itself.
   * @param part_p
   * @param selection_p
   * @return <code>null</code> means nothing to select.
   */
  public static Object handleSelection(IWorkbenchPart part_p, ISelection selection_p) {
    return handleSelection(part_p, selection_p, false);
  }
}
