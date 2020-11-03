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
package org.polarsys.capella.core.transition.common.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;

/**
 * A transposer rule to retrieve some elements for the given element
 */
public interface IRuleScope extends IRule<EObject> {

  /**
   * Retrieve all root related to the current source element
   */
  List<EObject> retrieveRootElements(EObject element, IContext context);

  /**
   * Retrieve containers related to the current source element
   */
  List<EObject> retrieveContainers(EObject element, IContext context);

  /**
   * Retrieve all elements related to the current source element
   * (can be its owned elements)
   */
  List<EObject> retrieveRelatedElements(EObject element, IContext context);

  /**
   * Retrieve all elements required to be in the scope when when source element is in the scope
   */
  List<EObject> retrieveRequiredElements(EObject element, IContext context);

}
