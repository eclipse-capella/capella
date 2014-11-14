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
package org.polarsys.capella.core.data.information.datatype.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.core.properties.controllers.GeneralizableElementController;
import org.polarsys.capella.core.data.core.properties.fields.VisibilityKindGroup;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.properties.Messages;
import org.polarsys.capella.core.data.information.datatype.properties.controllers.DataTypeController;
import org.polarsys.capella.core.data.information.datatype.properties.fields.DataTypeBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.CompositionMultipleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.TextValueGroup;

/**
 * The DataType section.
 */
public abstract class DataTypeSection extends NamedElementSection {

  private boolean _showPatternField;
  private boolean _showInclusiveFields;

  protected TextValueGroup _dataTypeComposite;
  private DataTypeBooleanPropertiesCheckbox _dataTypeBooleanPropertiesCheckbox;
  private CompositionMultipleSemanticField _superTypeWidget;
  private VisibilityKindGroup _visibilityKindGroup;
  private CompositionMultipleSemanticField _realizedInformationWidget;

  public DataTypeSection() {
    this(true, true);
  }

  public DataTypeSection(boolean showPatternField_p, boolean showInclusiveFields_p) {
    _showPatternField = showPatternField_p;
    _showInclusiveFields = showInclusiveFields_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    if (_showPatternField) {
      _dataTypeComposite = new TextValueGroup(_rootParentComposite, Messages.getString("DataType.PatternLabel"), getWidgetFactory()); //$NON-NLS-1$
      _dataTypeComposite.setDisplayedInWizard(displayedInWizard);
    }

    _dataTypeBooleanPropertiesCheckbox = new DataTypeBooleanPropertiesCheckbox(getCheckGroup(), getWidgetFactory(), _showInclusiveFields);
    _dataTypeBooleanPropertiesCheckbox.setDisplayedInWizard(displayedInWizard);

    _superTypeWidget = new CompositionMultipleSemanticField(getReferencesGroup(), Messages.getString("DataType.SuperTypeLabel"), getWidgetFactory(), new GeneralizableElementController()); //$NON-NLS-1$
    _superTypeWidget.setDisplayedInWizard(displayedInWizard);

    _realizedInformationWidget = new CompositionMultipleSemanticField(getReferencesGroup(), Messages.getString("DataType.RealizedInformationLabel"), getWidgetFactory(), new DataTypeController()); //$NON-NLS-1$
    _realizedInformationWidget.setDisplayedInWizard(displayedInWizard);

    _visibilityKindGroup = new VisibilityKindGroup(_rootParentComposite, getWidgetFactory());
    _visibilityKindGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    if (null != _dataTypeComposite) {
      _dataTypeComposite.loadData(capellaElement_p, DatatypePackage.eINSTANCE.getDataType_Pattern());
    }
    _dataTypeBooleanPropertiesCheckbox.loadData(capellaElement_p);
    _superTypeWidget.loadData(capellaElement_p, CapellacorePackage.eINSTANCE.getGeneralizableElement_Super(), CapellacorePackage.eINSTANCE.getGeneralizableElement_OwnedGeneralizations());
    _realizedInformationWidget.loadData(capellaElement_p, DatatypePackage.eINSTANCE.getDataType_OwnedInformationRealizations());
    _visibilityKindGroup.loadData(capellaElement_p, DatatypePackage.eINSTANCE.getDataType_Visibility());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_dataTypeBooleanPropertiesCheckbox);
    fields.add(_dataTypeComposite);
    fields.add(_realizedInformationWidget);
    fields.add(_superTypeWidget);
    fields.add(_visibilityKindGroup);

    return fields;
  }
}
