/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.internal.navigate;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryRegistry;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;

/**
 * Provides services to navigation from a {@link ModelElement} to other {@link ModelElement} according to semantic
 * rules.
 */
public class NavigationAdvisor {

  private static NavigationAdvisor singleton;

  private NavigationAdvisor() {

  }

  /**
   * Get navigable elements for given element.
   * 
   * @param element
   * @return a not <code>null</code> set, empty if nothing found. Returned set can't contain <code>null</code> value.
   */
  public Set<EObject> getNavigableElements(Object receiver) {

    if (receiver instanceof IMarker) {
      return MarkerViewHelper.getModelElementsFromMarker((IMarker) receiver)//
          .stream() //
          .filter(Objects::nonNull) //
          .collect(Collectors.toSet());

    }

    EObject element = CapellaAdapterHelper.resolveSemanticObject(receiver);
    Set<EObject> navigableElements = computeNavigableElements(element);

    if (element instanceof Part) {
      Part part = (Part) element;
      AbstractType partType = part.getAbstractType();

      if (partType instanceof Component) {
        navigableElements.addAll(computeNavigableElements(partType));
      }
    }

    return navigableElements;
  }

  private Set<EObject> computeNavigableElements(EObject element) {
    CategoryRegistry categoryRegistry = CategoryRegistry.getInstance();
    List<ICategory> categories = categoryRegistry.gatherRelatedElementsCategories(element);

    return categories.stream() //
        .flatMap(category -> category.compute(element).stream()) //
        .filter(EObject.class::isInstance).map(EObject.class::cast) //
        .collect(Collectors.toSet());
  }


  public static NavigationAdvisor getInstance() {
    if (null == singleton) {
      singleton = new NavigationAdvisor();
    }
    return singleton;
  }
}
