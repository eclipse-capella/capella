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
package org.polarsys.capella.core.transition.system.topdown.commands;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.transition.common.commands.TransitionCommand;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.launcher.HeadlessIntramodelLauncher;

/**
 */
public class IntramodelTransitionCommand extends TransitionCommand {

  /**
   * @param _rootElement_p
   * @param progressMonitor_p
   */
  public IntramodelTransitionCommand(Collection<Object> selection_p, IProgressMonitor progressMonitor_p) {
    super(selection_p, progressMonitor_p);
  }

  @Override
  public String getName() {
    return getClass().getName();
  }

  @Override
  protected void performTransformation(Collection<Object> elements_p) {
    HeadlessIntramodelLauncher launcher = new HeadlessIntramodelLauncher() {

      @Override
      protected Collection<GenericParameter<?>> getHeadlessParameters() {
        return getHeadlessParametersForLauncher();
      }

      @Override
      protected String getMapping() {
        return getTransitionMapping();
      }

      @Override
      protected String getOptionScope() {
        return getTransitionKind();
      }

    };
    launcher.run(elements_p, true, getProgressMonitor());
  }

  protected String getTransitionMapping() {

    String id = "org.polarsys.";

    String kind = getTransitionKind();
    if (ITopDownConstants.TRANSITION_TOPDOWN_FUNCTIONAL.equals(kind)) {

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_INTERFACE.equals(kind)) {

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_DATA.equals(kind)) {

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_STATEMACHINE.equals(kind)) {

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_PROPERTYVALUE.equals(kind)) {

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_EXCHANGEITEM.equals(kind)) {

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_ACTOR.equals(kind)) {

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_SYSTEM.equals(kind)) {

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_LC2PC.equals(kind)) {
      return id + kind;

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_OE2ACTOR.equals(kind)) {
      return id + kind;

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_OE2SYSTEM.equals(kind)) {
      return id + kind;

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_CAPABILITY.equals(kind)) {

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_OC2SM.equals(kind)) {
      return id + kind;

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_OA2SC.equals(kind)) {
      return id + kind;

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_OA2SM.equals(kind)) {
      return id + kind;

    }

    return "org.polarsys.capella.core.transition.system.topdown";
  }

  protected String getTransitionKind() {
    return ITopDownConstants.TRANSITION_TOPDOWN;
  }

  protected Collection<GenericParameter<?>> getHeadlessParametersForLauncher() {
    return Collections.EMPTY_LIST;
  }

  @Override
  protected Collection<Object> retrieveRelatedElements(Object rootElement_p) {
    Object rootElement = rootElement_p;

    if (rootElement instanceof Part) {
      rootElement = ((Part) rootElement).getAbstractType();
    }

    String kind = getTransitionKind();
    if (ITopDownConstants.TRANSITION_TOPDOWN_FUNCTIONAL.equals(kind)) {
      if (rootElement instanceof BlockArchitecture) {
        Collection<Object> result = new HashSet<Object>();
        for (AbstractCapability capability : AbstractCapabilityPkgExt.getAllAbstractCapabilities(BlockArchitectureExt.getAbstractCapabilityPkg(
            (BlockArchitecture) rootElement, false))) {
          result.addAll(capability.getOwnedFunctionalChains());
        }

        rootElement = BlockArchitectureExt.getFunctionPkg((BlockArchitecture) rootElement, false);
        result.add(rootElement);

        return result;
      }

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_INTERFACE.equals(kind)) {
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getInterfacePkg((BlockArchitecture) rootElement);

      } else if (rootElement instanceof Component) {
        rootElement = ComponentExt.getInterfacePkg((Component) rootElement, false);
      }

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_DATA.equals(kind)) {
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getDataPkg((BlockArchitecture) rootElement);

      } else if (rootElement instanceof Component) {
        rootElement = ComponentExt.getDataPkg((Component) rootElement, false);
      }

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_STATEMACHINE.equals(kind)) {
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getFirstComponent((BlockArchitecture) rootElement, false);
      }
      if (rootElement instanceof Component) {
        Collection<Object> result = new HashSet<Object>();
        result.addAll(((Component) rootElement).getOwnedStateMachines());
        return result;
      }

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_PROPERTYVALUE.equals(kind)) {

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_EXCHANGEITEM.equals(kind)) {

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_ACTOR.equals(kind)) {
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getActorPkg((BlockArchitecture) rootElement);
      }

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_SYSTEM.equals(kind)) {
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getFirstComponent((BlockArchitecture) rootElement);
      }

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_LC2PC.equals(kind)) {
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getFirstComponent((BlockArchitecture) rootElement);
      }

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_OE2ACTOR.equals(kind)) {
      if (rootElement instanceof OperationalAnalysis) {
        rootElement = ((OperationalAnalysis) rootElement).getOwnedEntityPkg();
      }

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_OE2SYSTEM.equals(kind)) {
      if (rootElement instanceof OperationalAnalysis) {
        rootElement = ((OperationalAnalysis) rootElement).getOwnedEntityPkg();
      }

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_CAPABILITY.equals(kind)) {
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getAbstractCapabilityPkg((BlockArchitecture) rootElement, false);
      }

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_OC2SM.equals(kind)) {
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getAbstractCapabilityPkg((BlockArchitecture) rootElement, false);
      }

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_OA2SC.equals(kind)) {
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getFunctionPkg((BlockArchitecture) rootElement, false);
      }

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_OA2SM.equals(kind)) {
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getFunctionPkg((BlockArchitecture) rootElement, false);
      }

    }

    return Collections.singleton(rootElement);
  }
}
