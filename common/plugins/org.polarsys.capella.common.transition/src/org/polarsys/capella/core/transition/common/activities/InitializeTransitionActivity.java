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

package org.polarsys.capella.core.transition.common.activities;

import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.core.transition.common.ExtensionHelper;
import org.polarsys.capella.core.transition.common.constants.ISchemaConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionSteps;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.attachment.DefaultAttachmentHandler;
import org.polarsys.capella.core.transition.common.handlers.contextscope.DefaultContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.handlers.notify.DefaultNotifyHandler;
import org.polarsys.capella.core.transition.common.handlers.options.DefaultOptionsHandler;
import org.polarsys.capella.core.transition.common.handlers.scope.CompoundScopeFilter;
import org.polarsys.capella.core.transition.common.handlers.scope.CompoundScopeRetriever;
import org.polarsys.capella.core.transition.common.handlers.scope.DefaultScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeFilter;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeRetriever;
import org.polarsys.capella.core.transition.common.handlers.scope.RuleRelatedElementsScopeRetriever;
import org.polarsys.capella.core.transition.common.handlers.selection.CompoundSelectionContextHandler;
import org.polarsys.capella.core.transition.common.handlers.selection.DefaultSelectionContextsHandler;
import org.polarsys.capella.core.transition.common.handlers.selection.TransformationSelectionContext;
import org.polarsys.capella.core.transition.common.handlers.session.DefaultSessionHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.transformation.DefaultTransformationHandler;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.api.ITransposerWorkflow;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * Initialize the transition: - Initialize handlers -
 */
public abstract class InitializeTransitionActivity extends AbstractActivity implements ITransposerWorkflow {

  public static final String ID = "org.polarsys.capella.core.transition.common.activities.InitializeTransitionActivity"; //$NON-NLS-1$

  public static final String PARAMETER_RULE_HANDLER = "org.polarsys.capella.core.transition.ruleHandler"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  public String getActivityIdentifier() {
    return ITransitionSteps.INITIALIZE_TRANSITION;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.polarsys.kitalpha.cadence.core.api.IActivity#run(org.polarsys.kitalpha.cadence.core.api.parameter.
   * ActivityParameters)
   */
  @Override
  public IStatus _run(ActivityParameters activityParams) {
    IStatus status = Status.OK_STATUS;

    IContext context = (IContext) activityParams.getParameter(ITransposerWorkflow.TRANSPOSER_CONTEXT).getValue();

    status = initializeContext(context, activityParams);

    return status;
  }

  /**
   * Should initialize RESOURCE_SET, MERGE_CONTEXT, RULE_HANDLER
   * 
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeContext(IContext context, ActivityParameters activityParams) {

    IStatus status = Status.OK_STATUS;

    context.put(ITransitionConstants.TRANSPOSER_APPLY_REQUIRED, Boolean.TRUE);
    context.put(ITransitionConstants.TRANSFORMED_ELEMENTS, new HashSet<EObject>());

    context.put(ITransitionConstants.TRANSITION_SELECTION, context.get(ITransitionConstants.TRANSPOSER_SELECTION));

    status = initializeTransitionSources(context, activityParams);

    IRulesHandler ruleHandler = (IRulesHandler) activityParams.getParameter(PARAMETER_RULE_HANDLER).getValue();
    context.put(ITransitionConstants.RULES_HANDLER, ruleHandler);

    // Initialize handlers and source/target of transition
    status = initializeSource(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { ITransitionConstants.TRANSITION_SOURCE_ROOT,
        ITransitionConstants.TRANSITION_SOURCE_RESOURCE, ITransitionConstants.TRANSITION_SOURCE_EDITING_DOMAIN });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeNotifyHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { ITransitionConstants.NOTIFY_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeOptionsHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { ITransitionConstants.OPTIONS_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = configureLogHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeAttachmentHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { ITransitionConstants.ATTACHMENT_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeContextScopeHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { ITransitionConstants.CONTEXT_SCOPE_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeScopeHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { ITransitionConstants.SCOPE_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }
    status = initializeSessionHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { ITransitionConstants.SESSION_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeTransformationHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { ITransitionConstants.TRANSFORMATION_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeTraceabilityTargetHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { ITransitionConstants.TRACEABILITY_TARGET_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeSelectionContextsHandler(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { ITransitionConstants.SELECTION_CONTEXTS_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeTarget(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { ITransitionConstants.TRANSITION_TARGET_ROOT,
        ITransitionConstants.TRANSITION_TARGET_RESOURCE, ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN });
    if (!checkStatus(status)) {
      return status;
    }

    return Status.OK_STATUS;
  }

  /**
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus configureLogHandler(IContext context, ActivityParameters activityParams) {
    LogHelper.getInstance().setLevel(Level.DEBUG);
    return Status.OK_STATUS;
  }

  /**
   * TRANSITION_SOURCES must be initialized
   * 
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeTransitionSources(IContext context, ActivityParameters activityParams) {
    Collection<Object> selection = (Collection) context.get(ITransitionConstants.TRANSITION_SELECTION);

    context.put(ITransitionConstants.TRANSITION_SOURCES, selection);

    return Status.OK_STATUS;
  }

  /**
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeTraceabilityTargetHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.TRACEABILITY_TARGET_HANDLER, activityParams);
    if (handler == null) {
      handler = createDefaultTraceabilityTargetHandler();
    }
    context.put(ITransitionConstants.TRACEABILITY_TARGET_HANDLER, handler);
    handler.init(context);
    return Status.OK_STATUS;
  }

  /**
   * Create default transformation traceability handler for common transition
   * 
   * @return
   */
  protected IHandler createDefaultTraceabilityTargetHandler() {
    return ITraceabilityHandler.DEFAULT;
  }

