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
package org.polarsys.capella.core.data.information.datatype.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.core.properties.controllers.GeneralizableElementController;
import org.polarsys.capella.core.data.core.properties.fields.VisibilityKindGroup;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.properties.Messages;
import org.polarsys.capella.core.data.information.datatype.properties.controllers.DataTypeController;
import org.polarsys.capella.core.data.information.datatype.properties.fields.DataTypeBooleanPropertiesCheckbox;
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

  public DataTypeSection(boolean showPatternField, boolean showInclusiveFields) {
    _showPatternField = showPatternField;
    _showInclusiveFields = showInclusiveFields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    if (_showPatternField) {
      _dataTypeComposite = new TextValueGroup(parent, Messages.getString("DataType.PatternLabel"), getWidgetFactory()); //$NON-NLS-1$
      _dataTypeComposite.setDisplayedInWizard(displayedInWizard);
    }

    _dataTypeBooleanPropertiesCheckbox = new DataTypeBooleanPropertiesCheckbox(getCheckGroup(), getWidgetFactory(), _showInclusiveFields);
    _dataTypeBooleanPropertiesCheckbox.setDisplayedInWizard(displayedInWizard);

    _superTypeWidget = new CompositionMultipleSemanticField(getReferencesGroup(), Messages.getString("DataType.SuperTypeLabel"), getWidgetFactory(), new GeneralizableElementController()); //$NON-NLS-1$
    _superTypeWidget.setDisplayedInWizard(displayedInWizard);

    _realizedInformationWidget = new CompositionMultipleSemanticField(getReferencesGroup(), Messages.getString("DataType.RealizedInformationLabel"), getWidgetFactory(), new DataTypeController()); //$NON-NLS-1$
    _realizedInformationWidget.setDisplayedInWizard(displayedInWizard);

    _visibilityKindGroup = new VisibilityKindGroup(parent, getWidgetFactory());
    _visibilityKindGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    if (null != _dataTypeComposite) {
      _dataTypeComposite.loadData(capellaElement, DatatypePackage.eINSTANCE.getDataType_Pattern());
    }
    _dataTypeBooleanPropertiesCheckbox.loadData(capellaElement);
    _superTypeWidget.loadData(capellaElement, CapellacorePackage.eINSTANCE.getGeneralizableElement_Super(), CapellacorePackage.eINSTANCE.getGeneralizableElement_OwnedGeneralizations());
    _realizedInformationWidget.loadData(capellaElement, DatatypePackage.eINSTANCE.getDataType_OwnedInformationRealizations());
    _visibilityKindGroup.loadData(capellaElement, DatatypePackage.eINSTANCE.getDataType_Visibility());
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
