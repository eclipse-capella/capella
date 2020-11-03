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
   * @param ele CapellaElement, eleList
   * @param eleList set of all the ele's container Capella Elements
   */
  public static void getAll_ContainerElements(CapellaElement ele, ArrayList<CapellaElement> eleList) {
    EObject elodyElement = ele.eContainer();
    if (null != elodyElement) {
      eleList.add((CapellaElement) elodyElement);
      getAll_ContainerElements((CapellaElement) elodyElement, eleList);
    }
  }

  /**
   * This method evaluates if a ele is a or in OperationalAnalysis
   * @param ele
   * @return 'true' if in OperationalAnalysis , 'false' if not
   */
  public static boolean isAOrInOperationalAnalysisLayer(CapellaElement ele) {
    return (ele instanceof OperationalAnalysis) || isInOperationalAnalysisLayer(ele);
  }

  public static boolean isInOperationalAnalysisLayer(CapellaElement ele) {

    ArrayList<CapellaElement> meleList = new ArrayList<CapellaElement>(0);
    getAll_ContainerElements(ele, meleList);

    for (CapellaElement element : meleList) {
      if ((element instanceof OperationalAnalysis) || (element instanceof OperationalActivityPkg)) {
        return true;
      }
    }

    return false;
  }

  /**
   * This method evaluates if a ele is a or in Logical Layer
   * @param ele
   * @return 'true' if in Logical Layer , 'false' if not
   */
  public static boolean isAOrInLogicalLayer(CapellaElement ele) {
    return (ele instanceof LogicalArchitecture) || isInLogicalLayer(ele);
  }

  /**
   * This method evaluates if a ele is in Logical Layer or not.
   * @param ele
   * @return 'true' if in Logical Layer , 'false' if not
   */
  public static boolean isInLogicalLayer(CapellaElement ele) {

    ArrayList<CapellaElement> meleList = new ArrayList<CapellaElement>(0);
    getAll_ContainerElements(ele, meleList);
    meleList.add(ele);
    for (CapellaElement element : meleList) {
      if ((element instanceof LogicalArchitecture) || (element instanceof LogicalArchitecturePkg) || (element instanceof LogicalComponent)) {
        return true;
      }
    }

    return false;
  }

  /**
   * This method evaluates if a ele is a or in SystemAnalysis
   * @param ele
   * @return 'true' if in SystemAnalysis , 'false' if not
   */
  public static boolean isAOrInContextLayer(CapellaElement ele) {
    return (ele instanceof SystemAnalysis) || isInContextLayer(ele);
  }

  /**
   * This method evaluates if a ele is in Context Layer or not.
   * @param ele
   * @return 'true' if in Logical Layer , 'false' if not
   */
  public static boolean isInContextLayer(CapellaElement ele) {

    ArrayList<CapellaElement> meleList = new ArrayList<CapellaElement>(0);
    getAll_ContainerElements(ele, meleList);

    for (CapellaElement element : meleList) {
      if (element instanceof SystemAnalysis) {
        return true;
      }
    }

    return false;
  }

  /**
   * This method evaluates if a ele is a or in PhysicalArchitecture
   * @param ele
   * @return 'true' if in PhysicalArchitecture , 'false' if not
   */
  public static boolean isAOrInPhysicalLayer(CapellaElement ele) {
    return (ele instanceof PhysicalArchitecture) || isInPhysicalLayer(ele);
  }

  /**
   * This method evaluates if a ele is in Physical Layer or not.
   * @param ele
   * @return 'true' if in Physical Layer , 'false' if not
   */
  public static boolean isInPhysicalLayer(CapellaElement ele) {

    ArrayList<CapellaElement> meleList = new ArrayList<CapellaElement>(0);
    getAll_ContainerElements(ele, meleList);
    meleList.add(ele);
    for (CapellaElement element : meleList) {
      if ((element instanceof PhysicalArchitecture) || (element instanceof PhysicalArchitecturePkg) || (element instanceof PhysicalComponent)) {
        return true;
      }
    }

    return false;
  }

  /**
   * This method evaluates if a ele is a or in PhysicalArchitecture
   * @param ele
   * @return 'true' if in PhysicalArchitecture , 'false' if not
   */
  public static boolean isAOrInEPBSLayer(CapellaElement ele) {
    return (ele instanceof EPBSArchitecture) || isInEPBSLayer(ele);
  }

  /**
   * This method evaluates if a ele is in EPBS Layer or not.
   * @param ele
   * @return 'true' if in Logical EPBS , 'false' if not
   */
  public static boolean isInEPBSLayer(CapellaElement ele) {

    ArrayList<CapellaElement> meleList = new ArrayList<CapellaElement>(0);
    getAll_ContainerElements(ele, meleList);
    meleList.add(ele);
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
  public static LAYERSNAME getLayersName(CapellaElement ele) {

    if (CapellaLayerCheckingExt.isInOperationalAnalysisLayer(ele) || (ele instanceof OperationalAnalysis)) {
      return LAYERSNAME.OA;
    }
    if (CapellaLayerCheckingExt.isInContextLayer(ele) || (ele instanceof SystemAnalysis)) {
      return LAYERSNAME.CTX;
    }
    if (CapellaLayerCheckingExt.isInLogicalLayer(ele) || (ele instanceof LogicalArchitecture)) {
      return LAYERSNAME.LA;
    }
    if (CapellaLayerCheckingExt.isInPhysicalLayer(ele) || (ele instanceof PhysicalArchitecture)) {
      return LAYERSNAME.PA;
    }
    if (CapellaLayerCheckingExt.isInEPBSLayer(ele) || (ele instanceof EPBSArchitecture)) {
      return LAYERSNAME.EPBS;
    }

    return LAYERSNAME.INVALID;
  }

  /**
   * Returns whether both elements are in the same block architecture
   */
  public static boolean areInSameLayer(EObject ele, EObject ele2) {
    BlockArchitecture archi1 = BlockArchitectureExt.getRootBlockArchitecture(ele);
    BlockArchitecture archi2 = BlockArchitectureExt.getRootBlockArchitecture(ele2);
    return (archi1 == archi2) || archi1.equals(archi2);
  }

  /**
   * Check weather given element belong to current layer(calculated from @param elementOnCurrentLayer) or upper layer
   * @param elementToCheck : on which the check is applied
   * @param elementOnCurrentLayer : used to calculate current layer
   */
  public static boolean isElementFromCurrentOrUpperLayer(CapellaElement elementToCheck, CapellaElement elementOnCurrentLayer) {
    // null value check

    if (isAOrInOperationalAnalysisLayer(elementOnCurrentLayer)) {
      if (isAOrInOperationalAnalysisLayer(elementToCheck)) {
        return true;
      }
    } else if (isAOrInContextLayer(elementOnCurrentLayer)) {
      if (isAOrInOperationalAnalysisLayer(elementToCheck) || isAOrInContextLayer(elementToCheck)) {
        return true;
      }
    } else if (isAOrInLogicalLayer(elementOnCurrentLayer)) {
      if (!isAOrInPhysicalLayer(elementToCheck) && !isAOrInEPBSLayer((elementToCheck))) {
        return true;
      }
    } else if (isAOrInPhysicalLayer(elementOnCurrentLayer)) {
      if (!isAOrInEPBSLayer((elementToCheck))) {
        return true;
      }
    } else if (isAOrInEPBSLayer(elementOnCurrentLayer) && isAOrInEPBSLayer(elementToCheck)) {
      return true;
    }

    return false;
  }

  /**
   * Check weather given element belong to current layer(calculated from @param elementOnCurrentLayer) or upper layer
   * @param elementToCheck : on which the check is applied
   * @param elementOnCurrentLayer : used to calculate current layer
   */
  public static boolean isElementFromUpperLayer(CapellaElement elementToCheck, CapellaElement elementOnCurrentLayer) {
    // null value check
    if ((elementToCheck == null) || (elementOnCurrentLayer == null)) {
      return false;
    }

    if (isAOrInOperationalAnalysisLayer(elementOnCurrentLayer)) {
      return false;
    } else if (isAOrInContextLayer(elementOnCurrentLayer)) {
      if (isAOrInOperationalAnalysisLayer(elementToCheck)) {
        return true;
      }
    } else if (isAOrInLogicalLayer(elementOnCurrentLayer)) {
      if (isAOrInContextLayer(elementToCheck) || isAOrInOperationalAnalysisLayer(elementToCheck)) {
        return true;
      }
    } else if (isAOrInPhysicalLayer(elementOnCurrentLayer)) {
      if (isAOrInLogicalLayer(elementToCheck) || isAOrInContextLayer(elementToCheck) || isAOrInOperationalAnalysisLayer(elementOnCurrentLayer)) {
        return true;
      }
    } else if (isAOrInEPBSLayer(elementOnCurrentLayer)) {
      if (!isAOrInEPBSLayer(elementToCheck)) {
        return true;
      }
    }

    return false;
  }
}
