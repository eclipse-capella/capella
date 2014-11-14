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
package org.polarsys.capella.core.ui.properties.sections;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.ui.properties.controllers.RepresentationContextualElementsController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.RepresentationContextualElementsField;

/**
 * Section that displays a {@link DRepresentation} properties.<br>
 * This implementation overrides common implementation to adapt it to {@link DRepresentation}.
 */
public class DiagramRepresentationPropertySection extends AbstractSection {
  private WeakReference<DRepresentation> _representation;
  private Text _nameTextField;
  private FocusAdapter _focusAdapter;
  private KeyAdapter _keyAdapter;
  private RepresentationContextualElementsField _contextualElementsField;

  /**
   * Execute a command that changes the data model according to related widget.
   */
  protected void commitNameChanged() {
    // Precondition : name must be different to execute the command.
    if (_nameTextField.getText().equals(_representation.get().getName())) {
      return;
    }
    executeCommmand(new AbstractReadWriteCommand() {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public Collection<?> getAffectedObjects() {
        return Collections.singleton(_representation);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public String getName() {
        return Messages.RepresentationSection_SetCommand_Representation_Name_Label;
      }

      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      public void run() {
        _representation.get().setName(_nameTextField.getText());
      }
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent_p, TabbedPropertySheetPage aTabbedPropertySheetPage_p) {
    super.createControls(parent_p, aTabbedPropertySheetPage_p);
    // This operation history listener is used to force refreshes when undo / redo operations are performed.
    OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(this);

    TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();

    _rootParentComposite.setLayout(new GridLayout());

    // Create the group.
    Group textGroup = widgetFactory.createGroup(_rootParentComposite, ICommonConstants.EMPTY_STRING);
    textGroup.setLayout(new GridLayout(2, false));
    textGroup.setLayoutData(new GridData(SWT.FILL, GridData.FILL, false, false));

    _focusAdapter = new FocusAdapter() {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void focusLost(FocusEvent e_p) {
        if (e_p.widget == _nameTextField) {
          commitNameChanged();
        }
      }
    };
    _keyAdapter = new KeyAdapter() {

      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void keyPressed(KeyEvent event_p) {
        if ((event_p != null) && (event_p.character == SWT.CR)) {
          if (event_p.widget == _nameTextField) {
            commitNameChanged();
          }
        }
      }
    };

    // Create name widget.
    createNameWidget(widgetFactory, textGroup);

    // Create Contextual Elements widget.
    createContextualElementsWidget(widgetFactory, _rootParentComposite);
  }

  /**
   * Create name widget.
   * @param widgetFactory_p
   * @param textGroup_p
   */
  protected void createNameWidget(TabbedPropertySheetWidgetFactory widgetFactory_p, Group textGroup_p) {
    // Create Name text field.
    widgetFactory_p.createCLabel(textGroup_p, Messages.RepresentationSection_Name_Title);
    _nameTextField = widgetFactory_p.createText(textGroup_p, ICommonConstants.EMPTY_STRING);
    _nameTextField.setLayoutData(new GridData(SWT.FILL, GridData.FILL, true, false));
    _nameTextField.addFocusListener(_focusAdapter);
    _nameTextField.addKeyListener(_keyAdapter);
  }

  /**
   * @param widgetFactory_p
   * @param rootParentComposite_p
   */
  protected void createContextualElementsWidget(TabbedPropertySheetWidgetFactory widgetFactory_p, Composite rootParentComposite_p) {
    boolean displayedInWizard = isDisplayedInWizard();
    _contextualElementsField =
        new RepresentationContextualElementsField(getReferencesGroup(), Messages.ContextualElements_Label, getWidgetFactory(),
            new RepresentationContextualElementsController());
    _contextualElementsField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    super.dispose();

    if (null != _representation) {
      // Unregister...
      CapellaReadOnlyHelper.unregister(_representation.get(), this);

      _representation.clear();
      _representation = null;
    }
  }

  /**
   * @see org.eclipse.core.commands.operations.IOperationHistoryListener#historyNotification(org.eclipse.core.commands.operations.OperationHistoryEvent)
   */
  @Override
  public void historyNotification(OperationHistoryEvent event_p) {
    // We only handle undo & redo operations to force a refresh.
    int eventType = event_p.getEventType();
    if ((OperationHistoryEvent.UNDONE == eventType) || (OperationHistoryEvent.REDONE == eventType)) {
      IUndoableOperation operation = event_p.getOperation();
      // Take into account the EMF command operation.
      if (operation instanceof EMFCommandOperation) {
        // Get the command.
        Command command = ((EMFCommandOperation) operation).getCommand();
        // Is the current capella element involved in this command ?
        if (command.getAffectedObjects().contains(_representation)) {
          // If so, let's refresh the content.
          refresh();
        }
      }
    }
  }

  /**
   * Reload widgets according to data model.
   */
  protected void loadData() {
    String name = ICommonConstants.EMPTY_STRING;
    if (null != _representation) {
      name = _representation.get().getName();
    }

    _nameTextField.setText(name);
    if (_contextualElementsField != null) {
      _contextualElementsField.loadData(_representation.get());
      boolean isContextual = ContextualDiagramHelper.getService().isContextualRepresentation(_representation.get());
      _contextualElementsField.setEnabled(isContextual);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void refresh() {
    loadData();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean select(Object toTest_p) {
    return (toTest_p instanceof DRepresentation) || (toTest_p instanceof IDDiagramEditPart);
  }

  @Override
  protected ExecutionManager getExecutionManager() {
    return TransactionHelper.getExecutionManager(_representation.get());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setInput(IWorkbenchPart part_p, ISelection selection_p) {
    if (!selection_p.isEmpty()) {
      // Unregister...
      if (null != _representation) {
        CapellaReadOnlyHelper.unregister(_representation.get(), this);
      }

      if (selection_p instanceof IStructuredSelection) {
        Object firstElement = ((IStructuredSelection) selection_p).getFirstElement();
        if (firstElement instanceof DRepresentation) {
          _representation = new WeakReference<DRepresentation>((DRepresentation) firstElement);
        } else if (firstElement instanceof IDDiagramEditPart) {
          IDDiagramEditPart diagramEditPart = (IDDiagramEditPart) firstElement;
          _representation = new WeakReference<DRepresentation>((DRepresentation) ((Diagram) diagramEditPart.getModel()).getElement());
        } else {
          _representation = null;
        }
      }
      loadData();

      // Register....
      if (null != _representation) {
        register(_representation.get());
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    super.setEnabled(enabled_p);

    if ((null != _nameTextField) && !_nameTextField.isDisposed()) {
      _nameTextField.setEnabled(enabled_p);
    }
    if (null != _contextualElementsField) {
      _contextualElementsField.setEnabled(enabled_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    return Collections.emptyList();
  }
}
