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

  private MultipleSemanticField _exchangedItemsField;
  private MultipleSemanticField _categoriesField;
  private MultipleSemanticField _realizedExchangesField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _exchangedItemsField = new MultipleSemanticField(getReferencesGroup(), Messages.FunctionalExchangeSection_ExchangedItems_Label, getWidgetFactory(), new AbstractMultipleSemanticFieldController() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement_p) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement_p.eClass(), FaPackage.eINSTANCE.getFunctionalExchange_ExchangedItems());
      }
    });
    _exchangedItemsField.setDisplayedInWizard(displayedInWizard);

    _categoriesField = new MultipleSemanticField(getReferencesGroup(), Messages.FunctionalExchangeSection_Categories_Label, getWidgetFactory(), new AbstractMultipleSemanticFieldController() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement_p) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement_p.eClass(), FaPackage.eINSTANCE.getFunctionalExchange_Categories());
      }
    });
    _categoriesField.setDisplayedInWizard(displayedInWizard);

    _realizedExchangesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.FunctionalExchangeSection_RealizedExchanges_Label, getWidgetFactory(), new FunctionalExchange_RealizedExchangesController());
    _realizedExchangesField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    _exchangedItemsField.loadData(capellaElement_p, FaPackage.eINSTANCE.getFunctionalExchange_ExchangedItems());
    _categoriesField.loadData(capellaElement_p, FaPackage.eINSTANCE.getFunctionalExchange_Categories());
    _realizedExchangesField.loadData(capellaElement_p, FaPackage.eINSTANCE.getFunctionalExchange_OwnedFunctionalExchangeRealizations());
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
    fields.add(_categoriesField);
    fields.add(_exchangedItemsField);
    fields.add(_realizedExchangesField);

    return fields;
  }
}
