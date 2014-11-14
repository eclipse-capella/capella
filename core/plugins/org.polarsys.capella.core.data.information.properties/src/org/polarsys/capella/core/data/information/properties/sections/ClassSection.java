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
package org.polarsys.capella.core.data.information.properties.sections;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.core.properties.fields.VisibilityKindGroup;
import org.polarsys.capella.core.data.core.properties.sections.GeneralizableElementSection;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.data.information.properties.controllers.Class_InformationRealizationsController;
import org.polarsys.capella.core.data.information.properties.fields.ClassBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
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
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();
    
    _classBooleanPropertiesCheckbox = new ClassBooleanPropertiesCheckbox(getCheckGroup(), getWidgetFactory());
    _classBooleanPropertiesCheckbox.setDisplayedInWizard(displayedInWizard);
    
    _realizedClassesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("ClassSection_RealizedClasses_Label"), getWidgetFactory(), new Class_InformationRealizationsController()); //$NON-NLS-1$
    _realizedClassesField.setDisplayedInWizard(displayedInWizard);
    
    _visibilityKindGroup = new VisibilityKindGroup(_rootParentComposite, getWidgetFactory());
    _visibilityKindGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    _classBooleanPropertiesCheckbox.loadData(capellaElement_p);
    _realizedClassesField.loadData(capellaElement_p, InformationPackage.eINSTANCE.getClass_OwnedInformationRealizations());
    _visibilityKindGroup.loadData(capellaElement_p, CapellacorePackage.eINSTANCE.getGeneralClass_Visibility());
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
