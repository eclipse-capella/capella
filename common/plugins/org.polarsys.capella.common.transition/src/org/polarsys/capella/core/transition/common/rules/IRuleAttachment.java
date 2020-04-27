/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;

/**
 * A transposer rule to retrieve 
 *
 * This interface will be changed soon to be merged with current AbstractRule
 */
public interface IRuleAttachment extends IRule<EObject> {

  /**
   * Returns the default container to store the result element
   */
  EObject retrieveDefaultContainer(EObject element, EObject result, IContext context);

  /**
   * Returns the containementFeature which should be used for the result element when stored into container
   */
  EStructuralFeature retrieveTargetContainementFeature(EObject element, EObject result, EObject container, IContext context);

}
