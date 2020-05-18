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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.properties.fields.PropertyGroup;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 * The Association section.
 */
public class AssociationSection extends NamedElementSection {

  private PropertyGroup propertyGroup1;
  private PropertyGroup propertyGroup2;

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    propertyGroup1 = new PropertyGroup(parent, getWidgetFactory());
    propertyGroup1.setDisplayedInWizard(displayedInWizard);

    propertyGroup2 = new PropertyGroup(parent, getWidgetFactory());
    propertyGroup2.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    if (capellaElement instanceof Association) {
      Set<Property> members = new HashSet<Property>(2);
      members.addAll(((Association) capellaElement).getOwnedMembers());
      members.addAll(((Association) capellaElement).getNavigableMembers());
      if (members.size() == 2) {
        Object[] p = members.toArray();
        propertyGroup1.loadData((Property) p[0]);
        propertyGroup2.loadData((Property) p[1]);
      }
    }
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == InformationPackage.eINSTANCE.getAssociation()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(propertyGroup1);
    fields.add(propertyGroup2);

    return fields;
  }
}
