/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario.es2is.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.scenario.Messages;
import org.polarsys.capella.core.projection.scenario.common.rules.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 */
public class Rule_DFScenario_IScenario extends Rule_CapellaElement {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_DFScenario_IScenario() {
    super(InteractionPackage.Literals.SCENARIO, InteractionPackage.Literals.SCENARIO, InteractionPackage.Literals.SCENARIO_REALIZATION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return InteractionPackage.Literals.ABSTRACT_CAPABILITY__OWNED_SCENARIOS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    //We attach scenario in the same container
    return element_p.eContainer();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    EObject result = super.transformDirectElement(element_p, context_p);
    if (result instanceof AbstractNamedElement && element_p instanceof AbstractNamedElement) {
      ((AbstractNamedElement) result).setName(((AbstractNamedElement) element_p).getName() + Messages.ScenarioSuffix_IS);
    }
    context_p.put(TransfoEngine.TRANSFO_TARGET, result);
    return result;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void update_(EObject element_p, ITransfo transfo_p) {
    super.update_(element_p, transfo_p);
    Scenario source = (Scenario) element_p;
    for (Scenario target : (List<Scenario>) Query.retrieveUnattachedTransformedElements(source, transfo_p, getTargetType())) {
      target.setKind(ScenarioKind.INTERFACE);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    Scenario s = (Scenario) source_p;
    result_p.addAll(s.getOwnedInteractionFragments());
    result_p.addAll(s.getOwnedMessages());
    result_p.addAll(s.getOwnedTimeLapses());
    result_p.addAll(s.getOwnedEvents());
    result_p.addAll(s.getOwnedInstanceRoles());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    //Nothing to do. We create a scenario in the same container than source scenario
  }

}
