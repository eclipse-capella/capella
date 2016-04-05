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

package org.polarsys.capella.core.transition.system.activities;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.scope.CompoundScopeRetriever;
import org.polarsys.capella.core.transition.common.handlers.scope.RuleContainersScopeRetriever;
import org.polarsys.capella.core.transition.common.handlers.scope.RuleRootElementsScopeRetriever;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ITraceabilityConfiguration;
import org.polarsys.capella.core.transition.system.constants.ISystemConstants;
import org.polarsys.capella.core.transition.system.handlers.attachment.CapellaDefaultAttachmentHandler;
import org.polarsys.capella.core.transition.system.handlers.optimize.CrossReferencerHandler;
import org.polarsys.capella.core.transition.system.handlers.traceability.config.MergeTargetConfiguration;
import org.polarsys.capella.core.transition.system.handlers.transformation.CapellaTransformationHandler;
import org.polarsys.capella.core.transition.system.helpers.SemanticHelper;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * Initialize the transition: - Initialize handlers -
 */
public class InitializeTransitionActivity extends org.polarsys.capella.core.transition.common.activities.InitializeTransitionActivity {

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

  /**
   * @param context_p
   * @param handler_p
   * @param activityParams_p
   */
  @Override
  protected IStatus initializeScopeRetrieverHandlers(IContext context, CompoundScopeRetriever handler, ActivityParameters activityParams) {
    // Add a scope retriever based on IRuleScope implementations
    handler.addScopeRetriever(new RuleRootElementsScopeRetriever(), context);
    handler.addScopeRetriever(new RuleContainersScopeRetriever(), context);
    return super.initializeScopeRetrieverHandlers(context, handler, activityParams);
  }

  protected IStatus initializeOptimizations(IContext context, ActivityParameters activityParams) {
    IHandler handler = new CrossReferencerHandler();
    context.put(ISystemConstants.CROSS_REFERENCER_HANDLER, handler);
    handler.init(context);
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
   * @param selection_p
   * @return
   */
  protected Collection<Object> adaptSelection(Collection<Object> selection) {
    Collection<Object> result = SemanticHelper.getSemanticObjects(selection);
    return result;
  }

  /**
   * In a system transition, TRANSITION_SOURCE_ROOT = getSystemEngineering(TRANSITION_SOURCES) TRANSITION_SOURCE_RESOURCE = TRANSITION_SOURCE_ROOT.eResource
   * TRANSITION_SOURCE_EDITING_DOMAIN = editingDomain(TRANSITION_SOURCE_RESOURCE)
   */
  @Override
  protected IStatus initializeSource(IContext context, ActivityParameters activityParams) {
    Collection<EObject> selection = (Collection<EObject>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    if (selection.size() > 0) {

      EObject source = (EObject) selection.toArray()[0];
      if (!(source instanceof CapellaElement)) {
        return new Status(IStatus.ERROR, Messages.Activity_Transition, "Input selection is not a CapellaElement");
      }

      ensureOpening(source);

      EObject root = SystemEngineeringExt.getSystemEngineering((CapellaElement) source);
      context.put(ITransitionConstants.TRANSITION_SOURCE_ROOT, root);
      context.put(ITransitionConstants.TRANSITION_SOURCE_RESOURCE, root.eResource());
      context.put(ITransitionConstants.TRANSITION_SOURCE_EDITING_DOMAIN, TransactionUtil.getEditingDomain(root.eResource()));

    } else {
      return new Status(IStatus.ERROR, Messages.Activity_Transition, "No input selection");
    }

    return Status.OK_STATUS;
  }

  /**
   * In a system transition, default use is where source and target resources are the same TRANSITION_TARGET_RESOURCE = TRANSITION_SOURCE_RESOURCE
   * TRANSITION_TARGET_EDITING_DOMAIN = editingDomain(TRANSITION_TARGET_RESOURCE) TRANSITION_TARGET_ROOT = TRANSITION_SOURCE_ROOT
   */
  @Override
  protected IStatus initializeTarget(IContext context, ActivityParameters activityParams) {
    // default transition, targetResource is same resource
    Resource sourceResource = (Resource) context.get(ITransitionConstants.TRANSITION_SOURCE_RESOURCE);
    Resource outputResource = sourceResource;

    if ((outputResource != null) && (outputResource.getContents().size() != 0)) {
      context.put(ITransitionConstants.TRANSITION_TARGET_RESOURCE, outputResource);
      context.put(ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN, TransactionUtil.getEditingDomain(outputResource));

      Project project = (Project) org.polarsys.capella.core.model.helpers.CapellaElementExt.getRoot((CapellaElement) outputResource.getContents().get(0));

      SystemEngineering sysengineering = null;
      Object sourceRoot = context.get(ITransitionConstants.TRANSITION_SOURCE_ROOT);
      if (sourceRoot instanceof SystemEngineering) {
        sysengineering = (SystemEngineering) sourceRoot;
      } else {
        sysengineering = getEngineering(project, project.getName());
      }
      if (sysengineering != null) {
        context.put(ITransitionConstants.TRANSITION_TARGET_ROOT, sysengineering);
      }
    } else {
      return new Status(IStatus.ERROR, Messages.Activity_Transition, "Output model is invalid");
    }

    return Status.OK_STATUS;
  }

  /**
   * Create default attachment handler for common transition
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
  protected IHandler createDefaultTraceabilityTargetHandler() {
    ITraceabilityConfiguration configuration = new MergeTargetConfiguration();
    return new CompoundTraceabilityHandler(configuration);
  }

  /**
   * @param source_p
   */
  protected void ensureOpening(EObject source) {
    Session session = SessionManager.INSTANCE.getSession(source);
    if ((session == null) && (source instanceof CapellaElement)) {
      CapellaElement element = SystemEngineeringExt.getSystemEngineering((CapellaElement) source);
      EObject root = element.eContainer();
      root.eResource();
    }
  }

  protected SystemEngineering getEngineering(Project project, String name) {

    for (ModelRoot root : project.getOwnedModelRoots()) {
      if (root instanceof SystemEngineering) {
        if (((SystemEngineering) root).getName().equals(name)) {
          return (SystemEngineering) root;
        }
      }
    }

    if (!project.getOwnedModelRoots().isEmpty()) {
      if (project.getOwnedModelRoots().size() == 1) {
        ModelRoot root = project.getOwnedModelRoots().iterator().next();
        if (root instanceof SystemEngineering) {
          return (SystemEngineering) root;
        }
      }
    }

    return null;
  }
}
