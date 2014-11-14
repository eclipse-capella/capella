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
import org.eclipse.sirius.diagram.tools.internal.graphical.edit.policies.DeleteHelper;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.properties.controllers.ComponentExchange_AllocatedFunctionalExchangesController;
import org.polarsys.capella.core.data.fa.properties.controllers.ComponentExchange_RealizedComponentExchangesController;
import org.polarsys.capella.core.data.fa.properties.fields.ComponentExchangeKindGroup;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 * The ComponentExchange section.
 */
public class ComponentExchangeSection extends NamedElementSection {

  private MultipleSemanticField _allocatedExchangeItemsField;
  private MultipleSemanticField _allocatedFunctionalExchangesField;
  private MultipleSemanticField _realizedComponentExchangesField;
  private ComponentExchangeKindGroup _componentExchangeKindGroup;
  private MultipleSemanticField _categoriesField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();
    
    _componentExchangeKindGroup = new ComponentExchangeKindGroup(_rootParentComposite, getWidgetFactory(), true);
    _componentExchangeKindGroup.setDisplayedInWizard(displayedInWizard);

    _allocatedExchangeItemsField = new MultipleSemanticField(getReferencesGroup(), Messages.ComponentExchangeSection_AllocatedExchangeItems_Label, getWidgetFactory(), new AbstractMultipleSemanticFieldController() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement_p) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement_p.eClass(), ModellingcorePackage.eINSTANCE.getAbstractInformationFlow_ConvoyedInformations());
      }
    });
    _allocatedExchangeItemsField.setDisplayedInWizard(displayedInWizard);

    final ComponentExchange_AllocatedFunctionalExchangesController controller = new ComponentExchange_AllocatedFunctionalExchangesController();
    _allocatedFunctionalExchangesField = new MultipleSemanticField(getReferencesGroup(),
      Messages.ComponentExchangeSection_AllocatedFunctionalExchanges_Label, getWidgetFactory(), controller) {
      /**
       * {@inheritDoc}
       * 
       * The synchronization of the delegations/allocations is now managed by {@link DeleteHelper} class
       */
      @Override
      protected void handleDeleteButtonClicked() {
        executeCommand(new AbstractReadWriteCommand() {
          @SuppressWarnings("synthetic-access")
          public void run() {
            doDeleteCommand(_semanticElement, _semanticFeature);
          }
        });
      }
    };
    _allocatedFunctionalExchangesField.setDisplayedInWizard(displayedInWizard);

    _categoriesField = new MultipleSemanticField(getReferencesGroup(), Messages.ComponentExchangeSection_Categories_Label, getWidgetFactory(), new AbstractMultipleSemanticFieldController() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(CapellaElement semanticElement_p) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement_p.eClass(), FaPackage.eINSTANCE.getComponentExchange_Categories());
      }
    });
    _categoriesField.setDisplayedInWizard(displayedInWizard);

    _realizedComponentExchangesField = new MultipleSemanticField(getReferencesGroup(),
      Messages.ComponentExchangeSection_RealizedComponentExchanges_Label, getWidgetFactory(), new ComponentExchange_RealizedComponentExchangesController());
    _realizedComponentExchangesField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    _componentExchangeKindGroup.loadData(capellaElement_p, FaPackage.eINSTANCE.getComponentExchange_Kind());
    _allocatedExchangeItemsField.loadData(capellaElement_p, ModellingcorePackage.eINSTANCE.getAbstractInformationFlow_ConvoyedInformations());
    _allocatedFunctionalExchangesField.loadData(capellaElement_p, FaPackage.eINSTANCE.getComponentExchange_OwnedComponentExchangeFunctionalExchangeAllocations());
    _categoriesField.loadData(capellaElement_p, FaPackage.eINSTANCE.getComponentExchange_Categories());
    _realizedComponentExchangesField.loadData(capellaElement_p, FaPackage.eINSTANCE.getComponentExchange_OwnedComponentExchangeRealizations());
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
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_allocatedExchangeItemsField);
    fields.add(_allocatedFunctionalExchangesField);
    fields.add(_componentExchangeKindGroup);
    fields.add(_categoriesField);
    fields.add(_realizedComponentExchangesField);

    return fields;
  }
}
