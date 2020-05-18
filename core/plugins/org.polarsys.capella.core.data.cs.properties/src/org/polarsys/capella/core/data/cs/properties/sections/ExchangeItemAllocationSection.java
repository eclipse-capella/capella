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
package org.polarsys.capella.core.data.cs.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.properties.fields.CommunicationLinkProtocolGroup;
import org.polarsys.capella.core.ui.properties.controllers.SimpleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;

/**
 * The ExchangeItemAllocation section.
 */
public class ExchangeItemAllocationSection extends NamedElementSection {

  private SimpleSemanticField _exchangeItemField;
  protected CommunicationLinkProtocolGroup _transmissionProtocolGroup;
  protected CommunicationLinkProtocolGroup _acquisitionProtocolGroup;

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    Group main = getWidgetFactory().createGroup(parent, ""); //$NON-NLS-1$
    main.setLayout(new GridLayout(6, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    main.setLayoutData(gd);

    boolean displayedInWizard = isDisplayedInWizard();

    _exchangeItemField = new SimpleSemanticField(main, Messages.ExchangeItemAllocationSection_AllocatedItem_Label, getWidgetFactory(), new SimpleSemanticFieldController()) {
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.SimpleSemanticField#handleOpenButtonClicked(org.eclipse.swt.widgets.Button)
       */
      @Override
      protected void handleOpenButtonClicked(Button button) {
        super.handleOpenButtonClicked(button);

        AbstractExchangeItem exchangeItem = (AbstractExchangeItem) semanticElement.eGet(CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM);
        _transmissionProtocolGroup.synchronizeProtocolsStatus((exchangeItem instanceof ExchangeItem) ? ((ExchangeItem) exchangeItem).getExchangeMechanism() : null);
        _acquisitionProtocolGroup.synchronizeProtocolsStatus((exchangeItem instanceof ExchangeItem) ? ((ExchangeItem) exchangeItem).getExchangeMechanism() : null);
      }
    };
    _exchangeItemField.setDisplayedInWizard(displayedInWizard);

    _transmissionProtocolGroup = new CommunicationLinkProtocolGroup(parent, Messages.ExchangeItemAllocationSection_TransmissionProtocol_Label, getWidgetFactory(), true, true, false);
    _transmissionProtocolGroup.setDisplayedInWizard(displayedInWizard);

    _acquisitionProtocolGroup = new CommunicationLinkProtocolGroup(parent, Messages.ExchangeItemAllocationSection_AcquisitionProtocol_Label, getWidgetFactory(), false, false, true);
    _acquisitionProtocolGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);
    ExchangeItemAllocation allocation = (ExchangeItemAllocation) capellaElement;

    _exchangeItemField.loadData(allocation, CsPackage.eINSTANCE.getExchangeItemAllocation_AllocatedItem());

    AbstractExchangeItem exchangeItem = allocation.getAllocatedItem();
    _transmissionProtocolGroup.loadData(allocation, CsPackage.eINSTANCE.getExchangeItemAllocation_SendProtocol());
    _transmissionProtocolGroup.synchronizeProtocolsStatus((exchangeItem instanceof ExchangeItem) ? ((ExchangeItem) exchangeItem).getExchangeMechanism() : null);
    _acquisitionProtocolGroup.loadData(allocation, CsPackage.eINSTANCE.getExchangeItemAllocation_ReceiveProtocol());
    _acquisitionProtocolGroup.synchronizeProtocolsStatus((exchangeItem instanceof ExchangeItem) ? ((ExchangeItem) exchangeItem).getExchangeMechanism() : null);
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == CsPackage.eINSTANCE.getExchangeItemAllocation()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();

    fields.addAll(super.getSemanticFields());
    fields.add(_acquisitionProtocolGroup);
    fields.add(_exchangeItemField);
    fields.add(_transmissionProtocolGroup);

    return fields;
  }
}
