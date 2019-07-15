/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.oa.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.properties.Messages;
import org.polarsys.capella.core.data.oa.properties.controllers.Entity_AllocatedActivitiesController;
import org.polarsys.capella.core.data.oa.properties.controllers.Entity_AllocatedRolesController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The Entity section.
 */
public class EntitySection extends NamedElementSection {

  private MultipleSemanticField _allocatedOperationalActivitiesField;
  private MultipleSemanticField _allocatedRolesField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _allocatedOperationalActivitiesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("EntitySection_AllocatedActivities_Label"), getWidgetFactory(), new Entity_AllocatedActivitiesController()); //$NON-NLS-1$
    _allocatedOperationalActivitiesField.setDisplayedInWizard(displayedInWizard);

    _allocatedRolesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("EntitySection_AllocatedRoles_Label"), getWidgetFactory(), new Entity_AllocatedRolesController()); //$NON-NLS-1$
    _allocatedRolesField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _allocatedOperationalActivitiesField.loadData(capellaElement, FaPackage.eINSTANCE.getAbstractFunctionalBlock_OwnedFunctionalAllocation());
    _allocatedRolesField.loadData(capellaElement, OaPackage.eINSTANCE.getEntity_OwnedRoleAllocations());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == OaPackage.eINSTANCE.getEntity()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_allocatedOperationalActivitiesField);
    fields.add(_allocatedRolesField);

    return fields;
  }
}
