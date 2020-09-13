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
package org.polarsys.capella.core.ui.semantic.browser.sirius.view;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.core.ui.semantic.browser.sirius.actions.DiagramOpenAction;
import org.polarsys.capella.core.ui.semantic.browser.sirius.helpers.SiriusSelectionHelper;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;

/**
 * Browser Semantic View. Load by extension point.
 */
public class SiriusSemanticBrowserView extends SemanticBrowserView {
  /**
   * {@inheritDoc}
   */
  @Override
  protected Object handleWorkbenchPageSelectionEvent(IWorkbenchPart part, ISelection selection) {
    return SiriusSelectionHelper.handleSelection(part, selection);
  }

  /**
   * @see org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView#handleDoubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
   */
  @Override
  protected void handleDoubleClick(DoubleClickEvent event) {
    boolean callSuper = true;
    ITreeSelection selection = (ITreeSelection) event.getSelection();
    if (!selection.isEmpty()) {
      for(Object selectedElement : selection.toList()) {
        if (selectedElement instanceof EObjectWrapper) {
          selectedElement = ((EObjectWrapper) selectedElement).getElement();
        }
        if (selectedElement instanceof DRepresentationDescriptor) {
          DiagramOpenAction action = new DiagramOpenAction();
          // Open related diagram editor.
          action.init(this);
          action.selectionChanged(null, new StructuredSelection(selectedElement));
          action.run(null);
          // if it is DRepresentation; then open the representation and return immediately.
          // Do not run into super.handleDoubleClick in order to avoid opening the wizard properties
          callSuper = false;
        }        
      }
    }
    if(callSuper) {
      super.handleDoubleClick(event);      
    }
  }
}
