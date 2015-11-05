/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.properties.controllers.FunctionalExchange_RealizedExchangesController;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The FunctionalExchange section.
 */
public class FunctionalExchangeSection extends NamedElementSection {

  private MultipleSemanticField exchangedItemsField;
  private MultipleSemanticField categoriesField;
  private MultipleSemanticField realizedExchangesField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    exchangedItemsField = new MultipleSemanticField(getReferencesGroup(), Messages.FunctionalExchangeSection_ExchangedItems_Label, getWidgetFactory(), new AbstractMultipleSemanticFieldController() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), FaPackage.eINSTANCE.getFunctionalExchange_ExchangedItems());
      }
    });
    exchangedItemsField.setDisplayedInWizard(displayedInWizard);

    categoriesField = new MultipleSemanticField(getReferencesGroup(), Messages.FunctionalExchangeSection_Categories_Label, getWidgetFactory(), new AbstractMultipleSemanticFieldController() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), FaPackage.eINSTANCE.getFunctionalExchange_Categories());
      }
    });
    categoriesField.setDisplayedInWizard(displayedInWizard);

    realizedExchangesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.FunctionalExchangeSection_RealizedExchanges_Label, getWidgetFactory(), new FunctionalExchange_RealizedExchangesController());
    realizedExchangesField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement) {
    super.loadData(capellaElement);

    exchangedItemsField.loadData(capellaElement, FaPackage.eINSTANCE.getFunctionalExchange_ExchangedItems());
    categoriesField.loadData(capellaElement, FaPackage.eINSTANCE.getFunctionalExchange_Categories());
    realizedExchangesField.loadData(capellaElement, FaPackage.eINSTANCE.getFunctionalExchange_OwnedFunctionalExchangeRealizations());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == FaPackage.eINSTANCE.getFunctionalExchange()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(categoriesField);
    fields.add(exchangedItemsField);
    fields.add(realizedExchangesField);

    return fields;
  }
}
