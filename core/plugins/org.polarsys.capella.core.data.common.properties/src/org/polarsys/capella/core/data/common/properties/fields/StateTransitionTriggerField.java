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
package org.polarsys.capella.core.data.common.properties.fields;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.ChangeEvent;
import org.polarsys.capella.core.data.capellacommon.StateEvent;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacommon.TimeEvent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.common.properties.CommonPropertiesPlugin;
import org.polarsys.capella.core.data.common.properties.IImageKeys;
import org.polarsys.capella.core.linkedtext.ui.CapellaEmbeddedLinkedTextEditorInput;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.controllers.IMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.ContainmentTableField;
import org.polarsys.capella.core.ui.properties.fields.EditableSemanticFieldException;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;
import org.polarsys.capella.core.ui.properties.helpers.NamingHelper;
import org.polarsys.capella.core.ui.properties.viewers.TableDelegatedViewer;
import org.polarsys.capella.core.ui.toolkit.ToolkitPlugin;
import org.polarsys.capella.core.ui.toolkit.viewers.CapellaElementLabelProvider;

/**
 *
 */
public class StateTransitionTriggerField extends ContainmentTableField {

  private Button _timeEventBtn;
  private Button _changeEventBtn;
  private Button _openBtn;

  private IMultipleSemanticFieldController _controller;

