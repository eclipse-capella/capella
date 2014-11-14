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
package org.polarsys.capella.core.transition.system.topdown.activities;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ITraceabilityConfiguration;
import org.polarsys.capella.core.transition.common.merge.scope.IModelScopeFilter;
import org.polarsys.capella.core.transition.common.merge.scope.PartialRootedModelScope;
import org.polarsys.capella.core.transition.system.topdown.handlers.level.ILevelHandler.Level;
import org.polarsys.capella.core.transition.system.topdown.handlers.level.LevelHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.handlers.traceability.config.MergeSourceConfiguration;
import org.polarsys.capella.core.transition.system.topdown.handlers.traceability.config.MergeTargetConfiguration;
import org.polarsys.capella.core.transition.system.topdown.merge.scope.ReferenceModelScope;
import org.polarsys.capella.core.transition.system.topdown.merge.scope.TargetModelScope;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class InitializeDiffMergeActivity extends org.polarsys.capella.core.transition.system.activities.InitializeDiffMergeActivity {

  public static final String ID = "org.polarsys.capella.core.transition.system.topdown.activities.InitializeDiffMergeActivity"; //$NON-NLS-1$

  @Override
  protected IHandler createDefaultTraceabilitySourceHandler() {
    ITraceabilityConfiguration configuration = new MergeSourceConfiguration();
    return new CompoundTraceabilityHandler(configuration);
  }

  @Override
  protected IHandler createDefaultTraceabilityTargetHandler() {
    ITraceabilityConfiguration configuration = new MergeTargetConfiguration();
    return new CompoundTraceabilityHandler(configuration);
  }

  @Override
  protected IStatus initializeReferenceScope(IContext context_p, ActivityParameters activityParams_p) {
    EObject sourceTop = (EObject) context_p.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
    context_p.put(ITransitionConstants.MERGE_REFERENCE_CONTAINER, sourceTop);

    List<EObject> rootSource = new ArrayList<EObject>();
    rootSource.add((EObject) context_p.get(ITransitionConstants.MERGE_REFERENCE_CONTAINER));

    IFeaturedModelScope sourceScope = new ReferenceModelScope(rootSource, context_p);

    context_p.put(ITransitionConstants.MERGE_REFERENCE_SCOPE, sourceScope);

    ((PartialRootedModelScope) sourceScope).build(getReferenceFilter(context_p));

    return Status.OK_STATUS;
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  @Override
  protected IStatus initializeTargetScope(IContext context_p, ActivityParameters activityParams_p) {

    EObject targetTop = (EObject) context_p.get(ITransitionConstants.TRANSITION_TARGET_ROOT);
    context_p.put(ITransitionConstants.MERGE_TARGET_CONTAINER, targetTop);

    List<EObject> rootTarget = new ArrayList<EObject>();
    rootTarget.add((EObject) context_p.get(ITransitionConstants.MERGE_TARGET_CONTAINER));

    TargetModelScope targetScope = new TargetModelScope(rootTarget, context_p);
    context_p.put(ITransitionConstants.MERGE_TARGET_SCOPE, targetScope);

    ((PartialRootedModelScope) targetScope).build(getTargetFilter(context_p));

    return Status.OK_STATUS;
  }

  @Override
  protected IModelScopeFilter getTargetFilter(final IContext context_p) {
    return new IModelScopeFilter() {
      public boolean accepts(EObject element_p) {
        BlockArchitecture architectureElement = BlockArchitectureExt.getRootBlockArchitecture(element_p);
        if (architectureElement == null) {
          return true;
        }
        for (Level level : LevelHandlerHelper.getInstance(context_p).getLevels(context_p)) {
          EClass clazz = LevelHandlerHelper.getInstance(context_p).getLevel(context_p, level);
          if ((clazz != null) && clazz.isInstance(architectureElement)) {
            return true;
          }
        }
        return false;
      }
    };
  }
}
