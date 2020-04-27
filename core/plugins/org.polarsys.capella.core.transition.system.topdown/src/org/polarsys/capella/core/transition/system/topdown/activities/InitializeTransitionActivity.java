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
package org.polarsys.capella.core.transition.system.topdown.activities;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.CompoundScopeFilter;
import org.polarsys.capella.core.transition.common.handlers.scope.CompoundScopeRetriever;
import org.polarsys.capella.core.transition.common.handlers.selection.CompoundSelectionContextHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ITraceabilityConfiguration;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.attachment.TopDownAttachmentHelper;
import org.polarsys.capella.core.transition.system.topdown.handlers.level.LevelHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.handlers.scope.ContextualScopeRetriever;
import org.polarsys.capella.core.transition.system.topdown.handlers.scope.FinalizableElementFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.scope.PropertyValuesScopeRetriever;
import org.polarsys.capella.core.transition.system.topdown.handlers.selection.TransformationSelectionContextsHandler;
import org.polarsys.capella.core.transition.system.topdown.handlers.traceability.config.MergeTargetConfiguration;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * Initialize the transition: - Initialize handlers -
 */
public class InitializeTransitionActivity extends org.polarsys.capella.core.transition.system.activities.InitializeTransitionActivity {

  public static final String ID = "org.polarsys.capella.core.transition.system.topdown.activities.InitializeTransitionActivity"; //$NON-NLS-1$

  @Override
  protected String getDefaultOptionsScope() {
    return ITopDownConstants.TRANSITION_TOPDOWN;
  }

  @Override
  protected Collection<EObject> adaptSelection(Collection<Object> selection) {
    Collection<EObject> superCollection = super.adaptSelection(selection);
    Collection<EObject> result = new ArrayList<>();
    for (EObject item : superCollection) {
      if (item instanceof Part) {
        item = ((Part) item).getAbstractType();
      }
      result.add((EObject) item);
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IHandler createDefaultAttachmentHandler() {
    return new TopDownAttachmentHelper();
  }

  @Override
  protected IStatus initializeContext(IContext context, ActivityParameters activityParams) {
    IStatus status = super.initializeContext(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeTransitionKind(context, activityParams);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context, new String[] { ITopDownConstants.TRANSITION_KIND });
    if (!checkStatus(status)) {
      return status;
    }

    return status;
  }

  @Override
  protected IStatus configureLogHandler(IContext context, ActivityParameters activityParams) {
    if (!(OptionsHandlerHelper.getInstance(context).getBooleanValue(context, ITopDownConstants.OPTIONS_SCOPE, ITopDownConstants.OPTIONS_LOG,
        ITopDownConstants.OPTIONS_LOG__DEFAULT.booleanValue()))) {
      LogHelper.getInstance().setLevel(Level.ERROR);
    } else {
      return super.configureLogHandler(context, activityParams);
    }
    return Status.OK_STATUS;
  }

  /**
   * Initialize TRANSITION_KIND parameter
   */
  protected IStatus initializeTransitionKind(IContext context, ActivityParameters activityParams) {
    Object scope = context.get(ITransitionConstants.OPTIONS_SCOPE);

    context.put(ITopDownConstants.TRANSITION_KIND, scope);
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IStatus initializeScopeRetrieverHandlers(IContext context, CompoundScopeRetriever handler, ActivityParameters activityParams) {
    super.initializeScopeRetrieverHandlers(context, handler, activityParams);
    handler.addScopeRetriever(new ContextualScopeRetriever(), context);
    handler.addScopeRetriever(new PropertyValuesScopeRetriever(), context);
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IStatus initializeScopeFilterHandlers(IContext context, CompoundScopeFilter handler, ActivityParameters activityParams) {
    super.initializeScopeFilterHandlers(context, handler, activityParams);
    handler.addScopeFilter(new FinalizableElementFilter(), context);
    return Status.OK_STATUS;
  }

  @Override
  protected IHandler createDefaultSelectionContextsHandler() {
    return new TransformationSelectionContextsHandler();
  }

  @Override
  protected IHandler createDefaultTraceabilityTargetHandler() {
    ITraceabilityConfiguration configuration = new MergeTargetConfiguration();
    return new CompoundTraceabilityHandler(configuration);
  }

  @Override
  protected void initializeSelectionContextHandlers(IContext context, CompoundSelectionContextHandler handler, ActivityParameters activityParams) {
    super.initializeSelectionContextHandlers(context, handler, activityParams);

    LevelHandlerHelper.getInstance(context).initializeSelectionContexts(context, handler);
  }
}
