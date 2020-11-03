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
package org.polarsys.capella.core.data.la.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.interaction.properties.sections.AbstractCapabilitySection;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.properties.Messages;
import org.polarsys.capella.core.data.la.properties.controllers.CapabilityRealization_InvolvedComponentsController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The CapabilityRealization section.
 */
public class CapabilityRealizationSection extends AbstractCapabilitySection {

  private MultipleSemanticField involvedComponentsField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    involvedComponentsField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("CapabilityRealizationSection_InvolvedComponents_Label"), getWidgetFactory(), new CapabilityRealization_InvolvedComponentsController()); //$NON-NLS-1$
    involvedComponentsField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    involvedComponentsField.loadData(capellaElement, LaPackage.eINSTANCE.getCapabilityRealization_OwnedCapabilityRealizationInvolvements());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == LaPackage.eINSTANCE.getCapabilityRealization()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(involvedComponentsField);

    return fields;
  }
}
