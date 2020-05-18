/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.common;


import org.polarsys.capella.core.tiger.impl.TransfoRuleBase;


/**
 */
public class ProjectionRuleBase extends TransfoRuleBase {
  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRuleBase#loadRules(java.lang.String, java.lang.String[])
   */
  @Override
  public void loadRules(String rulePkgName_p, String[] classNames_p) throws ClassNotFoundException {
    for (String className : classNames_p) {
      Class<?> class_ = Class.forName(rulePkgName_p + "." + className);  //$NON-NLS-1$
      loadRule(class_);
    }
  }
}
