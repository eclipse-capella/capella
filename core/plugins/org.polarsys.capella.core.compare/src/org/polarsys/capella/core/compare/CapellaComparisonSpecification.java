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
package org.polarsys.capella.core.compare;

import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.consonance.ui.sirius.SiriusComparisonSpecification;


/**
 * A definition of Capella comparisons.
 */
public class CapellaComparisonSpecification extends SiriusComparisonSpecification {
  
  /**
   * Constructor
   * @param leftScopeSpec_p a non-null scope specification
   * @param rightScopeSpec_p a non-null scope specification
   * @param ancestorScopeSpec_p an optional scope specification
   */
  public CapellaComparisonSpecification(IScopeSpecification leftScopeSpec_p,
      IScopeSpecification rightScopeSpec_p, IScopeSpecification ancestorScopeSpec_p) {
    super(leftScopeSpec_p, rightScopeSpec_p, ancestorScopeSpec_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.SiriusComparisonSpecification#createDiffPolicy()
   */
  @Override
  protected IDiffPolicy createDiffPolicy() {
    return new CapellaDiffPolicy();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonSpecification#createMergePolicy()
   */
  @Override
  protected IMergePolicy createMergePolicy() {
    return new CapellaMergePolicy();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonSpecification#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    CapellaComparePlugin.getDefault().cleanupProxyProjects();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonSpecification#doGetEditingDomain()
   */
  @Override
  protected EditingDomain doGetEditingDomain() {
    EditingDomain result = CapellaComparePlugin.getDefault().getEditingDomain();
    if (result == null)
      result = super.doGetEditingDomain();
    return result;
  }
  
}
