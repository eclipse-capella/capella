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
package org.polarsys.capella.core.ui.properties.sections;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
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
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.controllers.DAnnotationReferenceController;
import org.polarsys.capella.core.ui.properties.controllers.EOIController;
import org.polarsys.capella.core.ui.properties.controllers.RepresentationContextualElementsController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.RepresentationContextualElementsField;
import org.polarsys.capella.core.ui.properties.fields.TextValueGroup;
import org.polarsys.capella.core.ui.properties.providers.CapellaTransfertViewerLabelProvider;
import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;

/**
 * Section that displays a {@link DRepresentation} properties.<br>
 * This implementation overrides common implementation to adapt it to {@link DRepresentation}.
 */
public class DiagramRepresentationPropertySection extends AbstractSection {
  private WeakReference<DRepresentationDescriptor> _descriptor;
  private Text _nameTextField;
  private FocusAdapter _focusAdapter;
  private KeyAdapter _keyAdapter;
  private RepresentationContextualElementsField _contextualElementsField;
  private MultipleSemanticField _eoiField;
  private TextValueGroup packageGroup;


  /**
   * Execute a command that changes the data model according to related widget.
   */
  protected void commitNameChanged() {
    // Precondition : name must be different to execute the command.
    if (_descriptor.get() == null || _nameTextField.getText().equals(_descriptor.get().getName())) {
          return;
    }
    executeCommmand(new AbstractReadWriteCommand() {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public Collection<?> getAffectedObjects() {
        return Collections.singleton(_descriptor);
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
        _descriptor.get().setName(_nameTextField.getText());
      }
    });
  }


  @Override
  protected int getColumnCount() {
    return 1;
  }



  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    // This operation history listener is used to force refreshes when undo / redo operations are performed.
    OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(this);

    TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();

    // Create the group.
    Group textGroup = widgetFactory.createGroup(parent, ICommonConstants.EMPTY_STRING);
    textGroup.setLayout(new GridLayout(2, false));
    textGroup.setLayoutData(new GridData(SWT.FILL, GridData.FILL, false, false));

