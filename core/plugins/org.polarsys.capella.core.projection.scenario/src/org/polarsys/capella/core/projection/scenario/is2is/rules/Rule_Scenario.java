/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario.is2is.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;

/**
 */
public class Rule_Scenario extends CommonRule {

  /**
   * @param sourceType
   * @param targetType
   */
  public Rule_Scenario() {
    super(InteractionPackage.Literals.SCENARIO, InteractionPackage.Literals.SCENARIO,
        InteractionPackage.Literals.SCENARIO_REALIZATION);
    registerAttributeUpdate(InteractionPackage.Literals.SCENARIO__KIND);
  }

  @Override
  protected void runSubTransitionBeforeTransform(EObject element, ITransfo transfo) {
    Scenario scenario = (Scenario) element;
    if (!Query.isElementTransformed(element.eContainer(), _transfo)) {
      AbstractCapability capa = (AbstractCapability) scenario.eContainer();
      ICommand command2 = TransitionCommandHelper.getInstance().getCapabilityTransitionCommand(Arrays.asList(capa),
          new NullProgressMonitor());
      command2.run();
    }

    for (InstanceRole role : scenario.getOwnedInstanceRoles()) {
      AbstractInstance instance = role.getRepresentedInstance();
      if ((instance != null) && (instance.getAbstractType() instanceof Component)) {
        Component type = (Component) instance.getAbstractType();

        Collection<Object> elements = new ArrayList<Object>();
        elements.add(instance);

        IProgressMonitor monitor = new NullProgressMonitor();
        ICommand command = null;

        if (CapellaLayerCheckingExt.isInContextLayer(type)) {
          command = TransitionCommandHelper.getInstance().getActorTransitionCommand(elements, monitor);
        } 
        else if (CapellaLayerCheckingExt.isInLogicalLayer(type)) {
          if (type.isActor()) {
            command = TransitionCommandHelper.getInstance().getActorTransitionCommand(elements, monitor);

          } else {
            command = TransitionCommandHelper.getInstance().getLC2PCTransitionCommand(elements, monitor);
          }

        } 
        else if (CapellaLayerCheckingExt.isAOrInPhysicalLayer(type)) {
          if (!Query.isElementTransformed(type, transfo)
              && PreferenceHelper.getInstance().transitionPC2CIWhileScenarioTransition()) {
            command = TransitionCommandHelper.getInstance().getPC2CITransitionCommand(elements, monitor);
          }
        }

        if (command != null) {
          command.run();
        }
      }
    }
  }

  @Override
  protected Object transformElement(EObject element, ITransfo transfo) {
    Object result = super.transformElement(element, transfo);
    transfo.put(TransfoEngine.TRANSFO_TARGET, result);

    return result;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject,
   *      org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element, ITransfo transfo) {
    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element, getTargetType(),
        InteractionPackage.Literals.ABSTRACT_CAPABILITY__OWNED_SCENARIOS, transfo);
    TigerRelationshipHelper.attachTransformedRelatedElements(element,
        InteractionPackage.Literals.SCENARIO__PRE_CONDITION, transfo);
    TigerRelationshipHelper.attachTransformedRelatedElements(element,
        InteractionPackage.Literals.SCENARIO__POST_CONDITION, transfo);
  }

  @Override
  protected void doGoDeep(EObject element, List<EObject> result) {
    super.doGoDeep(element, result);

      Scenario s = (Scenario) element;
      result.add(s.getPreCondition());
      result.add(s.getPostCondition());
      result.addAll(s.getOwnedMessages());
      result.addAll(s.getOwnedEvents());
      result.addAll(s.getOwnedInteractionFragments());
      result.addAll(s.getOwnedTimeLapses());
      result.addAll(s.getOwnedInstanceRoles());

  }

  @Override
  protected void doAddContainer(EObject element_p, List<EObject> result_p) {
    // Nothing. We don't want to propagate parent here, we use runSubTransitionBeforeTransform for that.
  }
}
