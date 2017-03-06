/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.communication.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.core.data.core.properties.sections.CapellaElementSection;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.communication.properties.Messages;
import org.polarsys.capella.core.data.information.communication.properties.fields.CommunicationLinkKindGroup;
import org.polarsys.capella.core.data.information.communication.properties.fields.CommunicationLinkProtocolGroup;
import org.polarsys.capella.core.ui.properties.controllers.SimpleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;

/**
 * The CommunicationLink section.
 */
public class CommunicationLinkSection extends CapellaElementSection {

  private SimpleSemanticField _exchangeItemField;
  protected CommunicationLinkKindGroup _communicationLinkKindGroup;
  protected CommunicationLinkProtocolGroup _communicationLinkProtocolGroup;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    Group main = getWidgetFactory().createGroup(rootParentComposite, ""); //$NON-NLS-1$
    main.setLayout(new GridLayout(6, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    main.setLayoutData(gd);

    boolean displayedInWizard = isDisplayedInWizard();

    _exchangeItemField = new SimpleSemanticField(main, Messages.getString("CommunicationLinkExchangeItem.Label"), getWidgetFactory(), new SimpleSemanticFieldController()) { //$NON-NLS-1$
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.SimpleSemanticField#handleOpenButtonClicked(org.eclipse.swt.widgets.Button)
       */
      @Override
      protected void handleOpenButtonClicked(Button button) {
        super.handleOpenButtonClicked(button);
        AbstractExchangeItem exchangeItem = (AbstractExchangeItem) _semanticElement.eGet(CommunicationPackage.Literals.COMMUNICATION_LINK__EXCHANGE_ITEM);
        _communicationLinkKindGroup.synchronizeKindsStatus((exchangeItem instanceof ExchangeItem) ? ((ExchangeItem) exchangeItem).getExchangeMechanism() : null);
        _communicationLinkProtocolGroup.synchronizeProtocolsStatus(((CommunicationLink) _semanticElement).getKind());
      }
    };
    _exchangeItemField.setDisplayedInWizard(displayedInWizard);

    _communicationLinkKindGroup = new CommunicationLinkKindGroup(rootParentComposite, getWidgetFactory()) {
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticKindGroup#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event) {
        super.widgetSelected(event);
        Object src = ((Button) event.getSource()).getData();
        if (src instanceof CommunicationLinkKind) {
          _communicationLinkProtocolGroup.synchronizeProtocolsStatus((CommunicationLinkKind) src);
        }
      }
    };
    _communicationLinkKindGroup.setDisplayedInWizard(displayedInWizard);

    _communicationLinkProtocolGroup = new CommunicationLinkProtocolGroup(rootParentComposite, getWidgetFactory());
    _communicationLinkProtocolGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);
    CommunicationLink link = (CommunicationLink) capellaElement;

    _exchangeItemField.loadData(link, CommunicationPackage.eINSTANCE.getCommunicationLink_ExchangeItem());
    _communicationLinkKindGroup.loadData(link, CommunicationPackage.eINSTANCE.getCommunicationLink_Kind());
    AbstractExchangeItem exchangeItem = link.getExchangeItem();
    _communicationLinkKindGroup.synchronizeKindsStatus((exchangeItem instanceof ExchangeItem) ? ((ExchangeItem) exchangeItem).getExchangeMechanism() : null);
    _communicationLinkProtocolGroup.loadData(link, CommunicationPackage.eINSTANCE.getCommunicationLink_Protocol());
    _communicationLinkProtocolGroup.synchronizeProtocolsStatus(link.getKind());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == CommunicationPackage.eINSTANCE.getCommunicationLink()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_exchangeItemField);
    fields.add(_communicationLinkKindGroup);
    fields.add(_communicationLinkProtocolGroup);

    return fields;
  }
}
