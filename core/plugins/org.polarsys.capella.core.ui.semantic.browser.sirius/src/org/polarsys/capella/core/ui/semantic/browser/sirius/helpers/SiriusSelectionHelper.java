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
package org.polarsys.capella.core.ui.semantic.browser.sirius.helpers;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.ui.semantic.browser.sirius.view.SiriusSemanticBrowserView;

/**
 */
public class SiriusSelectionHelper {
  /**
   * Handle selection for specified parameters.
   * 
   * @param part
   * @param selection
   * @param handleSemanticBrowserSelectionEvent
   *          <code>true</code> means selection coming from the semantic browser itself are handled.
   * @return <code>null</code> means nothing to select.
   */
  public static Object handleSelection(IWorkbenchPart part, ISelection selection,
      boolean handleSemanticBrowserSelectionEvent) {
    Object result = null;
    if (selection != null && !selection.isEmpty()
        && (handleSemanticBrowserSelectionEvent || !(part instanceof SiriusSemanticBrowserView))) {
      if (selection instanceof IStructuredSelection) {
        IStructuredSelection selection_l = (IStructuredSelection) selection;
        Object firstElement = selection_l.getFirstElement();
        result = CapellaAdapterHelper.resolveDescriptorOrBusinessObject(firstElement);
      }
    }
    return result;
  }

  /**
   * Handle selection for specified parameters.<br>
   * Default implementation ignores selection coming from the semantic browser itself.
   * 
   * @param part
   * @param selection
   * @return <code>null</code> means nothing to select.
   */
  public static Object handleSelection(IWorkbenchPart part, ISelection selection) {
    return handleSelection(part, selection, false);
  }
}
