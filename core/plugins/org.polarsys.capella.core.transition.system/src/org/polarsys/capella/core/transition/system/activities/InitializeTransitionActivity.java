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
package org.polarsys.capella.core.transition.system.activities;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
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
  protected IStatus initializeContext(IContext context_p, ActivityParameters activityParams_p) {
    // Initialize handlers and source/target of transition
    IStatus status = super.initializeContext(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeOptimizations(context_p, activityParams_p);
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
  protected IStatus initializeScopeRetrieverHandlers(IContext context_p, CompoundScopeRetriever handler_p, ActivityParameters activityParams_p) {
    //Add a scope retriever based on IRuleScope implementations
    handler_p.addScopeRetriever(new RuleRootElementsScopeRetriever(), context_p);
    handler_p.addScopeRetriever(new RuleContainersScopeRetriever(), context_p);
    return super.initializeScopeRetrieverHandlers(context_p, handler_p, activityParams_p);
  }

  protected IStatus initializeOptimizations(IContext context_p, ActivityParameters activityParams_p) {
    IHandler handler = new CrossReferencerHandler();
    context_p.put(ISystemConstants.CROSS_REFERENCER_HANDLER, handler);
    handler.init(context_p);
    return Status.OK_STATUS;
  }

  @Override
  protected IStatus initializeTransitionSources(IContext context_p, ActivityParameters activityParams_p) {
    Collection<Object> selection = (Collection) context_p.get(ITransitionConstants.TRANSITION_SELECTION);

    context_p.put(ITransitionConstants.TRANSITION_SOURCES, adaptSelection(selection));

    return Status.OK_STATUS;
  }

  /**
   * Adapt selection to a system transition
   * (all graphical elements are mapped to their semantic elements)
   * @param selection_p
   * @return
   */
  protected Collection<Object> adaptSelection(Collection<Object> selection_p) {
    Collection<Object> result = SemanticHelper.getSemanticObjects(selection_p);
    return result;
  }

  /**
   * @param context_p
   * @param activityParams_p
   */
  @Override
  protected IStatus initializeSource(IContext context_p, ActivityParameters activityParams_p) {
    Collection<EObject> selection = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    if (selection.size() > 0) {

      EObject source = (EObject) selection.toArray()[0];
      if (!(source instanceof CapellaElement)) {
        return new Status(IStatus.ERROR, Messages.Activity_Transition, "Input selection is not a CapellaElement");
      }

      ensureOpening(source);
      context_p.put(ITransitionConstants.TRANSITION_SOURCE_ROOT, SystemEngineeringExt.getSystemEngineering((CapellaElement) source));
      context_p.put(ITransitionConstants.TRANSITION_SOURCE_RESOURCE, source.eResource());
      context_p.put(ITransitionConstants.TRANSITION_SOURCE_ARCHITECTURE, BlockArchitectureExt.getRootBlockArchitecture(source));

    } else {
      return new Status(IStatus.ERROR, Messages.Activity_Transition, "No input selection");
    }

    return Status.OK_STATUS;
  }

  @Override
  protected IStatus initializeTarget(IContext context_p, ActivityParameters activityParams_p) {
    //default transition, targetResource is same resource
    Resource sourceResource = (Resource) context_p.get(ITransitionConstants.TRANSITION_SOURCE_RESOURCE);
    Resource outputResource = sourceResource;

    if ((outputResource != null) && (outputResource.getContents().size() != 0)) {
      context_p.put(ITransitionConstants.TRANSITION_TARGET_RESOURCE, outputResource);

      Project project = (Project) org.polarsys.capella.core.model.helpers.CapellaElementExt.getRoot((CapellaElement) outputResource.getContents().get(0));

      SystemEngineering sysengineering = null;
      Object sourceRoot = context_p.get(ITransitionConstants.TRANSITION_SOURCE_ROOT);
      if (sourceRoot instanceof SystemEngineering) {
        sysengineering = (SystemEngineering) sourceRoot;
      } else {
        sysengineering = getEngineering(project, project.getName());
      }
      if (sysengineering != null) {
        context_p.put(ITransitionConstants.TRANSITION_TARGET_ROOT, sysengineering);
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
  protected void ensureOpening(EObject source_p) {
    Session session = SessionManager.INSTANCE.getSession(source_p);
    if ((session == null) && (source_p instanceof CapellaElement)) {
      CapellaElement element = SystemEngineeringExt.getSystemEngineering((CapellaElement) source_p);
      EObject root = element.eContainer();
      root.eResource();
    }
  }

  protected SystemEngineering getEngineering(Project project_p, String name_p) {

    for (ModelRoot root : project_p.getOwnedModelRoots()) {
      if (root instanceof SystemEngineering) {
        if (((SystemEngineering) root).getName().equals(name_p)) {
          return (SystemEngineering) root;
        }
      }
    }

    if (!project_p.getOwnedModelRoots().isEmpty()) {
      if (project_p.getOwnedModelRoots().size() == 1) {
        ModelRoot root = project_p.getOwnedModelRoots().iterator().next();
        if (root instanceof SystemEngineering) {
          return (SystemEngineering) root;
        }
      }
    }

    return null;
  }
}
