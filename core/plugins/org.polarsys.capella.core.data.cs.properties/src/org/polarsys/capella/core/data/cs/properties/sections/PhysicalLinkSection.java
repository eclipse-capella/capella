/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.cs.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.properties.controllers.PhysicalLinkAllocatedComponentExchangesController;
import org.polarsys.capella.core.data.cs.properties.controllers.RealizedPhysicalLinksController;
import org.polarsys.capella.core.data.fa.properties.sections.ComponentExchangeAllocatorSection;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The PhysicalLink section.
 */
public class PhysicalLinkSection extends ComponentExchangeAllocatorSection {

  private MultipleSemanticField _categoriesField;
  private MultipleSemanticField _realizedLinksField;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _categoriesField =
        new MultipleSemanticField(getReferencesGroup(), Messages.PhysicalLinkSection_Categories_Label, getWidgetFactory(),
            new AbstractMultipleSemanticFieldController() {
              /**
               * {@inheritDoc}
               */
              @Override
              protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
                return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), CsPackage.eINSTANCE.getPhysicalLink_Categories());
              }
            });
    _categoriesField.setDisplayedInWizard(displayedInWizard);

    _realizedLinksField =
        new MultipleSemanticField(getReferencesGroup(), Messages.PhysicalLinkSection_Realized_Label, getWidgetFactory(), new RealizedPhysicalLinksController());
    _realizedLinksField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    if (null != _categoriesField) {
      _categoriesField.loadData(capellaElement, CsPackage.eINSTANCE.getPhysicalLink_Categories());
    }
    if (null != _realizedLinksField) {
      _realizedLinksField.loadData(capellaElement, CsPackage.eINSTANCE.getPhysicalLink_OwnedPhysicalLinkRealizations());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_categoriesField);
    fields.add(_realizedLinksField);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected MultipleSemanticField createComponentExchangeAllocationsField() {
    final PhysicalLinkAllocatedComponentExchangesController controller = new PhysicalLinkAllocatedComponentExchangesController();
    return new MultipleSemanticField(getReferencesGroup(), Messages.ComponentExchangeAllocatorSection_ComponentExchangeAllocations_Label, getWidgetFactory(),
        controller) {
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
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == CsPackage.eINSTANCE.getPhysicalLink()));
  }
}
