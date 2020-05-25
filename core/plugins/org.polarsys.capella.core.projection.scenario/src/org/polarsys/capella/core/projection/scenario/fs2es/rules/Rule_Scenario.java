/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.scenario.fs2es.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.projection.scenario.Messages;
import org.polarsys.capella.core.projection.scenario.common.rules.Rule_InteractionElement;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 *
 */
public class Rule_Scenario extends Rule_InteractionElement {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_Scenario() {
    super(InteractionPackage.Literals.SCENARIO, InteractionPackage.Literals.SCENARIO, InteractionPackage.Literals.SCENARIO_REALIZATION);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void update_(EObject element_p, ITransfo transfo_p) {
    super.update_(element_p, transfo_p);
    Scenario source = (Scenario) element_p;

    ScenarioKind targetKind = ScenarioKind.DATA_FLOW;
    if (source.getKind() == ScenarioKind.INTERACTION) {
      targetKind = ScenarioKind.INTERACTION;
    }

    for (Scenario target : (List<Scenario>) Query.retrieveUnattachedTransformedElements(source, transfo_p, getTargetType())) {
      target.setKind(targetKind);
    }
  }

  @Override
  protected Object transformElement(EObject element_p, ITransfo transfo_p) {
    Object result = super.transformElement(element_p, transfo_p);
    Scenario source = (Scenario) element_p;
    if (result instanceof AbstractNamedElement && element_p instanceof AbstractNamedElement) {

      String suffix = Messages.ScenarioSuffix_CES;
      if (source.getKind() == ScenarioKind.INTERACTION) {
        suffix = Messages.ScenarioSuffix_OES;
      }
      ((AbstractNamedElement) result).setName(((AbstractNamedElement) element_p).getName() + suffix);
    }

    transfo_p.put(TransfoEngine.TRANSFO_TARGET, result);
    return result;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element_p, ITransfo transfo_p) throws TransfoException {
    TigerRelationshipHelper.attachUnattachedIntoSameContainer(element_p, getTargetType(), InteractionPackage.Literals.ABSTRACT_CAPABILITY__OWNED_SCENARIOS,
        transfo_p);
    TigerRelationshipHelper.attachTransformedRelatedElements(element_p, InteractionPackage.Literals.SCENARIO__PRE_CONDITION, transfo_p);
    TigerRelationshipHelper.attachTransformedRelatedElements(element_p, InteractionPackage.Literals.SCENARIO__POST_CONDITION, transfo_p);
  }

  @Override
  protected void doAddContainer(EObject element_p, List<EObject> result_p) {
    //Nothing to do. We create a scenario in the same container than source scenario
  }

  @Override
  protected void doGoDeep(EObject element_p, List<EObject> result_p) {
    super.doGoDeep(element_p, result_p);
    Scenario s = (Scenario) element_p;
    result_p.add(s.getPreCondition());
    result_p.add(s.getPostCondition());
    result_p.addAll(s.getOwnedInteractionFragments());
    result_p.addAll(s.getOwnedMessages());
    result_p.addAll(s.getOwnedTimeLapses());
    result_p.addAll(s.getOwnedEvents());
    result_p.addAll(s.getOwnedInstanceRoles());
  }

}
