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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.diffmerge.ui.sirius.SiriusComparisonMethodFactory;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;


/**
 * A factory for Capella comparisons.
 */
public class CapellaComparisonMethodFactory extends SiriusComparisonMethodFactory {
  
  /**
   * @see org.polarsys.capella.common.consonance.ui.sirius.SiriusComparisonMethodFactory#createComparisonMethod(org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition, org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition, org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition)
   */
  @Override
  public IComparisonMethod createComparisonMethod(
      IModelScopeDefinition leftScopeSpec, IModelScopeDefinition rightScopeSpec,
      IModelScopeDefinition ancestorScopeSpec) {
    return new CapellaComparisonMethod(
        leftScopeSpec, rightScopeSpec, ancestorScopeSpec);
  }
  
  /**
   * @see org.polarsys.capella.common.consonance.ui.sirius.SiriusComparisonMethodFactory#getLabel()
   */
  @Override
  public String getLabel() {
    return Messages.CapellaComparisonFactory_Label;
  }
  
  /**
   * @see org.polarsys.capella.common.consonance.ui.sirius.SiriusComparisonMethodFactory#getOverridenClasses()
   */
  @Override
  public Collection<Class<?>> getOverridenClasses() {
    return Collections.<Class<?>>singleton(SiriusComparisonMethodFactory.class);
  }
  
}
