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
package org.polarsys.capella.core.transfo.operationalactivity.rules;

import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.projection.common.rules.fa.Rule_FunctionalChain;

/**
 * This transformation rule is the one applied to the operational processes during functional transitions
 */
public class Rule_OperationalProcess extends Rule_FunctionalChain {

  public Rule_OperationalProcess() {
    // the source is an Operational Process and the target a functional Chain
    super(OaPackage.Literals.OPERATIONAL_PROCESS, FaPackage.Literals.FUNCTIONAL_CHAIN, FaPackage.Literals.FUNCTIONAL_CHAIN_REALIZATION);
  }

}
