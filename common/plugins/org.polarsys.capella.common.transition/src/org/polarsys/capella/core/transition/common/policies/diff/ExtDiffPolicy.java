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

package org.polarsys.capella.core.transition.common.policies.diff;

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ExtDiffPolicy extends DefaultDiffPolicy implements IDiffPolicy2 {

  private IContext context;

  protected IContext getContext() {
    return context;
  }

  public ExtDiffPolicy(IContext context) {
    this.context = context;
  }

  @Override
  public boolean coverMatch(IMatch match) {
    return super.coverMatch(match);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean considerEqual(Object value1, Object value2, EAttribute attribute) {
    return super.considerEqual(value1, value2, attribute);
  }

  /**
   * {@inheritDoc}
   */
  public boolean coverMatchOnReference(IMatch match, EReference reference) {
    //Default implementation
    return coverMatch(match);
  }
}
