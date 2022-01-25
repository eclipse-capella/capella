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
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.scenario.Activator;
import org.polarsys.capella.core.projection.scenario.common.rules.Rule_CapellaElement;
import org.polarsys.capella.core.projection.scenario.helpers.IScenarioHelper;
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
  
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    StateFragment source = (StateFragment) element_p;
    List<EObject> relatedElements = IScenarioHelper.getInstance(context_p).getTargetRelatedElements(source, context_p);
    if (relatedElements.isEmpty()) {
      return new Status(IStatus.WARNING, Activator.getDefault().getBundle().getSymbolicName(), "State Fragment is not transitioned");
    }
    return super.transformRequired(element_p, context_p);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected Collection<EObject> transformElement(EObject element_p, IContext context_p) {
    StateFragment source = (StateFragment) element_p;
    Collection<EObject> objects = new ArrayList<EObject>();

    // we create one StateFragment for each related instances in the transitioned scenario
    List<AbstractInstance> instances = IScenarioHelper.getInstance(context_p).getTargetInstances(source, context_p);
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

    StateFragment source = (StateFragment) element_p;

    if (source.getRelatedAbstractState() != null) {
      for (EObject related : IScenarioHelper.getInstance(context_p).getTargetRelatedElements(source, context_p)) {
        TigerRelationshipHelper.attachElementByRel(result_p, related, InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_STATE);
      }
    }
    if (source.getRelatedAbstractFunction() != null) {
      for (EObject related : IScenarioHelper.getInstance(context_p).getTargetRelatedElements(source, context_p)) {
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
