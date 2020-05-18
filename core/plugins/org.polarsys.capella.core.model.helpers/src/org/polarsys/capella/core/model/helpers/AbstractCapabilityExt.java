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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.helpers.ctx.services.CapabilityExt;
import org.polarsys.capella.core.data.helpers.ctx.services.OperationalCapabilityExt;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalCapability;

/**
 * AbstractCapability helpers
 */
public class AbstractCapabilityExt {

  /**
   * This method adds an involved actor.
   * 
   * @param capabilityUseCase
   *          the capability in which the component will be involved in
   * @param component
   *          the involved component
   */
  public static void addInvolvedComponent(AbstractCapability capability, Component component) {
    if (capability instanceof OperationalCapability && component instanceof Entity) {
      OperationalCapabilityExt.addInvolvedEntity((OperationalCapability) capability, (Entity) component);
    } else if (capability instanceof Capability && component instanceof SystemComponent) {
      CapabilityExt.addInvolvedSystemComponent((Capability) capability, (SystemComponent) component);
    } else if (capability instanceof CapabilityRealization) {
      CapabilityRealizationExt.addInvolvedComponent((CapabilityRealization) capability, component);
    }
  }

  /**
   * This method retrieves all the scenarios from the model (contained by the given AbstractCapability).
   * 
   * @param currentElement
   * @return List<Scenario>
   */
  public static List<Scenario> getAllScenarios(AbstractCapability currentElement) {
    List<Scenario> scList = new ArrayList<Scenario>();

    Set<EObject> scSet = EObjectExt.getAll(currentElement, InteractionPackage.Literals.SCENARIO);
    if (scSet != null) {
      for (EObject obj : scSet) {
        scList.add((Scenario) obj);
      }
    }
    return scList;
  }

  /**
   * This method retrieves the contributing components.
   * 
   * @param capability
   *          the capability whose contributing components will be retrieved
   * @return the contributing components
   */
  public static List<Component> getInvolvedComponents(AbstractCapability capability) {
    List<Component> involvedComponents = new ArrayList<Component>();
    if (capability instanceof OperationalCapability) {
      involvedComponents.addAll(OperationalCapabilityExt.getInvolvedEntities((OperationalCapability) capability));
    } else if (capability instanceof Capability) {
      involvedComponents.addAll(CapabilityExt.getInvolvedSystemComponents((Capability) capability));
    } else if (capability instanceof CapabilityRealization) {
      involvedComponents.addAll(((CapabilityRealization) capability).getInvolvedComponents().stream()
          .filter(Component.class::isInstance).map(Component.class::cast).collect(Collectors.toList()));
    }
    return involvedComponents;
  }

  /**
   * This method retrieves the AspectPkg containing the given capability.
   * 
   * @param capability
   *          the capability whose container AspectPkg will be retrieved
   * @return the owner AspectPkg
   */
  public static AbstractCapabilityPkg getOwnerAbstractCapabilityPkg(AbstractCapability capability) {
    AbstractCapabilityPkg aspectPkg = null;

    if (capability != null) {
      aspectPkg = (AbstractCapabilityPkg) EcoreUtil2.getFirstContainer(capability,
          CapellacommonPackage.Literals.ABSTRACT_CAPABILITY_PKG);
    }

    return aspectPkg;
  }

  /**
   * This method removes an involved actor.
   * 
   * @param capability
   *          the capability in which the component will not be involved in
   * @param component
   *          the non involved component
   */
  public static void removeInvolvedComponent(AbstractCapability capability, Component component) {
    if (capability instanceof Capability && component instanceof SystemComponent) {
      CapabilityExt.removeInvolvedSystemComponent((Capability) capability, (SystemComponent) component);
    } else if (capability instanceof CapabilityRealization) {
      CapabilityRealizationExt.removeInvolvedComponent((CapabilityRealization) capability, component);
    }
  }

  /**
   * This method returns all the components involved in all the scenarios of the capability.
   * 
   * @param abstractCapability
   *          the capability which's involved components in scenarios are searched
   */
  public static List<Component> getComponentsFromAbstractCapabilityScenarios(AbstractCapability abstractCapability) {
    List<Component> result = new ArrayList<Component>();

    EList<Scenario> scenarios = abstractCapability.getOwnedScenarios();
    for (Scenario scenario : scenarios) {
      List<Component> components = ScenarioExt.getOwnedComponents(scenario);
      if (!components.isEmpty()) {
        result.addAll(components);
      }
    }

    return result;
  }

  /**
   * This method returns all the components involved in all the functional chains of the capability.
   * 
   * @param abstractCapability
   *          the capability which's involved components in functional chains are searched
   */
  public static List<Component> getComponentsFromAbstractCapabilityFunctionalChains(
      AbstractCapability abstractCapability) {
    List<Component> result = new ArrayList<Component>();

    List<FunctionalChain> funcionalChains = getFunctionalChains(abstractCapability);
    for (FunctionalChain chain : funcionalChains) {
      List<Component> components = FunctionalChainExt.getComponents(chain);
      if (!components.isEmpty()) {
        result.addAll(components);
      }
    }

    return result;
  }

  /**
   * This method returns all the functional chains of a capability.
   * 
   * @param abstractCapability
   *          the capability which's functional chains are retrieved
   */
  public static List<FunctionalChain> getFunctionalChains(AbstractCapability abstractCapability) {

    List<FunctionalChain> functionalChains = new ArrayList<FunctionalChain>();
    EList<FunctionalChainAbstractCapabilityInvolvement> chainsLink = abstractCapability
        .getOwnedFunctionalChainAbstractCapabilityInvolvements();
    for (FunctionalChainAbstractCapabilityInvolvement chainL : chainsLink) {

      FunctionalChain functionalChain = chainL.getFunctionalChain();
      if (null != functionalChain) {
        functionalChains.add(functionalChain);
      }
    }
    return functionalChains;
  }

  /**
   * This method adds involved function to a capability.
   * 
   * @param abstractCapability
   *          the capability to which the function will be involved
   * @param function
   *          the function which will be added in the involved functions list of the abstractCapability
   */
  public static void addInvolvedFunction(AbstractCapability capability, AbstractFunction function) {
    if (!capability.getInvolvedAbstractFunctions().contains(function)) {
      AbstractFunctionAbstractCapabilityInvolvement link = InteractionFactory.eINSTANCE
          .createAbstractFunctionAbstractCapabilityInvolvement();
      link.setInvolved(function);
      capability.getOwnedAbstractFunctionAbstractCapabilityInvolvements().add(link);
    }
  }

  /**
   * This method adds involved functional chain to a capability.
   * 
   * @param abstractCapability
   *          the capability to which the functionalChain will be involved
   * @param functionalChain
   *          the functionalChain which will be added in the involved functional chains list of the abstractCapability
   */
  public static void addInvolvedFunctionalChain(AbstractCapability capability, FunctionalChain functionalChain) {
    if (!capability.getInvolvedFunctionalChains().contains(functionalChain)) {
      FunctionalChainAbstractCapabilityInvolvement link = InteractionFactory.eINSTANCE
          .createFunctionalChainAbstractCapabilityInvolvement();
      link.setInvolved(functionalChain);
      capability.getOwnedFunctionalChainAbstractCapabilityInvolvements().add(link);
    }
  }
}
