/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.clipboard.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IActionDelegate;
import org.polarsys.capella.core.platform.sirius.clipboard.commands.CapellaDiagramCopyCommand;
import org.polarsys.capella.core.platform.sirius.clipboard.util.MiscUtil;

/**
 * Copy action for Capella Diagrams
 */
public class CapellaDiagramCopyAction extends AbstractCopyPasteAction {

  /**
   * Default constructor
   */
  public CapellaDiagramCopyAction() {
    // Nothing
  }
  
  /**
   * @see IActionDelegate#run(IAction)
   */
  public void run(IAction action_p) {
    if (isInLabelEdition()) {
      // Copying while in text edition mode
      getEditingTextWidget().copy();
    } else if (isOnNoteContent()) {
      // Copying the content compartment of a Note
      copyTextToSystemClipboard(getSelectedNoteContentFigure().getText());
    } else {
      // Copying graphical and semantic elements
      List<? extends EObject> toCopy = getCopyPasteSelection();
      
      if (toCopy.size() > 0) {
        CapellaDiagramCopyCommand cmd = new CapellaDiagramCopyCommand(toCopy);
        MiscUtil.transactionallyExecute(toCopy, cmd);
      }
    }
  }

  /**
   * @see org.polarsys.capella.core.platform.sirius.clipboard.actions.AbstractCopyPasteAction#isEnabled()
   */
  @Override
  protected boolean isEnabled() {
    boolean result;
    if (isInLabelEdition()) {
      // Copying while in text edition mode
      result = getEditingTextWidget().getSelectionText().length() > 0;
    } else if (isOnNoteContent()) {
      // Copying the content compartment of a Note
      result = true;
    } else {
      if (isInSequenceDiagram()) {
        // Disable graphical copy/paste in Sequence Diagrams
        result = false;
      } else {
        // Nominal case: Selection must not be empty and not contain a diagram
        List<? extends View> selection = getCopyPasteSelection();
        result = !selection.isEmpty();
        for (View view : selection)
          if (view instanceof Diagram) {
            result = false; break;
          }
      }
    }
    return result;
  }
}
