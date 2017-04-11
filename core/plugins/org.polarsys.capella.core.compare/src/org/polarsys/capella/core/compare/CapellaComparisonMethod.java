/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy;
import org.eclipse.emf.diffmerge.ui.sirius.SiriusComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory;


/**
 * A definition of Capella comparisons.
 */
public class CapellaComparisonMethod extends SiriusComparisonMethod {
  
  /**
   * Constructor
   * @param leftScopeSpec a non-null scope specification
   * @param rightScopeSpec a non-null scope specification
   * @param ancestorScopeSpec an optional scope specification
   */
  public CapellaComparisonMethod(IModelScopeDefinition leftScopeSpec,
      IModelScopeDefinition rightScopeSpec, IModelScopeDefinition ancestorScopeSpec) {
    super(leftScopeSpec, rightScopeSpec, ancestorScopeSpec);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethod#createEditingDomain()
   */
  @Override
  protected EditingDomain createEditingDomain() {
    SemanticEditingDomainFactory factory = new SemanticEditingDomainFactory();
    EditingDomain result = factory.createEditingDomain();
    return result;
  }
  
  /**
   * @see org.polarsys.capella.common.consonance.ui.sirius.SiriusComparisonMethod#createDiffPolicy()
   */
  @Override
  protected IDiffPolicy createDiffPolicy() {
    return new CapellaDiffPolicy();
  }
  
  /**
   * @see org.polarsys.capella.common.consonance.ui.sirius.SiriusComparisonMethod#createMatchPolicy()
   */
  @Override
  protected IMatchPolicy createMatchPolicy() {
    return new DefaultMatchPolicy();
  }
  
  /**
   * @see org.polarsys.capella.common.consonance.ui.sirius.SiriusComparisonMethod#createMergePolicy()
   */
  @Override
  protected IMergePolicy createMergePolicy() {
    return new CapellaMergePolicy();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethod#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    CapellaComparePlugin.getDefault().cleanupProxyProjects();
  }
  
}
