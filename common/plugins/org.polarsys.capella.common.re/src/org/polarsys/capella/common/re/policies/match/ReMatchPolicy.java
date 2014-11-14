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
package org.polarsys.capella.common.re.policies.match;

import java.util.Collection;

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.re.merge.scope.ReSourceScope;
import org.polarsys.capella.common.re.merge.scope.ReTargetScope;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.policies.match.TraceabilityHandlerMatchPolicy;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A match policy based on CatalogElementLink
 * For an element, retrieve the referencing CatalogElementLink 
 */
public class ReMatchPolicy extends TraceabilityHandlerMatchPolicy {

  IQueryContext queryContext;

  /**
   * @param context_p2_p
   */
  public ReMatchPolicy(IContext context_p2_p) {
    super(context_p2_p);

    queryContext = new QueryContext();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Comparable<?> getMatchID(EObject element_p, IModelScope scope_p) {
    IContext context = getContext();

    //Retrieve handlers from context
    ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context.get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
    ITraceabilityHandler targetHandler = (ITraceabilityHandler) context.get(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER);

    ITraceabilityHandler currentHandler = null;
    CatalogElement element = null;
    CatalogElement otherElement = null;

    if ((scope_p instanceof ReSourceScope) && !(scope_p instanceof ReTargetScope)) {
      currentHandler = sourceHandler;
      element = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
      otherElement = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);

    } else {
      currentHandler = targetHandler;
      element = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);
      otherElement = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
    }

    Collection<CatalogElementLink> usedLinks = ReplicableElementHandlerHelper.getInstance(context).getAllElementsLinks(element, queryContext);

    //Retrieve a link using the element_p in the given CatalogElement and its owned elements.
    //We consider that an element cannot be used two time in several CatalogElement used by the given CatalogElement
    CatalogElementLink link = null;
    Collection<EObject> links = EObjectExt.getReferencers(element_p, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
    if (links.size() > 0) {
      for (EObject linkItem : links) {
        if (usedLinks.contains(linkItem)) {
          link = (CatalogElementLink) linkItem;
          break;
        }
      }
    }
    if (link == null) {
      //sometimes, created links are not available via crossreferencer. see why..
      for (CatalogElementLink linkItem : usedLinks) {
        if (linkItem.getTarget().equals(element_p)) {
          link = linkItem;
          break;
        }
      }
    }

    if (context.exists(IReConstants.COMMAND__CURRENT_VALUE)) {
      String value = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);

      if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value) || IReConstants.COMMAND__UPDATE_CURRENT_REPLICA_FROM_REPLICA.equals(value)
          || IReConstants.COMMAND__CREATE_REPLICABLE_ELEMENT.equals(value)) {

        if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {
          //if (update replicableElement from replica)
          if (currentHandler == sourceHandler) {
            if (link != null) {
              link = link.getOrigin();
            }
          }
        }
      } else if (IReConstants.COMMAND__CREATE_A_REPLICA_FROM_REPLICABLE.equals(value) || IReConstants.COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE.equals(value)) {

        //if (update replica from replicableElement)
        if (currentHandler == targetHandler) {
          if (link != null) {
            link = link.getOrigin();
          }
        }

      }
    }

    String id = "";
    if ((link != null) && (element != null) && (otherElement != null)) {
      id = currentHandler.getId(link, context);
    } else {
      id = currentHandler.getId(element_p, context);
    }
    return id;
  }
}
