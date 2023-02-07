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
package org.polarsys.capella.core.sirius.ui.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractSelectInEditorCommandHandler extends AbstractDiagramCommandHandler {

  List<DRepresentationElement> elementsToSelect = new ArrayList<DRepresentationElement>();

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    Collection<? extends DRepresentationElement> elements = getElementsToSelect();
    selectInEditor(elements);
    return elementsToSelect;
  }
  /**
   * Select elementsToSelect in the DiagramEditor
   * @param elementsToSelect
   */
  private void selectInEditor(Collection<? extends DRepresentationElement> elementsToSelect) {
    IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    if (activeEditor instanceof DialectEditor) {
      DialectUIManager.INSTANCE.selectAndReveal((DialectEditor) activeEditor, new ArrayList<>(elementsToSelect));
      this.elementsToSelect.addAll(elementsToSelect);
    }
  }
  
  /**
   * Elements to be selected in editor
   * Needs to be implemented by subclasses
   * @return
   */
  protected Collection<? extends DRepresentationElement> getElementsToSelect() {
    return Collections.emptyList();
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
