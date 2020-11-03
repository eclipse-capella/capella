/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

  protected boolean excluding = true;

  public Collection<EObject> retrieveElements(EObject source, IContext context) {
    Collection<EObject> elements = new LinkedList<EObject>();
    if (childsRetrievers != null) {
      for (IScopeRetriever retriever : childsRetrievers) {
        Collection<? extends EObject> retrievedElements = retriever.retrieveRelatedElements(source, context);
        if (retrievedElements != null) {
          elements.addAll(retrievedElements);
        }
      }
    }
    return elements;
  }

  protected boolean isExclusing() {
    return excluding;
  }

  /**
   * Set the filtering mode : If set to true (default) (the first filter is false, implies the elements is excluded)
   * otherwise, filtering is including (the first filter is true, implies the elements is included)
   */
  public void setFilterExcluding(boolean excluding) {
    this.excluding = excluding;
  }

  public boolean isInScope(EObject element, IContext context) {
    Collection<EObject> scope = getScope(context);
    return ((scope != null) && scope.contains(element));
  }

  public IStatus computeScope(Collection<EObject> bootstrap, IContext context) {
    Collection<EObject> scope = computeScopeInternal(bootstrap, context);
    ContextScopeHandlerHelper.getInstance(context).clear(ITransitionConstants.TRANSITION_SCOPE, context);
    ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.TRANSITION_SCOPE, scope, context);
    return Status.OK_STATUS;
  }

  public Collection<EObject> getScope(IContext context) {
    return ContextScopeHandlerHelper.getInstance(context).getCollection(ITransitionConstants.TRANSITION_SCOPE, context);
  }

  /**
   * Perform a go to deep to retrieve the scope
   * 
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
  public boolean isValidScopeElement(EObject element, IContext context) {

    // If Filtering is excluding (first filter is false, implies the elements is excluded)
    if (excluding) {
      if (childsFilters != null) {
        for (IScopeFilter filter : childsFilters) {
          if (!filter.isValidScopeElement(element, context)) {
            return false;
          }
        }
      }
      return true;
    }

    // If Filtering is including (first filter is true, implies the elements is included)
    if (childsFilters != null) {
      for (IScopeFilter filter : childsFilters) {
        if (filter.isValidScopeElement(element, context)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context) {
    if (childsFilters != null) {
      for (IScopeFilter filter : childsFilters) {
        filter.init(context);
      }
    }
    if (childsRetrievers != null) {
      for (IScopeRetriever retriever : childsRetrievers) {
        retriever.init(context);
      }
    }

    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context) {
    if (childsFilters != null) {
      for (IScopeFilter filter : childsFilters) {
        filter.dispose(context);
      }
    }
    if (childsRetrievers != null) {
      for (IScopeRetriever retriever : childsRetrievers) {
        retriever.dispose(context);
      }
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public void addScopeFilter(IScopeFilter filter, IContext context) {
    if (filter != null) {
      if (childsFilters == null) {
        childsFilters = new ArrayList<IScopeFilter>();
      }
      childsFilters.add(filter);
    }

  }

  /**
   * {@inheritDoc}
   */
  public void addScopeRetriever(IScopeRetriever retriever, IContext context) {
    if (retriever != null) {
      if (childsRetrievers == null) {
        childsRetrievers = new ArrayList<IScopeRetriever>();
      }
      childsRetrievers.add(retriever);
    }
  }
}
