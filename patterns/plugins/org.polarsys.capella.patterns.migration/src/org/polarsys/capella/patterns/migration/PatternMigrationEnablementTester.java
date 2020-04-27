/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.patterns.migration;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.diffmerge.patterns.repositories.catalogs.PatternCatalogResourceHelper;

public class PatternMigrationEnablementTester extends PropertyTester {

  /**
   * 
   */
  public PatternMigrationEnablementTester() {
    //
  }

  /**
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[],
   *      java.lang.Object)
   */
  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
    boolean result = false;
    if (property.equals(getClass().getSimpleName())) {
      if (receiver instanceof IResource) {
        try {
          return isValidReource((IResource) receiver);
        } catch (CoreException e) {
          e.printStackTrace();
        }
      }
    }
    return result;
  }

  /**
   * @param project
   * @return
   * @throws CoreException
   */
  private boolean isValidReource(IResource receiver) throws CoreException {
    return PatternCatalogResourceHelper.isPatternCatalogResource(receiver);
  }

}
