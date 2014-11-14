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
package org.polarsys.capella.core.data.interaction.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.properties.Messages;
import org.polarsys.capella.core.data.interaction.properties.controllers.DataFlowController;
import org.polarsys.capella.core.data.interaction.properties.controllers.DataFlowHelper;
import org.polarsys.capella.core.data.interaction.properties.controllers.ExchangedItemsController;
import org.polarsys.capella.core.data.interaction.properties.controllers.InterfaceController;
import org.polarsys.capella.core.data.interaction.properties.controllers.InterfaceHelper;
import org.polarsys.capella.core.data.interaction.properties.controllers.SequenceMessageController;
import org.polarsys.capella.core.data.interaction.properties.dialogs.DialogProvider;
import org.polarsys.capella.core.data.interaction.properties.fields.MessageKindGroup;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.EditableSemanticFieldException;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;
import org.polarsys.capella.core.ui.properties.fields.TextValueGroup;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 * The SequenceMessage section.
 */
public class SequenceMessageSection extends NamedElementSection {

  private SimpleEditableSemanticField invokedOperationField;
  private MultipleSemanticField exchangedItemsField;
  private MessageKindGroup messageKindGroup;
  protected TextValueGroup exchangeContextField;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    namedElementGroup.enableNameField(false);

    Group main = getWidgetFactory().createGroup(_rootParentComposite, ""); //$NON-NLS-1$
    main.setLayout(new GridLayout(5, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    main.setLayoutData(gd);

    boolean displayedInWizard = isDisplayedInWizard();

    invokedOperationField = new SimpleEditableSemanticField(main, Messages.getString("SequenceMessage.InvokedOperationLabel"), getWidgetFactory(), "", null) { //$NON-NLS-1$ //$NON-NLS-2$
          // This service is customized because the given semantic feature
          // is not the real feature to be reset.
          @Override
          protected void handleDeleteButtonClicked() {
            AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
              @SuppressWarnings("synthetic-access")
              @Override
              public void run() {
                SequenceMessageController.resetValue(_semanticElement);
                setValueTextField(_semanticElement);
                refresh();
              }
            };
            executeCommand(command);
          }

          // a refresh is needed in order to update the name text field
          @Override
          protected void handleEditButtonClicked() throws EditableSemanticFieldException {
            super.handleEditButtonClicked();
            refresh();
          }

          // a refresh is needed in order to update the name text field
          @SuppressWarnings("synthetic-access")
          @Override
          protected void handleOpenButtonClicked(final Button button_p) {
            AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
              @Override
              public void run() {
                SequenceMessage message = (SequenceMessage) _semanticElement;
                Scenario s = (Scenario) message.eContainer();
                if (_controller instanceof InterfaceController) {
                  EObject affectedElement = DialogProvider.openOperationDialog((SequenceMessage) _semanticElement);
                  if (affectedElement != null) {
                    InterfaceHelper.affectExchangeItem(message, (ExchangeItemAllocation) affectedElement);
                    ComponentExt.ensureUseAndImplementsForOperation(message, (ExchangeItemAllocation) affectedElement, false, (EObject) null);
                  }

                } else if (_controller instanceof DataFlowController) {
                  // INTERACTION, DATA_FLOW or FUNCTIONAL scenario.
                  EObject affectedElement = null;
                  if (ScenarioExt.isFunctionalScenario(s)) {
                    // INTERACTION scenario with Activities only or FUNCTIONAL scenario.
                    affectedElement = DialogProvider.openFunctionalExchangeDialog(message, DialogProvider.FUNCTIONAL_EXCHANGE_FUNCTION);
                  } else {
                    // INTERACTION scenario with Entities or DATA_FLOW scenario.
                    boolean isFunctionalScenario = ScenarioExt.isDataFlowFunctionalScenario(s);
                    if (isFunctionalScenario) {
                      // Scenario contains at least one FunctionalExchange -> Show FunctionalExchanges selection dialog.
                      affectedElement = DialogProvider.openFunctionalExchangeDialog(message, DialogProvider.FUNCTIONAL_EXCHANGE_TYPE);
                    } else {
                      // No FunctionalExchange -> Show ComponentExchanges selection dialog.
                      affectedElement = DialogProvider.openFunctionalExchangeDialog(message, DialogProvider.COMPONENT_EXCHANGE_TYPE);
                    }
                  }

                  if (affectedElement != null) {
                    DataFlowHelper.affectDataflowToMessage(message, (AbstractEventOperation) affectedElement);
                  }
                }
              }

              @Override
              public String getName() {
                return "Edit " + _semanticElement.eGet(ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name()); //$NON-NLS-1$
              }
            };
            executeCommand(command);
            refresh();
          }
        };
    invokedOperationField.setDisplayedInWizard(displayedInWizard);

