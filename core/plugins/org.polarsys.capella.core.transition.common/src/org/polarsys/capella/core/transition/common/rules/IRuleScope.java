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
package org.polarsys.capella.core.transition.common.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public interface IRuleScope {

  /**
   * Retrieve all root related to the current source element
   */
  public List<EObject> retrieveRootElements(EObject element_p, IContext context_p);

  /**
   * Retrieve containers related to the current source element
   */
  public List<EObject> retrieveContainers(EObject element_p, IContext context_p);

  /**
   * Retrieve all elements related to the current source element
   * (can be its owned elements)
   */
  public List<EObject> retrieveRelatedElements(EObject element_p, IContext context_p);

  /**
   * Retrieve all elements required to be in the scope when when source element is in the scope
   */
  public List<EObject> retrieveRequiredElements(EObject element_p, IContext context_p);

}
