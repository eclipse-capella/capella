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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.data.information.properties.fields.UnionBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.information.properties.fields.UnionKindGroup;
import org.polarsys.capella.core.ui.properties.controllers.SimpleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;

/**
 * The Union section.
 */
public class UnionSection extends NamedElementSection {

  private UnionBooleanPropertiesCheckbox _unionBooleanPropertiesCheckbox;
  private UnionKindGroup _unionKindGroup;
  private SimpleSemanticField _discriminantField;
  private SimpleSemanticField _defaultPropertyField;

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _unionBooleanPropertiesCheckbox = new UnionBooleanPropertiesCheckbox(getCheckGroup(), getWidgetFactory());
    _unionBooleanPropertiesCheckbox.setDisplayedInWizard(displayedInWizard);

    _discriminantField = new SimpleSemanticField(getReferencesGroup(), Messages.getString("UnionSection_Discriminant_Label"), getWidgetFactory(), new SimpleSemanticFieldController()); //$NON-NLS-1$
    _discriminantField.setDisplayedInWizard(displayedInWizard);

    _defaultPropertyField = new SimpleSemanticField(getReferencesGroup(), Messages.getString("UnionSection_DefaultProperty_Label"), getWidgetFactory(), new SimpleSemanticFieldController()); //$NON-NLS-1$
    _defaultPropertyField.setDisplayedInWizard(displayedInWizard);

    _unionKindGroup = new UnionKindGroup(parent, getWidgetFactory());
    _unionKindGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _unionBooleanPropertiesCheckbox.loadData(capellaElement);
    _discriminantField.loadData(capellaElement, InformationPackage.eINSTANCE.getUnion_Discriminant());
    _defaultPropertyField.loadData(capellaElement, InformationPackage.eINSTANCE.getUnion_DefaultProperty());
    _unionKindGroup.loadData(capellaElement, InformationPackage.eINSTANCE.getUnion_Kind());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == InformationPackage.eINSTANCE.getUnion()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = super.getSemanticFields();

    fields.add(_unionBooleanPropertiesCheckbox);
    fields.add(_discriminantField);
    fields.add(_defaultPropertyField);
    fields.add(_unionKindGroup);

    return fields;
  }
}
