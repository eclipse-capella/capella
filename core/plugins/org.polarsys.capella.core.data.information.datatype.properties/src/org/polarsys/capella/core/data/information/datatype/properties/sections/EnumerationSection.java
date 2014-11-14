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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datatype.properties.Messages;
import org.polarsys.capella.core.data.information.datatype.properties.controllers.EnumerationController;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.controllers.SimpleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;

/**
 * The Enumeration section.
 */
public class EnumerationSection extends DataTypeSection {

  private SimpleSemanticField domainTypeWidget;
  private SimpleEditableSemanticField minValueWidget;
  private SimpleEditableSemanticField maxValueWidget;
  private SimpleEditableSemanticField defaultValueWidget;
  private SimpleEditableSemanticField nullValueWidget;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    domainTypeWidget = new SimpleSemanticField(getReferencesGroup(),
      Messages.getString("Enumeration.DomainTypeLabel"), getWidgetFactory(), new SimpleSemanticFieldController()); //$NON-NLS-1$
    domainTypeWidget.setDisplayedInWizard(displayedInWizard);

    minValueWidget = new SimpleEditableSemanticField(getReferencesGroup(),
      Messages.getString("DataType.MinValueLabel"), getWidgetFactory(), Messages.getString("DataType.MinValueDefaultName"), new EnumerationController()) //$NON-NLS-1$ //$NON-NLS-2$
    {
      /**
       * {@inheritDoc}
       */
      @Override
      protected void handleDeleteButtonClicked() {
        super.handleDeleteButtonClicked();
        refresh();
      }
      /**
       * {@inheritDoc}
       */
      @Override
      protected void handleOpenButtonClicked(Button button_p) {
        super.handleOpenButtonClicked(button_p);
        refresh();
      }
    };
    minValueWidget.setDisplayedInWizard(displayedInWizard);

    maxValueWidget = new SimpleEditableSemanticField(getReferencesGroup(),
      Messages.getString("DataType.MaxValueLabel"), getWidgetFactory(), Messages.getString("DataType.MaxValueDefaultName"), new EnumerationController()) //$NON-NLS-1$ //$NON-NLS-2$
    {
      /**
       * {@inheritDoc}
       */
      @Override
      protected void handleDeleteButtonClicked() {
        super.handleDeleteButtonClicked();
        refresh();
      }
      /**
       * {@inheritDoc}
       */
      @Override
      protected void handleOpenButtonClicked(Button button_p) {
        super.handleOpenButtonClicked(button_p);
        refresh();
      }
    };
    maxValueWidget.setDisplayedInWizard(displayedInWizard);

    defaultValueWidget = new SimpleEditableSemanticField(getReferencesGroup(),
      Messages.getString("DataType.DefaultValueLabel"), getWidgetFactory(), Messages.getString("DataType.DefaultValueDefaultName"), new EnumerationController()) //$NON-NLS-1$ //$NON-NLS-2$
    {
      /**
       * {@inheritDoc}
       */
      @Override
      protected void handleDeleteButtonClicked() {
        super.handleDeleteButtonClicked();
        refresh();
      }
      /**
       * {@inheritDoc}
       */
      @Override
      protected void handleOpenButtonClicked(Button button_p) {
        super.handleOpenButtonClicked(button_p);
        refresh();
      }
    };
    defaultValueWidget.setDisplayedInWizard(displayedInWizard);

    nullValueWidget = new SimpleEditableSemanticField(getReferencesGroup(),
      Messages.getString("DataType.NullValueLabel"), getWidgetFactory(), Messages.getString("DataType.NullValueDefaultName"), new EnumerationController()) //$NON-NLS-1$ //$NON-NLS-2$
    {
      /**
       * {@inheritDoc}
       */
      @Override
      protected void handleDeleteButtonClicked() {
        super.handleDeleteButtonClicked();
        refresh();
      }
      /**
       * {@inheritDoc}
       */
      @Override
      protected void handleOpenButtonClicked(Button button_p) {
        super.handleOpenButtonClicked(button_p);
        refresh();
      }
    };
    nullValueWidget.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    domainTypeWidget.loadData(capellaElement_p, DatatypePackage.eINSTANCE.getEnumeration_DomainType());
    minValueWidget.loadData(capellaElement_p, DatatypePackage.eINSTANCE.getEnumeration_OwnedMinValue());
    maxValueWidget.loadData(capellaElement_p, DatatypePackage.eINSTANCE.getEnumeration_OwnedMaxValue());
    defaultValueWidget.loadData(capellaElement_p, DatatypePackage.eINSTANCE.getEnumeration_OwnedDefaultValue());
    nullValueWidget.loadData(capellaElement_p, DatatypePackage.eINSTANCE.getEnumeration_OwnedNullValue());

    evaluateButtonStatus((Enumeration) capellaElement_p);
  }

  /**
   * @param enumeration_p
   */
  protected void evaluateButtonStatus(Enumeration enumeration_p) {
    if (enumeration_p != null) {
      DataValue value = enumeration_p.getDefaultValue();
      if (null == value) {
        defaultValueWidget.enableEditButton(false);
      } else {
        defaultValueWidget.enableEditButton(true);
      }
      value = enumeration_p.getNullValue();
      if (null == value) {
        nullValueWidget.enableEditButton(false);
      } else {
        nullValueWidget.enableEditButton(true);
      }
      value = enumeration_p.getOwnedMinValue();
      if (null == value) {
        minValueWidget.enableEditButton(false);
      } else {
        minValueWidget.enableEditButton(true);
      }
      value = enumeration_p.getOwnedMaxValue();
      if (null == value) {
        maxValueWidget.enableEditButton(false);
      } else {
        maxValueWidget.enableEditButton(true);
      }
    }
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == DatatypePackage.eINSTANCE.getEnumeration()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(domainTypeWidget);
    fields.add(minValueWidget);
    fields.add(maxValueWidget);
    fields.add(defaultValueWidget);
    fields.add(nullValueWidget);

    return fields;
  }
}
