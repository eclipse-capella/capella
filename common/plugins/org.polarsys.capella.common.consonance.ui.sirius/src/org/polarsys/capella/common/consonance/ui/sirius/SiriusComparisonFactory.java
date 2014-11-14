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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.diffmerge.ui.gmf.GMFComparisonFactory;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecification;
import org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification;


/**
 * A factory of Sirius comparisons.
 */
public class SiriusComparisonFactory extends GMFComparisonFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecificationFactory#getLabel()
   */
  @Override
  public String getLabel() {
    return Messages.SiriusComparisonFactory_Label;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonSpecificationFactory#getOverridenClasses()
   */
  @Override
  public Collection<Class<?>> getOverridenClasses() {
    return Collections.<Class<?>>singleton(GMFComparisonFactory.class);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonSpecificationFactory#createComparisonSpecification(org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification, org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification, org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification)
   */
  @Override
  public IComparisonSpecification createComparisonSpecification(
      IScopeSpecification leftScopeSpec_p,
      IScopeSpecification rightScopeSpec_p,
      IScopeSpecification ancestorScopeSpec_p) {
    return new SiriusComparisonSpecification(leftScopeSpec_p, rightScopeSpec_p, ancestorScopeSpec_p);
  }
  
}
