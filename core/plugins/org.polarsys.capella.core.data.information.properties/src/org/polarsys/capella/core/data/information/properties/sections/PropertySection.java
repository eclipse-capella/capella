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
package org.polarsys.capella.core.data.information.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.core.properties.fields.FeatureBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.core.properties.fields.VisibilityKindGroup;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.data.information.properties.controllers.MultiplicityElementLengthController;
import org.polarsys.capella.core.data.information.properties.controllers.MultiplicityElementValueController;
import org.polarsys.capella.core.data.information.properties.fields.AggregationKindGroup;
import org.polarsys.capella.core.data.information.properties.fields.PropertyBooleanPropertiesCheckbox;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;

/**
 * The Property section.
 */
public class PropertySection extends MultiplicityElementSection {

  private FeatureBooleanPropertiesCheckbox featureBooleanPropertiesCheckbox;
  private PropertyBooleanPropertiesCheckbox propertyBooleanPropertiesCheckbox;
  private VisibilityKindGroup visibilityKindGroup;
  private AggregationKindGroup aggregationKindGroup;

  private SimpleEditableSemanticField minValueField;
  private SimpleEditableSemanticField maxValueField;
  private SimpleEditableSemanticField defaultValueField;
  private SimpleEditableSemanticField nullValueField;
  private SimpleEditableSemanticField minLengthField;
  private SimpleEditableSemanticField maxLengthField;

  /**
   * Default constructor.
   */
  public PropertySection() {
    super(true, true, true, true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    featureBooleanPropertiesCheckbox = new FeatureBooleanPropertiesCheckbox(getCheckGroup(), getWidgetFactory());
    featureBooleanPropertiesCheckbox.setDisplayedInWizard(displayedInWizard);

    propertyBooleanPropertiesCheckbox = new PropertyBooleanPropertiesCheckbox(getCheckGroup(), getWidgetFactory());
    propertyBooleanPropertiesCheckbox.setDisplayedInWizard(displayedInWizard);

    aggregationKindGroup = new AggregationKindGroup(parent, getWidgetFactory());
    aggregationKindGroup.setDisplayedInWizard(displayedInWizard);

    minValueField = new SimpleEditableSemanticField(getReferencesGroup(),
      Messages.getString("MultiplicityElement.MinValueLabel"), getWidgetFactory(), "", new MultiplicityElementValueController()); //$NON-NLS-1$ //$NON-NLS-2$
    minValueField.setDisplayedInWizard(displayedInWizard);

    maxValueField = new SimpleEditableSemanticField(getReferencesGroup(),
      Messages.getString("MultiplicityElement.MaxValueLabel"), getWidgetFactory(), "", new MultiplicityElementValueController()); //$NON-NLS-1$ //$NON-NLS-2$
    maxValueField.setDisplayedInWizard(displayedInWizard);

    defaultValueField = new SimpleEditableSemanticField(getReferencesGroup(),
      Messages.getString("MultiplicityElement.DefaultValueLabel"), getWidgetFactory(), "", new MultiplicityElementValueController()); //$NON-NLS-1$ //$NON-NLS-2$
    defaultValueField.setDisplayedInWizard(displayedInWizard);

    nullValueField = new SimpleEditableSemanticField(getReferencesGroup(),
      Messages.getString("MultiplicityElement.NullValueLabel"), getWidgetFactory(), "", new MultiplicityElementValueController()); //$NON-NLS-1$ //$NON-NLS-2$
    nullValueField.setDisplayedInWizard(displayedInWizard);

    minLengthField = new SimpleEditableSemanticField(getReferencesGroup(),
      Messages.getString("MultiplicityElement.MinLengthLabel"), getWidgetFactory(), "", new MultiplicityElementLengthController(), true, 2); //$NON-NLS-1$ //$NON-NLS-2$
    minLengthField.setDisplayedInWizard(displayedInWizard);

    maxLengthField = new SimpleEditableSemanticField(getReferencesGroup(),
      Messages.getString("MultiplicityElement.MaxLengthLabel"), getWidgetFactory(), "", new MultiplicityElementLengthController(), true, 3); //$NON-NLS-1$ //$NON-NLS-2$
    maxLengthField.setDisplayedInWizard(displayedInWizard);

    visibilityKindGroup = new VisibilityKindGroup(parent, getWidgetFactory());
    visibilityKindGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    featureBooleanPropertiesCheckbox.loadData(capellaElement);
    propertyBooleanPropertiesCheckbox.loadData(capellaElement);
    visibilityKindGroup.loadData(capellaElement, CapellacorePackage.eINSTANCE.getFeature_Visibility());
    aggregationKindGroup.loadData(capellaElement, InformationPackage.eINSTANCE.getProperty_AggregationKind());
    minValueField.loadData(capellaElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMinValue());
    maxValueField.loadData(capellaElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMaxValue());
    defaultValueField.loadData(capellaElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedDefaultValue());
    nullValueField.loadData(capellaElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedNullValue());
    minLengthField.loadData(capellaElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMinLength());
    maxLengthField.loadData(capellaElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMaxLength());

    evaluateButtonStatus((Property) capellaElement);
  }

  /**
   * @param property
   */
  protected void evaluateButtonStatus(Property property) {
    if (property != null) {
      AbstractType type = property.getAbstractType();
      if (type != null) {
        if (type instanceof DataType) {
          aggregationKindGroup.setEnabled(false);
        } else {
          aggregationKindGroup.setEnabled(true);
        }

        if (type instanceof StringType) {
          minLengthField.setEnabled(true);
          maxLengthField.setEnabled(true);
        } else {
          minLengthField.setEnabled(false);
          maxLengthField.setEnabled(false);
        }
      } else {
        aggregationKindGroup.setEnabled(true);
        minLengthField.setEnabled(false);
        maxLengthField.setEnabled(false);
      }
    }
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == InformationPackage.eINSTANCE.getProperty()) && (eObjectToTest.eClass() != InformationPackage.eINSTANCE
        .getUnionProperty()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(aggregationKindGroup);
    fields.add(defaultValueField);
    fields.add(featureBooleanPropertiesCheckbox);
    fields.add(maxLengthField);
    fields.add(maxValueField);
    fields.add(minLengthField);
    fields.add(minValueField);
    fields.add(nullValueField);
    fields.add(propertyBooleanPropertiesCheckbox);
    fields.add(visibilityKindGroup);

    return fields;
  }
}
