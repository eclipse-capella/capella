/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.queries.csServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;

public class GetABInsertActor extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext qContext) throws QueryException {
    Collection<? extends Component> components = new HashSet<>();
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture((EObject) input);
    components.addAll((List) QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_SUB_DEFINED_ACTORS,
        architecture, new QueryContext()));
    // if is not multi part model
    if (!TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(architecture))) {
      ComponentPkg context = BlockArchitectureExt.getContext(architecture);
      if (context != null) {
        // Remove component from existing part
        components.removeAll(QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_SUB_USED_COMPONENTS, context,
            new QueryContext()));
      }
    }
    return new ArrayList<>(components);
  }
}
