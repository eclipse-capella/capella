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
package org.polarsys.capella.core.data.pa.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.cs.properties.sections.ComponentSection;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.properties.Messages;
import org.polarsys.capella.core.data.pa.properties.controllers.RealizedLogicalComponentsController;
import org.polarsys.capella.core.data.pa.properties.fields.PhysicalComponentKindGroup;
import org.polarsys.capella.core.data.pa.properties.fields.PhysicalComponentNatureGroup;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The PhysicalComponent section.
 */
public class PhysicalComponentSection extends ComponentSection {

  private PhysicalComponentKindGroup _pcKindGroup;
  private PhysicalComponentNatureGroup _pcNatureGroup;
  private MultipleSemanticField _logicalComponentRealizations;

  /**
   * Default constructor.
   */
  public PhysicalComponentSection() {
    super(true, true, true, false, false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _pcKindGroup = new PhysicalComponentKindGroup(_rootParentComposite, getWidgetFactory(), true);
    _pcKindGroup.setDisplayedInWizard(displayedInWizard);
    _pcNatureGroup = new PhysicalComponentNatureGroup(_rootParentComposite, getWidgetFactory(), true);
    _pcNatureGroup.setDisplayedInWizard(displayedInWizard);

    _logicalComponentRealizations =
        new MultipleSemanticField(getReferencesGroup(), Messages.getString("PhysicalComponentSection_LogicalComponentRealizations_Label"), getWidgetFactory(), //$NON-NLS-1$
            new RealizedLogicalComponentsController());
    _logicalComponentRealizations.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    _pcKindGroup.loadData(capellaElement_p, PaPackage.eINSTANCE.getAbstractPhysicalComponent_Kind());
    _pcNatureGroup.loadData(capellaElement_p, PaPackage.eINSTANCE.getAbstractPhysicalComponent_Nature());
    _logicalComponentRealizations.loadData(capellaElement_p, PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_REALIZATIONS);

    updateAllocatedFunctionsField((PhysicalComponentNature) capellaElement_p.eGet(PaPackage.eINSTANCE.getAbstractPhysicalComponent_Nature()));
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == PaPackage.eINSTANCE.getPhysicalComponent()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_logicalComponentRealizations);
    fields.add(_pcKindGroup);
    fields.add(_pcNatureGroup);

    return fields;
  }

  private void updateAllocatedFunctionsField(PhysicalComponentNature nature_p) {
    if (PhysicalComponentNature.NODE.equals(nature_p)) {
      _allocatedFunctions.enableOpenButton(false);
    } else {
      _allocatedFunctions.enableOpenButton(true);
    }
  }
}
