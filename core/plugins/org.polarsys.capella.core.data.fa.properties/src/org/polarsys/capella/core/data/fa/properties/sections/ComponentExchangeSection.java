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
package org.polarsys.capella.core.data.fa.properties.sections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.properties.controllers.ComponentExchangeCategoriesController;
import org.polarsys.capella.core.data.fa.properties.controllers.ComponentExchange_AllocatedFunctionalExchangesController;
import org.polarsys.capella.core.data.fa.properties.controllers.ComponentExchange_RealizedComponentExchangesController;
import org.polarsys.capella.core.data.fa.properties.fields.ComponentExchangeKindGroup;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The ComponentExchange section.
 */
public class ComponentExchangeSection extends NamedElementSection {

  private MultipleSemanticField allocatedExchangeItemsField;
  private MultipleSemanticField allocatedFunctionalExchangesField;
  private MultipleSemanticField realizedComponentExchangesField;
  private ComponentExchangeKindGroup componentExchangeKindGroup;
  private MultipleSemanticField categoriesField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    componentExchangeKindGroup = new ComponentExchangeKindGroup(parent, getWidgetFactory(), true);
    componentExchangeKindGroup.setDisplayedInWizard(displayedInWizard);

    allocatedExchangeItemsField = new MultipleSemanticField(getReferencesGroup(),
        Messages.ComponentExchangeSection_AllocatedExchangeItems_Label, getWidgetFactory(),
        new AbstractMultipleSemanticFieldController() {
          /**
           * {@inheritDoc}
           */
          @Override
          protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
            return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(),
                ModellingcorePackage.eINSTANCE.getAbstractInformationFlow_ConvoyedInformations());
          }
        });
    allocatedExchangeItemsField.setDisplayedInWizard(displayedInWizard);

    final ComponentExchange_AllocatedFunctionalExchangesController controller = new ComponentExchange_AllocatedFunctionalExchangesController();
    allocatedFunctionalExchangesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.ComponentExchangeSection_AllocatedFunctionalExchanges_Label, getWidgetFactory(), controller);
    allocatedFunctionalExchangesField.setDisplayedInWizard(displayedInWizard);

    categoriesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.ComponentExchangeSection_Categories_Label, getWidgetFactory(),
        new ComponentExchangeCategoriesController()) {
      @Override
      protected void removeAllDataValue(EObject element, EStructuralFeature feature) {
        _controller.writeOpenValues(semanticElement, semanticFeature, (List) Collections.emptyList());
      }
    };
    categoriesField.setDisplayedInWizard(displayedInWizard);

    realizedComponentExchangesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.ComponentExchangeSection_RealizedComponentExchanges_Label, getWidgetFactory(),
        new ComponentExchange_RealizedComponentExchangesController());
    realizedComponentExchangesField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    componentExchangeKindGroup.loadData(capellaElement, FaPackage.eINSTANCE.getComponentExchange_Kind());
    allocatedExchangeItemsField.loadData(capellaElement,
        ModellingcorePackage.eINSTANCE.getAbstractInformationFlow_ConvoyedInformations());
    allocatedFunctionalExchangesField.loadData(capellaElement,
        FaPackage.eINSTANCE.getComponentExchange_OwnedComponentExchangeFunctionalExchangeAllocations());
    categoriesField.loadData(capellaElement, FaPackage.eINSTANCE.getComponentExchange_Categories());
    realizedComponentExchangesField.loadData(capellaElement,
        FaPackage.eINSTANCE.getComponentExchange_OwnedComponentExchangeRealizations());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == FaPackage.eINSTANCE.getComponentExchange()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();

    fields.addAll(super.getSemanticFields());
    fields.add(allocatedExchangeItemsField);
    fields.add(allocatedFunctionalExchangesField);
    fields.add(componentExchangeKindGroup);
    fields.add(categoriesField);
    fields.add(realizedComponentExchangesField);

    return fields;
  }
}
