/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class MigrationEnablementTester extends PropertyTester {

  /**
   * 
   */
  public MigrationEnablementTester() {
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
      if (receiver instanceof IProject) {
        try {
          return isValidProject((IProject) receiver);
        } catch (CoreException e) {
          e.printStackTrace();
        }
      } else if (receiver instanceof IResource) {
        try {
          return isValidProject(((IResource) receiver).getProject());
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
  private boolean isValidProject(IProject project) throws CoreException {
    return true;
  }

}
