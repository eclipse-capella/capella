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

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;

import com.google.common.collect.Iterators;

/**
 * The diff/merge match policy used to compare connections between pairs of REC/RPL.
 */
public class UpdateConnectionsMatchPolicy implements IMatchPolicy {

  /* maps elements in rpl1 and rpl2 to their corresponding rec elements */
  private final Map<EObject, EObject> rpl2rec;
  private final ConnectedCatalogElementsScope rplScope;
  private final ConnectedCatalogElementsScope recScope;

  private final ConnectionMatcher connectionMatcher;

  /**
   * Creates a match policy for the specified connected rec/rpl scopes, delegating connection
   * match id calculation to the supplied connection matcher.
   * 
   * @param rplScope
   * @param recScope
   * @param connectionMatcher
   */
  public UpdateConnectionsMatchPolicy(ConnectedCatalogElementsScope rplScope, ConnectedCatalogElementsScope recScope,
      ConnectionMatcher connectionMatcher) {
    this.recScope = recScope;
    this.rplScope = rplScope;
    this.connectionMatcher = connectionMatcher;
    rpl2rec = buildRpl2RecMap(rplScope.getLeftCatalogElement(), rplScope.getRightCatalogElement());
  }

  private Map<EObject, EObject> buildRpl2RecMap(CatalogElement leftRpl, CatalogElement rightRpl) {
    Map<EObject, EObject> result = new HashMap<EObject, EObject>();
    Iterator<CatalogElementLink> rplLinks = Iterators.concat(leftRpl.getOwnedLinks().iterator(),
        rightRpl.getOwnedLinks().iterator());
    while (rplLinks.hasNext()) {
      CatalogElementLink nextLink = rplLinks.next();
      result.put(nextLink.getTarget(), nextLink.getOrigin().getTarget());
    }
    return result;
  }

  public ConnectedCatalogElementsScope getRplScope() {
    return rplScope;
  }

  public ConnectedCatalogElementsScope getRecScope() {
    return recScope;
  }

  @Override
  /**
   * If element is part of either rpl1 or rpl2, returns the corresponding object in rec1/rec2. Otherwise, if the
   * element is an connection in either the rplScope or the recScope, delegates to the connection match function.
   * Otherwise the argument is returned
   */
  public Object getMatchID(EObject element, IModelScope scope) {

    Connection rplConnection = rplScope.adapt(element);
    if (rplConnection != null) {
      return connectionMatcher.getMatchID(rplConnection, scope, this);
    }
    Connection recConnection = recScope.adapt(element);
    if (recConnection != null) {
      return connectionMatcher.getMatchID(recConnection, scope, this);
    }

    if (element instanceof AbstractTrace) {
      TraceableElement sourceElement = ((AbstractTrace) element).getSourceElement();
      TraceableElement targetElement = ((AbstractTrace) element).getTargetElement();
      return new RelationshipMatchID(getMatchID(sourceElement, scope), getMatchID(targetElement, scope),
          element.getClass());
    }

    if (element instanceof PhysicalLinkEnd) {
      PhysicalLinkEnd end = (PhysicalLinkEnd) element;
      return new RelationshipMatchID(getMatchID(end.getPart(), scope), getMatchID(end.getPort(), scope),
          element.getClass());
    }

    if (element instanceof ComponentExchangeEnd) {
      ComponentExchangeEnd end = (ComponentExchangeEnd) element;
      return new RelationshipMatchID(getMatchID(end.getPart(), scope), getMatchID(end.getPort(), scope),
          element.getClass());
    }

    Object result = rpl2rec.get(element);

    if (result == null) {
      result = element;
    }

    return result;
  }

  @Override
  public Comparator<?> getMatchIDComparator() {
    return null;
  }

  @Override
  public boolean keepMatchIDs() {
    return false;
  }

}