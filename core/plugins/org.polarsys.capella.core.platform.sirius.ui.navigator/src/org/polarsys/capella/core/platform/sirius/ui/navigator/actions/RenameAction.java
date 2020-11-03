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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.actions.RenameResourceAction;
import org.eclipse.ui.actions.TextActionHandler;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.model.handler.provider.IReadOnlySectionHandler;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.ui.toolkit.AbstractCommandActionHandler;

/**
 * Standard action for renaming an {@link AbstractNamedElement} element in a Tree.
 * @see RenameResourceAction
 */
public class RenameAction extends AbstractCommandActionHandler {
  /**
   * Tree that this action is run against.
   */
  private Tree _navigatorTree;
  /**
   * Parent composite.
   */
  private Composite _textEditorParent;
  /**
   * Manager of the control where the end-user enters the new name.
   */
  private TreeEditor _treeEditor;
  /**
   * Text field where the end-user enters the new name.
   */
  private Text _textEditor;
  /**
   * Handler for Cut, Copy, Paste of text...
   */
  private TextActionHandler _textActionHandler;

  /**
   * Constructor.
   * @param domain_p
   */
  public RenameAction(CapellaCommonNavigator commonNavigator_p) {
    super(ICommonConstants.EMPTY_STRING); // this action is linked to a RetargetAction.
    _navigatorTree = commonNavigator_p.getCommonViewer().getTree();
    _treeEditor = new TreeEditor(_navigatorTree);
  }

  /**
   * Create the parent composite for the inner text editor widget.
   * @return
   */
  private Composite createParent() {
    Composite result = new Composite(_navigatorTree, SWT.NONE);
    TreeItem[] selectedItems = _navigatorTree.getSelection();
    _treeEditor.horizontalAlignment = SWT.LEFT;
    _treeEditor.grabHorizontal = true;
    _treeEditor.setEditor(result, selectedItems[0]);
    return result;
  }

