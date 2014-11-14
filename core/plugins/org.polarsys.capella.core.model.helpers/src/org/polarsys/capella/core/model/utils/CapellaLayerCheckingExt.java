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
package org.polarsys.capella.core.model.utils;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSArchitecturePkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecturePkg;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

/**
 * CapellaElement helpers
 */
public class CapellaLayerCheckingExt {

  public enum LAYERSNAME {
    OA, CTX, LA, PA, EPBS, INVALID
  }

  /**
   * This method return All the container up to SystemEngineering.
   * @param ele_p CapellaElement, eleList_p
   * @param eleList_p set of all the ele_p's container Capella Elements
   */
  public static void getAll_ContainerElements(CapellaElement ele_p, ArrayList<CapellaElement> eleList_p) {
    EObject elodyElement = ele_p.eContainer();
    if (null != elodyElement) {
      eleList_p.add((CapellaElement) elodyElement);
      getAll_ContainerElements((CapellaElement) elodyElement, eleList_p);
    }
  }

  /**
   * This method evaluates if a ele_p is a or in OperationalAnalysis
   * @param ele_p
   * @return 'true' if in OperationalAnalysis , 'false' if not
   */
  public static boolean isAOrInOperationalAnalysisLayer(CapellaElement ele_p) {
    return (ele_p instanceof OperationalAnalysis) || isInOperationalAnalysisLayer(ele_p);
  }

  public static boolean isInOperationalAnalysisLayer(CapellaElement ele_p) {

    ArrayList<CapellaElement> meleList = new ArrayList<CapellaElement>(0);
    getAll_ContainerElements(ele_p, meleList);

    for (CapellaElement element : meleList) {
      if ((element instanceof OperationalAnalysis) || (element instanceof OperationalActivityPkg)) {
        return true;
      }
    }

    return false;
  }

  /**
   * This method evaluates if a ele_p is a or in Logical Layer
   * @param ele_p
   * @return 'true' if in Logical Layer , 'false' if not
   */
  public static boolean isAOrInLogicalLayer(CapellaElement ele_p) {
    return (ele_p instanceof LogicalArchitecture) || isInLogicalLayer(ele_p);
  }

  /**
   * This method evaluates if a ele_p is in Logical Layer or not.
   * @param ele_p
   * @return 'true' if in Logical Layer , 'false' if not
   */
  public static boolean isInLogicalLayer(CapellaElement ele_p) {

    ArrayList<CapellaElement> meleList = new ArrayList<CapellaElement>(0);
    getAll_ContainerElements(ele_p, meleList);
    meleList.add(ele_p);
    for (CapellaElement element : meleList) {
      if ((element instanceof LogicalArchitecture) || (element instanceof LogicalArchitecturePkg) || (element instanceof LogicalComponent)) {
        return true;
      }
    }

    return false;
  }

  /**
   * This method evaluates if a ele_p is a or in SystemAnalysis
   * @param ele_p
   * @return 'true' if in SystemAnalysis , 'false' if not
   */
  public static boolean isAOrInContextLayer(CapellaElement ele_p) {
    return (ele_p instanceof SystemAnalysis) || isInContextLayer(ele_p);
  }

  /**
   * This method evaluates if a ele_p is in Context Layer or not.
   * @param ele_p
   * @return 'true' if in Logical Layer , 'false' if not
   */
  public static boolean isInContextLayer(CapellaElement ele_p) {

    ArrayList<CapellaElement> meleList = new ArrayList<CapellaElement>(0);
    getAll_ContainerElements(ele_p, meleList);

    for (CapellaElement element : meleList) {
      if (element instanceof SystemAnalysis) {
        return true;
      }
    }

    return false;
  }

  /**
   * This method evaluates if a ele_p is a or in PhysicalArchitecture
   * @param ele_p
   * @return 'true' if in PhysicalArchitecture , 'false' if not
   */
  public static boolean isAOrInPhysicalLayer(CapellaElement ele_p) {
    return (ele_p instanceof PhysicalArchitecture) || isInPhysicalLayer(ele_p);
  }

  /**
   * This method evaluates if a ele_p is in Physical Layer or not.
   * @param ele_p
   * @return 'true' if in Physical Layer , 'false' if not
   */
  public static boolean isInPhysicalLayer(CapellaElement ele_p) {

    ArrayList<CapellaElement> meleList = new ArrayList<CapellaElement>(0);
    getAll_ContainerElements(ele_p, meleList);
    meleList.add(ele_p);
    for (CapellaElement element : meleList) {
      if ((element instanceof PhysicalArchitecture) || (element instanceof PhysicalArchitecturePkg) || (element instanceof PhysicalComponent)) {
        return true;
      }
    }

    return false;
  }

  /**
   * This method evaluates if a ele_p is a or in PhysicalArchitecture
   * @param ele_p
   * @return 'true' if in PhysicalArchitecture , 'false' if not
   */
  public static boolean isAOrInEPBSLayer(CapellaElement ele_p) {
    return (ele_p instanceof EPBSArchitecture) || isInEPBSLayer(ele_p);
  }

