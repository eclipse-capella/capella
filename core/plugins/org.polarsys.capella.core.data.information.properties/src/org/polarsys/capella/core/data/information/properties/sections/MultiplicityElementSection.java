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
package org.polarsys.capella.core.data.information.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.core.properties.sections.TypedElementSection;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.data.information.properties.controllers.MultiplicityElementCardController;
import org.polarsys.capella.core.data.information.properties.fields.MultiplicityElementBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;

/**
 * The MultiplicityElement section.
 */
public abstract class MultiplicityElementSection extends TypedElementSection {

  private boolean _showIsOrdered;
  private boolean _showIsUnique;
  private boolean _showIsMinInclusive;
  private boolean _showIsMaxInclusive;
  private MultiplicityElementBooleanPropertiesCheckbox _multiplicityElementBooleanPropertiesCheckbox;

  private SimpleEditableSemanticField _minCardField;
  private SimpleEditableSemanticField _maxCardField;

  public MultiplicityElementSection(boolean showIsOrdered_p, boolean showIsUnique_p, boolean showIsMinInclusive_p, boolean showIsMaxInclusive_p) {
    _showIsOrdered = showIsOrdered_p;
    _showIsUnique = showIsUnique_p;
    _showIsMinInclusive = showIsMinInclusive_p;
    _showIsMaxInclusive = showIsMaxInclusive_p;
  }

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    if (_showIsOrdered || _showIsUnique || _showIsMinInclusive || _showIsMaxInclusive) {
      _multiplicityElementBooleanPropertiesCheckbox = new MultiplicityElementBooleanPropertiesCheckbox(getCheckGroup(),
          getWidgetFactory(), _showIsOrdered, _showIsUnique, _showIsMinInclusive, _showIsMaxInclusive);
      _multiplicityElementBooleanPropertiesCheckbox.setDisplayedInWizard(displayedInWizard);
    }

    _minCardField = new SimpleEditableSemanticField(getReferencesGroup(),
        Messages.getString("MultiplicityElement.MinCardLabel"), getWidgetFactory(), "", new MultiplicityElementCardController(), true, 0); //$NON-NLS-1$ //$NON-NLS-2$
    _minCardField.setDisplayedInWizard(displayedInWizard);

    _maxCardField = new SimpleEditableSemanticField(getReferencesGroup(),
        Messages.getString("MultiplicityElement.MaxCardLabel"), getWidgetFactory(), "", new MultiplicityElementCardController(), true, 1); //$NON-NLS-1$ //$NON-NLS-2$
    _maxCardField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    if (null != _multiplicityElementBooleanPropertiesCheckbox) {
      _multiplicityElementBooleanPropertiesCheckbox.loadData(capellaElement_p);
    }
    _minCardField.loadData(capellaElement_p, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMinCard());
    _maxCardField.loadData(capellaElement_p, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMaxCard());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_multiplicityElementBooleanPropertiesCheckbox);
    fields.add(_maxCardField);
    fields.add(_minCardField);

    return fields;
  }
}
