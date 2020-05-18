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
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.properties.controllers.RealizedPhysicalPortsController;
import org.polarsys.capella.core.data.fa.properties.controllers.AllocatedComponentPortsController;
import org.polarsys.capella.core.data.fa.properties.controllers.AllocatedFunctionPortsController;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The PhysicalPort section.
 */
public class PhysicalPortSection extends NamedElementSection {

  private MultipleSemanticField _allocatedComponentPorts;
  private MultipleSemanticField _allocatedFunctionPorts;
  private MultipleSemanticField _realizedPhysicalPortsField;

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _realizedPhysicalPortsField = new MultipleSemanticField(getReferencesGroup(), Messages.PhysicalPortSection_Realized_Label, getWidgetFactory(), new RealizedPhysicalPortsController());
    _realizedPhysicalPortsField.setDisplayedInWizard(displayedInWizard);

    _allocatedComponentPorts =
        new MultipleSemanticField(getReferencesGroup(), Messages.PhysicalPortSection_AllocatedComponentPorts_Label, getWidgetFactory(),
            new AllocatedComponentPortsController());
    _allocatedComponentPorts.setDisplayedInWizard(displayedInWizard);
    
    _allocatedFunctionPorts =
      new MultipleSemanticField(getReferencesGroup(), Messages.PhysicalPortSection_AllocatedFunctionPorts_Label, getWidgetFactory(),
          new AllocatedFunctionPortsController());
    _allocatedFunctionPorts.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    if (null != _realizedPhysicalPortsField) {
      _realizedPhysicalPortsField.loadData(capellaElement, CsPackage.eINSTANCE.getPhysicalPort_OwnedPhysicalPortRealizations());
    }
    if (null != _allocatedComponentPorts) {
      _allocatedComponentPorts.loadData(capellaElement, CsPackage.Literals.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS);
    }
    if (null != _allocatedFunctionPorts) {
      EObject owner = capellaElement.eContainer();
      if (ComponentExt.isActor(owner)) {
        _allocatedFunctionPorts.loadData(capellaElement, InformationPackage.Literals.PORT__OWNED_PORT_ALLOCATIONS);
        _allocatedFunctionPorts.setVisible(true);
      } else {
        _allocatedFunctionPorts.setVisible(false);
      }
    }
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == CsPackage.eINSTANCE.getPhysicalPort()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_realizedPhysicalPortsField);
    fields.add(_allocatedComponentPorts);
    fields.add(_allocatedFunctionPorts);

    return fields;
  }
}
