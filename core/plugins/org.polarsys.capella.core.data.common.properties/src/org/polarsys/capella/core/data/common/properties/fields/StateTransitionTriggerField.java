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
package org.polarsys.capella.core.data.common.properties.fields;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.ChangeEvent;
import org.polarsys.capella.core.data.capellacommon.StateEvent;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacommon.TimeEvent;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.common.properties.IImageKeys;
import org.polarsys.capella.core.linkedtext.ui.CapellaEmbeddedLinkedTextEditorInput;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.controllers.IMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.ContainmentTableField;
import org.polarsys.capella.core.ui.properties.fields.EditableSemanticFieldException;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;
import org.polarsys.capella.core.ui.properties.viewers.TableDelegatedViewer;
import org.polarsys.capella.core.ui.toolkit.ToolkitPlugin;

public class StateTransitionTriggerField extends ContainmentTableField {

  private Button _timeEventBtn;
  private Button _changeEventBtn;
  private Button _openBtn;

  private IMultipleSemanticFieldController _controller;

  /**
   * @param parent
   * @param widgetFactory
   * @param referencerFeature
   * @param referencedFeature
   * @param referencedFeatureType
   * @param label
   * @param selectionElementDialogMessage
   */
  public StateTransitionTriggerField(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, EReference referencerFeature,
      EReference referencedFeature, EClass referencedFeatureType, String label, String selectionElementDialogMessage) {
    super(parent, widgetFactory, referencerFeature, referencedFeature, referencedFeatureType, label, selectionElementDialogMessage,
          new CustomDelegatedViewer(widgetFactory));

    _controller = new AbstractMultipleSemanticFieldController() {
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), CapellacommonPackage.eINSTANCE.getStateTransition_Triggers());
      }
    };
  }

  protected static String getCustomText(Object object, int columnIndex) {
    if (object instanceof ChangeEvent) {
      ChangeEvent changeEvent = (ChangeEvent) object;
      String res = "[" + changeEvent.getKind() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
      if (changeEvent.getExpression() != null) {
        res += " " + getConstraintLabel(changeEvent.getExpression()); //$NON-NLS-1$
      } else {
        res += " " + changeEvent.getName();
      }
      return res;
    }
    if (object instanceof TimeEvent) {
      TimeEvent timeEvent = (TimeEvent) object;
      String res = "[" + timeEvent.getKind() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
      if (timeEvent.getExpression() != null) {
        res += " " + getConstraintLabel(timeEvent.getExpression()); //$NON-NLS-1$
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
  protected void createCustomActions(Composite parent) {
      final String ICONS_PATH = "icons/"; //$NON-NLS-1$
      _timeEventBtn = createTableButton(parent, AbstractUIPlugin.imageDescriptorFromPlugin(FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), ICONS_PATH + IImageKeys.TIME_EVENT_IMG_16).createImage(),
              new Runnable() {
                  @Override
                  public void run() {
                      handleStateEventButtonClick(CapellacommonPackage.Literals.TIME_EVENT);
                  }
    });
    _timeEventBtn.setToolTipText("Create a Time Event");

    _changeEventBtn = createTableButton(parent, AbstractUIPlugin.imageDescriptorFromPlugin(FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), ICONS_PATH + IImageKeys.CHANGE_EVENT_IMG_16).createImage(),
            new Runnable() {
                @Override
                public void run() {
                    handleStateEventButtonClick(CapellacommonPackage.Literals.CHANGE_EVENT);
                }
    });
    _changeEventBtn.setToolTipText("Create a Change Event");

    ImageRegistry imgRegistry = ToolkitPlugin.getDefault().getImageRegistry();
    Image openImage = imgRegistry.get(ToolkitPlugin.BROWSE_IMAGE_ITEM_ID);
    _openBtn = createTableButton(parent, openImage, new Runnable() {
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
        BlockArchitectureExt.getDataPkg(BlockArchitectureExt.getRootBlockArchitecture(semanticElement)).getOwnedStateEvents().add(stateEvent);
        org.polarsys.capella.core.model.helpers.CapellaElementExt.creationService(stateEvent);

        if (CapellaUIPropertiesPlugin.getDefault().openWizard(stateEvent)) {
          ((List<EObject>) semanticElement.eGet(semanticFeature)).add(stateEvent);
        } else {
          stateEvent.destroy();
        }
      }
    };
    TransactionHelper.getExecutionManager(semanticElement).execute(command);
    refreshViewer();
  }

  /**
   * @param openBtn
   */
  @SuppressWarnings("synthetic-access")
  protected void handleOpenButtonClick(final Button button) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        List<EObject> currentElements = _controller.readOpenValues(semanticElement, semanticFeature, false);
        List<EObject> availableElements = _controller.readOpenValues(semanticElement, semanticFeature, true);
        availableElements.removeAll(currentElements);

        String title = NamingHelper.getDefaultTitle(semanticElement);
        String message = NamingHelper.getDefaultMessage(semanticElement, (semanticFeature != null) ? semanticFeature.getName() : ""); //$NON-NLS-1$

        // calling selection wizard
        List<EObject> allResults = DialogHelper.openTransferDialog(button, currentElements, availableElements, title, message);
        if (null != allResults) {
          _controller.writeOpenValues(semanticElement, semanticFeature, allResults);
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
              if (semanticElement instanceof StateTransition) {
                StateTransition transition = (StateTransition) semanticElement;
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
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);

    if ((null != _timeEventBtn) && !_timeEventBtn.isDisposed()) {
      _timeEventBtn.setEnabled(enabled);
    }

    if ((null != _changeEventBtn) && !_changeEventBtn.isDisposed()) {
      _changeEventBtn.setEnabled(enabled);
    }
  }

  static class CustomDelegatedViewer extends TableDelegatedViewer {
    /**
     * @param widgetFactory
     */
    CustomDelegatedViewer(TabbedPropertySheetWidgetFactory widgetFactory) {
      super(widgetFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createContainer(Composite subarent) {
      super.createContainer(subarent);
      _columnViewer.setLabelProvider(new MDEAdapterFactoryLabelProvider() {
        @Override
        public final String getColumnText(final Object object, final int columnIndex) {
          final String customText = getCustomText(object, columnIndex);

          if (customText == null) {
            return super.getColumnText(object, columnIndex);
          }
          
          return customText;
        }
      });
    }
  }

  private static String getConstraintLabel(Constraint constraint) {
    return CapellaEmbeddedLinkedTextEditorInput.getDefaultText(constraint, constraint.getName());
  }
}
