/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.oa.OperationalProcess;

/**
 * return chains involved in operational process
 */
public class OperationalProcessChildren extends FunctionalChainChildren {

  public OperationalProcessChildren() {
    // Does nothing
  }

  @Override
  public boolean isValidInstanceOf(FunctionalChain functionalChain) {
    return functionalChain instanceof OperationalProcess;
  }

}
