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
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;

public class AbstractCapability_ExternalReferencedFunctionalChains implements IQuery {

  public AbstractCapability_ExternalReferencedFunctionalChains() {
  }

  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (isValidType(object)) {
      if (object instanceof AbstractCapability) {
        AbstractCapability c = (AbstractCapability) object;
        for (FunctionalChain ownedFC : c.getOwnedFunctionalChains()) {
          result.addAll(FunctionalChainExt.getFlatInvolvedElements(ownedFC, FaPackage.Literals.FUNCTIONAL_CHAIN));
        }
        result.removeAll(c.getInvolvedFunctionalChains());
      }
    }
    return result;
  }

  protected boolean isValidType(Object object) {
    return (!(object instanceof OperationalCapability) && (object instanceof AbstractCapability));
  }

}
