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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The PhysicalLinkCategory section.
 */
public class PhysicalLinkCategorySection extends NamedElementSection {

  private MultipleSemanticField _linksField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _linksField = new MultipleSemanticField(getReferencesGroup(), Messages.PhysicalLinkCategorySection_Links_Label, getWidgetFactory(), new AbstractMultipleSemanticFieldController() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), CsPackage.eINSTANCE.getPhysicalLinkCategory_Links());
      }
    });
    _linksField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _linksField.loadData(capellaElement, CsPackage.eINSTANCE.getPhysicalLinkCategory_Links());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == CsPackage.eINSTANCE.getPhysicalLinkCategory()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_linksField);

    return fields;
  }
}
