/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.compare;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.diffmerge.ui.sirius.SiriusComparisonMethodFactory;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.ecore.EObject;


/**
 * A factory for Capella comparisons.
 */
public class CapellaComparisonMethodFactory extends SiriusComparisonMethodFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.sirius.SiriusComparisonMethodFactory#createComparisonMethod(org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition, org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition, org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition)
   */
  @Override
  public IComparisonMethod<EObject> createComparisonMethod(
      IModelScopeDefinition leftScopeSpec, IModelScopeDefinition rightScopeSpec,
      IModelScopeDefinition ancestorScopeSpec) {
    return new CapellaComparisonMethod(
        leftScopeSpec, rightScopeSpec, ancestorScopeSpec, this);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.sirius.SiriusComparisonMethodFactory#getLabel()
   */
  @Override
  public String getLabel() {
    return Messages.CapellaComparisonFactory_Label;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.sirius.SiriusComparisonMethodFactory#getOverridenClasses()
   */
  @Override
  public Collection<Class<?>> getOverridenClasses() {
    return Collections.<Class<?>>singleton(SiriusComparisonMethodFactory.class);
  }
  
}
