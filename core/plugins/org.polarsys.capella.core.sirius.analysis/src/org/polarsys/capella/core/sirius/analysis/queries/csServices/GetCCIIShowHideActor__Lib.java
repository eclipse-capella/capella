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

import org.polarsys.capella.common.queries.ExtendingQuery;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.model.helpers.ComponentExt;

@ExtendingQuery(extendingQuery = GetCCIIShowHideActor.class)
public class GetCCIIShowHideActor__Lib extends GetCCIIShowHideComponent__Lib {
  @Override
  protected List<Component> filter(List<Component> components) {
    return components.stream().filter(ComponentExt::isActor).collect(Collectors.toList());
  }
}