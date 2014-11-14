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

import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.properties.controllers.Port_AllocatedPortsController;
import org.polarsys.capella.core.data.fa.properties.controllers.Port_RealizedPortsController;
import org.polarsys.capella.core.data.fa.properties.fields.ComponentPortKindGroup;
import org.polarsys.capella.core.data.fa.properties.fields.OrientationPortKindGroup;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.sections.PortSection;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The ComponentPort section.
 */
public class ComponentPortSection extends PortSection {

  private MultipleSemanticField _allocatedPortsField;
  private MultipleSemanticField _realizedPortsField;
  private ComponentPortKindGroup _componentPortKindGroup;
  private OrientationPortKindGroup _orientationPortKindGroup;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _allocatedPortsField = new MultipleSemanticField(getReferencesGroup(),
        Messages.ComponentPortSection_AllocatedPorts_Label, getWidgetFactory(), new Port_AllocatedPortsController());
    _allocatedPortsField.setDisplayedInWizard(displayedInWizard);

    _realizedPortsField = new MultipleSemanticField(getReferencesGroup(),
        Messages.ComponentPortSection_RealizedPorts_Label, getWidgetFactory(), new Port_RealizedPortsController());
    _realizedPortsField.setDisplayedInWizard(displayedInWizard);

    _componentPortKindGroup = new ComponentPortKindGroup(_rootParentComposite, getWidgetFactory(), true);
    _componentPortKindGroup.setDisplayedInWizard(displayedInWizard);

    _orientationPortKindGroup = new OrientationPortKindGroup(_rootParentComposite, getWidgetFactory(), true);
    _orientationPortKindGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    _allocatedPortsField.loadData(capellaElement_p, InformationPackage.eINSTANCE.getPort_OwnedPortAllocations());
    _realizedPortsField.loadData(capellaElement_p, InformationPackage.eINSTANCE.getPort_OwnedPortRealizations());
    _componentPortKindGroup.loadData(capellaElement_p, FaPackage.eINSTANCE.getComponentPort_Kind());
    _orientationPortKindGroup.loadData(capellaElement_p, FaPackage.eINSTANCE.getComponentPort_Orientation());
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
    fields.add(_allocatedPortsField);
    fields.add(_componentPortKindGroup);
    fields.add(_orientationPortKindGroup);
    fields.add(_realizedPortsField);

    return fields;
  }
}
