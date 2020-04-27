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
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.properties.controllers.Port_AllocatedPortsController;
import org.polarsys.capella.core.data.fa.properties.controllers.Port_RealizedPortsController;
import org.polarsys.capella.core.data.fa.properties.fields.ComponentPortKindGroup;
import org.polarsys.capella.core.data.fa.properties.fields.OrientationPortKindGroup;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.sections.PortSection;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The ComponentPort section.
 */
public class ComponentPortSection extends PortSection {

  private MultipleSemanticField allocatedPortsField;
  private MultipleSemanticField realizedPortsField;
  private ComponentPortKindGroup componentPortKindGroup;
  private OrientationPortKindGroup orientationPortKindGroup;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    allocatedPortsField = new MultipleSemanticField(getReferencesGroup(),
        Messages.ComponentPortSection_AllocatedPorts_Label, getWidgetFactory(), new Port_AllocatedPortsController());
    allocatedPortsField.setDisplayedInWizard(displayedInWizard);

    realizedPortsField = new MultipleSemanticField(getReferencesGroup(),
        Messages.ComponentPortSection_RealizedPorts_Label, getWidgetFactory(), new Port_RealizedPortsController());
    realizedPortsField.setDisplayedInWizard(displayedInWizard);

    componentPortKindGroup = new ComponentPortKindGroup(parent, getWidgetFactory(), true);
    componentPortKindGroup.setDisplayedInWizard(displayedInWizard);

    orientationPortKindGroup = new OrientationPortKindGroup(parent, getWidgetFactory(), true);
    orientationPortKindGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    allocatedPortsField.loadData(capellaElement, InformationPackage.eINSTANCE.getPort_OwnedPortAllocations());
    realizedPortsField.loadData(capellaElement, InformationPackage.eINSTANCE.getPort_OwnedPortRealizations());
    componentPortKindGroup.loadData(capellaElement, FaPackage.eINSTANCE.getComponentPort_Kind());
    orientationPortKindGroup.loadData(capellaElement, FaPackage.eINSTANCE.getComponentPort_Orientation());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == FaPackage.eINSTANCE.getComponentPort()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(allocatedPortsField);
    fields.add(componentPortKindGroup);
    fields.add(orientationPortKindGroup);
    fields.add(realizedPortsField);

    return fields;
  }
}
