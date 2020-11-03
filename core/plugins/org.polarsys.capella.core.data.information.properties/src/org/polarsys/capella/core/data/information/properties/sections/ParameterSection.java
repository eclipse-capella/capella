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
package org.polarsys.capella.core.data.information.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.data.information.properties.controllers.MultiplicityElementValueController;
import org.polarsys.capella.core.data.information.properties.fields.ParameterDirectionGroup;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;

/**
 * The Parameter section.
 */
public class ParameterSection extends MultiplicityElementSection {

  private ParameterDirectionGroup parameterDirectionGroup;

  private SimpleEditableSemanticField minValueField;
  private SimpleEditableSemanticField maxValueField;
  private SimpleEditableSemanticField defaultValueField;
  private SimpleEditableSemanticField nullValueField;

  /**
   * Default constructor.
   */
  public ParameterSection() {
    super(false, false, true, true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

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

    parameterDirectionGroup = new ParameterDirectionGroup(parent, getWidgetFactory());
    parameterDirectionGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    parameterDirectionGroup.loadData(capellaElement, InformationPackage.eINSTANCE.getParameter_Direction());
    minValueField.loadData(capellaElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMinValue());
    maxValueField.loadData(capellaElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMaxValue());
    defaultValueField.loadData(capellaElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedDefaultValue());
    nullValueField.loadData(capellaElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedNullValue());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == InformationPackage.eINSTANCE.getParameter()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(defaultValueField);
    fields.add(maxValueField);
    fields.add(minValueField);
    fields.add(nullValueField);
    fields.add(parameterDirectionGroup);

    return fields;
  }
}
