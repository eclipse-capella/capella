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
package org.polarsys.capella.core.libraries.queries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class QueryExt {

  /**
   * Returns owned parts with the given eclass type.
   */
  public static List<Part> getParts(Component element_p, EClass eClass_p, EClass excludeEClass_p) {
    List<Part> parts = new ArrayList<Part>();
    for (Part part : ComponentExt.getSubParts(element_p)) {
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

  public static SystemEngineering getSystemEngineeringFromLibrary(TransactionalEditingDomain domain,
      CapellaModel library) {
    if (library != null) {
      Project libraryProject = library.getProject(domain);
      SystemEngineering systemEngineering = QueryExt.getSystemEngineeringFrom(libraryProject);
      return systemEngineering;
    }
    return null;
  }

  public static BlockArchitecture getCorrespondingBlockArchitectureFromLibrary(BlockArchitecture currentArchBlock, CapellaModel library) {
    BlockArchitecture res = null;
    // we get the systemEngineering object ...
    Project libraryProject = library.getProject(TransactionHelper.getEditingDomain(currentArchBlock));
    if (libraryProject != null) {
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
    }

    return res;
  }

  public static BlockArchitecture getBlockArchitectureFromLibraryUpward(BlockArchitecture currentArchBlock, CapellaModel library) {
    BlockArchitecture res = null;
    // we get the systemEngineering object ...
    Project libraryProject = library.getProject(TransactionHelper.getEditingDomain(currentArchBlock));
    if (libraryProject != null) {
      SystemEngineering systemEngineering = QueryExt.getSystemEngineeringFrom(libraryProject);
      // ... in order to get architecture blocks from libraries (not restricted to blocks of the same level)
      if (currentArchBlock instanceof OperationalAnalysis) {
        res = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEngineering);
      } else if (currentArchBlock instanceof SystemAnalysis) {
        res = getSystemAnalysisFromLibrary(systemEngineering);
      } else if (currentArchBlock instanceof LogicalArchitecture) {
        res = getLogicalArchitectureFromLibrary(systemEngineering);
      } else if (currentArchBlock instanceof PhysicalArchitecture) {
        res = getPhysicalArchitectureFromLibrary(systemEngineering);
      } else if (currentArchBlock instanceof EPBSArchitecture) {
        res = getEPBSArchitectureFromLibrary(systemEngineering);
      }
    }

    return res;
  }

  public static BlockArchitecture getSystemAnalysisFromLibrary(SystemEngineering systemEngineering) {
    BlockArchitecture res = null;
    if (SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering) != null) {
      res = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
    } else {
      res = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEngineering);
    }
    return res;
  }

  public static BlockArchitecture getLogicalArchitectureFromLibrary(SystemEngineering systemEngineering) {
    BlockArchitecture res = null;
    if (SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering) != null) {
      res = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
    } else {
      res = getSystemAnalysisFromLibrary(systemEngineering);
    }
    return res;
  }

  public static BlockArchitecture getPhysicalArchitectureFromLibrary(SystemEngineering systemEngineering) {
    BlockArchitecture res = null;
    if (SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering) != null) {
      res = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
    } else {
      res = getLogicalArchitectureFromLibrary(systemEngineering);
    }
    return res;
  }

  public static BlockArchitecture getEPBSArchitectureFromLibrary(SystemEngineering systemEngineering) {
    BlockArchitecture res = null;
    if (SystemEngineeringExt.getEPBSArchitecture(systemEngineering) != null) {
      res = SystemEngineeringExt.getEPBSArchitecture(systemEngineering);
    } else {
      res = getPhysicalArchitectureFromLibrary(systemEngineering);
    }
    return res;
  }

  /**
   * Get the corresponding BlockArchitecture in library
   * @param input
   * @param library
   * @return the corresponding BlockArchitecture in library
   */
  public static EObject getCorrespondingElementInLibrary(EObject input, CapellaModel library) {
    BlockArchitecture currentArchBlock = BlockArchitectureExt.getRootBlockArchitecture(input);
    return getBlockArchitectureFromLibraryUpward(currentArchBlock, library);
  }
}
