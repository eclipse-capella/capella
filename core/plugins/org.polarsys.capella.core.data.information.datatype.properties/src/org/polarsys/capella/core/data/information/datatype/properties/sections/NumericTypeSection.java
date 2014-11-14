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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.properties.Messages;
import org.polarsys.capella.core.data.information.datatype.properties.controllers.NumericTypeController;
import org.polarsys.capella.core.data.information.datatype.properties.fields.NumericTypeKindGroup;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;

/**
 * The NumericType section.
 */
public class NumericTypeSection extends DataTypeSection {

  private NumericTypeKindGroup numericTypeKindGroup;
  private SimpleEditableSemanticField minValueWidget;
  private SimpleEditableSemanticField maxValueWidget;
  private SimpleEditableSemanticField defaultValueWidget;
  private SimpleEditableSemanticField nullValueWidget;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    numericTypeKindGroup = new NumericTypeKindGroup(_rootParentComposite, getWidgetFactory()) {
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        super.widgetSelected(event_p);
        _dataTypeComposite.loadData(_semanticElement);
      }
    };
    numericTypeKindGroup.setDisplayedInWizard(displayedInWizard);
    numericTypeKindGroup.getGroup().moveAbove(getReferencesGroup());

    minValueWidget =
        new SimpleEditableSemanticField(getReferencesGroup(),
            Messages.getString("DataType.MinValueLabel"), getWidgetFactory(), Messages.getString("DataType.MinValueDefaultName"), new NumericTypeController()); //$NON-NLS-1$ //$NON-NLS-2$
    minValueWidget.setDisplayedInWizard(displayedInWizard);
    maxValueWidget =
        new SimpleEditableSemanticField(getReferencesGroup(),
            Messages.getString("DataType.MaxValueLabel"), getWidgetFactory(), Messages.getString("DataType.MaxValueDefaultName"), new NumericTypeController()); //$NON-NLS-1$ //$NON-NLS-2$
    maxValueWidget.setDisplayedInWizard(displayedInWizard);
    defaultValueWidget =
        new SimpleEditableSemanticField(getReferencesGroup(),
            Messages.getString("DataType.DefaultValueLabel"), getWidgetFactory(), Messages.getString("DataType.DefaultValueDefaultName"), new NumericTypeController()); //$NON-NLS-1$ //$NON-NLS-2$
    defaultValueWidget.setDisplayedInWizard(displayedInWizard);
    nullValueWidget =
        new SimpleEditableSemanticField(getReferencesGroup(),
            Messages.getString("DataType.NullValueLabel"), getWidgetFactory(), Messages.getString("DataType.NullValueDefaultName"), new NumericTypeController()); //$NON-NLS-1$ //$NON-NLS-2$
    nullValueWidget.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    numericTypeKindGroup.loadData(capellaElement_p, DatatypePackage.eINSTANCE.getNumericType_Kind());
    minValueWidget.loadData(capellaElement_p, DatatypePackage.eINSTANCE.getNumericType_OwnedMinValue());
    maxValueWidget.loadData(capellaElement_p, DatatypePackage.eINSTANCE.getNumericType_OwnedMaxValue());
    defaultValueWidget.loadData(capellaElement_p, DatatypePackage.eINSTANCE.getNumericType_OwnedDefaultValue());
    nullValueWidget.loadData(capellaElement_p, DatatypePackage.eINSTANCE.getNumericType_OwnedNullValue());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == DatatypePackage.eINSTANCE.getNumericType()) && (eObjectToTest.eClass() != DatatypePackage.eINSTANCE
        .getPhysicalQuantity()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(defaultValueWidget);
    fields.add(maxValueWidget);
    fields.add(minValueWidget);
    fields.add(nullValueWidget);
    fields.add(numericTypeKindGroup);

    return fields;
  }
}
