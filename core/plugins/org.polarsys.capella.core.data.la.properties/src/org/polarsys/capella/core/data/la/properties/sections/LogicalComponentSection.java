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
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.properties.controllers.Component_RealizedComponentsController;
import org.polarsys.capella.core.data.cs.properties.sections.ComponentSection;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.properties.Messages;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The LogicalComponent section.
 */
public class LogicalComponentSection extends ComponentSection {

  private MultipleSemanticField systemComponentRealizations;
  
  /**
   * Default constructor.
   */
  public LogicalComponentSection() {
    super(true, true, true, true, true, true, true);
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == LaPackage.eINSTANCE.getLogicalComponent()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    systemComponentRealizations = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("LogicalComponentSection_RealizedComponents_Label"), getWidgetFactory(), //$NON-NLS-1$
        new Component_RealizedComponentsController());
    systemComponentRealizations.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    Component component = (Component) capellaElement;
    // if capellaElement is a component but not an actor, disable super
    if (null != systemComponentRealizations) {
      systemComponentRealizations.setEnabled(component.isActor() || BlockArchitectureExt.isRootComponent(component));
      systemComponentRealizations.loadData(capellaElement, CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();

    fields.addAll(super.getSemanticFields());
    fields.add(systemComponentRealizations);

    return fields;
  }
}
