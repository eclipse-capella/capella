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
package org.polarsys.capella.core.data.information.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.data.information.properties.controllers.ExchangeItemRealizationsController;
import org.polarsys.capella.core.data.information.properties.fields.ExchangeMechanismGroup;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ContainmentTableField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 * The ExchangeItem section.
 */
public class ExchangeItemSection extends NamedElementSection {

  private ExchangeMechanismGroup _exchangeMechanismGroup;
  private ContainmentTableField _containmentTableField;
  private MultipleSemanticField _realizedExchangeItemsField;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _exchangeMechanismGroup = new ExchangeMechanismGroup(_rootParentComposite, getWidgetFactory());
    _exchangeMechanismGroup.setDisplayedInWizard(displayedInWizard);

    Group exchangeItemGroup = getWidgetFactory().createGroup(_rootParentComposite, ""); //$NON-NLS-1$
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
            ((List<EObject>) _semanticElement.eGet(_semanticFeature)).add(item);
            Command cmd = CreationHelper.getAdditionnalCommand(MDEAdapterFactory.getEditingDomain(), item);
            MDEAdapterFactory.getEditingDomain().getCommandStack().execute(cmd);

            CapellaUIPropertiesPlugin.getDefault().openWizard(item);
          }
        };
        MDEAdapterFactory.getExecutionManager().execute(command);
        refreshViewer();
      }
    };
    _containmentTableField.setDisplayedInWizard(displayedInWizard);

    Group main = getWidgetFactory().createGroup(_rootParentComposite, ""); //$NON-NLS-1$
    main.setLayout(new GridLayout(5, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    main.setLayoutData(gd);

    _realizedExchangeItemsField = new MultipleSemanticField(main, Messages.getString("ExchangeItemSection_RealizedExchangeItems_Label"), getWidgetFactory(), new ExchangeItemRealizationsController()); //$NON-NLS-1$
    _realizedExchangeItemsField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    _exchangeMechanismGroup.loadData(capellaElement_p, InformationPackage.Literals.EXCHANGE_ITEM__EXCHANGE_MECHANISM);
    _containmentTableField.loadData(capellaElement_p, InformationPackage.Literals.EXCHANGE_ITEM__OWNED_ELEMENTS);
    _realizedExchangeItemsField.loadData(capellaElement_p, InformationPackage.Literals.EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS);
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
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_exchangeMechanismGroup);
    fields.add(_realizedExchangeItemsField);
    fields.add(_containmentTableField);

    return fields;
  }
}
