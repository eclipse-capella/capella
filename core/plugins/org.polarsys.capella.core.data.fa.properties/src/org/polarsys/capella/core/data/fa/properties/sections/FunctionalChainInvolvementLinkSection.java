/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.properties.sections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.fa.properties.sections.Messages;
import org.polarsys.capella.core.data.core.properties.sections.CapellaElementSection;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.controllers.SimpleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ConstraintReferenceGroup;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;

/**
 * The FunctionalChainInvolvementLink section.
 */
public class FunctionalChainInvolvementLinkSection extends CapellaElementSection {

  protected ConstraintReferenceGroup exchangeContext;
  private MultipleSemanticField exchangedItemsField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    exchangeContext = new ConstraintReferenceGroup(
        Collections.singletonMap(Messages.FunctionalChainInvolvementSection_ExchangeContext_Label,
            FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGE_CONTEXT));
    exchangeContext.createControls(parent, getWidgetFactory(), isDisplayedInWizard());

    exchangedItemsField = new MultipleSemanticField(getReferencesGroup(),
        Messages.FunctionalChainInvolvementSection_ExchangedItems_Label, getWidgetFactory(),
        new AbstractMultipleSemanticFieldController() {
          /**
           * {@inheritDoc}
           */
          @Override
          protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
            return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(),
                FaPackage.eINSTANCE.getFunctionalChainInvolvementLink_ExchangedItems());
          }
        });
    exchangedItemsField.setDisplayedInWizard(displayedInWizard);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    exchangeContext.loadData(capellaElement);
    exchangedItemsField.loadData(capellaElement,
        FaPackage.eINSTANCE.getFunctionalChainInvolvementLink_ExchangedItems());
    InvolvedElement involvedElement = ((FunctionalChainInvolvement) capellaElement).getInvolved();
    if (involvedElement instanceof FunctionalExchange) {
      exchangedItemsField.setEnabled(true);
    } else {
      exchangedItemsField.setEnabled(false);
    }

  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null)
        && (eObjectToTest.eClass() == FaPackage.eINSTANCE.getFunctionalChainInvolvementLink()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.addAll(exchangeContext.getFields());
    fields.add(exchangedItemsField);

    return fields;
  }
}
