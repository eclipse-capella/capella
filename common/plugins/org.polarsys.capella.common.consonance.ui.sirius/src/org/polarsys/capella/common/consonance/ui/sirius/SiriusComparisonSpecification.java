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
package org.polarsys.capella.common.consonance.ui.sirius;

import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.ui.gmf.GMFComparisonSpecification;
import org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification;


/**
 * A definition of Sirius comparisons.
 */
public class SiriusComparisonSpecification extends GMFComparisonSpecification {
  
  /**
   * Constructor
   * @param leftScopeSpec_p a non-null scope specification
   * @param rightScopeSpec_p a non-null scope specification
   * @param ancestorScopeSpec_p an optional scope specification
   */
  public SiriusComparisonSpecification(IScopeSpecification leftScopeSpec_p,
      IScopeSpecification rightScopeSpec_p, IScopeSpecification ancestorScopeSpec_p) {
    super(leftScopeSpec_p, rightScopeSpec_p, ancestorScopeSpec_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.gmf.GmfComparisonSpecification#createDiffPolicy()
   */
  @Override
  protected IDiffPolicy createDiffPolicy() {
    return new SiriusDiffPolicy();
  }
  
}
