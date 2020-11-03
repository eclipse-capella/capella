/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.properties.Messages;
import org.polarsys.capella.core.data.cs.properties.controllers.DeployedComponentsController;
import org.polarsys.capella.core.data.information.properties.sections.MultiplicityElementSection;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The Part section.
 */
public class PartSection extends MultiplicityElementSection {

  private MultipleSemanticField deployedComponentsField;

  /**
   * Default constructor.
   */
  public PartSection() {
    super(false, false, false, false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    deployedComponentsField = new MultipleSemanticField(getReferencesGroup(), Messages.getString("Part.DeployedComponentsLabel"), getWidgetFactory(), new DeployedComponentsController()); //$NON-NLS-1$
    deployedComponentsField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    if (capellaElement instanceof Part) {
      AbstractType type = ((Part) capellaElement).getAbstractType();
      if (type instanceof PhysicalComponent) {
        deployedComponentsField.loadData(capellaElement, CsPackage.eINSTANCE.getPart_OwnedDeploymentLinks());
        deployedComponentsField.setVisible(true);
      } else {
        deployedComponentsField.setVisible(false);
      }
    }
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == CsPackage.eINSTANCE.getPart()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(deployedComponentsField);

    return fields;
  }
}