    exchangedItemsField =
        new MultipleSemanticField(main, Messages.getString("SequenceMessage.ExchangedItemsLabel"), getWidgetFactory(), new ExchangedItemsController()); //$NON-NLS-1$
    exchangedItemsField.setDisplayedInWizard(displayedInWizard);

    messageKindGroup = new MessageKindGroup(_rootParentComposite, getWidgetFactory(), false);
    messageKindGroup.setDisplayedInWizard(displayedInWizard);

    exchangeContextField = new TextValueGroup(_rootParentComposite, Messages.getString("SequenceMessage.ExchangeContextLabel"), getWidgetFactory()); //$NON-NLS-1$
    exchangeContextField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    Scenario scenario = (Scenario) ((SequenceMessage) capellaElement_p).eContainer();
    if (null != scenario) {
      if (scenario.getKind() == ScenarioKind.INTERFACE) {
        invokedOperationField.setController(new InterfaceController());
        invokedOperationField.enableEditButton(true);
        invokedOperationField.setLabel(Messages.getString("SequenceMessage.InvokedOperationLabel")); //$NON-NLS-1$
        exchangedItemsField.setEnabled(false);

        exchangedItemsField.setVisible(false);
      } else if (scenario.getKind() == ScenarioKind.DATA_FLOW) {
        invokedOperationField.setController(new DataFlowController());
        invokedOperationField.enableEditButton(true);
        invokedOperationField.setLabel(Messages.getString("SequenceMessage.InvokedExchangeLabel")); //$NON-NLS-1$
        exchangedItemsField.setEnabled(true);

        exchangedItemsField.setVisible(true);
      } else if (scenario.getKind() == ScenarioKind.INTERACTION) {
        invokedOperationField.setController(new DataFlowController());
        invokedOperationField.enableEditButton(true);
        invokedOperationField.setLabel(Messages.getString("SequenceMessage.InvokedInteractionLabel")); //$NON-NLS-1$
        exchangedItemsField.setEnabled(true);

        exchangedItemsField.setVisible(true);
      } else if (scenario.getKind() == ScenarioKind.FUNCTIONAL) {
        invokedOperationField.setController(new DataFlowController());
        invokedOperationField.enableEditButton(true);
        invokedOperationField.setLabel(Messages.getString("SequenceMessage.InvokedExchangeLabel")); //$NON-NLS-1$
        exchangedItemsField.setEnabled(true);

        exchangedItemsField.setVisible(true);
      }
    }

    messageKindGroup.loadData(capellaElement_p, InteractionPackage.eINSTANCE.getSequenceMessage_Kind());
    invokedOperationField.loadData(capellaElement_p, InteractionPackage.eINSTANCE.getEventReceiptOperation_Operation());
    exchangedItemsField.loadData(capellaElement_p, InteractionPackage.eINSTANCE.getSequenceMessage_ExchangedItems());
    exchangeContextField.loadData(capellaElement_p, InteractionPackage.eINSTANCE.getSequenceMessage_ExchangeContext());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == InteractionPackage.eINSTANCE.getSequenceMessage()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(exchangedItemsField);
    fields.add(invokedOperationField);
    fields.add(messageKindGroup);
    fields.add(exchangeContextField);

    return fields;
  }
}
