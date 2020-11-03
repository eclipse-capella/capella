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
package org.polarsys.capella.core.platform.sirius.clipboard.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.platform.sirius.clipboard.util.LayerUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.SiriusUtil;

/**
 * Common facilities for copy/paste actions in Capella diagrams
 */
public abstract class AbstractCopyPasteAction implements IObjectActionDelegate {

  // A clipboard which allows for data transfer with the system keyboard,
  // useful for copying text which is not enclosed in a Text widget, e.g. the
  // content compartment of a Note
  private static final Clipboard CLIPBOARD = new Clipboard(PlatformUI.getWorkbench().getDisplay());

  // The current selection, if structured, or null
  private IStructuredSelection _selection;
  // The current shell
  private Shell _shell;
  // The current Text widget for label edition, or null if no label is being edited
  private Text _editingTextWidget;
  // The current compartment figure of a Note being selected, or null if none
  private WrappingLabel _noteContent;

  /**
   * Basic constructor
   */
  public AbstractCopyPasteAction() {
    super();
    _selection = null;
    _shell = null;
    _editingTextWidget = null;
    _noteContent = null;
  }

  /**
   * Set the given String as the new content of the system clipboard
   */
  protected void copyTextToSystemClipboard(String text) {
    if (text != null) {
      CLIPBOARD.setContents(new Object[] { text }, new Transfer[] { TextTransfer.getInstance() });
    }
  }

  /**
   * Simple helper returning the child control of the given composite which has the focus, if any
   */
  private Control getChildUnderFocus(Composite composite) {
    for (Control child : composite.getChildren()) {
      if (!child.isDisposed() && child.isFocusControl()) {
        return child;
      }
    }
    return null;
  }

  /**
   * Return the current selection adapted for copy/paste, i.e., only the relevant elements from the GMF layer
   */
  protected List<? extends View> getCopyPasteSelection() {
    List<? extends View> selectionList = LayerUtil.toGmf(getSelection());
    selectionList.removeIf(element -> (TitleBlockHelper.isTitleBlockAnnotation(LayerUtil.getSiriusElement((View)element).getTarget())));
    return selectionList;
  }

  /**
   * Return the text widget being edited at the last selection change, if any
   */
  protected Text getEditingTextWidget() {
    return _editingTextWidget;
  }

  /**
   * Return the content compartment of a Note being selected at the last selection change, if any
   */
  protected WrappingLabel getSelectedNoteContentFigure() {
    return _noteContent;
  }

  /**
   * Return a filtered, relevant selection
   */
  protected List<EditPart> getSelection() {
    return toGef(_selection);
  }

  /**
   * Return the current shell
   */
  protected Shell getShell() {
    return _shell;
  }

  /**
   * Return the content of the system clipboard as a String if applicable, otherwise return null
   */
  protected String getTextFromSystemClipboard() {
    String result = null;
    Object contents = CLIPBOARD.getContents(TextTransfer.getInstance());
    if (contents instanceof String) {
      result = (String) contents;
    }
    return result;
  }

  /**
   * Override to define pop-up menu enabled state. Computation should not be expensive.
   */
  protected abstract boolean isEnabled();

  /**
   * Return whether this copy/paste action occurs while a label is being edited
   */
  protected boolean isInLabelEdition() {
    return _editingTextWidget != null;
  }

  /**
   * Return whether the action occurred in the context of a Sequence Diagram
   */
  protected boolean isInSequenceDiagram() {
    boolean result = false;
    if ((_selection != null) && !_selection.isEmpty()) {
      Object selected = _selection.getFirstElement();
      if (selected instanceof EditPart) {
        Object gmfElement = ((EditPart) selected).getModel();
        if (gmfElement instanceof View) {
          EObject siriusElement = ((View) gmfElement).getElement();
          result = SiriusUtil.isInSequenceDiagram(siriusElement);
        }
      }
    }
    return result;
  }

  /**
   * Return whether this copy/paste action occurs while the content of a Note is selected
   */
  protected boolean isOnNoteContent() {
    return _noteContent != null;
  }

  /**
   * Return the text widget being edited in the current selection, if any
   */
  private Text lookupEditingTextWidget() {
    Text result = null;
    if ((_selection != null) && (_selection.toList().size() == 1) && (_selection.getFirstElement() instanceof EditPart)) {
      EditPart selected = (EditPart) _selection.getFirstElement();
      if ((selected.getViewer() != null) && (selected.getViewer().getControl() instanceof Composite)) {
        Composite selectedComposite = (Composite) selected.getViewer().getControl();
        Control underFocus = getChildUnderFocus(selectedComposite);
        if (underFocus instanceof Text) {
          result = (Text) underFocus;
        }
      }
    }
    return result;
  }

  /**
   * @see IActionDelegate#selectionChanged(IAction, ISelection)
   */
  public void selectionChanged(IAction action, ISelection selection) {
    if (selection instanceof IStructuredSelection) {
      _selection = (IStructuredSelection) selection;
      _editingTextWidget = lookupEditingTextWidget();
      // _noteContent is set to null because we should not apply copy/past behavior to a GMF note.
      // _noteContent = lookupSelectedNoteContentFigure();
      _noteContent = null;

      boolean enabled = isEnabled();
      if (action != null) {
        action.setEnabled(enabled);
      }
    } else {
      _selection = null;
      _editingTextWidget = null;
      _noteContent = null;
      if (action != null) {
        action.setEnabled(false);
      }
    }
  }

  /**
   * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
   */
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    _shell = targetPart.getSite().getShell();
  }

  /**
   * Extract relevant GEF elements from selection
   */
  private List<EditPart> toGef(IStructuredSelection selection) {
    List<EditPart> result = new ArrayList<EditPart>();
    if (selection != null) {
      @SuppressWarnings("unchecked")
      Iterator<Object> it = selection.iterator();
      while (it.hasNext()) {
        Object current = it.next();
        if (current instanceof EditPart) {
          result.add((EditPart) current);
        }
      }
    }
    return result;
  }

}
