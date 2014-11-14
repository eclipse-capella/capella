/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.queries.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.libraries.capellaModel.CapellaLibrary;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class QueryExt {

  /**
   * Returns owned parts with the given eclass type.
   */
  public static List<Part> getParts(PartitionableElement element_p, EClass eClass_p, EClass excludeEClass_p) {
    List<Part> parts = new ArrayList<Part>();
    for (Partition part : ComponentExt.getSubParts(element_p)) {
      if ((part instanceof Part) && eClass_p.isInstance(part.getAbstractType())
          && (!((excludeEClass_p != null) && excludeEClass_p.isInstance(part.getAbstractType())))) {
        parts.add((Part) part);
      }
    }
    return parts;
  }

  /**
   * Returns the systemEngineering of a project.
   */
  public static SystemEngineering getSystemEngineeringFrom(Project project) {
    SystemEngineering systemEngineering = null;
    Iterator<ModelRoot> it = project.getOwnedModelRoots().iterator();
    while ((systemEngineering == null) && it.hasNext()) {
      ModelRoot mr = it.next();
      if (mr instanceof SystemEngineering) {
        systemEngineering = (SystemEngineering) mr;
      }
    }
    return systemEngineering;
  }

  public static SystemEngineering getSystemEngineeringFromLibrary(CapellaLibrary library) {
    Project libraryProject = library.getProject();
    SystemEngineering systemEngineering = QueryExt.getSystemEngineeringFrom(libraryProject);
    return systemEngineering;
  }

  public static BlockArchitecture getCorrespondingBlockArchitectureFromLibrary(BlockArchitecture currentArchBlock, CapellaLibrary library) {
    BlockArchitecture res = null;
    // we get the systemEngineering object ...
    Project libraryProject = library.getProject();
    SystemEngineering systemEngineering = QueryExt.getSystemEngineeringFrom(libraryProject);
    // ... in order to get architecture blocks from libraries (for now, we filter these blocks so that we keep only blocks of the same level of the given
    if (currentArchBlock instanceof OperationalAnalysis) {
      res = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEngineering);
    } else if (currentArchBlock instanceof SystemAnalysis) {
      res = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
    } else if (currentArchBlock instanceof LogicalArchitecture) {
      res = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
    } else if (currentArchBlock instanceof PhysicalArchitecture) {
      res = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
    } else if (currentArchBlock instanceof EPBSArchitecture) {
      res = SystemEngineeringExt.getEPBSArchitecture(systemEngineering);
    }
    return res;
  }

  public static EObject getCorrespondingElementInLibrary(EObject input, CapellaLibrary library) {
    BlockArchitecture currentArchBlock = BlockArchitectureExt.getRootBlockArchitecture(input);
    return getCorrespondingBlockArchitectureFromLibrary(currentArchBlock, library);
  }
}
