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
package org.polarsys.capella.core.data.interaction.properties.sections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
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
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ConstraintReferenceGroup;
import org.polarsys.capella.core.ui.properties.fields.EditableSemanticFieldException;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;

/**
 * The SequenceMessage section.
 */
public class SequenceMessageSection extends NamedElementSection {

  private SimpleEditableSemanticField invokedOperationField;
  private MultipleSemanticField exchangedItemsField;
  private MessageKindGroup messageKindGroup;
  private ConstraintReferenceGroup exchangeContextField;

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    namedElementGroup.enableNameField(false);

    Group main = getWidgetFactory().createGroup(parent, ""); //$NON-NLS-1$
    main.setLayout(new GridLayout(6, false));
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
                SequenceMessageController.resetValue(semanticElement);
                setValueTextField(semanticElement);
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
          protected void handleOpenButtonClicked(final Button button) {
            AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
              @Override
              public void run() {
                SequenceMessage message = (SequenceMessage) semanticElement;
                Scenario s = (Scenario) message.eContainer();
                if (_controller instanceof InterfaceController) {
                  EObject affectedElement = DialogProvider.openOperationDialog((SequenceMessage) semanticElement);
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
                return "Edit " + semanticElement.eGet(ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name()); //$NON-NLS-1$
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

    messageKindGroup = new MessageKindGroup(parent, getWidgetFactory(), false);
    messageKindGroup.setDisplayedInWizard(displayedInWizard);

    exchangeContextField =
        new ConstraintReferenceGroup(Collections.singletonMap(
            Messages.getString("SequenceMessage.ExchangeContextLabel"), InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGE_CONTEXT)); //$NON-NLS-1$
    exchangeContextField.createControls(parent, getWidgetFactory(), displayedInWizard);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    Scenario scenario = (Scenario) ((SequenceMessage) capellaElement).eContainer();
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

    messageKindGroup.loadData(capellaElement, InteractionPackage.eINSTANCE.getSequenceMessage_Kind());
    invokedOperationField.loadData(capellaElement, InteractionPackage.eINSTANCE.getEventReceiptOperation_Operation());
    exchangedItemsField.loadData(capellaElement, InteractionPackage.eINSTANCE.getSequenceMessage_ExchangedItems());
    exchangeContextField.loadData(capellaElement);
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
    List<AbstractSemanticField> fields = new ArrayList<>();

    fields.addAll(super.getSemanticFields());
    fields.add(exchangedItemsField);
    fields.add(invokedOperationField);
    fields.add(messageKindGroup);
    fields.addAll(exchangeContextField.getFields());

    return fields;
  }
}
