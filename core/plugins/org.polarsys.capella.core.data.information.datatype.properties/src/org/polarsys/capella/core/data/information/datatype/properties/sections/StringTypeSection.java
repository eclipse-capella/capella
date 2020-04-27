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
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.properties.Messages;
import org.polarsys.capella.core.data.information.datatype.properties.controllers.StringTypeController;
import org.polarsys.capella.core.data.information.properties.controllers.MultiplicityElementLengthController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;

/**
 * The StringType section.
 */
public class StringTypeSection extends DataTypeSection {

  private SimpleEditableSemanticField minLengthWidget;
  private SimpleEditableSemanticField maxLengthWidget;
  private SimpleEditableSemanticField defaultValueWidget;
  private SimpleEditableSemanticField nullValueWidget;

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    minLengthWidget = new SimpleEditableSemanticField(getReferencesGroup(),
        Messages.getString("StringType.MinLengthLabel"), getWidgetFactory(), "", new MultiplicityElementLengthController(), true, 2); //$NON-NLS-1$ //$NON-NLS-2$
    minLengthWidget.setDisplayedInWizard(displayedInWizard);

    maxLengthWidget = new SimpleEditableSemanticField(getReferencesGroup(),
        Messages.getString("StringType.MaxLengthLabel"), getWidgetFactory(), "", new MultiplicityElementLengthController(), true, 3); //$NON-NLS-1$ //$NON-NLS-2$
    maxLengthWidget.setDisplayedInWizard(displayedInWizard);

    defaultValueWidget = new SimpleEditableSemanticField(getReferencesGroup(),
        Messages.getString("DataType.DefaultValueLabel"), getWidgetFactory(), Messages.getString("DataType.DefaultValueDefaultName"), new StringTypeController()); //$NON-NLS-1$ //$NON-NLS-2$
    defaultValueWidget.setDisplayedInWizard(displayedInWizard);
    nullValueWidget = new SimpleEditableSemanticField(getReferencesGroup(),
        Messages.getString("DataType.NullValueLabel"), getWidgetFactory(), Messages.getString("DataType.NullValueDefaultName"), new StringTypeController()); //$NON-NLS-1$ //$NON-NLS-2$
    nullValueWidget.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    minLengthWidget.loadData(capellaElement, DatatypePackage.eINSTANCE.getStringType_OwnedMinLength());
    maxLengthWidget.loadData(capellaElement, DatatypePackage.eINSTANCE.getStringType_OwnedMaxLength());
    defaultValueWidget.loadData(capellaElement, DatatypePackage.eINSTANCE.getStringType_OwnedDefaultValue());
    nullValueWidget.loadData(capellaElement, DatatypePackage.eINSTANCE.getStringType_OwnedNullValue());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == DatatypePackage.eINSTANCE.getStringType()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(defaultValueWidget);
    fields.add(maxLengthWidget);
    fields.add(minLengthWidget);
    fields.add(nullValueWidget);

    return fields;
  }
}
