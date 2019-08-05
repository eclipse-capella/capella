/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

@ExtendingQuery(extendingQuery = GetCCIIShowHideActor.class)
public class GetCCIIShowHideActor__Lib extends GetCCIIShowHideComponent__Lib {
  @Override
  protected List<Object> filter(List<Object> components) {
    return components.stream().filter(o -> o instanceof EObject && ComponentExt.isActor((EObject) o))
        .collect(Collectors.toList());
  }
}