  /**
   * This method evaluates if a ele_p is in EPBS Layer or not.
   * @param ele_p
   * @return 'true' if in Logical EPBS , 'false' if not
   */
  public static boolean isInEPBSLayer(CapellaElement ele_p) {

    ArrayList<CapellaElement> meleList = new ArrayList<CapellaElement>(0);
    getAll_ContainerElements(ele_p, meleList);
    meleList.add(ele_p);
    for (CapellaElement element : meleList) {
      if ((element instanceof ConfigurationItem) || (element instanceof EPBSArchitecture) || (element instanceof EPBSArchitecturePkg)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return the Layer name belong to the element given in parameter
   */
  public static LAYERSNAME getLayersName(CapellaElement ele_p) {

    if (CapellaLayerCheckingExt.isInOperationalAnalysisLayer(ele_p) || (ele_p instanceof OperationalAnalysis)) {
      return LAYERSNAME.OA;
    }
    if (CapellaLayerCheckingExt.isInContextLayer(ele_p) || (ele_p instanceof SystemAnalysis)) {
      return LAYERSNAME.CTX;
    }
    if (CapellaLayerCheckingExt.isInLogicalLayer(ele_p) || (ele_p instanceof LogicalArchitecture)) {
      return LAYERSNAME.LA;
    }
    if (CapellaLayerCheckingExt.isInPhysicalLayer(ele_p) || (ele_p instanceof PhysicalArchitecture)) {
      return LAYERSNAME.PA;
    }
    if (CapellaLayerCheckingExt.isInEPBSLayer(ele_p) || (ele_p instanceof EPBSArchitecture)) {
      return LAYERSNAME.EPBS;
    }

    return LAYERSNAME.INVALID;
  }

  /**
   * Returns whether both elements are in the same block architecture
   */
  public static boolean areInSameLayer(EObject ele_p, EObject ele2_p) {
    BlockArchitecture archi1 = BlockArchitectureExt.getRootBlockArchitecture(ele_p);
    BlockArchitecture archi2 = BlockArchitectureExt.getRootBlockArchitecture(ele2_p);
    return (archi1 == archi2) || archi1.equals(archi2);
  }

  /**
   * Check weather given element belong to current layer(calculated from @param elementOnCurrentLayer_p) or upper layer
   * @param elementToCheck_p : on which the check is applied
   * @param elementOnCurrentLayer_p : used to calculate current layer
   */
  public static boolean isElementFromCurrentOrUpperLayer(CapellaElement elementToCheck_p, CapellaElement elementOnCurrentLayer_p) {
    // null value check

    if (isAOrInOperationalAnalysisLayer(elementOnCurrentLayer_p)) {
      if (isAOrInOperationalAnalysisLayer(elementToCheck_p)) {
        return true;
      }
    } else if (isAOrInContextLayer(elementOnCurrentLayer_p)) {
      if (isAOrInOperationalAnalysisLayer(elementToCheck_p) || isAOrInContextLayer(elementToCheck_p)) {
        return true;
      }
    } else if (isAOrInLogicalLayer(elementOnCurrentLayer_p)) {
      if (!isAOrInPhysicalLayer(elementToCheck_p) && !isAOrInEPBSLayer((elementToCheck_p))) {
        return true;
      }
    } else if (isAOrInPhysicalLayer(elementOnCurrentLayer_p)) {
      if (!isAOrInEPBSLayer((elementToCheck_p))) {
        return true;
      }
    } else if (isAOrInEPBSLayer(elementOnCurrentLayer_p) && isAOrInEPBSLayer(elementToCheck_p)) {
      return true;
    }

    return false;
  }

  /**
   * Check weather given element belong to current layer(calculated from @param elementOnCurrentLayer_p) or upper layer
   * @param elementToCheck_p : on which the check is applied
   * @param elementOnCurrentLayer_p : used to calculate current layer
   */
  public static boolean isElementFromUpperLayer(CapellaElement elementToCheck_p, CapellaElement elementOnCurrentLayer_p) {
    // null value check
    if ((elementToCheck_p == null) || (elementOnCurrentLayer_p == null)) {
      return false;
    }

    if (isAOrInOperationalAnalysisLayer(elementOnCurrentLayer_p)) {
      return false;
    } else if (isAOrInContextLayer(elementOnCurrentLayer_p)) {
      if (isAOrInOperationalAnalysisLayer(elementToCheck_p)) {
        return true;
      }
    } else if (isAOrInLogicalLayer(elementOnCurrentLayer_p)) {
      if (isAOrInContextLayer(elementToCheck_p) || isAOrInOperationalAnalysisLayer(elementToCheck_p)) {
        return true;
      }
    } else if (isAOrInPhysicalLayer(elementOnCurrentLayer_p)) {
      if (isAOrInLogicalLayer(elementToCheck_p) || isAOrInContextLayer(elementToCheck_p) || isAOrInOperationalAnalysisLayer(elementOnCurrentLayer_p)) {
        return true;
      }
    } else if (isAOrInEPBSLayer(elementOnCurrentLayer_p)) {
      if (!isAOrInEPBSLayer(elementToCheck_p)) {
        return true;
      }
    }

    return false;
  }
}
