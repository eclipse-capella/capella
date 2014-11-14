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
package org.polarsys.capella.common.re.handlers.traceability;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.util.structures.FArrayList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.notify.INotifyChangeEvent;
import org.polarsys.capella.core.transition.common.handlers.notify.INotifyListener;
import org.polarsys.capella.core.transition.common.handlers.traceability.LevelBasedTraceabilityHandler;
import org.polarsys.capella.core.transition.common.merge.ExtendedComparison;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class MatchTraceabilityHandler extends LevelBasedTraceabilityHandler implements INotifyListener {
  ExtendedComparison _comparison;

  public MatchTraceabilityHandler(ExtendedComparison comparison_p, String identifier_p) {
    super(identifier_p);
    _comparison = comparison_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context_p) {
    initializeRootMappings(context_p);
    return super.init(context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getLevelElement(EObject source_p, IContext context_p) {
    return null;
  }

  protected CatalogElementLink getOriginLink(CatalogElementLink link_p) {
    CatalogElementLink result = link_p;
    while (result.getOrigin() != null) {
      result = result.getOrigin();
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Deprecated
  public String getId(EObject element_p, IContext context_p) {
    return super.getId(element_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initializeRootMappings(IContext context_p) {
    super.initializeRootMappings(context_p);

    if (_comparison != null) { //Update replica
      _comparison.getMapping().getContents();

      TreeIterator<IMatch> targetMatches = _comparison.getAllContents(Role.TARGET);
      TreeIterator<IMatch> referenceMatches = _comparison.getAllContents(Role.REFERENCE);
      List<IMatch> result = new FArrayList<IMatch>();

      while (targetMatches.hasNext()) {
        IMatch match = targetMatches.next();
        result.add(match);
      }
      while (referenceMatches.hasNext()) {
        IMatch match = referenceMatches.next();
        if (!result.contains(match)) {
          result.add(match);
        }
      }

      for (IMatch match : result) {
        EObject reference = match.get(Role.REFERENCE);
        EObject target = match.get(Role.TARGET);

        if ((reference != null) && (target != null)) {
          addMappings(reference, target, context_p);
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void notifyChanged(INotifyChangeEvent event_p, IContext context_p) {
    //Nothing here
  }

}