    _focusAdapter = new FocusAdapter() {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void focusLost(FocusEvent e) {
        if (e.widget == _nameTextField) {
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
      public void keyPressed(KeyEvent event) {
        if ((event != null) && (event.character == SWT.CR)) {
          if (event.widget == _nameTextField) {
            commitNameChanged();
          }
        }
      }
    };

    createNameWidget(widgetFactory, textGroup);
    createPackageWidget(widgetFactory, textGroup);
    createContextualElementsWidget(widgetFactory, parent);
    createEOIWidget(widgetFactory, parent);
  }

  /**
   * Create name widget.
   * 
   * @param widgetFactory
   * @param textGroup
   */
  protected void createNameWidget(TabbedPropertySheetWidgetFactory widgetFactory, Group textGroup) {
    // Create Name text field.
    widgetFactory.createCLabel(textGroup, Messages.RepresentationSection_Name_Title);
    _nameTextField = widgetFactory.createText(textGroup, ICommonConstants.EMPTY_STRING);
    _nameTextField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    _nameTextField.addFocusListener(_focusAdapter);
    _nameTextField.addKeyListener(_keyAdapter);
  }

  /**
   * @param widgetFactory
   * @param rootParentComposite
   */
  protected void createContextualElementsWidget(TabbedPropertySheetWidgetFactory widgetFactory,
      Composite rootParentComposite) {
    boolean displayedInWizard = isDisplayedInWizard();
    _contextualElementsField = new RepresentationContextualElementsField(getReferencesGroup(),
        Messages.ContextualElements_Label, getWidgetFactory(), new RepresentationContextualElementsController());
    _contextualElementsField.setDisplayedInWizard(displayedInWizard);
  }

  protected void createEOIWidget(TabbedPropertySheetWidgetFactory widgetFactory, Composite rootParentComposite) { 
    _eoiField = new MultipleSemanticField(getReferencesGroup(), Messages.EOI_label, widgetFactory, new EOIController()) {

      @Override
      protected void doDeleteCommand(EObject element, EStructuralFeature feature) {
        ((DAnnotationReferenceController)_controller).clear(element);
        if (_valueEditBtn != null) {
          _valueEditBtn.setEnabled(true);
        }
        setValueTextField((EObject) null);
      }


      /*
       * Overridden to customize title/message, and custom expand behaviour for the left tree viewer
       */
      @Override
      protected List<EObject> openTransferDialog(Button button, List<EObject> currentElements,
          List<EObject> availableElements, String title, String message) {
        // TODO: Can ILabelProvider be reused?
        DataLabelProvider leftLabelProvider =  new CapellaTransfertViewerLabelProvider();
        DataLabelProvider rightLabelProvider =  new CapellaTransfertViewerLabelProvider();
        boolean expandLeftViewer = CapellaUIPropertiesPlugin.getDefault().isAllowedExpandLeftViewerContent();
        boolean expandRightViewer = CapellaUIPropertiesPlugin.getDefault().isAllowedExpandRightViewerContent();
        return SelectionDialogHelper.multiplePropertyTransfertDialogWizard(
            button.getShell(), ((DRepresentationDescriptor)semanticElement).getName(), Messages.EOI_dialogMessage, availableElements, currentElements, leftLabelProvider, rightLabelProvider,
            expandLeftViewer ? 2 : 0, expandRightViewer ? AbstractTreeViewer.ALL_LEVELS : 0);
        }

    };
    _eoiField.setDisplayedInWizard(isDisplayedInWizard());
  }

  protected void createPackageWidget(TabbedPropertySheetWidgetFactory widgetFactory, Composite parent) {
    packageGroup = new TextValueGroup(parent, Messages.Package_label, widgetFactory, true) {

      /**
       * @param label
       * @param hasResetBtn
       */
      protected void createValueTextField(String label, boolean hasResetBtn) {
        widgetFactory.createCLabel(parent, label);
        valueField = widgetFactory.createText(parent, ICommonConstants.EMPTY_STRING);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.heightHint = 20;
        gd.widthHint = 150;
        valueField.setLayoutData(gd);
        addListeners();
      }

      @Override
      public void loadTextValue() {
        String name = DiagramHelper.getService().getPackageName((DRepresentationDescriptor)semanticElement);
        valueField.setText(name == null ? ICommonConstants.EMPTY_STRING : name);
        updateResetBtnStatus();
      }

      @Override
      protected void setDataValue(EObject object, EStructuralFeature feature, final Object value) {
        TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(object);
        String newName = ((String) value).trim();
        if (newName.isEmpty()) {
          newName = null;
        }
        Command c = DiagramHelper.getService().createSetPackageNameCommand(domain, (DRepresentationDescriptor) object, newName);
        if (c != null && c.canExecute()) {
          domain.getCommandStack().execute(c);
        }
      }
    };

    packageGroup.setDisplayedInWizard(isDisplayedInWizard());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    super.dispose();

    if (null != _descriptor) {
      // Unregister...
      CapellaReadOnlyHelper.unregister(_descriptor.get(), this);

      _descriptor.clear();
      _descriptor = null;
    }
  }

  /**
   * @see org.eclipse.core.commands.operations.IOperationHistoryListener#historyNotification(org.eclipse.core.commands.operations.OperationHistoryEvent)
   */
  @Override
  public void historyNotification(OperationHistoryEvent event) {
    // We only handle undo & redo operations to force a refresh.
    int eventType = event.getEventType();
    if ((OperationHistoryEvent.UNDONE == eventType) || (OperationHistoryEvent.REDONE == eventType)) {
      IUndoableOperation operation = event.getOperation();
      // Take into account the EMF command operation.
      if (operation instanceof EMFCommandOperation) {
        // Get the command.
        Command command = ((EMFCommandOperation) operation).getCommand();
        // Is the current capella element involved in this command ?
        if (command.getAffectedObjects().contains(_descriptor)) {
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
    boolean isContextual = false;
    
    if (null != _descriptor) {
      name = _descriptor.get().getName();
      _eoiField.loadData(_descriptor.get());
      packageGroup.loadData(_descriptor.get(), null);
      if (_contextualElementsField != null) {
        isContextual = ContextualDiagramHelper.getService().isContextualRepresentation(_descriptor.get());
        _contextualElementsField.loadData(_descriptor.get());
      }
    }
    
    if (_contextualElementsField != null) {
      _contextualElementsField.setEnabled(isContextual);
    }
    _nameTextField.setText(name);
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
  public boolean select(Object toTest) {
    return (toTest instanceof DRepresentationDescriptor) || (toTest instanceof DRepresentation)
        || (toTest instanceof IDDiagramEditPart);
  }

  @Override
  protected ExecutionManager getExecutionManager() {
    return TransactionHelper.getExecutionManager(_descriptor.get());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    if (!selection.isEmpty()) {
      // Unregister...
      if (null != _descriptor) {
        CapellaReadOnlyHelper.unregister(_descriptor.get(), this);
      }

      if (selection instanceof IStructuredSelection) {
        Object firstElement = ((IStructuredSelection) selection).getFirstElement();

        if (firstElement instanceof IDDiagramEditPart) {
          IDDiagramEditPart diagramEditPart = (IDDiagramEditPart) firstElement;
          Diagram gmfDiagram = (Diagram) diagramEditPart.getModel();
          if (gmfDiagram != null) {
            firstElement = (DRepresentation) gmfDiagram.getElement();
          }
        }
        if (firstElement instanceof DRepresentation) {
          firstElement = RepresentationHelper.getRepresentationDescriptor((DRepresentation) firstElement);
        }
        if (firstElement instanceof DRepresentationDescriptor) {
          _descriptor = new WeakReference<DRepresentationDescriptor>((DRepresentationDescriptor) firstElement);
        
        } else {
          _descriptor = null;
        }
      }
      
      loadData();

      // Register....
      if (null != _descriptor) {
        register(_descriptor.get());
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);

    if ((null != _nameTextField) && !_nameTextField.isDisposed()) {
      _nameTextField.setEnabled(enabled);
    }
    if (null != _contextualElementsField) {
      _contextualElementsField.setEnabled(enabled);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    return Arrays.asList(_eoiField, packageGroup);
  }
}
