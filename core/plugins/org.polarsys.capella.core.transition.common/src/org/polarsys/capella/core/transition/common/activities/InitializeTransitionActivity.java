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
package org.polarsys.capella.core.transition.common.activities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
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
   * @see org.polarsys.kitalpha.cadence.core.api.IActivity#run(org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters)
   */
  @Override
  public IStatus _run(ActivityParameters activityParams_p) {
    IStatus status = Status.OK_STATUS;

    IContext context = (IContext) activityParams_p.getParameter(ITransposerWorkflow.TRANSPOSER_CONTEXT).getValue();

    status = initializeContext(context, activityParams_p);

    return status;
  }

  /**
   * Should initialize RESOURCE_SET, MERGE_CONTEXT, RULE_HANDLER
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeContext(IContext context_p, ActivityParameters activityParams_p) {

    IStatus status = Status.OK_STATUS;

    context_p.put(ITransitionConstants.TRANSPOSER_APPLY_REQUIRED, Boolean.TRUE);
    context_p.put(ITransitionConstants.TRANSFORMED_ELEMENTS, new HashSet<EObject>());

    context_p.put(ITransitionConstants.TRANSITION_SELECTION, context_p.get(ITransitionConstants.TRANSPOSER_SELECTION));

    status = initializeTransitionSources(context_p, activityParams_p);

    IRulesHandler ruleHandler = (IRulesHandler) activityParams_p.getParameter(PARAMETER_RULE_HANDLER).getValue();
    context_p.put(ITransitionConstants.RULES_HANDLER, ruleHandler);

    // Initialize handlers and source/target of transition
    status = initializeSource(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status =
        checkParameters(context_p, new String[] { ITransitionConstants.TRANSITION_SOURCE_ROOT, ITransitionConstants.TRANSITION_SOURCE_RESOURCE,
                                                 ITransitionConstants.TRANSITION_SOURCE_EDITING_DOMAIN });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeNotifyHandler(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { ITransitionConstants.NOTIFY_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeOptionsHandler(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { ITransitionConstants.OPTIONS_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = configureLogHandler(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeAttachmentHandler(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { ITransitionConstants.ATTACHMENT_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeContextScopeHandler(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { ITransitionConstants.CONTEXT_SCOPE_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeScopeHandler(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { ITransitionConstants.SCOPE_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }
    status = initializeSessionHandler(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { ITransitionConstants.SESSION_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeTransformationHandler(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { ITransitionConstants.TRANSFORMATION_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeTraceabilityTargetHandler(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { ITransitionConstants.TRACEABILITY_TARGET_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeSelectionContextsHandler(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { ITransitionConstants.SELECTION_CONTEXTS_HANDLER });
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeTarget(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status =
        checkParameters(context_p, new String[] { ITransitionConstants.TRANSITION_TARGET_ROOT, ITransitionConstants.TRANSITION_TARGET_RESOURCE,
                                                 ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN });
    if (!checkStatus(status)) {
      return status;
    }

    return Status.OK_STATUS;
  }

  // FIXME refactor duplicated code
  public Collection<Object> getSemanticObjects(Collection<Object> elements_p) {
    Collection<Object> result = new ArrayList<Object>();
    for (Object object : elements_p) {
      Object semantic = resolveSemanticObject(object);
      if (semantic != null) {
        result.add(semantic);
      }
    }
    return result;
  }

  public Object resolveSemanticObject(Object object_p) {
    Object semantic = null;

    if (object_p != null) {
      if (object_p instanceof EObject) {
        semantic = object_p;

      } else if (object_p instanceof IAdaptable) {
        Object adapter = ((IAdaptable) object_p).getAdapter(EObject.class);
        if (adapter instanceof EObject) {
          semantic = adapter;
        }
      }
    }
    return semantic;
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus configureLogHandler(IContext context_p, ActivityParameters activityParams_p) {
    LogHelper.getInstance().setLevel(Level.DEBUG);
    return Status.OK_STATUS;
  }

  /**
   * TRANSITION_SOURCES must be initialized
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeTransitionSources(IContext context_p, ActivityParameters activityParams_p) {
    Collection<Object> selection = (Collection) context_p.get(ITransitionConstants.TRANSITION_SELECTION);

    context_p.put(ITransitionConstants.TRANSITION_SOURCES, selection);

    return Status.OK_STATUS;
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeTraceabilityTargetHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.TRACEABILITY_TARGET_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultTraceabilityTargetHandler();
    }
    context_p.put(ITransitionConstants.TRACEABILITY_TARGET_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  /**
   * Create default transformation traceability handler for common transition
   * @return
   */
  protected IHandler createDefaultTraceabilityTargetHandler() {
    return ITraceabilityHandler.DEFAULT;
  }

  /**
   * Initialize TRANSITION_SOURCE_ROOT and TRANSITION_SOURCE_RESOURCE and TRANSITION_SOURCE_EDITING_DOMAIN according to selection
   * 
   * in a common transition, 
   * TRANSITION_SOURCE_ROOT = TRANSITION_SOURCES.get(0)
   * TRANSITION_SOURCE_RESOURCE = TRANSITION_SOURCE_ROOT.eResource
   * TRANSITION_SOURCE_EDITING_DOMAIN = editingDomain(TRANSITION_SOURCE_RESOURCE)
   */
  protected IStatus initializeSource(IContext context_p, ActivityParameters activityParams_p) {
    Collection<Object> selection = (Collection<Object>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    if (selection.size() > 0) {
      Object source = selection.toArray()[0];
      context_p.put(ITransitionConstants.TRANSITION_SOURCE_ROOT, source);
      if (source instanceof EObject) {
        Resource res = ((EObject) source).eResource();
        context_p.put(ITransitionConstants.TRANSITION_SOURCE_RESOURCE, res);
        context_p.put(ITransitionConstants.TRANSITION_SOURCE_EDITING_DOMAIN, TransactionUtil.getEditingDomain(res));
      }

    } else {
      return new Status(IStatus.ERROR, Messages.Activity_Transition, "No input selection");
    }

    return Status.OK_STATUS;
  }

  /**
   * Initialize TRANSITION_TARGET_ROOT and TRANSITION_TARGET_RESOURCE and TRANSITION_TARGET_EDITING_DOMAIN  according to selection
   * @param context_p
   * @param activityParams_p
   */
  protected abstract IStatus initializeTarget(IContext context_p, ActivityParameters activityParams_p);

  /**
   * Initialize the Notify handler and set it into context via ITransitionConstants.NOTIFY_HANDLER
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeNotifyHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.NOTIFY_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultNotifyHandler();
    }

    IStatus status = handler.init(context_p);
    context_p.put(ITransitionConstants.NOTIFY_HANDLER, handler);
    return status;
  }

  /**
   * Create default options handler for common transition
   * @return
   */
  protected IHandler createDefaultNotifyHandler() {
    return new DefaultNotifyHandler();
  }

  /**
   * Initialize the Options handler and set it into context via ITransitionConstants.OPTIONS_HANDLER
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeOptionsHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.OPTIONS_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultOptionsHandler();
    }

    String optionScope = loadStringFromParameters(ITransitionConstants.OPTIONS_SCOPE, activityParams_p);
    if (optionScope == null) {
      optionScope = getDefaultOptionsScope();
    }
    context_p.put(ITransitionConstants.OPTIONS_PARAMETERS, activityParams_p);
    context_p.put(ITransitionConstants.OPTIONS_SCOPE, optionScope);
    context_p.put(ITransitionConstants.OPTIONS_HANDLER, handler);
    IStatus status = handler.init(context_p);
    return status;
  }

  /**
   * Create default options handler for common transition
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
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeAttachmentHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.ATTACHMENT_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultAttachmentHandler();
    }
    context_p.put(ITransitionConstants.ATTACHMENT_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  /**
   * Create default attachment handler for common transition
   * @return
   */
  protected IHandler createDefaultAttachmentHandler() {
    return new DefaultAttachmentHandler();
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeSelectionContextsHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.SELECTION_CONTEXTS_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultSelectionContextsHandler();
    }
    if (handler instanceof CompoundSelectionContextHandler) {
      initializeSelectionContextHandlers(context_p, (CompoundSelectionContextHandler) handler, activityParams_p);
    }
    context_p.put(ITransitionConstants.SELECTION_CONTEXTS_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  /**
   * @param context_p
   * @param handler_p
   * @param activityParams_p
   */
  protected void initializeSelectionContextHandlers(IContext context_p, CompoundSelectionContextHandler handler_p, ActivityParameters activityParams_p) {
    handler_p.addSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION, new TransformationSelectionContext());
  }

  /**
   * @return
   */
  protected IHandler createDefaultSelectionContextsHandler() {
    return new DefaultSelectionContextsHandler();
  }

  /**
   * Initialize the Scope handler and set it into context via SCOPE_HANDLER
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeScopeHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.SCOPE_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultScopeHandler();
    }
    if (handler instanceof CompoundScopeFilter) {
      initializeScopeFilterHandlers(context_p, (CompoundScopeFilter) handler, activityParams_p);
    }
    if (handler instanceof CompoundScopeRetriever) {
      initializeScopeRetrieverHandlers(context_p, (CompoundScopeRetriever) handler, activityParams_p);
    }
    context_p.put(ITransitionConstants.SCOPE_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  protected IStatus initializeContextScopeHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.CONTEXT_SCOPE_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultContextScopeHandler();
    }
    context_p.put(ITransitionConstants.CONTEXT_SCOPE_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  /**
   * @param context_p
   * @param handler_p
   * @param activityParams_p
   */
  protected IStatus initializeScopeRetrieverHandlers(IContext context_p, CompoundScopeRetriever handler_p, ActivityParameters activityParams_p) {
    //Add a scope retriever based on IRuleScope implementations
    handler_p.addScopeRetriever(new RuleRelatedElementsScopeRetriever(), context_p);

    for (Object handler : ExtensionHelper.collectFromExtensions(context_p, ISchemaConstants.EXTENSION_ID, ISchemaConstants.SCOPE_RETRIEVER)) {
      if (handler instanceof IScopeRetriever) {
        handler_p.addScopeRetriever((IScopeRetriever) handler, context_p);
      }
    }

    return Status.OK_STATUS;
  }

  /**
   * Initialize scope filters
   * @param context_p
   * @param handler_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeScopeFilterHandlers(IContext context_p, CompoundScopeFilter handler_p, ActivityParameters activityParams_p) {
    for (Object handler : ExtensionHelper.collectFromExtensions(context_p, ISchemaConstants.EXTENSION_ID, ISchemaConstants.SCOPE_FILTER)) {
      if (handler instanceof IScopeFilter) {
        handler_p.addScopeFilter((IScopeFilter) handler, context_p);
      }
    }
    return Status.OK_STATUS;
  }

  /**
   * Create default scope handler for common transition
   * @return
   */
  protected IHandler createDefaultContextScopeHandler() {
    return new DefaultContextScopeHandler();
  }

  /**
   * Create default scope handler for common transition
   * @return
   */
  protected IHandler createDefaultScopeHandler() {
    return new DefaultScopeHandler();
  }

  /**
   * Initialize the Transformation handler and set it into context via TRANSFORMATION_HANDLER
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeTransformationHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.TRANSFORMATION_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultTransformationHandler();
    }
    context_p.put(ITransitionConstants.TRANSFORMATION_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  /**
   * Create default transformation handler for common transition
   * @return
   */
  protected IHandler createDefaultTransformationHandler() {
    return new DefaultTransformationHandler();
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  protected IStatus initializeSessionHandler(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = loadHandlerFromParameters(ITransitionConstants.SESSION_HANDLER, activityParams_p);
    if (handler == null) {
      handler = createDefaultSessionHandler();
    }
    context_p.put(ITransitionConstants.SESSION_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  /**
   * Create default session handler for common transition
   * @return
   */
  protected IHandler createDefaultSessionHandler() {
    return new DefaultSessionHandler();
  }

}
