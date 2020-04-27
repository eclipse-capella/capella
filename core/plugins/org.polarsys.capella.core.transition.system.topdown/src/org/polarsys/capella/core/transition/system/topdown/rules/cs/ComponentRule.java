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
package org.polarsys.capella.core.transition.system.topdown.rules.cs;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.options.IOptionsHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.selection.EClassSelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.ISelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ComponentRule extends org.polarsys.capella.core.transition.system.rules.cs.ComponentRule {

  @Override
  protected void retrieveComponentAllocations(EObject source_p, List<EObject> result_p, IContext context_p) {
    if (!(source_p instanceof SystemComponent && BlockArchitectureExt.isRootComponent((SystemComponent)source_p))) {
      super.retrieveComponentAllocations(source_p, result_p, context_p);

    } else {
      String transitionKind = (String) context_p.get(ITopDownConstants.TRANSITION_KIND);
      boolean transition = ITopDownConstants.TRANSITION_TOPDOWN_SYSTEM.equals(transitionKind);
      if (transition) {
        super.retrieveComponentAllocations(source_p, result_p, context_p);
      }
    }
  }
  
  @Override
  protected void retrieveRepresentingPartitions(EObject source_p, List<EObject> result_p, IContext context_p) {
    Component element = (Component) source_p;

    for (Part partition : element.getRepresentingParts()) {
      if (TriStateBoolean.False.equals(CapellaProjectHelper.isReusableComponentsDriven(partition))) {
        result_p.add(partition);

      } else if ((partition instanceof Part) && (partition.getType() != null)) {
        if ((partition.eContainer() != null) && (ComponentPkgExt.isRootComponentPkg(partition.eContainer()))) {
          result_p.add(partition);
        }
        if ((partition.getType() instanceof Component) && (((Component) partition.getType()).getRepresentingParts().size() == 1)) {
          result_p.add(partition);
        }
      }
    }
  }

  @Override
  protected void retrieveComponentGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveComponentGoDeep(source_p, result_p, context_p);

    Component element = (Component) source_p;

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);

    IOptionsHandler options = OptionsHandlerHelper.getInstance(context_p);

    //We put in targetArchi only if ExchangeItem transition or preference is enabled
    boolean transitionStateMachine =
        options.getBooleanValue(context_p, ITopDownConstants.TRANSITION_TOPDOWN, ITopDownConstants.OPTIONS_TRANSITION__STATE_MACHINE,
            ITopDownConstants.OPTIONS_TRANSITION__STATE_MACHINE_DEFAULT.booleanValue());

    boolean transitionInterface =
        options.getBooleanValue(context_p, ITopDownConstants.TRANSITION_TOPDOWN, ITopDownConstants.OPTIONS_TRANSITION__INTERFACE,
            ITopDownConstants.OPTIONS_TRANSITION__INTERFACE_DEFAULT.booleanValue());

    boolean transitionData =
        options.getBooleanValue(context_p, ITopDownConstants.TRANSITION_TOPDOWN, ITopDownConstants.OPTIONS_TRANSITION__DATATYPE,
            ITopDownConstants.OPTIONS_TRANSITION__DATATYPE_DEFAULT.booleanValue());

    if (transitionStateMachine) {
      if (handler.contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {
        handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedStateMachines(), context_p);
        result_p.addAll(element.getOwnedStateMachines());
      }
    }

    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {
      result_p.addAll(element.getOwnedCommunicationLinks());
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getOwnedCommunicationLinks(), context_p);

      if (!(element instanceof Entity)) {
        result_p.addAll(ComponentExt.getSubParts(element, true));
        handler.addAll(ITransitionConstants.SOURCE_SCOPE, ComponentExt.getSubParts(element, true), context_p);
      }

      if (transitionInterface) {
        if (element.getOwnedInterfacePkg() != null) {
          result_p.add(element.getOwnedInterfacePkg());
          handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedInterfacePkg(), context_p);
        }
      }

      if (transitionData) {
        if (element.getOwnedDataPkg() != null) {
          result_p.add(element.getOwnedDataPkg());
          handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedDataPkg(), context_p);
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject container = element_p.eContainer();
    if (container != null) {
      EObject parent = container;
      while (parent != null) {

        ISelectionContext sContext =
            SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION,
                element_p, result_p);

        EObject targetContainer =
            TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(parent, context_p,
                new EClassSelectionContext(sContext, CsPackage.Literals.COMPONENT));
        if (targetContainer == null) {
          targetContainer = TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(parent, context_p, sContext);
        }
        if (targetContainer != null) {
          return targetContainer;
        }
        parent = parent.eContainer();
      }
    }

    return super.getBestContainer(element_p, result_p, context_p);
  }

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    Collection<EObject> selection = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    for (EObject source : selection) {
      if (source == element_p) {
        return Status.OK_STATUS;

      } else if (source instanceof Component) {
        if (ComponentExt.isComponentAncestor((Component) source, (Component) element_p)) {
          return Status.OK_STATUS;
        }

      } else if (source instanceof Structure) {
        return Status.OK_STATUS;
      }
    }

    if (TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(element_p, context_p)) {
      return Status.OK_STATUS;
    }
    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, element_p, context_p)) {
      return Status.OK_STATUS;
    }
    return new Status(IStatus.WARNING, Messages.Activity_Transition, "Component already transitioned");
  }

}
