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
package org.polarsys.capella.core.data.information.properties.fields;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.core.properties.controllers.TypedElementController;
import org.polarsys.capella.core.data.core.properties.fields.FeatureBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.core.properties.fields.VisibilityKindGroup;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.data.information.properties.controllers.MultiplicityElementCardController;
import org.polarsys.capella.core.data.information.properties.controllers.MultiplicityElementValueController;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class PropertyGroup extends AbstractSemanticField {

  private Text roleTextField;
  private FeatureBooleanPropertiesCheckbox featureBooleanPropertiesCheckbox;
  private MultiplicityElementBooleanPropertiesCheckbox multiplicityElementBooleanPropertiesCheckbox;
  private PropertyBooleanPropertiesCheckbox propertyBooleanPropertiesCheckbox;
  protected NavigableCheckbox navigableCheckbox;

  private VisibilityKindGroup visibilityKindGroup;
  private AggregationKindGroup aggregationKindGroup;

  private SimpleSemanticField abstractTypeField;
  private SimpleEditableSemanticField minCardField;
  private SimpleEditableSemanticField maxCardField;
  private SimpleEditableSemanticField minValueField;
  private SimpleEditableSemanticField maxValueField;
  private SimpleEditableSemanticField defaultValueField;
  private SimpleEditableSemanticField nullValueField;

  /**
   * @param parent_p
   * @param widgetFactory_p
   */
  public PropertyGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super(widgetFactory_p);

    Group group = _widgetFactory.createGroup(parent_p, ""); //$NON-NLS-1$
    group.setLayout(new GridLayout(5, false));
    group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Role
    _widgetFactory.createCLabel(group, Messages.getString("Property.RoleLabel")); //$NON-NLS-1$
    roleTextField = _widgetFactory.createText(group, ""); //$NON-NLS-1$
    roleTextField.addFocusListener(this);
    roleTextField.addKeyListener(this);
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 4;
    roleTextField.setLayoutData(gd);

    Group checkGroup = _widgetFactory.createGroup(group, ""); //$NON-NLS-1$
    checkGroup.setLayout(new GridLayout(5, true));
    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 5;
    checkGroup.setLayoutData(gd);
    propertyBooleanPropertiesCheckbox = new PropertyBooleanPropertiesCheckbox(checkGroup, _widgetFactory, true, true, false);
    multiplicityElementBooleanPropertiesCheckbox = new MultiplicityElementBooleanPropertiesCheckbox(checkGroup, _widgetFactory, true, true, true, true);
    featureBooleanPropertiesCheckbox = new FeatureBooleanPropertiesCheckbox(checkGroup, _widgetFactory, true, true);

    aggregationKindGroup = new AggregationKindGroup(group, _widgetFactory);

    createNavigableCheckbox(checkGroup);

    abstractTypeField = new SimpleSemanticField(group, Messages.getString("TypedElement.TypeLabel"), _widgetFactory, new TypedElementController()); //$NON-NLS-1$

    minCardField =
        new SimpleEditableSemanticField(
            group,
            Messages.getString("MultiplicityElement.MinCardLabel"), _widgetFactory, "", new MultiplicityElementCardController()); //$NON-NLS-1$ //$NON-NLS-2$
    maxCardField =
        new SimpleEditableSemanticField(
            group,
            Messages.getString("MultiplicityElement.MaxCardLabel"), _widgetFactory, "", new MultiplicityElementCardController()); //$NON-NLS-1$ //$NON-NLS-2$
    minValueField =
        new SimpleEditableSemanticField(
            group,
            Messages.getString("MultiplicityElement.MinValueLabel"), _widgetFactory, "", new MultiplicityElementValueController()); //$NON-NLS-1$ //$NON-NLS-2$
    maxValueField =
        new SimpleEditableSemanticField(
            group,
            Messages.getString("MultiplicityElement.MaxValueLabel"), _widgetFactory, "", new MultiplicityElementValueController()); //$NON-NLS-1$ //$NON-NLS-2$
    defaultValueField =
        new SimpleEditableSemanticField(
            group,
            Messages.getString("MultiplicityElement.DefaultValueLabel"), _widgetFactory, "", new MultiplicityElementValueController()); //$NON-NLS-1$ //$NON-NLS-2$
    nullValueField =
        new SimpleEditableSemanticField(
            group,
            Messages.getString("MultiplicityElement.NullValueLabel"), _widgetFactory, "", new MultiplicityElementValueController()); //$NON-NLS-1$ //$NON-NLS-2$

    visibilityKindGroup = new VisibilityKindGroup(group, _widgetFactory);
  }

  /**
   * @param checkGroup_p
   * @param widgetFactory_p
   */
  protected void createNavigableCheckbox(Group checkGroup_p) {
    navigableCheckbox = new NavigableCheckbox(checkGroup_p, _widgetFactory);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement_p) {
    loadData(semanticElement_p, null);

    if (null != _semanticElement) {
      setTextValue(roleTextField, _semanticElement, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name());
    }

    featureBooleanPropertiesCheckbox.loadData(semanticElement_p);
    multiplicityElementBooleanPropertiesCheckbox.loadData(semanticElement_p);
    propertyBooleanPropertiesCheckbox.loadData(semanticElement_p);
    navigableCheckbox.loadData(semanticElement_p);

    visibilityKindGroup.loadData(semanticElement_p, CapellacorePackage.eINSTANCE.getFeature_Visibility());
    aggregationKindGroup.loadData(semanticElement_p, InformationPackage.eINSTANCE.getProperty_AggregationKind());

    abstractTypeField.loadData(semanticElement_p, ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType());
    minCardField.loadData(semanticElement_p, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMinCard());
    maxCardField.loadData(semanticElement_p, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMaxCard());
    minValueField.loadData(semanticElement_p, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMinValue());
    maxValueField.loadData(semanticElement_p, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMaxValue());
    defaultValueField.loadData(semanticElement_p, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedDefaultValue());
    nullValueField.loadData(semanticElement_p, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedNullValue());

    evaluateButtonStatus((Property) semanticElement_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    if (null != roleTextField && !roleTextField.isDisposed()) {
      roleTextField.setEnabled(enabled_p);
    }

    if (null != featureBooleanPropertiesCheckbox) {
      featureBooleanPropertiesCheckbox.setEnabled(enabled_p);
    }
    if (null != multiplicityElementBooleanPropertiesCheckbox) {
      multiplicityElementBooleanPropertiesCheckbox.setEnabled(enabled_p);
    }
    if (null != propertyBooleanPropertiesCheckbox) {
      propertyBooleanPropertiesCheckbox.setEnabled(enabled_p);
    }
    if (null != navigableCheckbox) {
      navigableCheckbox.setEnabled(enabled_p);
    }

    if (null != visibilityKindGroup) {
      visibilityKindGroup.setEnabled(enabled_p);
    }
    if (null != aggregationKindGroup) {
      aggregationKindGroup.setEnabled(enabled_p);
    }

    if (null != abstractTypeField) {
      abstractTypeField.setEnabled(enabled_p);
    }
    if (null != minCardField) {
      minCardField.setEnabled(enabled_p);
    }
    if (null != maxCardField) {
      maxCardField.setEnabled(enabled_p);
    }
    if (null != minValueField) {
      minValueField.setEnabled(enabled_p);
    }
    if (null != maxValueField) {
      maxValueField.setEnabled(enabled_p);
    }
    if (null != defaultValueField) {
      defaultValueField.setEnabled(enabled_p);
    }
    if (null != nullValueField) {
      nullValueField.setEnabled(enabled_p);
    }
  }

  /**
   * @param property_p
   */
  private void evaluateButtonStatus(Property property_p) {
    if (property_p != null) {
      AbstractType type = property_p.getAbstractType();
      if (type != null) {
        if ((type instanceof DataType) ||
            ((type instanceof Class) && ((Class) type).isIsPrimitive()) ||
            ((type instanceof Collection) && ((Collection) type).isIsPrimitive()))
        {
          aggregationKindGroup.setEnabled(false);
        }
        else {
          aggregationKindGroup.setEnabled(true);
        }
      }
      else {
        aggregationKindGroup.setEnabled(true);
      }
    }
  }

  /**
   * @param textField_p text field to be filled
   */
  @Override
  protected void fillTextField(Text textField_p) {
    if (textField_p.equals(roleTextField)) {
      setDataValue(_semanticElement, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(), roleTextField.getText());
    }
  }
}
