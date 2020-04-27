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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.properties.controllers.Port_RealizedPortsController;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The FunctionInputPort section.
 */
public class FunctionInputPortSection extends NamedElementSection {

  private MultipleSemanticField incomingExchangeItemsField;
  private MultipleSemanticField realizedFunctionInputPortsField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    incomingExchangeItemsField = new MultipleSemanticField(getReferencesGroup(), Messages.FunctionInputPortSection_IncomingExchangeItems_Label, getWidgetFactory(), new AbstractMultipleSemanticFieldController() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
        return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), FaPackage.eINSTANCE.getFunctionInputPort_IncomingExchangeItems());
      }
    });
    incomingExchangeItemsField.setDisplayedInWizard(displayedInWizard);

    realizedFunctionInputPortsField = new MultipleSemanticField(getReferencesGroup(),
        Messages.FunctionInputPortSection_RealizedFunctionInputPorts_Label, getWidgetFactory(), new Port_RealizedPortsController());
    realizedFunctionInputPortsField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    incomingExchangeItemsField.loadData(capellaElement, FaPackage.eINSTANCE.getFunctionInputPort_IncomingExchangeItems());
    realizedFunctionInputPortsField.loadData(capellaElement, InformationPackage.eINSTANCE.getPort_OwnedPortRealizations());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == FaPackage.eINSTANCE.getFunctionInputPort()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(incomingExchangeItemsField);
    fields.add(realizedFunctionInputPortsField);

    return fields;
  }
}
