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
package org.polarsys.capella.core.data.information.properties.fields;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
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
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;

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
   * @param parent
   * @param widgetFactory
   */
  public PropertyGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(widgetFactory);

    Group group = widgetFactory.createGroup(parent, ""); //$NON-NLS-1$
    group.setLayout(new GridLayout(6, false));
    group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    // Role
    widgetFactory.createCLabel(group, Messages.getString("Property.RoleLabel")); //$NON-NLS-1$
    roleTextField = widgetFactory.createText(group, ""); //$NON-NLS-1$
    roleTextField.addFocusListener(this);
    roleTextField.addKeyListener(this);
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 4;
    roleTextField.setLayoutData(gd);

    Group checkGroup = widgetFactory.createGroup(group, ""); //$NON-NLS-1$
    checkGroup.setLayout(new GridLayout(5, true));
    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 5;
    checkGroup.setLayoutData(gd);
    propertyBooleanPropertiesCheckbox = new PropertyBooleanPropertiesCheckbox(checkGroup, widgetFactory, true, true, false);
    multiplicityElementBooleanPropertiesCheckbox = new MultiplicityElementBooleanPropertiesCheckbox(checkGroup, widgetFactory, true, true, true, true);
    featureBooleanPropertiesCheckbox = new FeatureBooleanPropertiesCheckbox(checkGroup, widgetFactory, true, true);

    aggregationKindGroup = new AggregationKindGroup(group, widgetFactory);

    createNavigableCheckbox(checkGroup);

    abstractTypeField = new SimpleSemanticField(group, Messages.getString("TypedElement.TypeLabel"), widgetFactory, new TypedElementController()); //$NON-NLS-1$

    minCardField = new SimpleEditableSemanticField(group,
        Messages.getString("MultiplicityElement.MinCardLabel"), widgetFactory, "", new MultiplicityElementCardController(), true, 0); //$NON-NLS-1$ //$NON-NLS-2$
    minCardField.setDisplayedInWizard(true);

    maxCardField = new SimpleEditableSemanticField(group,
        Messages.getString("MultiplicityElement.MaxCardLabel"), widgetFactory, "", new MultiplicityElementCardController(), true, 1); //$NON-NLS-1$ //$NON-NLS-2$
    maxCardField.setDisplayedInWizard(true);

    minValueField = new SimpleEditableSemanticField(group, Messages.getString("MultiplicityElement.MinValueLabel"), widgetFactory, "", new MultiplicityElementValueController()); //$NON-NLS-1$ //$NON-NLS-2$
    maxValueField = new SimpleEditableSemanticField(group, Messages.getString("MultiplicityElement.MaxValueLabel"), widgetFactory, "", new MultiplicityElementValueController()); //$NON-NLS-1$ //$NON-NLS-2$
    defaultValueField = new SimpleEditableSemanticField(group,
        Messages.getString("MultiplicityElement.DefaultValueLabel"), widgetFactory, "", new MultiplicityElementValueController()); //$NON-NLS-1$ //$NON-NLS-2$
    nullValueField = new SimpleEditableSemanticField(group, Messages.getString("MultiplicityElement.NullValueLabel"), widgetFactory, "", new MultiplicityElementValueController()); //$NON-NLS-1$ //$NON-NLS-2$

    visibilityKindGroup = new VisibilityKindGroup(group, widgetFactory);
  }

  /**
   * @param checkGroup
   * @param widgetFactory
   */
  protected void createNavigableCheckbox(Group checkGroup) {
    navigableCheckbox = new NavigableCheckbox(checkGroup, widgetFactory);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement) {
    loadData(semanticElement, null);

    if (null != semanticElement) {
      setTextValue(roleTextField, semanticElement, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name());
    }

    featureBooleanPropertiesCheckbox.loadData(semanticElement);
    multiplicityElementBooleanPropertiesCheckbox.loadData(semanticElement);
    propertyBooleanPropertiesCheckbox.loadData(semanticElement);
    navigableCheckbox.loadData(semanticElement);

    visibilityKindGroup.loadData(semanticElement, CapellacorePackage.eINSTANCE.getFeature_Visibility());
    aggregationKindGroup.loadData(semanticElement, InformationPackage.eINSTANCE.getProperty_AggregationKind());

    abstractTypeField.loadData(semanticElement, ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType());
    minCardField.loadData(semanticElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMinCard());
    maxCardField.loadData(semanticElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMaxCard());
    minValueField.loadData(semanticElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMinValue());
    maxValueField.loadData(semanticElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMaxValue());
    defaultValueField.loadData(semanticElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedDefaultValue());
    nullValueField.loadData(semanticElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedNullValue());

    evaluateButtonStatus((Property) semanticElement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    if (null != roleTextField && !roleTextField.isDisposed()) {
      roleTextField.setEnabled(enabled);
    }

    if (null != featureBooleanPropertiesCheckbox) {
      featureBooleanPropertiesCheckbox.setEnabled(enabled);
    }
    if (null != multiplicityElementBooleanPropertiesCheckbox) {
      multiplicityElementBooleanPropertiesCheckbox.setEnabled(enabled);
    }
    if (null != propertyBooleanPropertiesCheckbox) {
      propertyBooleanPropertiesCheckbox.setEnabled(enabled);
    }
    if (null != navigableCheckbox) {
      navigableCheckbox.setEnabled(enabled);
    }

    if (null != visibilityKindGroup) {
      visibilityKindGroup.setEnabled(enabled);
    }
    if (null != aggregationKindGroup) {
      aggregationKindGroup.setEnabled(enabled);
    }

    if (null != abstractTypeField) {
      abstractTypeField.setEnabled(enabled);
    }
    if (null != minCardField) {
      minCardField.setEnabled(enabled);
    }
    if (null != maxCardField) {
      maxCardField.setEnabled(enabled);
    }
    if (null != minValueField) {
      minValueField.setEnabled(enabled);
    }
    if (null != maxValueField) {
      maxValueField.setEnabled(enabled);
    }
    if (null != defaultValueField) {
      defaultValueField.setEnabled(enabled);
    }
    if (null != nullValueField) {
      nullValueField.setEnabled(enabled);
    }
  }

  /**
   * @param property
   */
  private void evaluateButtonStatus(Property property) {
    if (property != null) {
      AbstractType type = property.getAbstractType();
      if (type != null) {
        if ((type instanceof DataType) || ((type instanceof Class) && ((Class) type).isIsPrimitive()) || ((type instanceof Collection) && ((Collection) type).isIsPrimitive())) {
          aggregationKindGroup.setEnabled(false);
        } else {
          aggregationKindGroup.setEnabled(true);
        }
      } else {
        aggregationKindGroup.setEnabled(true);
      }
    }
  }

  /**
   * @param textField
   *          text field to be filled
   */
  @Override
  protected void fillTextField(Text textField) {
    if (textField.equals(roleTextField)) {
      setDataValue(semanticElement, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(), roleTextField.getText());
    }
  }
}
