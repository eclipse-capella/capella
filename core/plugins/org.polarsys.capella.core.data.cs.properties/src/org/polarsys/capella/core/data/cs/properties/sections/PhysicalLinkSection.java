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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.properties.controllers.PhysicalLinkAllocatedComponentExchangesController;
import org.polarsys.capella.core.data.cs.properties.controllers.PhysicalLinkCategoriesController;
import org.polarsys.capella.core.data.cs.properties.controllers.RealizedPhysicalLinksController;
import org.polarsys.capella.core.data.fa.properties.sections.ComponentExchangeAllocatorSection;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The PhysicalLink section.
 */
public class PhysicalLinkSection extends ComponentExchangeAllocatorSection {

  private MultipleSemanticField _categoriesField;
  private MultipleSemanticField _realizedLinksField;

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _categoriesField =
        new MultipleSemanticField(getReferencesGroup(), Messages.PhysicalLinkSection_Categories_Label, getWidgetFactory(),
            new PhysicalLinkCategoriesController()) {
      @Override
      protected void removeAllDataValue(EObject element, EStructuralFeature feature) {
        _controller.writeOpenValues(semanticElement, semanticFeature, (List) Collections.emptyList());
      }
    };
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
    List<AbstractSemanticField> fields = new ArrayList<>();

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
        controller);
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
