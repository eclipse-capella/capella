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

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

/**
 * A CatalogElementScope can find and filter all members of a catalog element by class
 */
public class CatalogElementScope {

  private final CatalogElement catalogElement;

  private final Function<CatalogElementLink, EObject> linkToElement = new Function<CatalogElementLink, EObject>() {
    @Override
    public EObject apply(CatalogElementLink input) {
      return input.getTarget();
    }
  };

  public CatalogElementScope(CatalogElement catalogElement) {
    this.catalogElement = catalogElement;
  }

  /**
   * Returns an unmodifiable list that includes all members of this scopes catalog element which are instances of the
   * given class and satisfy an optional filter.
   * 
   * @param clazz
   * @param filter
   * @return
   */
  public <T> ImmutableList<T> getElements(Class<T> clazz, Predicate<? super T> filter) {

    Predicate<? super T> p = filter;
    if (p == null) {
      p = Predicates.alwaysTrue();
    }

    return FluentIterable.from(catalogElement.getOwnedLinks()).transform(linkToElement).filter(clazz).filter(p)
        .toList();
  }

  /**
   * Returns an unmodifiable list that includes all members of this scopes catalog element which are instances of the
   * given class.
   * 
   * See {@link #getElements(Class, Predicate)}
   * 
   * @param clazz
   * @return
   */
  public <T> Collection<T> getElements(Class<T> clazz) {
    return getElements(clazz, Predicates.alwaysTrue());
  }

  public Collection<EObject> getElements() {
    return Collections2.transform(catalogElement.getOwnedLinks(), linkToElement);
  }

}