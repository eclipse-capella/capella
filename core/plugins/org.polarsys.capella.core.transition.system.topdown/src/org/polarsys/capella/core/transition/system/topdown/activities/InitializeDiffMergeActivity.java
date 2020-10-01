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
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.merge.ICategoryItem;
import org.polarsys.capella.core.transition.common.handlers.merge.IMergeHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ITraceabilityConfiguration;
import org.polarsys.capella.core.transition.common.merge.scope.IModelScopeFilter;
import org.polarsys.capella.core.transition.common.merge.scope.PartialRootedModelScope;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.level.ILevelHandler.Level;
import org.polarsys.capella.core.transition.system.topdown.handlers.level.LevelHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.AppliedPropertyValuesCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.ArchitectureLinkCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.EmptyPackageCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.NoLeafFunctionalAllocationCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.OutsideArchitectureCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.RealizationLinkCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.RemoveRealizedCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.TargetDifferencesCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.UpdatedAttributeCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.merge.UpdatedReferenceCategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.traceability.config.MergeSourceConfiguration;
import org.polarsys.capella.core.transition.system.topdown.handlers.traceability.config.MergeTargetConfiguration;
import org.polarsys.capella.core.transition.system.topdown.merge.scope.ReferenceModelScope;
import org.polarsys.capella.core.transition.system.topdown.merge.scope.TargetModelScope;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class InitializeDiffMergeActivity
    extends org.polarsys.capella.core.transition.system.activities.InitializeDiffMergeActivity {

  public static final String ID = "org.polarsys.capella.core.transition.system.topdown.activities.InitializeDiffMergeActivity"; //$NON-NLS-1$

  @Override
  protected IHandler createDefaultTraceabilitySourceHandler(IContext context) {
    ITraceabilityConfiguration configuration = new MergeSourceConfiguration();
    return new CompoundTraceabilityHandler(configuration);
  }

  @Override
  protected IHandler createDefaultTraceabilityTargetHandler(IContext context) {
    ITraceabilityConfiguration configuration = new MergeTargetConfiguration();
    return new CompoundTraceabilityHandler(configuration);
  }

  @Override
  protected IStatus initializeReferenceScope(IContext context, ActivityParameters activityParams) {
    EObject sourceTop = (EObject) context.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
    context.put(ITransitionConstants.MERGE_REFERENCE_CONTAINER, sourceTop);

    List<EObject> rootSource = new ArrayList<EObject>();
    rootSource.add((EObject) context.get(ITransitionConstants.MERGE_REFERENCE_CONTAINER));

    IEditableModelScope sourceScope = new ReferenceModelScope(rootSource, context);

    context.put(ITransitionConstants.MERGE_REFERENCE_SCOPE, sourceScope);

    ((PartialRootedModelScope) sourceScope).build(getReferenceFilter(context));

    return Status.OK_STATUS;
  }

  /**
   * @param context_p
   * @param activityParams_p
   * @return
   */
  @Override
  protected IStatus initializeTargetScope(IContext context, ActivityParameters activityParams) {

    EObject targetTop = (EObject) context.get(ITransitionConstants.TRANSITION_TARGET_ROOT);
    context.put(ITransitionConstants.MERGE_TARGET_CONTAINER, targetTop);

    List<EObject> rootTarget = new ArrayList<>();
    rootTarget.add((EObject) context.get(ITransitionConstants.MERGE_TARGET_CONTAINER));

    TargetModelScope targetScope = new TargetModelScope(rootTarget, context);
    context.put(ITransitionConstants.MERGE_TARGET_SCOPE, targetScope);
    targetScope.build(getTargetFilter(context));

    return Status.OK_STATUS;
  }

  @Override
  protected IStatus initializeCategoriesHandlers(IContext context, IMergeHandler handler,
      ActivityParameters activityParams) {
    super.initializeCategoriesHandlers(context, handler, activityParams);

    handler.addCategory(new AppliedPropertyValuesCategoryFilter(context), context);
    handler.addCategory(new TargetDifferencesCategoryFilter(context), context);
    handler.addCategory(new OutsideArchitectureCategoryFilter(context), context);
    handler.addCategory(new RealizationLinkCategoryFilter(context), context);
    handler.addCategory(new ArchitectureLinkCategoryFilter(context), context);
    handler.addCategory(new EmptyPackageCategoryFilter(context), context);
    handler.addCategory(new UpdatedAttributeCategoryFilter(context), context);
    handler.addCategory(new UpdatedReferenceCategoryFilter(context), context);
    handler.addCategory(new RemoveRealizedCategoryFilter(context), context);
    handler.addCategory(new NoLeafFunctionalAllocationCategoryFilter(context), context);
    
    adaptToCommand(context, handler);
    return Status.OK_STATUS;
  }

  /**
   * Perform some adaptation according to the current Transition (enable filters, disable ones)
   */
  protected void adaptToCommand(IContext context, IMergeHandler handler) {
    String kind = (String) context.get(ITopDownConstants.TRANSITION_KIND);
    if (ITopDownConstants.TRANSITION_TOPDOWN_OE2SYSTEM.equals(kind)) {
      ICategoryItem item = (ICategoryItem) handler.getCategory(context, RealizationLinkCategoryFilter.ID);
      if (item != null) {
        item.setInFocusMode(true);
      }
    }
  }

  @Override
  protected IModelScopeFilter getTargetFilter(final IContext context) {
    return new IModelScopeFilter() {
      public boolean accepts(EObject element) {
        BlockArchitecture architectureElement = BlockArchitectureExt.getRootBlockArchitecture(element);
        if (architectureElement == null) {
          return true;
        }
        for (Level level : LevelHandlerHelper.getInstance(context).getLevels(context)) {
          EClass clazz = LevelHandlerHelper.getInstance(context).getLevel(context, level);
          if ((clazz != null) && clazz.isInstance(architectureElement)) {
            return true;
          }
        }
        return false;
      }
    };
  }
}
