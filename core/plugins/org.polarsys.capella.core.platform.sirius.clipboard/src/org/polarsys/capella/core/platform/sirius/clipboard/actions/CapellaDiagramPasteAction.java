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
package org.polarsys.capella.core.platform.sirius.clipboard.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionDelegate;
import org.polarsys.capella.core.platform.sirius.clipboard.Activator;
import org.polarsys.capella.core.platform.sirius.clipboard.Messages;
import org.polarsys.capella.core.platform.sirius.clipboard.commands.CapellaDiagramPasteCommand;
import org.polarsys.capella.core.platform.sirius.clipboard.commands.GraphicalAdjustmentCommand;
import org.polarsys.capella.core.platform.sirius.clipboard.util.CapellaDiagramClipboard;
import org.polarsys.capella.core.platform.sirius.clipboard.util.GmfUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.LayerUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.MiscUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.SiriusUtil;

/**
 * Paste action for Capella Diagrams
 */
// For WrapLabel: upward compatibility
public class CapellaDiagramPasteAction extends AbstractCopyPasteAction {

  // The location of the mouse cursor at right-click
  private Point _cursorLocation;
  // Whether paste happened successfully (nominal case only)
  private boolean _success;

  /**
   * Default constructor
   */
  public CapellaDiagramPasteAction() {
    _cursorLocation = null;
    _success = false;
  }

  /**
   * @see IActionDelegate#run(IAction)
   */
  public void run(IAction action_p) {
    Text currentEditingText = getEditingTextWidget();
    WrapLabel selectedNoteContent = getSelectedNoteContentFigure();
    if (currentEditingText != null) {
      // Pasting while in text edition mode
      currentEditingText.paste();
    } else if (selectedNoteContent != null) {
      // Pasting in the content compartment of a Note
      String toPaste = getTextFromSystemClipboard();
      if (toPaste != null) {
        selectedNoteContent.setText(toPaste);
      }
    } else {
      // Pasting in a model element (nominal case)
      pasteElements();
    }
  }

  /**
   * Paste semantic and graphical elements (nominal case)
   */
  private void pasteElements() {
    ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(getShell());
    IRunnableWithProgress runnable = new IRunnableWithProgress() {
      public void run(IProgressMonitor monitor_p) throws InvocationTargetException, InterruptedException {
        doRun(monitor_p);
      }
    };
    try {
      progressDialog.run(false, false, runnable);
    } catch (InterruptedException e) {
      // Automatic rollback
    } catch (InvocationTargetException e) {
      // Should not happen
    }
    // Display message in the case of failure
    if (!_success) {
      MessageDialog.openError(getShell(), Activator.LABEL, Messages.CapellaDiagramPasteAction_Failure);
    }
  }

  /**
   * Core computation for nominal paste, called from progress dialog
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  /* package */void doRun(IProgressMonitor monitor_p) {
    try {
      monitor_p.beginTask(Messages.CapellaDiagramPasteAction_ProgressMessage, 2);
      // Selection not empty because action enabled
      List<? extends View> gmfSelection = getCopyPasteSelection();
      // Sirius + Capella layer
      monitor_p.worked(1);
      CapellaDiagramPasteCommand siriusCmd = new CapellaDiagramPasteCommand(gmfSelection);
      _success = MiscUtil.transactionallyExecute(gmfSelection, siriusCmd);
      monitor_p.worked(1);
      // GMF layer, in a separate command to let Sirius create GMF elements
      if (_success) {
        GraphicalAdjustmentCommand gmfCmd;
        View target = siriusCmd.getGmfTarget();
        EObject siriusTarget = LayerUtil.getSiriusElement(target);
        if (SiriusUtil.layoutIsConstrained(siriusTarget) && mustRefresh()) {
          // Just refresh the diagram
          gmfCmd = new GraphicalAdjustmentCommand(target);
        } else {
          // Duplicate layout
          Point relativeLocation = getRelativeLocation();
          gmfCmd =
              new GraphicalAdjustmentCommand((List) siriusCmd.getResults(), siriusCmd.getPastedSiriusElementsOrigins(), target, relativeLocation, mustRefresh());
        }
        _success = MiscUtil.transactionallyExecute(gmfSelection, gmfCmd);
      }
    } finally {
      monitor_p.done();
    }
  }

  /**
   * Return the location of the mouse cursor at right-click relative to the underlying edit part, or null if no location can be computed
   */
  private Point getRelativeLocation() {
    Point result = null;
    EditPart target = getSelection().get(0);
    if (target instanceof GraphicalEditPart) {
      result = GmfUtil.getEditPartRelativeLocation(_cursorLocation, (GraphicalEditPart) target);
    }
    return result;
  }

  /**
   * @see org.polarsys.capella.core.platform.sirius.clipboard.actions.AbstractCopyPasteAction#isEnabled()
   */
  @Override
  protected boolean isEnabled() {
    boolean result;
    if (isInLabelEdition()) {
      // Pasting while in text edition mode
      result = true;
    } else if (isOnNoteContent()) {
      // Pasting the content compartment of a Note
      result = getTextFromSystemClipboard() != null;
    } else {
      // Nominal case
      result = !isInSequenceDiagram() && !CapellaDiagramClipboard.getInstance().isEmpty();
    }
    return result;
  }

  /**
   * Return whether refresh must be performed at the end of the paste operation, e.g. to draw arcs.
   */
  protected boolean mustRefresh() {
    return true;
  }

  /**
   * At selectionChanged, store mouse cursor location
   * @see org.polarsys.capella.core.platform.sirius.clipboard.actions.AbstractCopyPasteAction#selectionChanged(org.eclipse.jface.action.IAction,
   *      org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void selectionChanged(IAction action_p, ISelection selection_p) {
    super.selectionChanged(action_p, selection_p);
    if (getShell() != null) {
      _cursorLocation = getShell().getDisplay().getCursorLocation();
    }
  }

}
