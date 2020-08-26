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
package org.polarsys.capella.core.projection.scenario.topdown.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.projection.common.TransitionHelper;
import org.polarsys.capella.core.projection.scenario.common.rules.Rule_InteractionElement;
import org.polarsys.capella.core.projection.scenario.helpers.ScenarioExt;
import org.polarsys.capella.core.projection.scenario.topdown.TopDownExt;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;

/**
 */
public class Rule_Scenario extends Rule_InteractionElement {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_Scenario() {
    super(InteractionPackage.Literals.SCENARIO, InteractionPackage.Literals.SCENARIO,
        InteractionPackage.Literals.SCENARIO_REALIZATION);
  }

  @Override
  protected void runSubTransitionBeforeTransform(EObject element_p, ITransfo transfo_p) {
    Scenario scenario = (Scenario) element_p;
    if (!Query.isElementTransformed(element_p.eContainer(), _transfo)) {
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
          if (type.isActor()) {
            command = TransitionCommandHelper.getInstance().getActorTransitionCommand(elements, monitor);

          } else {
            command = TransitionCommandHelper.getInstance().getSystemTransitionCommand(elements, monitor);
          }

        } else if (CapellaLayerCheckingExt.isInLogicalLayer(type)) {
          if (type.isActor()) {
            command = TransitionCommandHelper.getInstance().getActorTransitionCommand(elements, monitor);

          } else {
            command = TransitionCommandHelper.getInstance().getLC2PCTransitionCommand(elements, monitor);
          }

        } else if (CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(type)) {
          if (!(Query.isElementTransformed(type, transfo_p))) {
            command = TransitionCommandHelper.getInstance().getOE2ActorTransitionCommand(elements, monitor);
          }
          
        } else if (CapellaLayerCheckingExt.isAOrInPhysicalLayer(type)) {
          if (!Query.isElementTransformed(type, transfo_p)
              && PreferenceHelper.getInstance().transitionPC2CIWhileScenarioTransition()) {
            command = TransitionCommandHelper.getInstance().getPC2CITransitionCommand(elements, monitor);
          }
        }

        if (command != null) {
          command.run();
        }
      }
    }

    ArrayList<AbstractEventOperation> transformedOperations = new ArrayList<AbstractEventOperation>();
    for (SequenceMessage message : scenario.getOwnedMessages()) {
      AbstractEventOperation operation = ScenarioExt.getOperation(message);

      if (operation != null) {
        if (!transformedOperations.contains(operation)) {

          if (operation instanceof FunctionalExchange) {

            Collection<Object> elements = new ArrayList<Object>();
            elements.add(operation);

            IProgressMonitor monitor = new NullProgressMonitor();
            ICommand command = null;

            if (CapellaLayerCheckingExt.isInContextLayer(operation)) {
              command = TransitionCommandHelper.getInstance().getFunctionalTransitionCommand(elements, monitor);

            } else if (CapellaLayerCheckingExt.isInLogicalLayer(operation)) {
              command = TransitionCommandHelper.getInstance().getFunctionalTransitionCommand(elements, monitor);

            } else if (CapellaLayerCheckingExt.isInOperationalAnalysisLayer(operation)) {
              command = TransitionCommandHelper.getInstance().getFunctionalTransitionCommand(elements, monitor);
            }

            if (command != null) {
              command.run();
            }

            transformedOperations.add(operation);
          }

        }
      }
    }
  }

  @Override
  protected Object transformElement(EObject element_p, ITransfo transfo_p) {
    Object result = super.transformElement(element_p, transfo_p);
    transfo_p.put(TransfoEngine.TRANSFO_TARGET, result);

    // Retrieve parts of each abstract ends for furthers use
    TopDownExt.getTargetInstances((Scenario) element_p, transfo_p);
    return result;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void update_(EObject element_p, ITransfo transfo_p) {
    super.update_(element_p, transfo_p);
    
    //Update the kind of the scenario. If source was an Interaction Scenario, we need to set the correct new one
    Scenario source = (Scenario) element_p;
    ScenarioKind targetKind = source.getKind();
    if (targetKind == ScenarioKind.INTERACTION) {
      boolean isFunctionalScenario = TransitionHelper.getService().isFunctionalScenario(source);
      targetKind = isFunctionalScenario ? ScenarioKind.FUNCTIONAL : ScenarioKind.DATA_FLOW;
    }

    for (Scenario target : (List<Scenario>) Query.retrieveUnattachedTransformedElements(source, transfo_p,
        getTargetType())) {
      target.setKind(targetKind);
    }
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject,
   *      org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element_p, ITransfo transfo_p) {
    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element_p, getTargetType(),
        InteractionPackage.Literals.ABSTRACT_CAPABILITY__OWNED_SCENARIOS, transfo_p);
    TigerRelationshipHelper.attachTransformedRelatedElements(element_p,
        InteractionPackage.Literals.SCENARIO__PRE_CONDITION, transfo_p);
    TigerRelationshipHelper.attachTransformedRelatedElements(element_p,
        InteractionPackage.Literals.SCENARIO__POST_CONDITION, transfo_p);
  }

  @Override
  protected void doGoDeep(EObject element_p, List<EObject> result_p) {
    super.doGoDeep(element_p, result_p);

    Scenario s = (Scenario) element_p;
    result_p.add(s.getPreCondition());
    result_p.add(s.getPostCondition());
    result_p.addAll(s.getOwnedMessages());
    result_p.addAll(s.getOwnedEvents());
    result_p.addAll(s.getOwnedInteractionFragments());
    result_p.addAll(s.getOwnedTimeLapses());
    result_p.addAll(s.getOwnedInstanceRoles());
  }

  @Override
  protected void doAddContainer(EObject element_p, List<EObject> result_p) {
    // Nothing. We don't want to propagate parent here, we use runSubTransitionBeforeTransform for that.
  }

}
