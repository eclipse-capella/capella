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
package org.polarsys.capella.core.ui.semantic.browser.sirius.actions;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;

import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.ShowInDiagramAction;

/**
 * Open a diagram and select/reveal in this open diagram, the element displayed as root one in the current viewer.
 */
public class DiagramOpenFocusAction extends DiagramOpenAction {

  /**
   * Post editor open handling method.<br>
   * Default behavior sets the selection of open editor with specified element.
   * @param element_p
   * @param openEditor_p
   */
  @Override
  protected void postEditorOpen(Object element_p, IEditorPart openEditor_p) {
    if (null != openEditor_p) {
      // Select and reveal current element displayed in the semantic browser in open editor.
      ShowInDiagramAction action = new ShowInDiagramAction();
      action.selectionChanged(new StructuredSelection(element_p));
      action.run();
    }
  }

}
