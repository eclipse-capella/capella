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
package org.polarsys.capella.core.data.oa.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.interaction.properties.sections.AbstractCapabilitySection;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.properties.Messages;
import org.polarsys.capella.core.data.oa.properties.controllers.OperationalCapability_InvolvedEntitiesController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The OperationalCapability section.
 */
public class OperationalCapabilitySection extends AbstractCapabilitySection {

  private MultipleSemanticField _involvedEntitiesField;

  /**
   * Constructor
   */
  public OperationalCapabilitySection() {
    super(false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _involvedEntitiesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("OperationalCapabilitySection_InvolvedEntities_Label"), getWidgetFactory(), new OperationalCapability_InvolvedEntitiesController()); //$NON-NLS-1$
    _involvedEntitiesField.setDisplayedInWizard(displayedInWizard);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _involvedEntitiesField.loadData(capellaElement, OaPackage.eINSTANCE.getOperationalCapability_OwnedEntityOperationalCapabilityInvolvements());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == OaPackage.eINSTANCE.getOperationalCapability()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getInvolvedFunctionsLabel() {
    return Messages.getString("OperationalCapabilitySection_RealizedActivities_Label"); //$NON-NLS-1$
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getInvolvedFunctionalChainsLabel() {
    return Messages.getString("OperationalCapabilitySection_RealizedOperationalProcesses_Label"); //$NON-NLS-1$
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_involvedEntitiesField);

    return fields;
  }
}
