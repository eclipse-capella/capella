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

package org.polarsys.capella.core.transition.common.policies.match;

import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A match policy with a IContext
 */
public class ContextMatchPolicy extends DefaultMatchPolicy {

  private IContext context;

  /**
   * @return the context
   */
  public IContext getContext() {
    return context;
  }

  /**
   * Constructor
   * @param a non-null mapping of corresponding elements whose further modifications will impact this policy
   */
  public ContextMatchPolicy(IContext context) {
    this.context = context;
  }
}