  /**
   * @param parent_p
   * @param widgetFactory_p
   * @param referencerFeature_p
   * @param referencedFeature_p
   * @param referencedFeatureType_p
   * @param label_p
   * @param selectionElementDialogMessage_p
   */
  public StateTransitionTriggerField(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, EReference referencerFeature_p,
      EReference referencedFeature_p, EClass referencedFeatureType_p, String label_p, String selectionElementDialogMessage_p) {
    super(parent_p, widgetFactory_p, referencerFeature_p, referencedFeature_p, referencedFeatureType_p, label_p, selectionElementDialogMessage_p,
          new CustomDelegatedViewer(widgetFactory_p));

    _controller = new AbstractMultipleSemanticFieldController() {
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement_p) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement_p.eClass(), CapellacommonPackage.eINSTANCE.getStateTransition_Triggers());
      }
    };
  }

  protected static String getCustomText(Object object_p, int columnIndex_p) {
    if (object_p instanceof ChangeEvent) {
      ChangeEvent changeEvent = (ChangeEvent) object_p;
      String res = "[" + changeEvent.getKind() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
      if (changeEvent.getCondition() != null) {
        res += " " + getConstraintLabel(changeEvent.getCondition()); //$NON-NLS-1$
      } else {
        res += " " + changeEvent.getName();
      }
      return res;
    }
    if (object_p instanceof TimeEvent) {
      TimeEvent timeEvent = (TimeEvent) object_p;
      String res = "[" + timeEvent.getKind() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
      if (timeEvent.getCondition() != null) {
        res += " " + getConstraintLabel(timeEvent.getCondition()); //$NON-NLS-1$
      } else {
        res += " " + timeEvent.getName();
      }
      return res;
    }

    return null;
  }

  /**
   * Create the actions.
   */
  @Override
  @SuppressWarnings("synthetic-access")
  protected void createCustomActions(Composite parent_p) {

    _timeEventBtn = createTableButton(parent_p, CommonPropertiesPlugin.getDefault().getImage(IImageKeys.TIME_EVENT_IMG_16), new Runnable() {
      @Override
      public void run() {
        handleStateEventButtonClick(CapellacommonPackage.Literals.TIME_EVENT);
      }
    });
    _timeEventBtn.setToolTipText("Create a Time Event");

    _changeEventBtn = createTableButton(parent_p, CommonPropertiesPlugin.getDefault().getImage(IImageKeys.CHANGE_EVENT_IMG_16), new Runnable() {
      @Override
      public void run() {
        handleStateEventButtonClick(CapellacommonPackage.Literals.CHANGE_EVENT);
      }
    });
    _changeEventBtn.setToolTipText("Create a Change Event");

    ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
    Image openImage = imgRegistry.get(ToolkitPlugin.BROWSE_IMAGE_ITEM_ID);
    _openBtn = createTableButton(parent_p, openImage, new Runnable() {
      @Override
      public void run() {
        handleOpenButtonClick(_openBtn);
      }
    });
    _openBtn.setToolTipText("Browse available elements");
  }

  /**
   * @param eventClass
   */
  @SuppressWarnings("synthetic-access")
  protected void handleStateEventButtonClick(final EClass eventClass) throws EditableSemanticFieldException {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      @SuppressWarnings("unchecked")
      @Override
      public void run() {
        StateEvent stateEvent = (StateEvent) CapellacommonFactory.eINSTANCE.create(eventClass);
        BlockArchitectureExt.getDataPkg(BlockArchitectureExt.getRootBlockArchitecture(_semanticElement)).getOwnedStateEvents().add(stateEvent);
        org.polarsys.capella.core.model.helpers.CapellaElementExt.creationService(stateEvent);

        if (CapellaUIPropertiesPlugin.getDefault().openWizard(stateEvent)) {
          ((List<EObject>) _semanticElement.eGet(_semanticFeature)).add(stateEvent);
        } else {
          stateEvent.destroy();
        }
      }
    };
    TransactionHelper.getExecutionManager(_semanticElement).execute(command);
    refreshViewer();
  }

  /**
   * @param openBtn_p
   */
  @SuppressWarnings("synthetic-access")
  protected void handleOpenButtonClick(final Button button_p) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        List<EObject> currentElements = _controller.readOpenValues(_semanticElement, _semanticFeature, false);
        List<EObject> availableElements = _controller.readOpenValues(_semanticElement, _semanticFeature, true);
        availableElements.removeAll(currentElements);

        String title = NamingHelper.getDefaultTitle(_semanticElement);
        String message = NamingHelper.getDefaultMessage(_semanticElement, (_semanticFeature != null) ? _semanticFeature.getName() : ""); //$NON-NLS-1$

        // calling selection wizard
        List<EObject> allResults = DialogHelper.openTransferDialog(button_p, currentElements, availableElements, title, message);
        if (null != allResults) {
          _controller.writeOpenValues(_semanticElement, _semanticFeature, allResults);
        }
      }
    };
    executeCommand(command);
    refreshViewer();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleDelete() {

    if (null != _delegatedViewer) {
      ColumnViewer columnViewer = _delegatedViewer.getColumnViewer();
      if (null != columnViewer) {
        final List<?> selectedReferencedElements = ((IStructuredSelection) columnViewer.getSelection()).toList();

        AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
          @Override
          @SuppressWarnings("synthetic-access")
          public void run() {

            for (Object object : selectedReferencedElements) {
              if (_semanticElement instanceof StateTransition) {
                StateTransition transition = (StateTransition) _semanticElement;
                transition.getTriggers().remove(object);
              }
            }
          }
        };
        executeCommand(command);
        refreshViewer();
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    super.setEnabled(enabled_p);

    if ((null != _timeEventBtn) && !_timeEventBtn.isDisposed()) {
      _timeEventBtn.setEnabled(enabled_p);
    }

    if ((null != _changeEventBtn) && !_changeEventBtn.isDisposed()) {
      _changeEventBtn.setEnabled(enabled_p);
    }
  }

  static class CustomDelegatedViewer extends TableDelegatedViewer {
    /**
     * @param widgetFactory_p
     */
    CustomDelegatedViewer(TabbedPropertySheetWidgetFactory widgetFactory_p) {
      super(widgetFactory_p);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createContainer(Composite sub_parent_p) {
      super.createContainer(sub_parent_p);
      _columnViewer.setLabelProvider(new CapellaElementLabelProvider() {
        /**
         * {@inheritDoc}
         */
        @Override
        public final String getColumnText(final Object object_p, final int columnIndex_p) {
          final String customText = getCustomText(object_p, columnIndex_p);

          if (customText == null) {
            return super.getColumnText(object_p, columnIndex_p);
          }

          return run(object_p, new RunnableWithResult.Impl<String>() {
            @Override
            public void run() {
              setResult(customText);
            }
          });
        }
      });
    }
  }

  private static String getConstraintLabel(Constraint constraint_p) {
    return CapellaEmbeddedLinkedTextEditorInput.getDefaultText(constraint_p, constraint_p.getName());
  }
}
