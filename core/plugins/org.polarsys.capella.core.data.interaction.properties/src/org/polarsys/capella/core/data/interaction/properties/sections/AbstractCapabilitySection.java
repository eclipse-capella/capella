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
import org.polarsys.capella.core.data.interaction.properties.controllers.AbstractCapability_AvailableInStatesController;
import org.polarsys.capella.core.data.interaction.properties.controllers.AbstractCapability_InvolvedFunctionalChainsController;
import org.polarsys.capella.core.data.interaction.properties.controllers.AbstractCapability_InvolvedFunctionsController;
import org.polarsys.capella.core.data.interaction.properties.controllers.AbstractCapability_RealizedCapabilitiesController;
import org.polarsys.capella.core.data.interaction.properties.controllers.AbstractCapability_SuperController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.CompositionMultipleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ConstraintReferenceGroup;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

import com.google.common.collect.ImmutableMap;

/**
 * The AbstractCapability section.
 */
public abstract class AbstractCapabilitySection extends NamedElementSection {

  private CompositionMultipleSemanticField superTypes;
  private MultipleSemanticField realizedFunctionsField;
  private MultipleSemanticField realizedFunctionalChainsField;
  private MultipleSemanticField availableInStatesField;
  private MultipleSemanticField realizedCapabilitiesField;
  private ConstraintReferenceGroup prePostConditions;

  private boolean showRealizedCapabilitiesField;

  /**
   * Constructor
   */
  public AbstractCapabilitySection() {
    this(true);
  }

  /**
   * Constructor
   */
  public AbstractCapabilitySection(boolean showRealizedCapabilitiesField) {
    this.showRealizedCapabilitiesField = showRealizedCapabilitiesField;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    prePostConditions = new ConstraintReferenceGroup(ImmutableMap.of(
        Messages.getString("AbstractCapabilitySection_PreCondition_Label"), InteractionPackage.Literals.ABSTRACT_CAPABILITY__PRE_CONDITION, //$NON-NLS-1$
        Messages.getString("AbstractCapabilitySection_PostCondition_Label"), InteractionPackage.Literals.ABSTRACT_CAPABILITY__POST_CONDITION //$NON-NLS-1$
    ));
    prePostConditions.createControls(parent, getWidgetFactory(), isDisplayedInWizard());

    superTypes = new CompositionMultipleSemanticField(getReferencesGroup(),
      Messages.getString("AbstractCapabilitySection_SuperType_Label"), getWidgetFactory(), new AbstractCapability_SuperController()); //$NON-NLS-1$
    superTypes.setDisplayedInWizard(isDisplayedInWizard());

    realizedFunctionsField = new MultipleSemanticField(getReferencesGroup(),
        getInvolvedFunctionsLabel(), getWidgetFactory(), new AbstractCapability_InvolvedFunctionsController());
    realizedFunctionsField.setDisplayedInWizard(isDisplayedInWizard());

    realizedFunctionalChainsField = new MultipleSemanticField(getReferencesGroup(),
        getInvolvedFunctionalChainsLabel(), getWidgetFactory(), new AbstractCapability_InvolvedFunctionalChainsController());
    realizedFunctionalChainsField.setDisplayedInWizard(isDisplayedInWizard());
    
    availableInStatesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("AbstractCapabilitySection_AvailableInStates_Label"), getWidgetFactory(), new AbstractCapability_AvailableInStatesController()); //$NON-NLS-1$
    availableInStatesField.setDisplayedInWizard(isDisplayedInWizard());

    if (showRealizedCapabilitiesField) {
      realizedCapabilitiesField = new MultipleSemanticField(getReferencesGroup(),
          Messages.getString("AbstractCapabilitySection_RealizedCapabilities_Label"), getWidgetFactory(), new AbstractCapability_RealizedCapabilitiesController()); //$NON-NLS-1$
      realizedCapabilitiesField.setDisplayedInWizard(isDisplayedInWizard());
    }
  }

  /**
   * @return the involved functions field label
   */
  protected String getInvolvedFunctionsLabel() {
    return Messages.getString("AbstractCapabilitySection_RealizedFunctions_Label"); //$NON-NLS-1$
  }

  /**
   * @return the involved functional chains field label
   */
  protected String getInvolvedFunctionalChainsLabel() {
    return Messages.getString("AbstractCapabilitySection_RealizedFunctionalChains_Label"); //$NON-NLS-1$
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    prePostConditions.loadData(capellaElement);

    if (null != superTypes) {
      superTypes.loadData(capellaElement, InteractionPackage.eINSTANCE.getAbstractCapability_Super(),
          InteractionPackage.eINSTANCE.getAbstractCapability_SuperGeneralizations());
    }

    if (null != availableInStatesField) {
      availableInStatesField.loadData(capellaElement, InteractionPackage.eINSTANCE.getAbstractCapability_AvailableInStates());
    }

    if (null != realizedFunctionsField) {
      realizedFunctionsField.loadData(capellaElement, InteractionPackage.eINSTANCE.getAbstractCapability_OwnedAbstractFunctionAbstractCapabilityInvolvements());
    }

    if (null != realizedFunctionalChainsField) {
      realizedFunctionalChainsField.loadData(capellaElement, InteractionPackage.eINSTANCE.getAbstractCapability_OwnedFunctionalChainAbstractCapabilityInvolvements());
    }

    if (null != realizedCapabilitiesField) {
      realizedCapabilitiesField.loadData(capellaElement, InteractionPackage.eINSTANCE.getAbstractCapability_OwnedAbstractCapabilityRealizations());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(superTypes);
    fields.add(availableInStatesField);
    fields.add(realizedFunctionsField);
    fields.add(realizedFunctionalChainsField);
    if (null != realizedCapabilitiesField) {
      fields.add(realizedCapabilitiesField);
    }
    fields.addAll(prePostConditions.getFields());

    return fields;
  }
}
