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
   * Returns the default container to store the result_p element
   */
  public EObject retrieveDefaultContainer(EObject element_p, EObject result_p, IContext context_p);

  /**
   * Returns the containementFeature which should be used for the result_p element when stored into container_p
   */
  public EStructuralFeature retrieveTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p);

}
