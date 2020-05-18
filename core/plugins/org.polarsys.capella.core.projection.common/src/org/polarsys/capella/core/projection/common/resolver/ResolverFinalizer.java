/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.common.resolver;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.tiger.IFinalizer;
import org.polarsys.capella.core.tiger.IResolver;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;

/**
 *
 */
public class ResolverFinalizer implements IFinalizer, IResolver {

  private static String RESOLVER_MAPPINGS = "resolverMappings"; //$NON-NLS-1$

  @SuppressWarnings("unchecked")
  protected Map<InternalValue<EObject>, List<EObject>> getMappings(ITransfo transfo_p) {
    if (transfo_p.get(RESOLVER_MAPPINGS) == null) {
      transfo_p.put(RESOLVER_MAPPINGS, new HashMap<InternalValue<EObject>, List<EObject>>());
    }
    return (Map<InternalValue<EObject>, List<EObject>>) transfo_p.get(RESOLVER_MAPPINGS);
  }

  protected List<EObject> getCachedValue(EObject[] context_p, ITransfo transfo_p) {
    InternalValue<EObject> key = new InternalValue<EObject>(context_p);
    Map<InternalValue<EObject>, List<EObject>> mappings = getMappings(transfo_p);

    return mappings.get(key);
  }

  protected void setCachedValue(EObject[] context_p, List<EObject> value_p, ITransfo transfo_p) {
    InternalValue<EObject> key = new InternalValue<EObject>(context_p);
    Map<InternalValue<EObject>, List<EObject>> mappings = getMappings(transfo_p);
    mappings.put(key, value_p);
  }

  @SuppressWarnings("unchecked")
  protected void setCachedValue(EObject[] context_p, EObject value_p, ITransfo transfo_p) {
    InternalValue<EObject> key = new InternalValue<EObject>(context_p);
    Map<InternalValue<EObject>, List<EObject>> mappings = getMappings(transfo_p);
    mappings.put(key, (List<EObject>) Collections.singleton(value_p));
  }

  protected class InternalValue<A> {
    A[] table;

    public InternalValue(A[] firstValue_p) {
      table = firstValue_p;
    }

    @Override
    public int hashCode() {
      int hash = 17;
      if (table != null) {
        for (A element : table) {
          hash += (31 * element.hashCode());
        }
      }

      return hash;
    }

    @Override
    //Allow also null values in this case
    public boolean equals(Object obj_p) {
      if (obj_p instanceof InternalValue<?>) {
        InternalValue<?> p = (InternalValue<?>) obj_p;
        if (p.table == table) {
          return true;

        } else if (p.table != null && table != null) {
          if (p.table.length == table.length) {
            for (int i = 0; i < table.length; i++) {
              if (p.table[i] == table[i] || (table[i] != null && table[i].equals(p.table[i]))) {
                return true;
              }
            }
          }
        }
      }
      return false;
    }
  }

  /**
   * @see org.polarsys.capella.core.tiger.IFinalizer#finalize(org.polarsys.capella.core.tiger.ITransfo)
   */
  public void finalize(ITransfo transfo_p) {
    if (transfo_p != null && transfo_p.containsKey(RESOLVER_MAPPINGS)) {
      Object result = transfo_p.get(RESOLVER_MAPPINGS);
      if (result != null && result instanceof List<?>) {
        ((List<?>) result).clear();
        transfo_p.remove(RESOLVER_MAPPINGS);
      }
    }
  }

  /**
   * @see org.polarsys.capella.core.tiger.IResolver#resolve(org.eclipse.emf.ecore.EObject, java.util.List, java.lang.String, java.lang.String, boolean, org.polarsys.capella.core.tiger.ITransfo, org.eclipse.emf.ecore.EObject[])
   */
  public List<EObject> resolve(EObject source_p, List<EObject> items_p, String title_p, String message_p, boolean multipleSelection_p, ITransfo transfo_p,
      EObject[] context_p) {

    List<EObject> resultObject = getCachedValue(context_p, transfo_p);
    if (resultObject != null) {
      return resultObject;
    }

    IResolver resolver = Query.getResolver(transfo_p);
    if (resolver != null) {
      resultObject = resolver.resolve(source_p, items_p, title_p, message_p, multipleSelection_p, transfo_p, context_p);
    }

    if (resultObject != null && resultObject.size() > 0) {
      setCachedValue(context_p, resultObject, transfo_p);
    }

    return resultObject;
  }

  /**
   * Retrieve a resolver for the given transfo_p
   */
  public static ResolverFinalizer getResolver(ITransfo transfo_p) {
    for (IFinalizer finalizer : transfo_p.getFinalizers()) {
      if (finalizer instanceof ResolverFinalizer) {
        return (ResolverFinalizer) finalizer;
      }
    }
    return null;
  }

}
