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
package org.polarsys.capella.core.transition.system.topdown.commands;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
import org.polarsys.capella.core.transition.common.commands.LauncherCommand;
import org.polarsys.capella.core.transition.common.launcher.DefaultLauncher;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.launcher.HeadlessIntramodelLauncher;

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
    String kind = getTransitionKind();

    switch (kind) {
    case ITopDownConstants.TRANSITION_TOPDOWN_LC2PC:
    case ITopDownConstants.TRANSITION_TOPDOWN_OE2ACTOR:
    case ITopDownConstants.TRANSITION_TOPDOWN_OE2SYSTEM:
    case ITopDownConstants.TRANSITION_TOPDOWN_OC2SM:
    case ITopDownConstants.TRANSITION_TOPDOWN_OA2SC:
    case ITopDownConstants.TRANSITION_TOPDOWN_OA2SM:
    case ITopDownConstants.TRANSITION_TOPDOWN_CAPABILITY:
      return "org.polarsys." + kind;

    default:
      return "org.polarsys.capella.core.transition.system.topdown";
    }
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

    switch (getTransitionKind()) {
    case ITopDownConstants.TRANSITION_TOPDOWN_FUNCTIONAL:
      if (rootElement instanceof BlockArchitecture) {
        Collection<Object> result = new HashSet<>();
        for (AbstractCapability capability : AbstractCapabilityPkgExt.getAllAbstractCapabilities(
            BlockArchitectureExt.getAbstractCapabilityPkg((BlockArchitecture) rootElement, false))) {
          result.addAll(capability.getOwnedFunctionalChains());
        }

        rootElement = BlockArchitectureExt.getFunctionPkg((BlockArchitecture) rootElement, false);
        result.add(rootElement);

        return result;
      }
      return Collections.singleton(rootElement);

    case ITopDownConstants.TRANSITION_TOPDOWN_INTERFACE:
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getInterfacePkg((BlockArchitecture) rootElement, false);

      } else if (rootElement instanceof Component) {
        rootElement = ComponentExt.getInterfacePkg((Component) rootElement, false);
      }
      return Collections.singleton(rootElement);

    case ITopDownConstants.TRANSITION_TOPDOWN_DATA:
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getDataPkg((BlockArchitecture) rootElement, false);

      } else if (rootElement instanceof Component) {
        rootElement = ComponentExt.getDataPkg((Component) rootElement, false);
      }
      return Collections.singleton(rootElement);

    case ITopDownConstants.TRANSITION_TOPDOWN_STATEMACHINE:
      if (rootElement instanceof BlockArchitecture) {
        rootElement = ((BlockArchitecture) rootElement).getSystem();
      }
      if (rootElement instanceof Component) {
        Collection<Object> result = new HashSet<>();
        result.addAll(((Component) rootElement).getOwnedStateMachines());
        return result;
      }
      return Collections.singleton(rootElement);

    case ITopDownConstants.TRANSITION_TOPDOWN_ACTOR:
      Collection<Object> result = new HashSet<>();
      if (rootElement instanceof BlockArchitecture) {
        ComponentPkg actorPkg = BlockArchitectureExt.getActorPkg((BlockArchitecture) rootElement, false);
        if (actorPkg != null) {
          result.addAll(ComponentPkgExt.getExternalActors(actorPkg));
        }
      }
      if (rootElement instanceof ComponentPkg) {
        result.addAll(ComponentPkgExt.getExternalActors((ComponentPkg) rootElement));
      }
      if (rootElement instanceof Component && ComponentExt.isExternalActor((Component) rootElement)) {
        result.add(rootElement);
      }
      if (result.isEmpty()) {
        result.add(rootElement);
      }
      return result;

    case ITopDownConstants.TRANSITION_TOPDOWN_SYSTEM:
      if (rootElement instanceof SystemComponentPkg) {
        rootElement = BlockArchitectureExt.getRootBlockArchitecture((SystemComponentPkg) rootElement);
      }
      if (rootElement instanceof BlockArchitecture) {
        rootElement = ((BlockArchitecture) rootElement).getSystem();
      }
      return Collections.singleton(rootElement);
    case ITopDownConstants.TRANSITION_TOPDOWN_LC2PC:
    case ITopDownConstants.TRANSITION_TOPDOWN_PC2CI:
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getComponentPkg((BlockArchitecture) rootElement, false);
      }
      return Collections.singleton(rootElement);

    case ITopDownConstants.TRANSITION_TOPDOWN_OE2ACTOR:
    case ITopDownConstants.TRANSITION_TOPDOWN_OE2SYSTEM:
      if (rootElement instanceof OperationalAnalysis) {
        rootElement = ((OperationalAnalysis) rootElement).getOwnedEntityPkg();
      }
      return Collections.singleton(rootElement);

    case ITopDownConstants.TRANSITION_TOPDOWN_CAPABILITY:
    case ITopDownConstants.TRANSITION_TOPDOWN_OC2SM:
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getAbstractCapabilityPkg((BlockArchitecture) rootElement, false);
      }
      return Collections.singleton(rootElement);

    case ITopDownConstants.TRANSITION_TOPDOWN_OA2SC:
    case ITopDownConstants.TRANSITION_TOPDOWN_OA2SM:
      if (rootElement instanceof BlockArchitecture) {
        rootElement = BlockArchitectureExt.getFunctionPkg((BlockArchitecture) rootElement, false);
      }
      return Collections.singleton(rootElement);

    case ITopDownConstants.TRANSITION_TOPDOWN_PROPERTYVALUE:
    case ITopDownConstants.TRANSITION_TOPDOWN_EXCHANGEITEM:
    default:
      return Collections.singleton(rootElement);
    }
  }
}
