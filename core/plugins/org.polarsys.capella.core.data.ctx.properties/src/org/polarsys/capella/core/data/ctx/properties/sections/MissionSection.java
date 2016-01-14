/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.ctx.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.properties.Messages;
import org.polarsys.capella.core.data.ctx.properties.controllers.Mission_ExploitedCapabilitiesController;
import org.polarsys.capella.core.data.ctx.properties.controllers.Mission_InvolvedActorsController;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The Mission section.
 */
public class MissionSection extends NamedElementSection {

  private MultipleSemanticField _involvedActorsField;
  private MultipleSemanticField _exploitedCapabilitiesField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _involvedActorsField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("MissionSection_InvolvedActors_Label"), getWidgetFactory(), new Mission_InvolvedActorsController()); //$NON-NLS-1$
    _involvedActorsField.setDisplayedInWizard(displayedInWizard);

    _exploitedCapabilitiesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("MissionSection_ExploitedCapabilities_Label"), getWidgetFactory(), new Mission_ExploitedCapabilitiesController()); //$NON-NLS-1$
    _exploitedCapabilitiesField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement) {
    super.loadData(capellaElement);

    _involvedActorsField.loadData(capellaElement, CtxPackage.eINSTANCE.getMission_OwnedActorMissionInvolvements());
    _exploitedCapabilitiesField.loadData(capellaElement, CtxPackage.eINSTANCE.getMission_OwnedCapabilityExploitations());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == CtxPackage.eINSTANCE.getMission()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_exploitedCapabilitiesField);
    fields.add(_involvedActorsField);

    return fields;
  }
}