  /**
   * Create the text editor widget.
   * @param selectedElement_p
   */
  private void createTextEditor(EObject selectedElement_p) {
    // Create text editor parent. This draws a nice bounding rectangle.
    _textEditorParent = createParent();
    _textEditorParent.setVisible(false);
    final int inset = getCellEditorInset(_textEditorParent);
    if (inset > 0) {
      _textEditorParent.addListener(SWT.Paint, new Listener() {
        @SuppressWarnings("synthetic-access")
        public void handleEvent(Event e) {
          Point textSize = _textEditor.getSize();
          Point parentSize = _textEditorParent.getSize();
          e.gc.drawRectangle(0, 0, Math.min(textSize.x + 4, parentSize.x - 1), parentSize.y - 1);
        }
      });
    }
    // Create inner text editor.
    _textEditor = new Text(_textEditorParent, SWT.NONE);
    _textEditor.setFont(_navigatorTree.getFont());
    _textEditorParent.setBackground(_textEditor.getBackground());

    // The text control should not be editable if the element is readonly (locked by other)
    IReadOnlySectionHandler handler = CapellaReadOnlyHelper.getReadOnlySectionHandler();
    if (handler != null) {
      _textEditor.setEditable(!handler.isLockedByOthers(selectedElement_p));
    }

    _textEditor.addListener(SWT.Modify, new Listener() {
      @SuppressWarnings("synthetic-access")
      public void handleEvent(Event e) {
        Point textSize = _textEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        textSize.x += textSize.y; // Add extra space for new characters.
        Point parentSize = _textEditorParent.getSize();
        _textEditor.setBounds(2, inset, Math.min(textSize.x, parentSize.x - 4), parentSize.y - (2 * inset));
        _textEditorParent.redraw();
      }
    });
    _textEditor.addListener(SWT.Traverse, new Listener() {
      @SuppressWarnings("synthetic-access")
      public void handleEvent(Event event) {
        // Workaround due to extra
        // traverse events
        switch (event.detail) {
          case SWT.TRAVERSE_ESCAPE:
            // Do nothing in this case
            disposeTextWidget();
            event.doit = true;
            event.detail = SWT.TRAVERSE_NONE;
          break;
          case SWT.TRAVERSE_RETURN:
            saveChangesAndDispose();
            event.doit = true;
            event.detail = SWT.TRAVERSE_NONE;
          break;
        }
      }
    });
    _textEditor.addFocusListener(new FocusAdapter() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void focusLost(FocusEvent fe) {
        saveChangesAndDispose();
      }
    });
    if (_textActionHandler != null) {
      _textActionHandler.addText(_textEditor);
    }
  }

  /**
   * Close the text widget and reset the editorText field.
   */
  private void disposeTextWidget() {
    if (_textActionHandler != null) {
      _textActionHandler.removeText(_textEditor);
      _textActionHandler.dispose();
      _textActionHandler = null;
    }

    if (_textEditorParent != null) {
      _textEditorParent.dispose();
      _textEditorParent = null;
      _textEditor = null;
      _treeEditor.setEditor(null, null);
    }
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#run()
   */
  @Override
  public void run() {
    EObject selectedElement = (EObject) getStructuredSelection().getFirstElement();
    queryNewNameInline(selectedElement);
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  public boolean updateSelection(IStructuredSelection selection_p) {
    boolean result = false;
    if (!selection_p.isEmpty()) {
      Object selectedElement = selection_p.getFirstElement();
      // Deal with abstract named elements only.
      if (selectedElement instanceof EObject) {
        if (!(selectedElement instanceof SequenceMessage)) {
          result = true;
        }
      }
    }
    return result;
  }

  /**
   * Return the new name to be given to the abstract element.
   * @param selectedElement_p
   */
  private void queryNewNameInline(EObject selectedElement_p) {
    // Make sure text editor is created only once. Simply reset text
    // editor when action is executed more than once.
    if (_textEditorParent == null) {
      createTextEditor(selectedElement_p);
    }

    EAttribute attribute = CapellaResourceHelper.getEditableAttribute(selectedElement_p);
    String name = null;
    if (attribute != null) {
      name = (String) selectedElement_p.eGet(attribute);
    }
    _textEditor.setText(null != name ? name : ICommonConstants.EMPTY_STRING);

    // Open text editor with initial size.
    _textEditorParent.setVisible(true);
    Point textSize = _textEditor.computeSize(SWT.DEFAULT, SWT.DEFAULT);
    textSize.x += textSize.y; // Add extra space for new characters.
    Point parentSize = _textEditorParent.getSize();
    int inset = getCellEditorInset(_textEditorParent);
    _textEditor.setBounds(2, inset, Math.min(textSize.x, parentSize.x - 4), parentSize.y - (2 * inset));
    _textEditorParent.redraw();
    _textEditor.selectAll();
    _textEditor.setFocus();
  }

  /**
   * Save the changes and dispose created text editor widget.
   */
  private void saveChangesAndDispose() {
    // We must get the selection outside the asyncExec to make sure performing against the right element.
    EObject selectedElement = (EObject) getStructuredSelection().getFirstElement();
    String newName = _textEditor.getText();
    // Ensure a change was performed.
    if (null != selectedElement) {
      EAttribute attribute = CapellaResourceHelper.getEditableAttribute(selectedElement);
      String selectedElementName = null;
      if (attribute != null) {
        selectedElementName = (String) selectedElement.eGet(attribute);
      }

      if ((null != newName) && !newName.equals(selectedElementName)) {
        // Create a set command to change the value.
        TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(selectedElement);
        domain.getCommandStack().execute(SetCommand.create(domain, selectedElement, attribute, newName));
      }
    }
    // Dispose the text widget.
    disposeTextWidget();
    // Ensure the Navigator tree has focus, which it may not if the text widget previously had focus.
    if ((_navigatorTree != null) && !_navigatorTree.isDisposed()) {
      _navigatorTree.setFocus();
    }
  }

  /**
   * Get the inset used for cell editors
   * @param control_p the Control
   * @return int
   */
  private static int getCellEditorInset(Control control_p) {
    return 1; // one pixel wide black border
  }

  /**
   * Set the text action handler.
   * @param actionHandler the action handler
   */
  public void setTextActionHandler(TextActionHandler actionHandler) {
    _textActionHandler = actionHandler;
  }
}
