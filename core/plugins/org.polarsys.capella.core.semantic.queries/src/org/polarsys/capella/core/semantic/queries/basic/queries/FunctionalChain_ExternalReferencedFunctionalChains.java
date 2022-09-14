/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;

public class FunctionalChain_ExternalReferencedFunctionalChains implements IQuery {

  public FunctionalChain_ExternalReferencedFunctionalChains() {
  }

  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (isValidType(object)) {
      if (object instanceof FunctionalChain) {
        FunctionalChain fc = (FunctionalChain) object;
        result.addAll(FunctionalChainExt.getFlatInvolvementsOf(fc, FaPackage.Literals.FUNCTIONAL_CHAIN));
        result.removeAll(fc.getOwnedFunctionalChainInvolvements());
      }
    }
    return result;
  }

  protected boolean isValidType(Object object) {
    return (!(object instanceof OperationalProcess) && (object instanceof FunctionalChain));
  }

}
