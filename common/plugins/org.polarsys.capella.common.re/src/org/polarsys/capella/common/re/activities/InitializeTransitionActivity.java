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
  protected void initializeSelectionContextHandlers(IContext context, CompoundSelectionContextHandler handler, ActivityParameters activityParams) {
    handler.addSelectionContext(context, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION, new ReSelectionContext());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IStatus initializeScopeRetrieverHandlers(IContext context, CompoundScopeRetriever handler, ActivityParameters activityParams) {
    IStatus status = super.initializeScopeRetrieverHandlers(context, handler, activityParams);
    handler.addScopeRetriever(new UnmodifiableElementsScopeRetriever(), context);
    return status;
  }

  @Override
  protected IStatus initializeContext(IContext context, ActivityParameters activityParams) {

    IStatus status = Status.OK_STATUS;

    // Initialize handlers and source/target of transition
    status = initializeReplicableElementHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { IReConstants.REPLICABLE_ELEMENT_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    // Initialize handlers and source/target of transition
    status = initializeAttributeHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { IReConstants.ATTRIBUTE_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    // Initialize handlers and source/target of transition
    status = initializeLocationHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { IReConstants.LOCATION_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    // Initialize handlers and source/target of transition
    status = initializeDependenciesHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { IReConstants.DEPENDENCIES_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }
    status = super.initializeContext(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    return status;
  }

  /**
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeReplicableElementHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(IReConstants.REPLICABLE_ELEMENT_HANDLER, activityParams);
    if (handler == null) {
      handler = createDefaultReplicableElementHandler();
    }
    context.put(IReConstants.REPLICABLE_ELEMENT_HANDLER, handler);
    handler.init(context);
    return Status.OK_STATUS;
  }

  /**
   * @return
   */
  protected IHandler createDefaultReplicableElementHandler() {
    return new ReplicableElementHandler();
  }

  /**
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeDependenciesHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(IReConstants.DEPENDENCIES_HANDLER, activityParams);
    if (handler == null) {
      handler = createDefaultDependenciesHandler();
    }
    context.put(IReConstants.DEPENDENCIES_HANDLER, handler);
    handler.init(context);
    return Status.OK_STATUS;
  }

  /**
   * @return
   */
  protected IHandler createDefaultDependenciesHandler() {
    return new DefaultDependenciesHandler();
  }

  /**
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeAttributeHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(IReConstants.ATTRIBUTE_HANDLER, activityParams);
    if (handler == null) {
      handler = createDefaultAttributeHandler();
    }
    context.put(IReConstants.ATTRIBUTE_HANDLER, handler);
    handler.init(context);
    return Status.OK_STATUS;
  }

  /**
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeLocationHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(IReConstants.LOCATION_HANDLER, activityParams);
    if (handler == null) {
      handler = createDefaultLocationHandler();
    }
    context.put(IReConstants.LOCATION_HANDLER, handler);
    handler.init(context);
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
  protected IStatus initializeTransitionSources(IContext context, ActivityParameters activityParams) {
    Collection<Object> selection = (Collection) context.get(ITransitionConstants.TRANSITION_SELECTION);
    Collection<Object> result = selection;
    context.put(ITransitionConstants.TRANSITION_SOURCES, result);
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
  protected IStatus initializeTarget(IContext context, ActivityParameters activityParams) {
    // default transition, targetResource is same resource
    Resource sourceResource = (Resource) context.get(ITransitionConstants.TRANSITION_SOURCE_RESOURCE);
    Resource outputResource = sourceResource;

    if ((outputResource != null) && (!outputResource.getContents().isEmpty())) {
      context.put(ITransitionConstants.TRANSITION_TARGET_RESOURCE, outputResource);
      context.put(ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN, TransactionUtil.getEditingDomain(outputResource));
      EObject root = EcoreUtil.getRootContainer(outputResource.getContents().get(0));
      if (root != null) {
        context.put(ITransitionConstants.TRANSITION_TARGET_ROOT, root);
      }
    } else {
      return new Status(IStatus.ERROR, Messages.Activity_Transition, "Output model is invalid");
    }

    return Status.OK_STATUS;
  }

}
