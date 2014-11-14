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
package org.polarsys.capella.core.projection.actorsandinterfaces.ctx2la;


import org.polarsys.capella.core.tiger.impl.TransfoRuleBase;


/**
 */
public class ActorTransfoRuleBase extends TransfoRuleBase {
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
