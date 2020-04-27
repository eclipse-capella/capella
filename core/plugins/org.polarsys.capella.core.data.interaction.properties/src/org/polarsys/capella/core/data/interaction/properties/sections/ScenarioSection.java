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
package org.polarsys.capella.core.data.interaction.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.properties.Messages;
import org.polarsys.capella.core.data.interaction.properties.controllers.Scenario_RealizedScenariosController;
import org.polarsys.capella.core.data.interaction.properties.fields.ScenarioKindGroup;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ConstraintReferenceGroup;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

import com.google.common.collect.ImmutableMap;

/**
 * The Scenario section.
 */
public class ScenarioSection extends NamedElementSection {

  private ScenarioKindGroup scenarioKindGroup;
  private MultipleSemanticField realizedScenariosField;
  private ConstraintReferenceGroup prePostGroup;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    scenarioKindGroup = new ScenarioKindGroup(parent, getWidgetFactory(), true);
    scenarioKindGroup.setDisplayedInWizard(displayedInWizard);

    prePostGroup = new ConstraintReferenceGroup(ImmutableMap.of(
        Messages.getString("Scenario_PreCondition_Label"), InteractionPackage.Literals.SCENARIO__PRE_CONDITION, //$NON-NLS-1$
        Messages.getString("Scenario_PostCondition_Label"), InteractionPackage.Literals.SCENARIO__POST_CONDITION //$NON-NLS-1$
    ));
    prePostGroup.createControls(parent, getWidgetFactory(), isDisplayedInWizard());


    realizedScenariosField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("ScenarioSection_RealizedScenarios_Label"), getWidgetFactory(), new Scenario_RealizedScenariosController()); //$NON-NLS-1$
    realizedScenariosField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    scenarioKindGroup.loadData(capellaElement, InteractionPackage.eINSTANCE.getScenario_Kind());
    realizedScenariosField.loadData(capellaElement, InteractionPackage.eINSTANCE.getScenario_OwnedScenarioRealization());
    prePostGroup.loadData(capellaElement);
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == InteractionPackage.eINSTANCE.getScenario()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();
    fields.addAll(super.getSemanticFields());
    fields.add(realizedScenariosField);
    fields.add(scenarioKindGroup);
    fields.addAll(prePostGroup.getFields());
    return fields;
  }
}
