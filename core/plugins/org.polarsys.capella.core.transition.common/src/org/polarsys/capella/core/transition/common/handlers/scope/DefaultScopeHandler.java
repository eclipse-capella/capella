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
package org.polarsys.capella.core.transition.common.handlers.scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DefaultScopeHandler implements IScopeHandler {

  Collection<IScopeFilter> childsFilters;

  Collection<IScopeRetriever> childsRetrievers;

  public Collection<EObject> retrieveElements(EObject source_p, IContext context_p) {
    Collection<EObject> elements = new LinkedList<EObject>();
    if (childsRetrievers != null) {
      for (IScopeRetriever retriever : childsRetrievers) {
        Collection<? extends EObject> retrievedElements = retriever.retrieveRelatedElements(source_p, context_p);
        if (retrievedElements != null) {
          elements.addAll(retrievedElements);
        }
      }
    }
    return elements;
  }

  public boolean isInScope(EObject element_p, IContext context_p) {
    Collection<EObject> scope = getScope(context_p);
    return ((scope != null) && scope.contains(element_p));
  }

  public IStatus computeScope(Collection<EObject> bootstrap_p, IContext context_p) {
    Collection<EObject> scope = computeScopeInternal(bootstrap_p, context_p);
    ContextScopeHandlerHelper.getInstance(context_p).clear(ITransitionConstants.TRANSITION_SCOPE, context_p);
    ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.TRANSITION_SCOPE, scope, context_p);
    return Status.OK_STATUS;
  }

  public Collection<EObject> getScope(IContext context_p) {
    return ContextScopeHandlerHelper.getInstance(context_p).getCollection(ITransitionConstants.TRANSITION_SCOPE, context_p);
  }

  /**
   * Perform a go to deep to retrieve the scope
   * @param bootstrap
   * @param context
   * @param ruleHandler
   */
  protected Collection<EObject> computeScopeInternal(Collection<EObject> bootstrap, IContext context) {

    HashSet<EObject> _agenda = new HashSet<EObject>();
    LinkedList<EObject> agenda = new LinkedList<EObject>();

    HashSet<EObject> visited = new HashSet<EObject>();

    // Add the bootstrap in the agenda
    if (bootstrap != null) {
      for (EObject element : bootstrap) {
        if (isValidScopeElement(element, context)) {
          agenda.add(element);
        }
      }
    }

    // While the agenda is not empty
    while (!agenda.isEmpty()) {
      EObject currentElement = agenda.removeFirst();

      // No 'null' element in the agenda
      if (currentElement == null) {
        continue;
      }

      if (visited.contains(currentElement)) {
        continue;
      }
      visited.add(currentElement);
      _agenda.add(currentElement);

      // Retrieve related elements
      Collection<EObject> relatedElements = retrieveElements(currentElement, context);

      if (relatedElements != null) {
        for (EObject relatedElement : relatedElements) {
          if ((relatedElement != null) && isValidScopeElement(relatedElement, context)) {
            agenda.add(relatedElement);
          }
        }
      }
    }

    if (childsRetrievers != null) {
      for (IScopeRetriever retriever : childsRetrievers) {
        Collection<? extends EObject> retrievedElements = retriever.retrieveSharedElements(context);

        if (retrievedElements != null) {
          for (EObject relatedElement : retrievedElements) {
            if ((relatedElement != null) && isValidScopeElement(relatedElement, context)) {
              _agenda.add(relatedElement);
            }
          }
        }
      }
    }

    return _agenda;
  }

  /**
   * @param relatedElement_p
   * @return
   */
  public boolean isValidScopeElement(EObject element_p, IContext context_p) {
    if (childsFilters != null) {
      for (IScopeFilter filter : childsFilters) {
        if (!filter.isValidScopeElement(element_p, context_p)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context_p) {
    if (childsFilters != null) {
      for (IScopeFilter filter : childsFilters) {
        filter.init(context_p);
      }
    }
    if (childsRetrievers != null) {
      for (IScopeRetriever retriever : childsRetrievers) {
        retriever.init(context_p);
      }
    }

    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context_p) {
    if (childsFilters != null) {
      for (IScopeFilter filter : childsFilters) {
        filter.dispose(context_p);
      }
    }
    if (childsRetrievers != null) {
      for (IScopeRetriever retriever : childsRetrievers) {
        retriever.dispose(context_p);
      }
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public void addScopeFilter(IScopeFilter filter_p, IContext context_p) {
    if (filter_p != null) {
      if (childsFilters == null) {
        childsFilters = new ArrayList<IScopeFilter>();
      }
      childsFilters.add(filter_p);
    }

  }

  /**
   * {@inheritDoc}
   */
  public void addScopeRetriever(IScopeRetriever retriever_p, IContext context_p) {
    if (retriever_p != null) {
      if (childsRetrievers == null) {
        childsRetrievers = new ArrayList<IScopeRetriever>();
      }
      childsRetrievers.add(retriever_p);
    }
  }
}
