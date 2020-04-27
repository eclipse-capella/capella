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
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.properties.Messages;
import org.polarsys.capella.core.data.information.datatype.properties.controllers.PhysicalQuantityController;
import org.polarsys.capella.core.data.information.datatype.properties.controllers.PhysicalQuantityUnitController;
import org.polarsys.capella.core.data.information.datatype.properties.fields.NumericTypeKindGroup;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;

/**
 * The PhysicalQuantity section.
 */
public class PhysicalQuantitySection extends DataTypeSection {

  private NumericTypeKindGroup numericTypeKindGroup;
  private SimpleEditableSemanticField minValueWidget;
  private SimpleEditableSemanticField maxValueWidget;
  private SimpleEditableSemanticField defaultValueWidget;
  private SimpleEditableSemanticField nullValueWidget;
  private SimpleSemanticField unitWidget;

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    numericTypeKindGroup = new NumericTypeKindGroup(parent, getWidgetFactory()) {
      @Override
      public void widgetSelected(SelectionEvent event) {
        super.widgetSelected(event);
        _dataTypeComposite.loadData(semanticElement);
      }
    };
    numericTypeKindGroup.setDisplayedInWizard(displayedInWizard);
    numericTypeKindGroup.getGroup().moveAbove(getReferencesGroup());

    minValueWidget =
        new SimpleEditableSemanticField(getReferencesGroup(),
            Messages.getString("DataType.MinValueLabel"), getWidgetFactory(), Messages.getString("DataType.MinValueDefaultName"), new PhysicalQuantityController()); //$NON-NLS-1$ //$NON-NLS-2$
    minValueWidget.setDisplayedInWizard(displayedInWizard);
    maxValueWidget =
        new SimpleEditableSemanticField(getReferencesGroup(),
            Messages.getString("DataType.MaxValueLabel"), getWidgetFactory(), Messages.getString("DataType.MaxValueDefaultName"), new PhysicalQuantityController()); //$NON-NLS-1$ //$NON-NLS-2$
    maxValueWidget.setDisplayedInWizard(displayedInWizard);
    defaultValueWidget =
        new SimpleEditableSemanticField(getReferencesGroup(),
            Messages.getString("DataType.DefaultValueLabel"), getWidgetFactory(), Messages.getString("DataType.DefaultValueDefaultName"), new PhysicalQuantityController()); //$NON-NLS-1$ //$NON-NLS-2$
    defaultValueWidget.setDisplayedInWizard(displayedInWizard);
    nullValueWidget =
        new SimpleEditableSemanticField(getReferencesGroup(),
            Messages.getString("DataType.NullValueLabel"), getWidgetFactory(), Messages.getString("DataType.NullValueDefaultName"), new PhysicalQuantityController()); //$NON-NLS-1$ //$NON-NLS-2$
    nullValueWidget.setDisplayedInWizard(displayedInWizard);

    unitWidget = new SimpleSemanticField(getReferencesGroup(), Messages.getString("PhysicalDimension.UnitLabel"), getWidgetFactory(), new PhysicalQuantityUnitController()); //$NON-NLS-1$
    unitWidget.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    numericTypeKindGroup.loadData(capellaElement, DatatypePackage.eINSTANCE.getNumericType_Kind());
    minValueWidget.loadData(capellaElement, DatatypePackage.eINSTANCE.getNumericType_OwnedMinValue());
    maxValueWidget.loadData(capellaElement, DatatypePackage.eINSTANCE.getNumericType_OwnedMaxValue());
    defaultValueWidget.loadData(capellaElement, DatatypePackage.eINSTANCE.getNumericType_OwnedDefaultValue());
    nullValueWidget.loadData(capellaElement, DatatypePackage.eINSTANCE.getNumericType_OwnedNullValue());
    unitWidget.loadData(capellaElement, DatatypePackage.eINSTANCE.getPhysicalQuantity_Unit());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == DatatypePackage.eINSTANCE.getPhysicalQuantity()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();

    fields.addAll(super.getSemanticFields());
    fields.add(defaultValueWidget);
    fields.add(maxValueWidget);
    fields.add(minValueWidget);
    fields.add(nullValueWidget);
    fields.add(numericTypeKindGroup);
    fields.add(unitWidget);

    return fields;
  }
}
