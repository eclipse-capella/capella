/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.semantic.queries.basic.queries.utils;

import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;

public class QueryHelper {
  
  private QueryHelper() {
  }
  
  public static FunctionalChain getFunctionalChain(Object functionalChainOrFunctionalChainReference) {
    if (functionalChainOrFunctionalChainReference instanceof FunctionalChain) {
      return (FunctionalChain) functionalChainOrFunctionalChainReference;
    }
    if (functionalChainOrFunctionalChainReference instanceof FunctionalChainReference) {
      return ((FunctionalChainReference) functionalChainOrFunctionalChainReference).getReferencedFunctionalChain();
    }
    return null;
  }
}
