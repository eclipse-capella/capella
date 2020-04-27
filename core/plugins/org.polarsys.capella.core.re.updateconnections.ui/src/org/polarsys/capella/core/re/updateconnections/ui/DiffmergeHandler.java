/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.updateconnections.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy;
import org.polarsys.capella.common.re.CatalogElement;

/**
 * Provides a diff/merge comparison to update connections between pairs of REC/RPL elements.
 */
public class DiffmergeHandler {

  private UpdateConnectionsMatchPolicy matchPolicy;

  private SparseModelScope recModelScope;
  private SparseModelScope rplModelScope;

  /**
   * Initialize a new instance to compare connections between a pair of RPL elements
   * and those between its corresponding REC elements.  
   * 
   * @param rpl1 the first RPL element
   * @param rpl2 the second RPL element
   * @param connectionMatcher a matcher used to calculate connection match identifiers
   */
  public DiffmergeHandler(CatalogElement rpl1, CatalogElement rpl2,
      ConnectionMatcher connectionMatcher) {
    ConnectedCatalogElementsScope recScope = new ConnectedCatalogElementsScope(rpl1.getOrigin(), rpl2.getOrigin());
    ConnectedCatalogElementsScope rplScope = new ConnectedCatalogElementsScope(rpl1, rpl2);
    matchPolicy = new UpdateConnectionsMatchPolicy(rplScope, recScope, connectionMatcher);
    ConnectedCatalogElementsScope.complement(recScope, rplScope);

    recModelScope = recScope.asDiffmergeScope();
    recModelScope.setOriginator(Messages.DiffmergeHandler_0);
    rplModelScope = rplScope.asDiffmergeScope();
    rplModelScope.setOriginator(Messages.DiffmergeHandler_1);

  }

  /**
   * Calculate the differences, setting the rec scope as the target scope and the rpl scope as the reference scope.
   * 
   * @param monitor
   * @return
   */
  public EComparison computeDifferences(IProgressMonitor monitor) {
    EComparison comparison = new EComparisonImpl(recModelScope, rplModelScope);
    recModelScope.setMapping(comparison.getMapping());
    rplModelScope.setMapping(comparison.getMapping());
    comparison.compute(matchPolicy, new DefaultDiffPolicy(), new UpdateConnectionsMergePolicy(), monitor);
    return comparison;
  }

}
