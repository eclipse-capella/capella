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
package org.polarsys.capella.core.data.fa.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.properties.controllers.FunctionalChainRealizationsController;
import org.polarsys.capella.core.data.fa.properties.controllers.FunctionalChain_AvailableInStatesController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ConstraintReferenceGroup;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

import com.google.common.collect.ImmutableMap;

/**
 * The FunctionalChain section.
 */
public class FunctionalChainSection extends NamedElementSection {

  private boolean showFunctionalChainRealizations;
  private MultipleSemanticField availableInStatesField;
  private MultipleSemanticField realizedFunctionalChainsField;
  private ConstraintReferenceGroup prePostGroup;

  /**
   * Default constructor.
   */
  public FunctionalChainSection() {
    this(true);
  }

  /**
   * Constructor.
   * 
   * @param showFunctionalChainRealizations
   */
  public FunctionalChainSection(boolean showFunctionalChainRealizations) {
    this.showFunctionalChainRealizations = showFunctionalChainRealizations;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    prePostGroup = new ConstraintReferenceGroup(ImmutableMap.of(Messages.FunctionalChainSection_Precondition_Label,
        FaPackage.Literals.FUNCTIONAL_CHAIN__PRE_CONDITION, Messages.FunctionalChainSection_Postcondition_Label,
        FaPackage.Literals.FUNCTIONAL_CHAIN__POST_CONDITION));

    prePostGroup.createControls(parent, getWidgetFactory(), isDisplayedInWizard());

    availableInStatesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.FunctionalChainSection_AvailableInStates_Label, getWidgetFactory(),
        new FunctionalChain_AvailableInStatesController());
    availableInStatesField.setDisplayedInWizard(displayedInWizard);

    if (showFunctionalChainRealizations) {
      realizedFunctionalChainsField = new MultipleSemanticField(getReferencesGroup(),
          Messages.FunctionalChainSection_FunctionalChainRealizations_Label, getWidgetFactory(),
          new FunctionalChainRealizationsController());
      realizedFunctionalChainsField.setDisplayedInWizard(displayedInWizard);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    availableInStatesField.loadData(capellaElement, FaPackage.eINSTANCE.getFunctionalChain_AvailableInStates());
    if (null != realizedFunctionalChainsField) {
      realizedFunctionalChainsField.loadData(capellaElement,
          FaPackage.eINSTANCE.getFunctionalChain_OwnedFunctionalChainRealizations());
    }
    prePostGroup.loadData(capellaElement);
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == FaPackage.eINSTANCE.getFunctionalChain()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(availableInStatesField);
    fields.add(realizedFunctionalChainsField);
    fields.addAll(prePostGroup.getFields());

    return fields;
  }
}
