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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.semantic.queries.basic.queries.utils.QueryHelper;

public class FunctionalChainParent implements IQuery {

  public List<Object> compute(Object object) {
    FunctionalChain functionalChain = QueryHelper.getFunctionalChain(object);
    if (functionalChain != null && isValidInstanceOf(functionalChain)) {
      return EObjectExt.getReferencers(functionalChain, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED).stream()
          .filter(FunctionalChainReference.class::isInstance).map(EObject::eContainer).distinct()
          .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

  protected boolean isValidInstanceOf(FunctionalChain functionalChain) {
    return !(functionalChain instanceof OperationalProcess);
  }

}
