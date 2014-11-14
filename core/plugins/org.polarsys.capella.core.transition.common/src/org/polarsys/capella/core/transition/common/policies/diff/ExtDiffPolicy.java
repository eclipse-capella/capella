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

  public ExtDiffPolicy(IContext context_p) {
    context = context_p;
  }

  @Override
  public boolean coverMatch(IMatch match_p) {
    return super.coverMatch(match_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean considerEqual(Object value1_p, Object value2_p, EAttribute attribute_p) {
    return super.considerEqual(value1_p, value2_p, attribute_p);
  }

  /**
   * {@inheritDoc}
   */
  public boolean coverMatchOnReference(IMatch match_p, EReference reference_p) {
    //Default implementation
    return coverMatch(match_p);
  }
}
