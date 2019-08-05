/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.transition.common.commands.LauncherCommand;
import org.polarsys.capella.core.transition.common.launcher.DefaultLauncher;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.launcher.HeadlessIntramodelLauncher;

/**
 */
public class IntramodelTransitionCommand extends LauncherCommand {

  public IntramodelTransitionCommand(Collection<?> selection, IProgressMonitor progressMonitor) {
    super(selection, progressMonitor);
  }

  @Override
  public String getName() {
    return "Capella Transition";
  }

  @Override
  protected DefaultLauncher createLauncher() {
    return new HeadlessIntramodelLauncher() {

      @Override
      protected String getMapping() {
        return getTransitionMapping();
      }

      @Override
      protected String getOptionScope() {
        return getTransitionKind();
      }

    };
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

  @Override
  protected Collection<Object> retrieveRelatedElements(Object element) {
    Object rootElement = element;

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
        rootElement = BlockArchitectureExt.getInterfacePkg((BlockArchitecture) rootElement, false);

      } else if (rootElement instanceof Component) {
        rootElement = ComponentExt.getInterfacePkg((Component) rootElement, false);
      }

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_DATA.equals(kind)) {
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getDataPkg((BlockArchitecture) rootElement, false);

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
        rootElement = BlockArchitectureExt.getActorPkg((BlockArchitecture) rootElement, false);
      }

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_SYSTEM.equals(kind)) {
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getFirstComponent((BlockArchitecture) rootElement, false);
      }

    } else if (ITopDownConstants.TRANSITION_TOPDOWN_LC2PC.equals(kind)) {
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getFirstComponent((BlockArchitecture) rootElement, false);
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
