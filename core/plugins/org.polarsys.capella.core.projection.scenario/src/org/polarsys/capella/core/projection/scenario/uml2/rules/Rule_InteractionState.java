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
package org.polarsys.capella.core.projection.scenario.uml2.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.scenario.common.rules.Rule_CapellaElement;
import org.polarsys.capella.core.projection.scenario.helpers.IScenarioHelper;
import org.polarsys.capella.core.projection.scenario.helpers.InstanceRoles;
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
  
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    InteractionState source = (InteractionState) element_p;
    StateFragment fragment = ScenarioExt.getFragment(source);
    return TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(fragment, context_p);
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

    // we create one InteractionState for each related instances in the transitioned scenario
    List<AbstractInstance> instances = IScenarioHelper.getInstance(context_p).getTargetInstances(ScenarioExt.getFragment(source), context_p);
    for (int i = 0; i < instances.size(); i++) {
      objects.add(transformDirectElement(element_p, context_p));
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
      for (EObject related : IScenarioHelper.getInstance(context_p).getTargetRelatedElements(fragment, context_p)) {
        TigerRelationshipHelper.attachElementByRel(result_p, related, InteractionPackage.Literals.INTERACTION_STATE__RELATED_ABSTRACT_STATE);
      }
    }
    if (fragment.getRelatedAbstractFunction() != null) {
      for (EObject related : IScenarioHelper.getInstance(context_p).getTargetRelatedElements(fragment, context_p)) {
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
        for (AbstractInstance tracedInstance : IScenarioHelper.getInstance(context_p).getTargetInstances(ScenarioExt.getFragment(source), context_p)) {
          InstanceRole tracedRole = InstanceRoles.get(tracedInstance);
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
