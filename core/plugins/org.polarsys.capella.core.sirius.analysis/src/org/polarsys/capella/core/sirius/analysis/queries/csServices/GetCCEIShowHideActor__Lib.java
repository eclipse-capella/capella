/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.queries.csServices;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.ExtendingQuery;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.queries.GetBrotherComponents;

@ExtendingQuery (extendingQuery = GetBrotherComponents.class)
public class GetCCEIShowHideActor__Lib extends GetCCEIShowHideComponent__Lib {
  @Override
  protected List<Object> filter(List<Object> components) {
    return components.stream().filter(o -> o instanceof EObject && ComponentExt.isActor((EObject) o))
        .collect(Collectors.toList());
  }
}
