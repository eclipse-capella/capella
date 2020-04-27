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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.core.properties.fields.VisibilityKindGroup;
import org.polarsys.capella.core.data.core.properties.sections.GeneralizableElementSection;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.data.information.properties.controllers.Class_InformationRealizationsController;
import org.polarsys.capella.core.data.information.properties.fields.ClassBooleanPropertiesCheckbox;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The Class section.
 */
public class ClassSection extends GeneralizableElementSection {

  private ClassBooleanPropertiesCheckbox _classBooleanPropertiesCheckbox;
  private VisibilityKindGroup _visibilityKindGroup;
  private MultipleSemanticField _realizedClassesField;

  /**
   * Default constructor.
   */
  public ClassSection() {
    super(true, false);
  }

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();
    
    _classBooleanPropertiesCheckbox = new ClassBooleanPropertiesCheckbox(getCheckGroup(), getWidgetFactory());
    _classBooleanPropertiesCheckbox.setDisplayedInWizard(displayedInWizard);
    
    _realizedClassesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("ClassSection_RealizedClasses_Label"), getWidgetFactory(), new Class_InformationRealizationsController()); //$NON-NLS-1$
    _realizedClassesField.setDisplayedInWizard(displayedInWizard);
    
    _visibilityKindGroup = new VisibilityKindGroup(parent, getWidgetFactory());
    _visibilityKindGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _classBooleanPropertiesCheckbox.loadData(capellaElement);
    _realizedClassesField.loadData(capellaElement, InformationPackage.eINSTANCE.getClass_OwnedInformationRealizations());
    _visibilityKindGroup.loadData(capellaElement, CapellacorePackage.eINSTANCE.getGeneralClass_Visibility());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == InformationPackage.eINSTANCE.getClass_()) &&
        (eObjectToTest.eClass() != InformationPackage.eINSTANCE.getDomainElement()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = super.getSemanticFields();

    fields.add(_classBooleanPropertiesCheckbox);
    fields.add(_realizedClassesField);
    fields.add(_visibilityKindGroup);

    return fields;
  }
}
