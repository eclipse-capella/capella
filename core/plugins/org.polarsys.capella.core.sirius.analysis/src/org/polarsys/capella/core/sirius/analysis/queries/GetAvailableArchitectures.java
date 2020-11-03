/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

public class GetAvailableArchitectures extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    List<Object> returnedArchitectures = new ArrayList<Object>();
    SystemEngineering rootSystemEngineering =
        (SystemEngineering) CapellaServices.getService().getAncestor((EObject) input, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING);
    BlockArchitecture ownerBlockArchitecture = null;
    if (input instanceof BlockArchitecture) {
      ownerBlockArchitecture = (BlockArchitecture) input;
    } else {
      ownerBlockArchitecture = (BlockArchitecture) CapellaServices.getService().getAncestor((EObject) input, CsPackage.Literals.BLOCK_ARCHITECTURE);
    }
    EList<ModellingArchitecture> ownedArchitectures = rootSystemEngineering.getOwnedArchitectures();
    for (ModellingArchitecture modellingArchitecture : ownedArchitectures) {
      if (modellingArchitecture instanceof OperationalAnalysis) {
        returnedArchitectures.add(modellingArchitecture);
      }
      if (modellingArchitecture instanceof SystemAnalysis) {
        if (!(ownerBlockArchitecture instanceof OperationalAnalysis)) {
          returnedArchitectures.add(modellingArchitecture);
        }
      }
      if (modellingArchitecture instanceof LogicalArchitecture) {
        if (!((ownerBlockArchitecture instanceof OperationalAnalysis) || (ownerBlockArchitecture instanceof SystemAnalysis))) {
          returnedArchitectures.add(modellingArchitecture);
        }
      }
      if (modellingArchitecture instanceof PhysicalArchitecture) {
        if (!((ownerBlockArchitecture instanceof OperationalAnalysis) || (ownerBlockArchitecture instanceof SystemAnalysis) || (ownerBlockArchitecture instanceof LogicalArchitecture))) {
          returnedArchitectures.add(modellingArchitecture);
        }
      }
      if (modellingArchitecture instanceof EPBSArchitecture) {
        if (ownerBlockArchitecture instanceof EPBSArchitecture) {
          returnedArchitectures.add(modellingArchitecture);
        }
      }
    }
    return returnedArchitectures;
  }
}
