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
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.scenario.common.rules.Rule_CapellaElement;
import org.polarsys.capella.core.projection.scenario.handlers.ScenarioHandlerHelper;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_InteractionState extends Rule_CapellaElement {
  /**
   * @param eclass_p
   */
  public Rule_InteractionState() {
    super(InteractionPackage.Literals.INTERACTION_STATE, InteractionPackage.Literals.INTERACTION_STATE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Collection<EObject> transformElement(EObject element_p, IContext context_p) {
    InteractionState source = (InteractionState) element_p;
    Collection<EObject> objects = new ArrayList<EObject>();

    // we create one InteractionState for each related instanceRoles in the transitioned scenario
    for (int i = 0; i < ScenarioHandlerHelper.getInstance(context_p).getTargetInstanceRoles(ScenarioExt.getFragment(source), context_p).size(); i++) {
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

    InteractionState source = (InteractionState) element_p;
    StateFragment fragment = ScenarioExt.getFragment(source);
    if (fragment.getRelatedAbstractState() != null) {
      for (EObject related : ScenarioHandlerHelper.getInstance(context_p).getTargetRelatedElements(fragment, context_p)) {
        TigerRelationshipHelper.attachElementByRel(result_p, related, InteractionPackage.Literals.INTERACTION_STATE__RELATED_ABSTRACT_STATE);
      }
    }
    if (fragment.getRelatedAbstractFunction() != null) {
      for (EObject related : ScenarioHandlerHelper.getInstance(context_p).getTargetRelatedElements(fragment, context_p)) {
        TigerRelationshipHelper.attachElementByRel(result_p, related, InteractionPackage.Literals.INTERACTION_STATE__RELATED_ABSTRACT_FUNCTION);
      }
    }
  }

  @Override
  public void attach_(EObject element_p, ITransfo transfo_p) throws TransfoException {
    IContext context_p = IContext.getContext(transfo_p);
    super.attach_(element_p, transfo_p);

    InteractionState source = (InteractionState) element_p;
    // we don't attach all transformed elements to all transformed related, we attach each transformed InteractionState to the related instanceRole
    int i = 0;
    for (EObject result : Query.retrieveTransformedElements(element_p, transfo_p, getTargetType())) {
      if (isFirstAttach(source, result, context_p)) {

        int j = 0;
        for (InstanceRole tracedRole : ScenarioHandlerHelper.getInstance(context_p).getTargetInstanceRoles(ScenarioExt.getFragment(source), context_p)) {
          if (i == j) {
            TigerRelationshipHelper.attachElementByRel(result, tracedRole, InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES);
          }
          j++;
        }
      }
      i++;
    }

  }

}
