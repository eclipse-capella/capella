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
package org.polarsys.capella.core.data.ctx.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.cs.properties.sections.ComponentSection;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.properties.Messages;
import org.polarsys.capella.core.data.ctx.properties.controllers.Actor_RealizedOperationalActorsController;
import org.polarsys.capella.core.data.ctx.properties.controllers.Actor_RealizedOperationalEntitiesController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The Actor section.
 */
public class ActorSection extends ComponentSection {

  private MultipleSemanticField _realizedOperationalActors;
  private MultipleSemanticField _realizedOperationalEntities;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _realizedOperationalActors = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("ActorSection_RealizedOperationalActors_Label"), getWidgetFactory(), new Actor_RealizedOperationalActorsController()); //$NON-NLS-1$
    _realizedOperationalActors.setDisplayedInWizard(displayedInWizard);

    _realizedOperationalEntities = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("ActorSection_RealizedOperationalEntities_Label"), getWidgetFactory(), new Actor_RealizedOperationalEntitiesController()); //$NON-NLS-1$
    _realizedOperationalEntities.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _realizedOperationalActors.loadData(capellaElement, CtxPackage.Literals.ACTOR__OWNED_OPERATIONAL_ACTOR_REALIZATIONS);
    _realizedOperationalEntities.loadData(capellaElement, CtxPackage.Literals.ACTOR__OWNED_OPERATIONAL_ENTITY_REALIZATIONS);
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == CtxPackage.eINSTANCE.getActor()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_realizedOperationalActors);
    fields.add(_realizedOperationalEntities);

    return fields;
  }
}
