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
package org.polarsys.capella.core.data.common.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.common.properties.Messages;
import org.polarsys.capella.core.data.common.properties.controllers.StateTransitionRealizationsController;
import org.polarsys.capella.core.data.common.properties.fields.StateTransitionGroup;
import org.polarsys.capella.core.data.core.properties.sections.CapellaElementSection;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.controllers.SimpleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;

/**
 * The StateTransition section.
 */
public class StateTransitionSection extends CapellaElementSection {

  private StateTransitionGroup _stateTransitionGroup;
  private SimpleSemanticField _effectField;
  private SimpleSemanticField _triggerField;
  private MultipleSemanticField _realizationsField;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);
    
    boolean displayedInWizard = isDisplayedInWizard();

    _stateTransitionGroup = new StateTransitionGroup(_rootParentComposite, getWidgetFactory());
    _stateTransitionGroup.setDisplayedInWizard(isDisplayedInWizard());

    _effectField = new SimpleSemanticField(getReferencesGroup(), Messages.getString("StateTransition.Effect.Label"), getWidgetFactory(), new SimpleSemanticFieldController()); //$NON-NLS-1$
    _effectField.setDisplayedInWizard(displayedInWizard);

    _triggerField = new SimpleSemanticField(getReferencesGroup(), Messages.getString("StateTransition.Trigger.Label"), getWidgetFactory(), new SimpleSemanticFieldController()); //$NON-NLS-1$
    _triggerField.setDisplayedInWizard(displayedInWizard);

    _realizationsField = new MultipleSemanticField(getReferencesGroup(), Messages.getString("StateTransition.Realizations.Label"), getWidgetFactory(), new StateTransitionRealizationsController()); //$NON-NLS-1$
    _realizationsField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    _stateTransitionGroup.loadData(capellaElement_p);
    _effectField.loadData(capellaElement_p, CapellacommonPackage.eINSTANCE.getStateTransition_Effect());
    _triggerField.loadData(capellaElement_p, CapellacommonPackage.eINSTANCE.getStateTransition_Trigger());
    _realizationsField.loadData(capellaElement_p, CapellacommonPackage.eINSTANCE.getStateTransition_OwnedStateTransitionRealizations());
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
    fields.add(_effectField);
    fields.add(_triggerField);
    fields.add(_realizationsField);

    return fields;
  }
}
