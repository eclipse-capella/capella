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
package org.polarsys.capella.core.ui.properties.fields;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.ui.toolkit.actions.move.MoveDownAction;
import org.polarsys.capella.core.ui.toolkit.actions.move.MoveUpAction;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.ui.properties.IImageKeys;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;
import org.polarsys.capella.core.ui.properties.viewers.TableDelegatedViewer;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.AbstractReadOnlyCommand;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 */
public class ContainmentTableField extends AbstractStructuredRepresentationField {

  protected EReference _referencerFeature;
  protected EClass _referencedFeatureType;
  protected String _selectionElementDialogMessage;

  private Button _addBtn;
  private Button _upBtn;
  private Button _downBtn;

  /**
   * Constructor.
   *
   * @param parent_p
   * @param widgetFactory_p
   * @param referencerFeature_p
   * @param referencedFeature_p a feature that refers to another element from elements contained by the semantic feature.
   * @param referencedFeatureType_p the concrete type of the referenced feature (Use to create new element).
   * @param label_p the label displayed at the right top of the table.
   * @param selectionElementDialogMessage_p The message displayed when opening the dialog to add / create an element.
   */
  public ContainmentTableField(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, EReference referencerFeature_p,
      EReference referencedFeature_p, EClass referencedFeatureType_p, String label_p, String selectionElementDialogMessage_p) {
    super(parent_p, widgetFactory_p, referencedFeature_p, label_p, new TableDelegatedViewer(widgetFactory_p));

    _referencerFeature = referencerFeature_p;
    _referencedFeatureType = referencedFeatureType_p;
    _selectionElementDialogMessage = selectionElementDialogMessage_p;
  }

  /**
   * Create the actions.
   */
  @Override
  protected void createCustomActions(Composite parent_p) {
    _downBtn = createTableButton(parent_p, CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.IMG_ARROW_DOWN), new Runnable() {
      public void run() {
        handleDown();
      }
    });
    _upBtn = createTableButton(parent_p, CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.IMG_ARROW_UP), new Runnable() {
      public void run() {
        handleUp();
      }
    });
    _addBtn = createTableButton(parent_p, CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.IMG_ADD_BUTTON), new Runnable() {
      public void run() {
        handleAdd();
      }
    });
  }

  /**
   * Handle Delete button.
   */
  @SuppressWarnings("unchecked")
  protected void handleDelete() {
    if (null != _delegatedViewer) {
      ColumnViewer columnViewer = _delegatedViewer.getColumnViewer();
      if (null != columnViewer) {
        final List<EObject> selectedReferencedElements = ((IStructuredSelection) columnViewer.getSelection()).toList();
        AbstractReadWriteCommand deleteCommand = new AbstractReadWriteCommand() {
          @Override
          public String getName() {
            return Messages.ReferencesTableField_DeleteCommand_Label;
          }

          public void run() {
            CapellaDeleteCommand command = new CapellaDeleteCommand(MDEAdapterFactory.getExecutionManager(),
              getContainedElementsfor(selectedReferencedElements), true, false, false);
            if (command.canExecute()) {
              command.execute();
            }
          }
        };
        executeCommand(deleteCommand);
        refreshViewer();
      }
    }
  }

  /**
   * Handle Add button.
   */
  @SuppressWarnings("unchecked")
  protected void handleAdd() {
    // Create and open a selection element dialog.
    final Collection<? extends EObject> selectedElements = DialogHelper.openMultiSelectionDialog(_addBtn, getAvailableElementsToAdd());
    if (null != selectedElements) {
      AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
        public void run() {
          // Get the collection related to the semantic feature.
          List<EObject> containedElements = (List<EObject>) _semanticElement.eGet(_semanticFeature);
          // Get the type pointed by the referenced feature.
          // Get the factory instance that enables to create reference type objects.
          EFactory eFactoryInstance = _referencedFeatureType.getEPackage().getEFactoryInstance();
          for (EObject selectedElement : selectedElements) {
            // Create a new object.
            EObject createdObjectForSelectedOne = eFactoryInstance.create(_referencedFeatureType);
            // Set selected object in this created object according to the reference feature.
            if (_referencedFeature != null) {
              createdObjectForSelectedOne.eSet(_referencedFeature, selectedElement);
            }
            if (_referencerFeature != null) {
              createdObjectForSelectedOne.eSet(_referencerFeature, _semanticElement);
            }
            // Add it in already contained object.
            containedElements.add(createdObjectForSelectedOne);
          }
        }
      };
      MDEAdapterFactory.getExecutionManager().execute(command);
      refreshViewer();
    }
  }

  /**
   * Handle Down button.
   */
  @SuppressWarnings("unchecked")
  protected void handleDown() {
    List<EObject> selectedReferencedElements = ((IStructuredSelection) _delegatedViewer.getColumnViewer().getSelection()).toList();
    MoveDownAction action = new MoveDownAction(MDEAdapterFactory.getEditingDomain());
    action.updateSelection(new StructuredSelection(getContainedElementsfor(selectedReferencedElements)));
    action.run();
    refreshViewer();
  }

  /**
   * Handle Up button.
   */
  @SuppressWarnings("unchecked")
  protected void handleUp() {
    List<EObject> selectedReferencedElements = ((IStructuredSelection) _delegatedViewer.getColumnViewer().getSelection()).toList();
    MoveUpAction action = new MoveUpAction(MDEAdapterFactory.getEditingDomain());
    action.updateSelection(new StructuredSelection(getContainedElementsfor(selectedReferencedElements)));
    action.run();
    refreshViewer();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    super.setEnabled(enabled_p);

    if ((null != _downBtn) && !_downBtn.isDisposed()) {
      _downBtn.setEnabled(enabled_p);
    }
    if ((null != _upBtn) && !_upBtn.isDisposed()) {
      _upBtn.setEnabled(enabled_p);
    }
    if ((null != _addBtn) && !_addBtn.isDisposed()) {
      _addBtn.setEnabled(enabled_p);
    }
  }

  /**
   * Get available elements to add.<br>
   * Default implementation runs a query according to internal semantic element and semantic feature.<br>
   * Available elements are retrieved within a read only command.
   * @return a not <code>null</code>list.
   */
  protected List<? extends EObject> getAvailableElementsToAdd() {
    final List<ModelElement> availableElements = new ArrayList<ModelElement>(0);
    AbstractReadOnlyCommand command = new AbstractReadOnlyCommand() {
      /**
       * {@inheritDoc}
       */
      public void run() {
        IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(_semanticElement.eClass(), _semanticFeature);
        if (null != query) {
          availableElements.addAll(query.getAvailableElements(_semanticElement));
        }
      }
    };
    MDEAdapterFactory.getExecutionManager().execute(command);
    // Remove already referenced elements.
    availableElements.removeAll(getReferencedElementsByContainedOnes());
    return availableElements;
  }

  /**
   * Get the selection element dialog.<br>
   * Default implementation returns a {@link SelectElementsDialog} that only allows the end-user to select elements.
   * @param parentShell_p
   * @param editingDomain_p
   * @param adapterFactory_p
   * @param dialogTitle_p
   * @param dialogMessage_p
   * @param displayedElements_p
   * @return
   */
  protected SelectElementsDialog getSelectionElementDialog(Shell parentShell_p, TransactionalEditingDomain editingDomain_p, AdapterFactory adapterFactory_p, String dialogTitle_p, String dialogMessage_p,
      List<? extends EObject> displayedElements_p) {
    return new SelectElementsDialog(parentShell_p, editingDomain_p, adapterFactory_p, dialogTitle_p, dialogMessage_p, displayedElements_p, true, null);
  }
}
