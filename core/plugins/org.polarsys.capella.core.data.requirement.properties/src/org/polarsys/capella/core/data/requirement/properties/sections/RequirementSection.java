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
package org.polarsys.capella.core.data.requirement.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.requirement.properties.fields.RequirementBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.requirement.properties.fields.RequirementGroup;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 * The Requirement section.
 */
public abstract class RequirementSection extends NamedElementSection {

  private RequirementBooleanPropertiesCheckbox _propertiesCheckbox;
  private RequirementGroup _requirementGroup;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    Group checkGroup = getWidgetFactory().createGroup(_rootParentComposite, ""); //$NON-NLS-1$
    checkGroup.setLayout(new GridLayout(5, true));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    checkGroup.setLayoutData(gd);

    _propertiesCheckbox = new RequirementBooleanPropertiesCheckbox(checkGroup, getWidgetFactory());
    _propertiesCheckbox.setDisplayedInWizard(displayedInWizard);

    _requirementGroup = new RequirementGroup(_rootParentComposite, getWidgetFactory());
    _requirementGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    _propertiesCheckbox.loadData(capellaElement_p);
    _requirementGroup.loadData(capellaElement_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_propertiesCheckbox);
    fields.add(_requirementGroup);

    return fields;
  }
}