  /**
   * Initialize TRANSITION_SOURCE_ROOT and TRANSITION_SOURCE_RESOURCE and TRANSITION_SOURCE_EDITING_DOMAIN according to
   * selection
   * 
   * in a common transition, TRANSITION_SOURCE_ROOT = TRANSITION_SOURCES.get(0) TRANSITION_SOURCE_RESOURCE =
   * TRANSITION_SOURCE_ROOT.eResource TRANSITION_SOURCE_EDITING_DOMAIN = editingDomain(TRANSITION_SOURCE_RESOURCE)
   */
  protected IStatus initializeSource(IContext context, ActivityParameters activityParams) {
    Collection<Object> selection = (Collection<Object>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    if (!selection.isEmpty()) {
      Object source = selection.toArray()[0];
      if (source instanceof EObject) {
        EObject element = (EObject) source;
        Resource resource = element.eResource();
        context.put(ITransitionConstants.TRANSITION_SOURCE_ROOT, EcoreUtil.getRootContainer(element));
        context.put(ITransitionConstants.TRANSITION_SOURCE_RESOURCE, resource);
        context.put(ITransitionConstants.TRANSITION_SOURCE_EDITING_DOMAIN, TransactionUtil.getEditingDomain(resource));
      }

    } else {
      return new Status(IStatus.ERROR, Messages.Activity_Transition, "No input selection");
    }

    return Status.OK_STATUS;
  }

  /**
   * Initialize TRANSITION_TARGET_ROOT and TRANSITION_TARGET_RESOURCE and TRANSITION_TARGET_EDITING_DOMAIN according to
   * selection
   * 
   * @param context
   * @param activityParams
   */
  protected abstract IStatus initializeTarget(IContext context, ActivityParameters activityParams);

  /**
   * Initialize the Notify handler and set it into context via ITransitionConstants.NOTIFY_HANDLER
   * 
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeNotifyHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.NOTIFY_HANDLER, activityParams);
    if (handler == null) {
      handler = createDefaultNotifyHandler();
    }

    IStatus status = handler.init(context);
    context.put(ITransitionConstants.NOTIFY_HANDLER, handler);
    return status;
  }

  /**
   * Create default options handler for common transition
   * 
   * @return
   */
  protected IHandler createDefaultNotifyHandler() {
    return new DefaultNotifyHandler();
  }

  /**
   * Initialize the Options handler and set it into context via ITransitionConstants.OPTIONS_HANDLER
   * 
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeOptionsHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.OPTIONS_HANDLER, activityParams);
    if (handler == null) {
      handler = createDefaultOptionsHandler();
    }

    String optionScope = loadStringFromParameters(ITransitionConstants.OPTIONS_SCOPE, activityParams);
    if (optionScope == null) {
      optionScope = getDefaultOptionsScope();
    }
    context.put(ITransitionConstants.OPTIONS_PARAMETERS, activityParams);
    context.put(ITransitionConstants.OPTIONS_SCOPE, optionScope);
    context.put(ITransitionConstants.OPTIONS_HANDLER, handler);
    IStatus status = handler.init(context);
    return status;
  }

  /**
   * Create default options handler for common transition
   * 
   * @return
   */
  protected IHandler createDefaultOptionsHandler() {
    return new DefaultOptionsHandler();
  }

  protected String getDefaultOptionsScope() {
    return "capella.core.transition"; //$NON-NLS-1$
  }

  /**
   * Initialize the Attachment handler and set it into context via ATTACHMENT_HANDLER
   * 
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeAttachmentHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.ATTACHMENT_HANDLER, activityParams);
    if (handler == null) {
      handler = createDefaultAttachmentHandler();
    }
    context.put(ITransitionConstants.ATTACHMENT_HANDLER, handler);
    handler.init(context);
    return Status.OK_STATUS;
  }

  /**
   * Create default attachment handler for common transition
   * 
   * @return
   */
  protected IHandler createDefaultAttachmentHandler() {
    return new DefaultAttachmentHandler();
  }

  /**
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeSelectionContextsHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.SELECTION_CONTEXTS_HANDLER, activityParams);
    if (handler == null) {
      handler = createDefaultSelectionContextsHandler();
    }
    if (handler instanceof CompoundSelectionContextHandler) {
      initializeSelectionContextHandlers(context, (CompoundSelectionContextHandler) handler, activityParams);
    }
    context.put(ITransitionConstants.SELECTION_CONTEXTS_HANDLER, handler);
    handler.init(context);
    return Status.OK_STATUS;
  }

  /**
   * @param context
   * @param handler
   * @param activityParams
   */
  protected void initializeSelectionContextHandlers(IContext context, CompoundSelectionContextHandler handler,
      ActivityParameters activityParams) {
    handler.addSelectionContext(context, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION,
        new TransformationSelectionContext());
  }

  /**
   * @return
   */
  protected IHandler createDefaultSelectionContextsHandler() {
    return new DefaultSelectionContextsHandler();
  }

  /**
   * Initialize the Scope handler and set it into context via SCOPE_HANDLER
   * 
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeScopeHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.SCOPE_HANDLER, activityParams);
    if (handler == null) {
      handler = createDefaultScopeHandler();
    }
    if (handler instanceof CompoundScopeFilter) {
      initializeScopeFilterHandlers(context, (CompoundScopeFilter) handler, activityParams);
    }
    if (handler instanceof CompoundScopeRetriever) {
      initializeScopeRetrieverHandlers(context, (CompoundScopeRetriever) handler, activityParams);
    }
    context.put(ITransitionConstants.SCOPE_HANDLER, handler);
    handler.init(context);
    return Status.OK_STATUS;
  }

  protected IStatus initializeContextScopeHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.CONTEXT_SCOPE_HANDLER, activityParams);
    if (handler == null) {
      handler = createDefaultContextScopeHandler();
    }
    context.put(ITransitionConstants.CONTEXT_SCOPE_HANDLER, handler);
    handler.init(context);
    return Status.OK_STATUS;
  }

  /**
   * @param iContext1
   * @param compoundScopeRetriever1
   * @param activityParams
   */
  protected IStatus initializeScopeRetrieverHandlers(IContext context, CompoundScopeRetriever scope,
      ActivityParameters activityParams) {
    // Add a scope retriever based on IRuleScope implementations
    scope.addScopeRetriever(new RuleRelatedElementsScopeRetriever(), context);

    for (Object handler : ExtensionHelper.collectFromExtensions(context, ISchemaConstants.EXTENSION_ID,
        ISchemaConstants.SCOPE_RETRIEVER, (String) context.get(ITransitionConstants.TRANSPOSER_PURPOSE),
        (String) context.get(ITransitionConstants.TRANSPOSER_MAPPING))) {
      if (handler instanceof IScopeRetriever) {
        scope.addScopeRetriever((IScopeRetriever) handler, context);
      }
    }

    return Status.OK_STATUS;
  }

  /**
   * Initialize scope filters
   * 
   * @param context
   * @param compoundScopeFilter1
   * @param activityParams
   * @return
   */
  protected IStatus initializeScopeFilterHandlers(IContext context, CompoundScopeFilter scope,
      ActivityParameters activityParams) {

    for (Object handler : ExtensionHelper.collectFromExtensions(context, ISchemaConstants.EXTENSION_ID,
        ISchemaConstants.SCOPE_FILTER, (String) context.get(ITransitionConstants.TRANSPOSER_PURPOSE),
        (String) context.get(ITransitionConstants.TRANSPOSER_MAPPING))) {
      if (handler instanceof IScopeFilter) {
        scope.addScopeFilter((IScopeFilter) handler, context);
      }
    }
    return Status.OK_STATUS;
  }

  /**
   * Create default scope handler for common transition
   * 
   * @return
   */
  protected IHandler createDefaultContextScopeHandler() {
    return new DefaultContextScopeHandler();
  }

  /**
   * Create default scope handler for common transition
   * 
   * @return
   */
  protected IHandler createDefaultScopeHandler() {
    return new DefaultScopeHandler();
  }

  /**
   * Initialize the Transformation handler and set it into context via TRANSFORMATION_HANDLER
   * 
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeTransformationHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.TRANSFORMATION_HANDLER, activityParams);
    if (handler == null) {
      handler = createDefaultTransformationHandler();
    }
    context.put(ITransitionConstants.TRANSFORMATION_HANDLER, handler);
    handler.init(context);
    return Status.OK_STATUS;
  }

  /**
   * Create default transformation handler for common transition
   * 
   * @return
   */
  protected IHandler createDefaultTransformationHandler() {
    return new DefaultTransformationHandler();
  }

  /**
   * @param context
   * @param activityParams
   * @return
   */
  protected IStatus initializeSessionHandler(IContext context, ActivityParameters activityParams) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.SESSION_HANDLER, activityParams);
    if (handler == null) {
      handler = createDefaultSessionHandler();
    }
    context.put(ITransitionConstants.SESSION_HANDLER, handler);
    handler.init(context);
    return Status.OK_STATUS;
  }

  /**
   * Create default session handler for common transition
   * 
   * @return
   */
  protected IHandler createDefaultSessionHandler() {
    return new DefaultSessionHandler();
  }

}
