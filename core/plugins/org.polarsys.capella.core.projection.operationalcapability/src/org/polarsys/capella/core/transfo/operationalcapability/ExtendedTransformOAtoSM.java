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
package org.polarsys.capella.core.transfo.operationalcapability;

import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;

/**
 * Transform OA that handles the deep transformation case.<br>
 */
public class ExtendedTransformOAtoSM extends TransformOperationalActivity {

  /**
   * Tiger context used. This context is declared implementing an extension point.
   */
  private static final String CAPELLA_OPERATIONALCAPABILITIES_RULES = "capella.operationalcapabilities.rules.tomission"; //$NON-NLS-1$

  /**
   * @throws ClassNotFoundException
   * @see org.polarsys.capella.core.transfo.systemfunction.TransformSystemFunction#createTransfo(org.polarsys.capella.core.tiger.ITransfoRuleBase)
   */
  @Override
  protected ITransfo createTransfo(ITransfoRuleBase ruleBase_p) throws ClassNotFoundException {
    return super.createTransfo(ruleBase_p, CAPELLA_OPERATIONALCAPABILITIES_RULES);
  }

}
