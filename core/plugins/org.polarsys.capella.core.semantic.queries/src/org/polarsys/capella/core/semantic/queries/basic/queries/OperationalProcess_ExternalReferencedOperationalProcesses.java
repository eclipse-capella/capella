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
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;

public class OperationalProcess_ExternalReferencedOperationalProcesses implements IQuery {

  public OperationalProcess_ExternalReferencedOperationalProcesses() {
  }

  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof OperationalProcess) {
      OperationalProcess op = (OperationalProcess) object;
      result.addAll(FunctionalChainExt.getFlatInvolvementsOf(op, OaPackage.Literals.OPERATIONAL_PROCESS));
      result.removeAll(op.getOwnedFunctionalChainInvolvements());
    }
    return result;
  }
}
