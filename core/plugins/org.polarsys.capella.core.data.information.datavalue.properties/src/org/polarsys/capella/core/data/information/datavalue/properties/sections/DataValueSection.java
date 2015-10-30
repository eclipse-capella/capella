/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.datavalue.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.core.properties.sections.TypedElementSection;
import org.polarsys.capella.core.data.information.datavalue.properties.fields.DataValueBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 * The DataValue section.
 */
public abstract class DataValueSection extends TypedElementSection {

  private DataValueBooleanPropertiesCheckbox _propertiesCheckbox;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    createControls(parent, aTabbedPropertySheetPage, true);
  }

  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage, boolean showIsAbstract) {
    super.createControls(parent, aTabbedPropertySheetPage);

    if (showIsAbstract) {
      boolean displayedInWizard = isDisplayedInWizard();
      
      Group checkGroup = getWidgetFactory().createGroup(_rootParentComposite, ""); //$NON-NLS-1$
      checkGroup.setLayout(new GridLayout(5, true));
      GridData gd = new GridData(GridData.FILL_HORIZONTAL);
      gd.horizontalSpan = 2;
      checkGroup.setLayoutData(gd);
      checkGroup.moveAbove(getReferencesGroup());
      
      _propertiesCheckbox = new DataValueBooleanPropertiesCheckbox(checkGroup, getWidgetFactory(), showIsAbstract);
      _propertiesCheckbox.setDisplayedInWizard(displayedInWizard);
    }
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement) {
    super.loadData(capellaElement);

    if (null != _propertiesCheckbox) {
      _propertiesCheckbox.loadData(capellaElement);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    if (null != _propertiesCheckbox) {
      fields.add(_propertiesCheckbox);
    }

    return fields;
  }
}
