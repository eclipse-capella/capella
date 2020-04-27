/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.ui.properties.fields;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.command.AbstractReadOnlyCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.IImageKeys;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;
import org.polarsys.capella.core.ui.properties.viewers.IDelegatedViewer;
import org.polarsys.capella.core.ui.properties.viewers.TableDelegatedViewer;
import org.polarsys.capella.core.ui.toolkit.actions.move.MoveDownAction;
import org.polarsys.capella.core.ui.toolkit.actions.move.MoveUpAction;

public class ContainmentTableField extends AbstractStructuredRepresentationField {

  protected EReference _referencerFeature;
  protected EClass _referencedFeatureType;
  protected String _selectionElementDialogMessage;

  private Button _addBtn;
  private Button _upBtn;
  private Button _downBtn;

  /**
   * Constructor
   * @param parent The parent composite
   * @param widgetFactory
   * @param referencerFeature
   * @param referencedFeature
   * @param referencedFeatureType
   * @param label
   * @param selectionElementDialogMessage
   * @param viewer
   */
  public ContainmentTableField(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, EReference referencerFeature,
      EReference referencedFeature, EClass referencedFeatureType, String label, String selectionElementDialogMessage, IDelegatedViewer viewer) {
    super(parent, widgetFactory, referencedFeature, label, viewer);

    _referencerFeature = referencerFeature;
    _referencedFeatureType = referencedFeatureType;
    _selectionElementDialogMessage = selectionElementDialogMessage;
  }

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param referencerFeature
   * @param referencedFeature a feature that refers to another element from elements contained by the semantic feature.
   * @param referencedFeatureType the concrete type of the referenced feature (Use to create new element).
   * @param label the label displayed at the right top of the table.
   * @param selectionElementDialogMessage The message displayed when opening the dialog to add / create an element.
   */
  public ContainmentTableField(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, EReference referencerFeature,
      EReference referencedFeature, EClass referencedFeatureType, String label, String selectionElementDialogMessage) {

    this(parent, widgetFactory, referencerFeature, referencedFeature, referencedFeatureType, label, selectionElementDialogMessage,
         new TableDelegatedViewer(widgetFactory));

  }

  /**
   * Create the actions.
   */
  @Override
  protected void createCustomActions(Composite parent) {
    _downBtn = createTableButton(parent, CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.IMG_ARROW_DOWN), new Runnable() {
      public void run() {
        handleDown();
      }
    });
    _upBtn = createTableButton(parent, CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.IMG_ARROW_UP), new Runnable() {
      public void run() {
        handleUp();
      }
    });
    _addBtn = createTableButton(parent, CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.IMG_ADD_BUTTON), new Runnable() {
      public void run() {
        handleAdd();
      }
    });
  }

  /**
   * Handle Delete button.
   */
  @Override
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
            CapellaDeleteCommand command =
                new CapellaDeleteCommand(TransactionHelper.getExecutionManager(semanticElement), getContainedElementsfor(selectedReferencedElements), true,
                    false, false);
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
          List<EObject> containedElements = (List<EObject>) semanticElement.eGet(semanticFeature);
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
              createdObjectForSelectedOne.eSet(_referencerFeature, semanticElement);
            }
            // Add it in already contained object.
            containedElements.add(createdObjectForSelectedOne);
          }
        }
      };
      TransactionHelper.getExecutionManager(semanticElement).execute(command);
      refreshViewer();
    }
  }

  /**
   * Handle Down button.
   */
  @SuppressWarnings("unchecked")
  protected void handleDown() {
    List<EObject> selectedReferencedElements = ((IStructuredSelection) _delegatedViewer.getColumnViewer().getSelection()).toList();
    MoveDownAction action = new MoveDownAction();
    action.selectionChanged(new StructuredSelection(getContainedElementsfor(selectedReferencedElements)));
    action.run();
    refreshViewer();
  }

  /**
   * Handle Up button.
   */
  @SuppressWarnings("unchecked")
  protected void handleUp() {
    List<EObject> selectedReferencedElements = ((IStructuredSelection) _delegatedViewer.getColumnViewer().getSelection()).toList();
    MoveUpAction action = new MoveUpAction();
    action.selectionChanged(new StructuredSelection(getContainedElementsfor(selectedReferencedElements)));
    action.run();
    refreshViewer();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);

    if ((null != _downBtn) && !_downBtn.isDisposed()) {
      _downBtn.setEnabled(enabled);
    }
    if ((null != _upBtn) && !_upBtn.isDisposed()) {
      _upBtn.setEnabled(enabled);
    }
    if ((null != _addBtn) && !_addBtn.isDisposed()) {
      _addBtn.setEnabled(enabled);
    }
  }

  /**
   * Get available elements to add.<br>
   * Default implementation runs a query according to internal semantic element and semantic feature.<br>
   * Available elements are retrieved within a read only command.
   * @return a not <code>null</code>list.
   */
  protected List<? extends EObject> getAvailableElementsToAdd() {
    final List<EObject> availableElements = new ArrayList<>(0);
    AbstractReadOnlyCommand command = new AbstractReadOnlyCommand() {
      /**
       * {@inheritDoc}
       */
      public void run() {
        IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), semanticFeature);
        if (null != query) {
          availableElements.addAll(query.getAvailableElements(semanticElement));
        }
      }
    };
    TransactionHelper.getExecutionManager(semanticElement).execute(command);
    // Remove already referenced elements.
    availableElements.removeAll(getReferencedElementsByContainedOnes());
    return availableElements;
  }

  protected SelectElementsDialog getSelectionElementDialog(Shell parentShell,
      String dialogTitle, String dialogMessage, List<? extends EObject> displayedElements) {
    return new SelectElementsDialog(parentShell, dialogTitle, dialogMessage, displayedElements);
  }
}
