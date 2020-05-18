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
import org.polarsys.capella.core.data.ctx.properties.controllers.Mission_InvolvedSystemComponentsController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The Mission section.
 */
public class MissionSection extends NamedElementSection {

  private MultipleSemanticField _involvedSystemComponentsField;
  private MultipleSemanticField _exploitedCapabilitiesField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _involvedSystemComponentsField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("MissionSection_InvolvedSystemComponents_Label"), getWidgetFactory(), new Mission_InvolvedSystemComponentsController()); //$NON-NLS-1$
    _involvedSystemComponentsField.setDisplayedInWizard(displayedInWizard);

    _exploitedCapabilitiesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("MissionSection_ExploitedCapabilities_Label"), getWidgetFactory(), new Mission_ExploitedCapabilitiesController()); //$NON-NLS-1$
    _exploitedCapabilitiesField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _involvedSystemComponentsField.loadData(capellaElement, CtxPackage.Literals.MISSION__OWNED_MISSION_INVOLVEMENTS);
    _exploitedCapabilitiesField.loadData(capellaElement, CtxPackage.Literals.MISSION__OWNED_CAPABILITY_EXPLOITATIONS);
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
    fields.add(_involvedSystemComponentsField);

    return fields;
  }
}
