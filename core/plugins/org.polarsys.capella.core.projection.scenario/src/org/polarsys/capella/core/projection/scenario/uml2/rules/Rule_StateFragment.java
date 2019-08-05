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
package org.polarsys.capella.core.projection.scenario.uml2.rules;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.scenario.common.rules.Rule_CapellaElement;
import org.polarsys.capella.core.projection.scenario.handlers.ScenarioHandlerHelper;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_StateFragment extends Rule_CapellaElement {
  /**
   * @param eclass_p
   */
  public Rule_StateFragment() {
    super(InteractionPackage.Literals.STATE_FRAGMENT, InteractionPackage.Literals.STATE_FRAGMENT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Collection<EObject> transformElement(EObject element_p, IContext context_p) {
    StateFragment source = (StateFragment) element_p;
    Collection<EObject> objects = new ArrayList<EObject>();

    // we create one InteractionState for each related instanceRoles in the transitioned scenario
    for (int i = 0; i < ScenarioHandlerHelper.getInstance(context_p).getTargetInstanceRoles(source, context_p).size(); i++) {
      objects.add(super.transformDirectElement(element_p, context_p));
    }
    return objects;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    StateFragment source = (StateFragment) element_p;

    if (source.getRelatedAbstractState() != null) {
      for (EObject related : ScenarioHandlerHelper.getInstance(context_p).getTargetRelatedElements(source, context_p)) {
        TigerRelationshipHelper.attachElementByRel(result_p, related, InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_STATE);
      }
    }
    if (source.getRelatedAbstractFunction() != null) {
      for (EObject related : ScenarioHandlerHelper.getInstance(context_p).getTargetRelatedElements(source, context_p)) {
        TigerRelationshipHelper.attachElementByRel(result_p, related, InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION);
      }
    }
  }

  @Override
  public void attach_(EObject element_p, ITransfo transfo_p) throws TransfoException {
    super.attach_(element_p, transfo_p);

    TigerRelationshipHelper.attachIemeWithIeme(element_p, getTargetType(), InteractionPackage.Literals.INTERACTION_FRAGMENT,
        InteractionPackage.Literals.TIME_LAPSE__START, transfo_p);
    TigerRelationshipHelper.attachIemeWithIeme(element_p, getTargetType(), InteractionPackage.Literals.INTERACTION_FRAGMENT,
        InteractionPackage.Literals.TIME_LAPSE__FINISH, transfo_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return InteractionPackage.Literals.SCENARIO__OWNED_TIME_LAPSES;
  }

}
