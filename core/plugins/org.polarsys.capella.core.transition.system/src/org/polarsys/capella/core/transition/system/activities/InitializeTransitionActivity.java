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

package org.polarsys.capella.core.transition.system.activities;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.scope.CompoundScopeRetriever;
import org.polarsys.capella.core.transition.common.handlers.scope.RuleContainersScopeRetriever;
import org.polarsys.capella.core.transition.common.handlers.scope.RuleRootElementsScopeRetriever;
import org.polarsys.capella.core.transition.system.handlers.attachment.CapellaDefaultAttachmentHandler;
import org.polarsys.capella.core.transition.system.handlers.transformation.CapellaTransformationHandler;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * Initialize the transition: - Initialize handlers -
 */
public abstract class InitializeTransitionActivity
    extends org.polarsys.capella.core.transition.common.activities.InitializeTransitionActivity {

  public static final String ID = "org.polarsys.capella.core.transition.system.activities.InitializeTransitionActivity"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  protected IStatus initializeContext(IContext context, ActivityParameters activityParams) {
    // Initialize handlers and source/target of transition
    IStatus status = super.initializeContext(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeOptimizations(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    return status;
  }

  @Override
  protected IStatus initializeScopeRetrieverHandlers(IContext context, CompoundScopeRetriever handler,
      ActivityParameters activityParams) {
    // Add a scope retriever based on IRuleScope implementations
    handler.addScopeRetriever(new RuleRootElementsScopeRetriever(), context);
    handler.addScopeRetriever(new RuleContainersScopeRetriever(), context);
    return super.initializeScopeRetrieverHandlers(context, handler, activityParams);
  }

  protected IStatus initializeOptimizations(IContext context, ActivityParameters activityParams) {
    return Status.OK_STATUS;
  }

  @Override
  protected IStatus initializeTransitionSources(IContext context, ActivityParameters activityParams) {
    Collection<Object> selection = (Collection) context.get(ITransitionConstants.TRANSITION_SELECTION);

    context.put(ITransitionConstants.TRANSITION_SOURCES, adaptSelection(selection));

    return Status.OK_STATUS;
  }

  /**
   * Adapt selection to a system transition (all graphical elements are mapped to their semantic elements)
   * 
   * @param selection_p
   * @return
   */
  protected Collection<EObject> adaptSelection(Collection<Object> selection) {
    return CapellaAdapterHelper.resolveSemanticObjects(selection);
  }

  /**
   * In a system transition, TRANSITION_SOURCE_ROOT = getSystemEngineering(TRANSITION_SOURCES)
   * TRANSITION_SOURCE_RESOURCE = TRANSITION_SOURCE_ROOT.eResource TRANSITION_SOURCE_EDITING_DOMAIN =
   * editingDomain(TRANSITION_SOURCE_RESOURCE)
   */
  @Override
  protected IStatus initializeSource(IContext context, ActivityParameters activityParams) {
    Collection<EObject> selection = (Collection<EObject>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    if (!selection.isEmpty()) {
      EObject source = (EObject) selection.toArray()[0];
      if (!(source instanceof CapellaElement)) {
        return new Status(IStatus.ERROR, Messages.Activity_Transition, "Input selection is not a CapellaElement");
      }
      ensureOpening(source);
    }

    return super.initializeSource(context, activityParams);
  }

  /**
   * In a system transition, default use is where source and target resources are the same TRANSITION_TARGET_RESOURCE =
   * TRANSITION_SOURCE_RESOURCE TRANSITION_TARGET_EDITING_DOMAIN = editingDomain(TRANSITION_TARGET_RESOURCE)
   * TRANSITION_TARGET_ROOT = TRANSITION_SOURCE_ROOT
   */
  @Override
  protected IStatus initializeTarget(IContext context, ActivityParameters activityParams) {
    Resource sourceResource = (Resource) context.get(ITransitionConstants.TRANSITION_SOURCE_RESOURCE);

    if ((sourceResource != null) && (!sourceResource.getContents().isEmpty())) {
      context.put(ITransitionConstants.TRANSITION_TARGET_RESOURCE,
          context.get(ITransitionConstants.TRANSITION_SOURCE_RESOURCE));
      context.put(ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN,
          context.get(ITransitionConstants.TRANSITION_SOURCE_EDITING_DOMAIN));
      context.put(ITransitionConstants.TRANSITION_TARGET_ROOT,
          context.get(ITransitionConstants.TRANSITION_SOURCE_ROOT));

    } else {
      return new Status(IStatus.ERROR, Messages.Activity_Transition, "Output model is invalid");
    }

    return Status.OK_STATUS;
  }

  /**
   * Create default attachment handler for common transition
   * 
   * @return
   */
  @Override
  protected IHandler createDefaultAttachmentHandler() {
    return new CapellaDefaultAttachmentHandler();
  }

  @Override
  protected IHandler createDefaultTransformationHandler() {
    return new CapellaTransformationHandler();
  }

  @Override
  protected abstract IHandler createDefaultTraceabilityTargetHandler();

  protected void ensureOpening(EObject source) {
    Session session = SessionManager.INSTANCE.getSession(source);
    if ((session == null) && (source instanceof CapellaElement)) {
      CapellaElement element = SystemEngineeringExt.getSystemEngineering((CapellaElement) source);
      EObject root = element.eContainer();
      root.eResource();
    }
  }
}
