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
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.data.information.properties.controllers.UnionPropertyController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The UnionProperty section.
 */
public class UnionPropertySection extends PropertySection {

  private MultipleSemanticField _qualifierField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _qualifierField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("UnionProperty.QualifierLabel"), getWidgetFactory(), new UnionPropertyController()); //$NON-NLS-1$
    _qualifierField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _qualifierField.loadData(capellaElement, InformationPackage.eINSTANCE.getUnionProperty_Qualifier());

    evaluateButtonStatus((UnionProperty) capellaElement);
  }
  
  /**
   * If the discriminant of the owning Union is unset, then the qualifier buttons (ADD and BROWSE) shall be disabled<br>
   * If the type of the discriminant of the owning Union is unset, then the qualifier buttons (ADD and BROWSE) shall be disabled<br>
   * If the type of the discriminant of the owning Union is BooleanType or Enumeration, then the qualifier button ADD shall be disabled<br>
   * 
   * @param unionProperty
   */
  protected void evaluateButtonStatus(UnionProperty unionProperty) {
    super.evaluateButtonStatus(unionProperty);

    if (unionProperty != null) {
      if (unionProperty.eContainer() instanceof Union) {
        UnionProperty discriminant = ((Union) unionProperty.eContainer()).getDiscriminant();
        if (discriminant != null) {
          Type discriminantType = discriminant.getType();
          if (discriminantType != null) {
            boolean enable = !(discriminantType instanceof Enumeration) && !(discriminantType instanceof BooleanType);
            _qualifierField.enableAddButton(enable);
            _qualifierField.enableOpenButton(true);
          }
          else {
            _qualifierField.enableAddButton(false);
            _qualifierField.enableOpenButton(false);
          }
        }
        else {
          _qualifierField.enableAddButton(false);
          _qualifierField.enableOpenButton(false);
        }
      }
    }
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == InformationPackage.eINSTANCE.getUnionProperty()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_qualifierField);

    return fields;
  }
}
