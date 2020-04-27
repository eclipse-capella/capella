/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.common.policies.merge;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.merge.ICategoryItem;
import org.polarsys.capella.core.transition.common.handlers.merge.IMergeHandler;
import org.polarsys.capella.core.transition.common.handlers.merge.MergeHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ExtMergePolicy extends DefaultMergePolicy implements IHandler, IMergePolicy2 {

  protected static final String MERGE_POLICY__UNCOPY_FEATURES = "MERGE_POLICY__UNCOPY_FEATURES"; //$NON-NLS-1$

  private IContext context;

  protected Collection<EStructuralFeature> getUnwantedFeatures(IContext context) {
    if (!context.exists(MERGE_POLICY__UNCOPY_FEATURES)) {
      context.put(MERGE_POLICY__UNCOPY_FEATURES, new HashSet<EStructuralFeature>());
    }
    return (Collection<EStructuralFeature>) context.get(MERGE_POLICY__UNCOPY_FEATURES);
  }

  public IContext getContext() {
    return context;
  }

  public ExtMergePolicy(IContext context) {
    this.context = context;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean copyFeature(EStructuralFeature feature, IFeaturedModelScope scope) {
    IContext context = getContext();

    if (getUnwantedFeatures(context).contains(feature)) {
      return false;
    }

    IMergeHandler handler = MergeHandlerHelper.getInstance(context);
    for (ICategoryItem item : ((IMergeHandler) handler).getCategories(context)) {
      if (item.isActive() && !item.isInFocusMode() && item.covers(feature)) {
        getUnwantedFeatures(context).add(feature);
        return false;
      }
    }

    return super.copyFeature(feature, scope);
  }

  /**
   * {@inheritDoc}
   */
  public boolean copy(EObject source) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context) {
    getUnwantedFeatures(context).clear();
    return Status.OK_STATUS;
  }

  @Override
  public void setDependencies(IMergeableDifference difference) {
    IHandler handler = MergeHandlerHelper.getInstance(context);
    for (ICategoryItem item : ((IMergeHandler) handler).getCategories(context)) {
      if (item.covers(difference)) {
        item.setDependencies(difference);
      }
    }
  }

}
