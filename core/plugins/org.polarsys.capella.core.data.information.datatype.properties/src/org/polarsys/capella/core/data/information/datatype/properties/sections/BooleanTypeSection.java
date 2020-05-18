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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.properties.Messages;
import org.polarsys.capella.core.data.information.datatype.properties.controllers.BooleanTypeController;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;

/**
 * The BooleanType section.
 */
public class BooleanTypeSection extends DataTypeSection {

  private SimpleEditableSemanticField defaultValueWidget;

  /**
   * Default constructor.
   */
  public BooleanTypeSection() {
    super(false, false);
  }

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    defaultValueWidget = new SimpleEditableSemanticField(getReferencesGroup(),
      Messages.getString("DataType.DefaultValueLabel"), getWidgetFactory(), Messages.getString("DataType.DefaultValueDefaultName"), new BooleanTypeController()) //$NON-NLS-1$ //$NON-NLS-2$
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
      protected void handleOpenButtonClicked(Button button) {
        super.handleOpenButtonClicked(button);
        refresh();
      }
    };
    defaultValueWidget.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    defaultValueWidget.loadData(capellaElement, DatatypePackage.eINSTANCE.getBooleanType_OwnedDefaultValue());

    evaluateButtonStatus((BooleanType) capellaElement);
  }

  /**
   * @param booleanType
   */
  protected void evaluateButtonStatus(BooleanType booleanType) {
    if (booleanType != null) {
      DataValue defaultValue = booleanType.getDefaultValue();
      if (null == defaultValue) {
        defaultValueWidget.enableEditButton(false);
      } else {
        defaultValueWidget.enableEditButton(true);
      }
    }
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == DatatypePackage.eINSTANCE.getBooleanType()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(defaultValueWidget);

    return fields;
  }
}
