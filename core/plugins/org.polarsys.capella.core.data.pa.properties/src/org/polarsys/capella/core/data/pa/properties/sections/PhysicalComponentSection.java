/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.properties.controllers.Component_RealizedComponentsController;
import org.polarsys.capella.core.data.cs.properties.sections.ComponentSection;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.properties.Messages;
import org.polarsys.capella.core.data.pa.properties.fields.PhysicalComponentKindGroup;
import org.polarsys.capella.core.data.pa.properties.fields.PhysicalComponentNatureGroup;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The PhysicalComponent section.
 */
public class PhysicalComponentSection extends ComponentSection {

  private PhysicalComponentKindGroup pcKindGroup;
  private PhysicalComponentNatureGroup pcNatureGroup;
  private MultipleSemanticField logicalComponentRealizations;

  /**
   * Default constructor.
   */
  public PhysicalComponentSection() {
    super(true, true, true, true, true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    pcKindGroup = new PhysicalComponentKindGroup(rootParentComposite, getWidgetFactory(), true);
    pcKindGroup.setDisplayedInWizard(displayedInWizard);
    pcNatureGroup = new PhysicalComponentNatureGroup(rootParentComposite, getWidgetFactory(),
        CapellaModelPreferencesPlugin.getDefault().isChangePhysicalComponentNatureAllowed());
    pcNatureGroup.setDisplayedInWizard(displayedInWizard);

    logicalComponentRealizations = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("PhysicalComponentSection_LogicalComponentRealizations_Label"), getWidgetFactory(), //$NON-NLS-1$
        new Component_RealizedComponentsController());
    logicalComponentRealizations.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    pcKindGroup.loadData(capellaElement, PaPackage.eINSTANCE.getPhysicalComponent_Kind());
    pcNatureGroup.loadData(capellaElement, PaPackage.eINSTANCE.getPhysicalComponent_Nature());
    logicalComponentRealizations.loadData(capellaElement, CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);

    updateAllocatedFunctionsField((PhysicalComponentNature) capellaElement.eGet(PaPackage.eINSTANCE.getPhysicalComponent_Nature()));
    pcNatureGroup.setEnabled(CapellaModelPreferencesPlugin.getDefault().isChangePhysicalComponentNatureAllowed());
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
    fields.add(logicalComponentRealizations);
    fields.add(pcKindGroup);
    fields.add(pcNatureGroup);

    return fields;
  }

  private void updateAllocatedFunctionsField(PhysicalComponentNature nature) {
    if (PhysicalComponentNature.NODE.equals(nature)) {
      _allocatedFunctions.enableOpenButton(false);
    } else {
      _allocatedFunctions.enableOpenButton(true);
    }
  }
}
