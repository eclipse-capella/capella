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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.IImageKeys;
import org.polarsys.capella.core.ui.properties.controllers.IMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;
import org.polarsys.capella.core.ui.properties.viewers.IDelegatedViewer;

/**
 */
public class ReferenceTableField extends AbstractStructuredRepresentationField {

  /**
   * 
   */
  protected Button _browseBtn;
  
  /**
   * 
   */
  protected IMultipleSemanticFieldController _controller;

  /**
   * Constructor.
   *
   * @param parent
   * @param widgetFactory
   * @param referencedFeature
   * @param label
   * @param controller
   * @param viewerType
   */
  public ReferenceTableField(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, EReference referencedFeature, 
      String label, IMultipleSemanticFieldController controller, IDelegatedViewer delegatedViewer)
  {
    super(parent, widgetFactory, referencedFeature, label, delegatedViewer);
    
    _controller = controller;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createCustomActions(Composite parent) {
    _browseBtn = createTableButton(parent, CapellaUIPropertiesPlugin.getDefault().getImage(IImageKeys.IMG_BROWSE_BUTTON), new Runnable() {
      public void run() {
        handleBrowse();
      }
    });
  }

  /**
   * Handle Browse button.
   */
  protected void handleBrowse() {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(CapellacorePackage.Literals.CAPELLA_ELEMENT, semanticFeature);
        if (null != query) {
          List<EObject> currentElements = _controller.readOpenValues(semanticElement, semanticFeature, false);
          List<EObject> availableElements = _controller.readOpenValues(semanticElement, semanticFeature, true);
          availableElements.removeAll(currentElements);

          String title = NamingHelper.getDefaultTitle(semanticElement);
          String message = NamingHelper.getDefaultMessage(semanticElement, (semanticFeature != null) ? semanticFeature.getName() : ""); //$NON-NLS-1$

          // calling selection wizard
          List<EObject> allResults = DialogHelper.openTransferDialog(_browseBtn, currentElements, availableElements, title, message);
          if (null != allResults) {
            _controller.writeOpenValues(semanticElement, semanticFeature, allResults);
          }
        }
      }
    };
    TransactionHelper.getExecutionManager(semanticElement).execute(command);
    refreshViewer();
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
        if (!selectedReferencedElements.isEmpty()) {
          AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
            public void run() {
              if (semanticFeature instanceof EReference && !((EReference) semanticFeature).isContainment()) {
                if (semanticFeature.isMany()) {
                  ((List<EObject>) semanticElement.eGet(semanticFeature)).removeAll(selectedReferencedElements);
                } else {
                  semanticElement.eSet(semanticFeature, null);
                }
              }
            }
          };
          TransactionHelper.getExecutionManager(semanticElement).execute(command);
          refreshViewer();
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);

    if (null != _browseBtn && !_browseBtn.isDisposed()) {
      _browseBtn.setEnabled(enabled);
    }
  }
}
