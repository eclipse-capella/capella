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
package org.polarsys.capella.core.data.oa.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.properties.Messages;
import org.polarsys.capella.core.data.oa.properties.controllers.CommunicationMean_AllocatedComponentExchangesController;
import org.polarsys.capella.core.data.oa.properties.controllers.CommunicationMean_AllocatedExchangesController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

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
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _allocatedExchangesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("CommunicationMeanSection_AllocatedExchanges_Label"), getWidgetFactory(), new CommunicationMean_AllocatedExchangesController()); //$NON-NLS-1$
    _allocatedExchangesField.setDisplayedInWizard(displayedInWizard);

    _allocatedComponentExchangesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("CommunicationMeanSection_AllocatedInteractions_Label"), getWidgetFactory(), new CommunicationMean_AllocatedComponentExchangesController()); //$NON-NLS-1$
    _allocatedComponentExchangesField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    _allocatedExchangesField.loadData(capellaElement_p, ModellingcorePackage.eINSTANCE.getAbstractInformationFlow_ConvoyedInformations());
    _allocatedComponentExchangesField.loadData(capellaElement_p, FaPackage.eINSTANCE.getComponentExchange_OwnedComponentExchangeFunctionalExchangeAllocations());
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
