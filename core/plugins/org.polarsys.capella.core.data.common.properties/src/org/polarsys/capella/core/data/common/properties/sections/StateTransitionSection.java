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
package org.polarsys.capella.core.data.common.properties.sections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.common.properties.Messages;
import org.polarsys.capella.core.data.common.properties.controllers.EffectsController;
import org.polarsys.capella.core.data.common.properties.controllers.StateTransitionRealizationsController;
import org.polarsys.capella.core.data.common.properties.fields.StateTransitionGroup;
import org.polarsys.capella.core.data.common.properties.fields.StateTransitionTriggerField;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ConstraintReferenceGroup;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The StateTransition section.
 */
public class StateTransitionSection extends NamedElementSection {

  private StateTransitionGroup _stateTransitionGroup;
  private MultipleSemanticField _effectField;
  private StateTransitionTriggerField _triggerField;
  private MultipleSemanticField _realizationsField;
  private ConstraintReferenceGroup _guardGroup;
  
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();


    _guardGroup = new ConstraintReferenceGroup(Collections.singletonMap(Messages.getString("StateTransitionGroup.Guard.Label"), CapellacommonPackage.Literals.STATE_TRANSITION__GUARD)); //$NON-NLS-1$
    _guardGroup.createControls(parent, getWidgetFactory(), isDisplayedInWizard());

    _effectField =
        new MultipleSemanticField(getReferencesGroup(),
            Messages.getString("StateTransition.Effect.Label"), getWidgetFactory(), new EffectsController()); //$NON-NLS-1$
    _effectField.setDisplayedInWizard(displayedInWizard);

    Group triggersGroup = getWidgetFactory().createGroup(parent, ""); //$NON-NLS-1$
    triggersGroup.setLayout(new GridLayout(1, false));
    GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
    layoutData.horizontalSpan = 2;
    triggersGroup.setLayoutData(layoutData);

    _triggerField =
        new StateTransitionTriggerField(triggersGroup, getWidgetFactory(), null, null, CapellacommonPackage.Literals.STATE_EVENT,
            Messages.getString("StateTransition.Trigger.Label"), //$NON-NLS-1$
            "trigger dialog message"); //$NON-NLS-1$
    _triggerField.setDisplayedInWizard(displayedInWizard);

    _realizationsField =
        new MultipleSemanticField(getReferencesGroup(),
            Messages.getString("StateTransition.Realizations.Label"), getWidgetFactory(), new StateTransitionRealizationsController()); //$NON-NLS-1$
    _realizationsField.setDisplayedInWizard(displayedInWizard);

    _stateTransitionGroup = new StateTransitionGroup(parent, getWidgetFactory());
    _stateTransitionGroup.setDisplayedInWizard(isDisplayedInWizard());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _stateTransitionGroup.loadData(capellaElement);
    _effectField.loadData(capellaElement, CapellacommonPackage.eINSTANCE.getStateTransition_Effect());
    _guardGroup.loadData(capellaElement);
    _triggerField.loadData(capellaElement, CapellacommonPackage.eINSTANCE.getStateTransition_Triggers());
    _realizationsField.loadData(capellaElement, CapellacommonPackage.eINSTANCE.getStateTransition_OwnedStateTransitionRealizations());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == CapellacommonPackage.eINSTANCE.getStateTransition()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_stateTransitionGroup);
    fields.addAll(_guardGroup.getFields());
    fields.add(_effectField);
    fields.add(_triggerField);
    fields.add(_realizationsField);

    return fields;
  }
}
