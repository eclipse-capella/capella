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
package org.polarsys.capella.core.transition.common.policies.merge;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.filter.CompoundFilteringItems;
import org.polarsys.capella.core.transition.common.handlers.filter.FilteringDifferencesHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.filter.IFilterItem;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ExtMergePolicy extends DefaultMergePolicy implements IHandler, IMergePolicy2 {

  protected static final String MERGE_POLICY__UNCOPY_FEATURES = "MERGE_POLICY__UNCOPY_FEATURES"; //$NON-NLS-1$

  private IContext _context;

  protected Collection<EStructuralFeature> getUnwantedFeatures(IContext context_p) {
    if (!context_p.exists(MERGE_POLICY__UNCOPY_FEATURES)) {
      context_p.put(MERGE_POLICY__UNCOPY_FEATURES, new HashSet<EStructuralFeature>());
    }
    return (Collection<EStructuralFeature>) context_p.get(MERGE_POLICY__UNCOPY_FEATURES);
  }

  public IContext getContext() {
    return _context;
  }

  public ExtMergePolicy(IContext context_p) {
    _context = context_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean copyFeature(EStructuralFeature feature_p) {
    IContext context = getContext();

    if (getUnwantedFeatures(context).contains(feature_p)) {
      return false;
    }

    IHandler handler = FilteringDifferencesHandlerHelper.getInstance(context);
    if (handler instanceof CompoundFilteringItems) {
      for (IFilterItem item : ((CompoundFilteringItems) handler).getFilterItems(context)) {
        if (!item.isMergeable(feature_p, context)) {
          getUnwantedFeatures(context).add(feature_p);
          return false;
        }
      }
    }

    return super.copyFeature(feature_p);
  }

  /**
   * {@inheritDoc}
   */
  public boolean copy(EObject source_p) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context_p) {
    getUnwantedFeatures(context_p).clear();
    return Status.OK_STATUS;
  }

}
