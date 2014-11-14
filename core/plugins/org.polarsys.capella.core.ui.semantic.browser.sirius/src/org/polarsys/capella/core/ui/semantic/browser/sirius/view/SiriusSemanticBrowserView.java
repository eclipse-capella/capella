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
package org.polarsys.capella.core.ui.semantic.browser.sirius.view;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.table.metamodel.table.DTable;
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
  protected Object handleWorkbenchPageSelectionEvent(IWorkbenchPart part_p, ISelection selection_p) {
    return SiriusSelectionHelper.handleSelection(part_p, selection_p);
  }

  /**
   * @see org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView#handleDoubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
   */
  @Override
  protected void handleDoubleClick(DoubleClickEvent event_p) {
    super.handleDoubleClick(event_p);
    ITreeSelection selection = (ITreeSelection) event_p.getSelection();
    if (!selection.isEmpty()) {
      Object selectedElement = selection.getFirstElement();
      if (selectedElement instanceof EObjectWrapper) {
        selectedElement = ((EObjectWrapper) selectedElement).getElement();
      }
      if ((selectedElement instanceof DSemanticDiagram) || (selectedElement instanceof DTable)) {
        DiagramOpenAction action = new DiagramOpenAction();
        // Open related diagram editor.
        action.init(this);
        action.selectionChanged(null, new StructuredSelection(selectedElement));
        action.run(null);
      }
    }
  }
}
