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
package org.polarsys.capella.core.data.interaction.properties.sections;

import java.util.ArrayList;
import java.util.List;

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
import org.polarsys.capella.core.data.interaction.properties.fields.AbstractCapabilityValueGroup;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.CompositionMultipleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The AbstractCapability section.
 */
public abstract class AbstractCapabilitySection extends NamedElementSection {

  private AbstractCapabilityValueGroup _abstractCapabilityValueGroup;
  private CompositionMultipleSemanticField _superTypes;
  private MultipleSemanticField _realizedFunctionsField;
  private MultipleSemanticField _realizedFunctionalChainsField;
  private MultipleSemanticField _availableInStatesField;
  private MultipleSemanticField _realizedCapabilitiesField;

  private boolean _showRealizedCapabilitiesField;

  /**
   * Constructor
   */
  public AbstractCapabilitySection() {
    this(true);
  }

  /**
   * Constructor
   */
  public AbstractCapabilitySection(boolean showRealizedCapabilitiesField_p) {
    _showRealizedCapabilitiesField = showRealizedCapabilitiesField_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _abstractCapabilityValueGroup = new AbstractCapabilityValueGroup(_rootParentComposite, getWidgetFactory());
    _abstractCapabilityValueGroup.setDisplayedInWizard(displayedInWizard);

    _superTypes = new CompositionMultipleSemanticField(getReferencesGroup(),
      Messages.getString("AbstractCapabilitySection_SuperType_Label"), getWidgetFactory(), new AbstractCapability_SuperController()); //$NON-NLS-1$
    _superTypes.setDisplayedInWizard(displayedInWizard);

    _realizedFunctionsField = new MultipleSemanticField(getReferencesGroup(),
        getInvolvedFunctionsLabel(), getWidgetFactory(), new AbstractCapability_InvolvedFunctionsController());
    _realizedFunctionsField.setDisplayedInWizard(displayedInWizard);

    _realizedFunctionalChainsField = new MultipleSemanticField(getReferencesGroup(),
        getInvolvedFunctionalChainsLabel(), getWidgetFactory(), new AbstractCapability_InvolvedFunctionalChainsController());
    _realizedFunctionalChainsField.setDisplayedInWizard(displayedInWizard);
    
    _availableInStatesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("AbstractCapabilitySection_AvailableInStates_Label"), getWidgetFactory(), new AbstractCapability_AvailableInStatesController()); //$NON-NLS-1$
    _availableInStatesField.setDisplayedInWizard(displayedInWizard);

    if (_showRealizedCapabilitiesField) {
      _realizedCapabilitiesField = new MultipleSemanticField(getReferencesGroup(),
          Messages.getString("AbstractCapabilitySection_RealizedCapabilities_Label"), getWidgetFactory(), new AbstractCapability_RealizedCapabilitiesController()); //$NON-NLS-1$
      _realizedCapabilitiesField.setDisplayedInWizard(displayedInWizard);
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
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    _abstractCapabilityValueGroup.loadData(capellaElement_p);

    if (null != _superTypes) {
      _superTypes.loadData(capellaElement_p, InteractionPackage.eINSTANCE.getAbstractCapability_Super(),
          InteractionPackage.eINSTANCE.getAbstractCapability_SuperGeneralizations());
    }

    if (null != _availableInStatesField) {
    _availableInStatesField.loadData(capellaElement_p, InteractionPackage.eINSTANCE.getAbstractCapability_AvailableInStates());
    }

    if (null != _realizedFunctionsField) {
      _realizedFunctionsField.loadData(capellaElement_p, InteractionPackage.eINSTANCE.getAbstractCapability_OwnedAbstractFunctionAbstractCapabilityInvolvements());
    }

    if (null != _realizedFunctionalChainsField) {
      _realizedFunctionalChainsField.loadData(capellaElement_p, InteractionPackage.eINSTANCE.getAbstractCapability_OwnedFunctionalChainAbstractCapabilityInvolvements());
    }

    if (null != _realizedCapabilitiesField) {
      _realizedCapabilitiesField.loadData(capellaElement_p, InteractionPackage.eINSTANCE.getAbstractCapability_OwnedAbstractCapabilityRealizations());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_abstractCapabilityValueGroup);
    fields.add(_superTypes);
    fields.add(_availableInStatesField);
    fields.add(_realizedFunctionsField);
    fields.add(_realizedFunctionalChainsField);
    if (null != _realizedCapabilitiesField) {
      fields.add(_realizedCapabilitiesField);
    }

    return fields;
  }
}
