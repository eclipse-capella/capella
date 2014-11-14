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
package org.polarsys.capella.common.re.activities;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.attachment.ReAttachmentHandler;
import org.polarsys.capella.common.re.handlers.attributes.DefaultAttributeHandler;
import org.polarsys.capella.common.re.handlers.location.DefaultLocationHandler;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandler;
import org.polarsys.capella.common.re.handlers.scope.DefaultDependenciesHandler;
import org.polarsys.capella.common.re.handlers.scope.ScopeHandler;
import org.polarsys.capella.common.re.handlers.scope.UnmodifiableElementsScopeRetriever;
import org.polarsys.capella.common.re.handlers.selection.ReSelectionContext;
import org.polarsys.capella.common.re.handlers.transformation.ReTransformationHandler;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.scope.CompoundScopeRetriever;
import org.polarsys.capella.core.transition.common.handlers.selection.CompoundSelectionContextHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.TraceabilityConfiguration;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class InitializeTransitionActivity extends org.polarsys.capella.core.transition.common.activities.InitializeTransitionActivity {

  public static final String ID = InitializeTransitionActivity.class.getCanonicalName();

  @Override
  protected IHandler createDefaultTransformationHandler() {
    return new ReTransformationHandler();
  }

  @Override
  protected IHandler createDefaultAttachmentHandler() {
    return new ReAttachmentHandler();
  }

  @Override
  protected void initializeSelectionContextHandlers(IContext context_p, CompoundSelectionContextHandler handler_p, ActivityParameters activityParams_p) {
    handler_p.addSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION, new ReSelectionContext());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IStatus initializeScopeRetrieverHandlers(IContext context_p, CompoundScopeRetriever handler_p, ActivityParameters activityParams_p) {
    IStatus status = super.initializeScopeRetrieverHandlers(context_p, handler_p, activityParams_p);
    handler_p.addScopeRetriever(new UnmodifiableElementsScopeRetriever(), context_p);
    return status;
  }

  @Override
  protected IStatus initializeContext(IContext context_p, ActivityParameters activityParams_p) {

    IStatus status = Status.OK_STATUS;

    // Initialize handlers and source/target of transition
    status = initializeReplicableElementHandler(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { IReConstants.REPLICABLE_ELEMENT_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    // Initialize handlers and source/target of transition
    status = initializeAttributeHandler(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { IReConstants.ATTRIBUTE_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    // Initialize handlers and source/target of transition
    status = initializeLocationHandler(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { IReConstants.LOCATION_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    // Initialize handlers and source/target of transition
    status = initializeDependenciesHandler(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { IReConstants.DEPENDENCIES_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }
    status = super.initializeContext(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    return status;
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeReplicableElementHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(IReConstants.REPLICABLE_ELEMENT_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultReplicableElementHandler();
    }
    context_p.put(IReConstants.REPLICABLE_ELEMENT_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  /**
   * @return
   */
  protected IHandler createDefaultReplicableElementHandler() {
    return new ReplicableElementHandler();
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeDependenciesHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(IReConstants.DEPENDENCIES_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultDependenciesHandler();
    }
    context_p.put(IReConstants.DEPENDENCIES_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  /**
   * @return
   */
  protected IHandler createDefaultDependenciesHandler() {
    return new DefaultDependenciesHandler();
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeAttributeHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(IReConstants.ATTRIBUTE_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultAttributeHandler();
    }
    context_p.put(IReConstants.ATTRIBUTE_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeLocationHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(IReConstants.LOCATION_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultLocationHandler();
    }
    context_p.put(IReConstants.LOCATION_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  /**
   * @return
   */
  protected IHandler createDefaultLocationHandler() {
    return new DefaultLocationHandler();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IHandler createDefaultScopeHandler() {
    return new ScopeHandler();
  }

  /**
   * @return
   */
  protected IHandler createDefaultAttributeHandler() {
    return new DefaultAttributeHandler();
  }

  @Override
  protected IStatus initializeTransitionSources(IContext context_p, ActivityParameters activityParams_p) {
    Collection<Object> selection = (Collection) context_p.get(ITransitionConstants.TRANSITION_SELECTION);
    Collection<Object> result = selection;
    context_p.put(ITransitionConstants.TRANSITION_SOURCES, result);
    return Status.OK_STATUS;
  }

  @Override
  protected IHandler createDefaultTraceabilityTargetHandler() {
    return new CompoundTraceabilityHandler(new TraceabilityConfiguration());
  }

  /**
   * In a common transition, 
   * default use is where source and target resources are the same
   * 
   * TRANSITION_TARGET_RESOURCE = TRANSITION_SOURCE_RESOURCE
   * TRANSITION_TARGET_EDITING_DOMAIN = editingDomain(TRANSITION_TARGET_RESOURCE)
   * TRANSITION_TARGET_ROOT = TRANSITION_TARGET_RESOURCE.getContents().get(0) 
   */
  @Override
  protected IStatus initializeTarget(IContext context_p, ActivityParameters activityParams_p) {
    // default transition, targetResource is same resource
    Resource sourceResource = (Resource) context_p.get(ITransitionConstants.TRANSITION_SOURCE_RESOURCE);
    Resource outputResource = sourceResource;

    if ((outputResource != null) && (outputResource.getContents().size() != 0)) {
      context_p.put(ITransitionConstants.TRANSITION_TARGET_RESOURCE, outputResource);
      context_p.put(ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN, TransactionUtil.getEditingDomain(outputResource));

      EObject root = EcoreUtil.getRootContainer(outputResource.getContents().get(0));
      if (root != null) {
        context_p.put(ITransitionConstants.TRANSITION_TARGET_ROOT, root);
      }
    } else {
      return new Status(IStatus.ERROR, Messages.Activity_Transition, "Output model is invalid");
    }

    return Status.OK_STATUS;
  }

}
