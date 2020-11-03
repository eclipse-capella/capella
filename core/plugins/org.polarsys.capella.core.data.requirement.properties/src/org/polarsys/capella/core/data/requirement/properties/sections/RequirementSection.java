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
package org.polarsys.capella.core.data.requirement.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.requirement.properties.fields.RequirementBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.requirement.properties.fields.RequirementGroup;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 * The Requirement section.
 */
public abstract class RequirementSection extends NamedElementSection {

  private RequirementBooleanPropertiesCheckbox propertiesCheckbox;
  private RequirementGroup requirementGroup;

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    Group checkGroup = getWidgetFactory().createGroup(parent, ""); //$NON-NLS-1$
    checkGroup.setLayout(new GridLayout(5, true));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    checkGroup.setLayoutData(gd);

    propertiesCheckbox = new RequirementBooleanPropertiesCheckbox(checkGroup, getWidgetFactory());
    propertiesCheckbox.setDisplayedInWizard(displayedInWizard);

    requirementGroup = new RequirementGroup(parent, getWidgetFactory());
    requirementGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    propertiesCheckbox.loadData(capellaElement);
    requirementGroup.loadData(capellaElement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(propertiesCheckbox);
    fields.add(requirementGroup);

    return fields;
  }
}
