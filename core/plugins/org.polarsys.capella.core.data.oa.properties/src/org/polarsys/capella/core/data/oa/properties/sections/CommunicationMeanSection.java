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
package org.polarsys.capella.core.data.oa.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.properties.Messages;
import org.polarsys.capella.core.data.oa.properties.controllers.CommunicationMean_AllocatedComponentExchangesController;
import org.polarsys.capella.core.data.oa.properties.controllers.CommunicationMean_AllocatedExchangesController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The CommunicationMean section.
 */
public class CommunicationMeanSection extends NamedElementSection {

  private MultipleSemanticField _allocatedExchangesField;
  private MultipleSemanticField _allocatedComponentExchangesField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _allocatedExchangesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("CommunicationMeanSection_AllocatedExchanges_Label"), getWidgetFactory(), new CommunicationMean_AllocatedExchangesController()); //$NON-NLS-1$
    _allocatedExchangesField.setDisplayedInWizard(displayedInWizard);

    _allocatedComponentExchangesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("CommunicationMeanSection_AllocatedInteractions_Label"), getWidgetFactory(), new CommunicationMean_AllocatedComponentExchangesController()); //$NON-NLS-1$
    _allocatedComponentExchangesField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _allocatedExchangesField.loadData(capellaElement, ModellingcorePackage.eINSTANCE.getAbstractInformationFlow_ConvoyedInformations());
    _allocatedComponentExchangesField.loadData(capellaElement, FaPackage.eINSTANCE.getComponentExchange_OwnedComponentExchangeFunctionalExchangeAllocations());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == OaPackage.eINSTANCE.getCommunicationMean()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_allocatedComponentExchangesField);
    fields.add(_allocatedExchangesField);

    return fields;
  }
}
