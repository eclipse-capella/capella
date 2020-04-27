/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;

/**
 * Structure helpers
 */
public class StructureExt {

  /**
   * Gets the root component from an element container
   * @param elementContainer
   *          the element container
   * @return the root component
   */
  static public Component getRootComponent(Structure elementContainer) {
    Component comp = null;
    if (null != elementContainer) {
      Object container = elementContainer.eContainer();
      if (container instanceof Component) {
        comp = (Component) container;
      } else if (container instanceof Structure) {
        comp = getRootComponent((Structure) container);
      }
    }
    return comp;
  }

  /**
   * Gets the root component architecture from the element container
   * @param elementContainer
   *          the element container
   * @return the component architecture
   */
  static public ComponentArchitecture getRootComponentArchitecture(Structure elementContainer) {
    ComponentArchitecture compArch = null;
    if (null != elementContainer) {
      Object container = elementContainer.eContainer();
      if (container instanceof ComponentArchitecture) {
        compArch = (ComponentArchitecture) container;
      } else if (container instanceof Structure) {
        compArch = getRootComponentArchitecture((Structure) container);
      } else if (container instanceof Component) {
        compArch = ComponentExt.getRootComponentArchitecture((Component) container);
      }
    }
    return compArch;
  }

  /**
   * Gets the root block architecture from the element container
   * @param elementContainer_p
   *          the element container
   * @return the block architecture
   */
  static public BlockArchitecture getRootBlockArchitecture(Structure modelElement) {
	  return BlockArchitectureExt.getRootBlockArchitecture(modelElement);
  }

  
  /**
   * Gets all the DataPkgs from the Parent Hierarchy of the root component/component architecture of the current structure according to layer visibility and
   * multiple decomposition rules
   * @param structure
   *          the structure
   * @return list of DataPkgs
   */
  static public List<DataPkg> getDataPkgsFromParentHierarchy(Structure structure) {
    List<DataPkg> list = new ArrayList<DataPkg>(1);
    if (null != structure) {
      BlockArchitecture compArch = getRootBlockArchitecture(structure);
      if (null != compArch) {
        DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(compArch);
        if (null != dataPkg) {
          list.add(dataPkg);
        }
        // TODO if the layer visibility is there uncomment the code
        if (compArch instanceof SystemEngineering)
          return list; // return if SystemEngineering
        list.addAll(DataPkgExt.getDataPkgsFromBlockArchitectureParent(compArch));
      }
      Component parentComp = getRootComponent(structure);
      if (null != parentComp) {
	      DataPkg dataPkg = parentComp.getOwnedDataPkg();
	      if (null != dataPkg) {
	        list.add(dataPkg);
	      }
	      list.addAll(DataPkgExt.getDataPkgsFromComponentParent(parentComp));
      }
    }
    return list;
  }
  
  /**
   * Gets all the Interfaces from the Parent Hierarchy of the root component/component architecture of the current operation according to layer visibility and
   * multiple decomposition rules
   * @param classifier the Classifier
   * @return list of Interfaces
   */
  static public List<Interface> getOwnedInterfacesFromParentHierarchy(Structure classifier) {
    List<Interface> list = new ArrayList<Interface>(1);
    if (null != classifier) {
      BlockArchitecture compArch = getRootBlockArchitecture(classifier);
      if (null != compArch) {
        list.addAll(InterfacePkgExt.getAllInterfaces(compArch.getOwnedInterfacePkg()));
        // Layer visibility is there
        if (compArch instanceof SystemEngineering)
          return list; // return if SystemEngineering
        list.addAll(InterfacePkgExt.getOwnedInterfacesFromBlockArchitectureParent(compArch));
      }
      Component parentComp = getRootComponent(classifier);
      if (null != parentComp) {
        list.addAll(InterfacePkgExt.getAllInterfaces(parentComp.getOwnedInterfacePkg()));
        list.addAll(InterfacePkgExt.getOwnedInterfacesFromComponentParent(parentComp));
      }
    }
    return list;
  }
  

  public static String getNewStructureName(Structure interfacePkg, EObject container) {
    return CapellaElementExt.getName(interfacePkg) +
    ICommonConstants.WHITE_SPACE_CHARACTER + ICommonConstants.PARENTHESIS_OPEN_CHARACTER +
    "from" +  //$NON-NLS-1$
    ICommonConstants.WHITE_SPACE_CHARACTER + 
    CapellaElementExt.getName(container) +
    ICommonConstants.PARENTHESIS_CLOSE_CHARACTER;
  }
}
