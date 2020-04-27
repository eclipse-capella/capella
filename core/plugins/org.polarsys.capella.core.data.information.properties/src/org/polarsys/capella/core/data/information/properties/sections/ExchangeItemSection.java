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
package org.polarsys.capella.core.data.information.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.core.data.core.properties.sections.GeneralizableElementSection;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.data.information.properties.controllers.ExchangeItemRealizationsController;
import org.polarsys.capella.core.data.information.properties.fields.ExchangeItemBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.information.properties.fields.ExchangeMechanismGroup;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ContainmentTableField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The ExchangeItem section.
 */
public class ExchangeItemSection extends GeneralizableElementSection {

  private ExchangeItemBooleanPropertiesCheckbox _exchangeItemBooleanPropertiesCheckbox;	
  private ExchangeMechanismGroup _exchangeMechanismGroup;
  private ContainmentTableField _containmentTableField;
  private MultipleSemanticField _realizedExchangeItemsField;

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _exchangeItemBooleanPropertiesCheckbox = new ExchangeItemBooleanPropertiesCheckbox(getCheckGroup(), getWidgetFactory());
    _exchangeItemBooleanPropertiesCheckbox.setDisplayedInWizard(displayedInWizard);    
    
    _exchangeMechanismGroup = new ExchangeMechanismGroup(parent, getWidgetFactory());
    _exchangeMechanismGroup.setDisplayedInWizard(displayedInWizard);

    Group exchangeItemGroup = getWidgetFactory().createGroup(parent, ""); //$NON-NLS-1$
    exchangeItemGroup.setLayout(new GridLayout(1, false));
    GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
    layoutData.horizontalSpan = 2;
    exchangeItemGroup.setLayoutData(layoutData);

    _containmentTableField = new ContainmentTableField(
      exchangeItemGroup,
      getWidgetFactory(),
      null, null,
      InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT,
      Messages.getString("ExchangeItemSection_Table_Title"), //$NON-NLS-1$
      Messages.getString("ExchangeItemSection_SelectionDialog_Message")) //$NON-NLS-1$
    {
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.AbstractStructuredRepresentationField.properties.fields.ReferencesTableField#handleAdd()
       */
      @SuppressWarnings("unchecked")
      @Override
      protected void handleAdd() {
        AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
          @SuppressWarnings("synthetic-access")
          public void run() {
            ExchangeItemElement item = InformationFactory.eINSTANCE.createExchangeItemElement();
            ((List<EObject>) semanticElement.eGet(semanticFeature)).add(item);
            EditingDomain domain = TransactionHelper.getEditingDomain(item);
            Command cmd = CreationHelper.getAdditionnalCommand(domain, item);
            domain.getCommandStack().execute(cmd);

            CapellaUIPropertiesPlugin.getDefault().openWizard(item);
          }
        };
        TransactionHelper.getExecutionManager(semanticElement).execute(command);
        refreshViewer();
      }
    };
    _containmentTableField.setDisplayedInWizard(displayedInWizard);

    Group main = getWidgetFactory().createGroup(parent, ""); //$NON-NLS-1$
    main.setLayout(new GridLayout(6, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    main.setLayoutData(gd);

    _realizedExchangeItemsField = new MultipleSemanticField(main, Messages.getString("ExchangeItemSection_RealizedExchangeItems_Label"), getWidgetFactory(), new ExchangeItemRealizationsController()); //$NON-NLS-1$
    _realizedExchangeItemsField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _exchangeItemBooleanPropertiesCheckbox.loadData(capellaElement);
    _exchangeMechanismGroup.loadData(capellaElement, InformationPackage.Literals.EXCHANGE_ITEM__EXCHANGE_MECHANISM);
    _containmentTableField.loadData(capellaElement, InformationPackage.Literals.EXCHANGE_ITEM__OWNED_ELEMENTS);
    _realizedExchangeItemsField.loadData(capellaElement, InformationPackage.Literals.EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS);
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == InformationPackage.eINSTANCE.getExchangeItem()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();

    fields.addAll(super.getSemanticFields());
    fields.add(_exchangeItemBooleanPropertiesCheckbox);
    fields.add(_exchangeMechanismGroup);
    fields.add(_realizedExchangeItemsField);
    fields.add(_containmentTableField);

    return fields;
  }
}
