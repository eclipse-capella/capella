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
package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * Operation helpers
 */
public class OperationExt {

  static public ComponentArchitecture getRootComponentArchitecture(Operation operation_p) {
    ComponentArchitecture arch = null;
    if (null != operation_p) {
      Classifier owningClass = (Classifier) operation_p.eContainer();
      if (owningClass instanceof Class) {
        arch = StructureExt.getRootComponentArchitecture((DataPkg) owningClass.eContainer());
      }
      else if (owningClass instanceof Interface) {
        arch = StructureExt.getRootComponentArchitecture((InterfacePkg) owningClass.eContainer());
      }
    }
    return arch;
  }

  /**
   * 
   * @param modelElement_p : any 'ModelElement'
   * @return : 'BlockArchitecture', value can also be null
   */
  public static BlockArchitecture getRootBlockArchitecture(ModelElement modelElement_p) {
    BlockArchitecture arch = null;
    if(modelElement_p != null) {
      EObject container = modelElement_p.eContainer();
      if (container instanceof BlockArchitecture) {
        return (BlockArchitecture) container;
      }else if(container instanceof Component) {
        arch = ComponentExt.getRootBlockArchitecture((Component) container);
      } else if(container instanceof Structure) {
        arch = StructureExt.getRootBlockArchitecture((Structure) container);
      } else if (container instanceof Classifier) {
        arch = ClassifierExt.getRootBlockArchitecture((Classifier) container);
      }else{
        EObject container2 = container.eContainer();
        if (null != container2) {
          arch = getRootBlockArchitecture((ModelElement) container2);
        }
      }
    }
    return arch;
  }
  
  static public Component getRootComponent(Operation operation_p) {
    Component comp = null;
    if (null != operation_p) {
      Classifier owningClass = (Classifier) operation_p.eContainer();
      if (owningClass instanceof Class) {
        DataPkg ownerDataPkg = ClassExt.getOwnerDataPkg((Class) owningClass);
        comp = DataPkgExt.getRootComponent(ownerDataPkg);
      }
      else if (owningClass instanceof Interface) {
        comp = InterfacePkgExt.getRootComponent((InterfacePkg) owningClass.eContainer());
      }
    }
    return comp;
  }

  /**
   * Gets all the data packages from the component architecture, traversing up from the operation
   * @param operation_p
   *          the Operation from which the parent container is to be found out
   * @return list of DataPkg
   */
  static public List<DataPkg> getAllDataPkgs(Operation operation_p) {
    // Container of Parameter is Operation
    List<DataPkg> list = new ArrayList<DataPkg>();
    if (null != operation_p) {
      BlockArchitecture arch = null;
      Classifier owningClass = (Classifier) operation_p.eContainer();
      if (owningClass instanceof Class) {
        arch = StructureExt.getRootBlockArchitecture((DataPkg) owningClass.eContainer());
      }
      else if (owningClass instanceof Interface) {
        arch = StructureExt.getRootBlockArchitecture((InterfacePkg) owningClass.eContainer());
      }

      if (null != arch) {
        if (arch instanceof LogicalArchitecture) {
          list.add(((LogicalArchitecture) arch).getOwnedDataPkg());
          for (LogicalArchitecture logArch : LogicalArchitectureExt.getAllLogicalArchitectures((LogicalArchitecture) arch)) {
            list.add(logArch.getOwnedDataPkg());
          }
        } else if (arch instanceof PhysicalArchitecture) {
          list.add(((PhysicalArchitecture) arch).getOwnedDataPkg());
        } else if (arch instanceof EPBSArchitecture) {
          list.add(((EPBSArchitecture) arch).getOwnedDataPkg());
       // FIXME : update to SystemAnalysis
        } else if (arch instanceof SystemAnalysis) {
          list.add(((SystemAnalysis) arch).getOwnedDataPkg());
        } else if (arch instanceof SystemEngineering) {
        	// FIXME : update to SystemAnalysis
          SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis((SystemEngineering) arch);
        // FIXME : update to SystemAnalysis
          list.add(ca.getOwnedDataPkg());
        }
      }

      // ComponentArchitecture is null; Get the SharedPkg
      SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(operation_p);
      if (null != sysEng) {
        for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(operation_p))
          list.add(sharedPkg.getOwnedDataPkg());
      }
    }
    return list;
  }
  
  static public List<DataPkg> getDataPkgsFromSharedPkg(Operation operation_p) {
    List<DataPkg> list = new ArrayList<DataPkg>(1);
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(operation_p);
    if (null != sysEng) {
      for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(operation_p))
        list.add(sharedPkg.getOwnedDataPkg());
    }
    return list;
  }
  
  /**
   * Gets all the interfaces from the operation
   * @param operation_p
   *          the Operation from which the parent container is to be found out
   * @return list of interfaces
   */
  static public List<CapellaElement> getAllInterfaces(Operation operation_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != operation_p) {
      Classifier owningInterface = (Classifier) operation_p.eContainer();
      if (owningInterface instanceof Interface) {
        InterfacePkg interfacePkg = (InterfacePkg) owningInterface.eContainer();
        BlockArchitecture arch = InterfacePkgExt.getRootBlockArchitecture(interfacePkg);
        if (null != arch) {
          if (arch instanceof LogicalArchitecture) {
            list.addAll(LogicalArchitectureExt.getAllInterfacesInLogicalArchitecture((LogicalArchitecture) arch));
          } else if (arch instanceof PhysicalArchitecture) {
            list.addAll(PhysicalArchitectureExt.getAllInterfaces(arch, null, false));
         // FIXME : update to SystemAnalysis
          } else if (arch instanceof SystemAnalysis) {
            list.addAll(InterfacePkgExt.getAllInterfaces(((SystemAnalysis) arch).getOwnedInterfacePkg()));
          }
        }
        // TODO Do the same for SharedAssetsPkg
        // ComponentArchitecture is null; Get the SharedPkg
        SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(operation_p);
        if (null != sysEng) {
          for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(operation_p)) {
        	  list.addAll(GenericPkgExt.getAllInterfaces(sharedPkg.getOwnedGenericPkg()));
          }
        }
      }
    }
    list = ListExt.removeDuplicates(list);
    return list;
  }

  /**
   * Gets all the DataPkgs from the Parent Hierarchy of the root component/component architecture of the current operation according to layer visibility and
   * multiple decomposition rules
   * @param operation_p
   *          the Operation
   * @return list of DataPkgs
   */
  static public List<DataPkg> getDataPkgsFromParentHierarchy(Operation operation_p) {
    List<DataPkg> list = new ArrayList<DataPkg>(1);
    if (null != operation_p) {
      BlockArchitecture compArch = OperationExt.getRootBlockArchitecture(operation_p);
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
      Component parentComp = OperationExt.getRootComponent(operation_p);
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
   * @param operation_p
   *          the Operation
   * @return list of Interfaces
   */
  static public List<Interface> getOwnedInterfacesFromParentHierarchy(Operation operation_p) {
    List<Interface> list = new ArrayList<Interface>(1);
    if (null != operation_p) {
      BlockArchitecture compArch = OperationExt.getRootBlockArchitecture(operation_p);
      if (null != compArch) {
        list.addAll(InterfacePkgExt.getAllInterfaces(compArch.getOwnedInterfacePkg()));
        // Layer visibility is there
        if (compArch instanceof SystemEngineering)
          return list; // return if SystemEngineering
        list.addAll(InterfacePkgExt.getOwnedInterfacesFromBlockArchitectureParent(compArch, true));
      }
      Component parentComp = OperationExt.getRootComponent(operation_p);
      if (null != parentComp) {
        if (parentComp instanceof LogicalComponent) {
          list.addAll(InterfacePkgExt.getAllInterfaces(((LogicalComponent) parentComp).getOwnedInterfacePkg()));
        }
        list.addAll(InterfacePkgExt.getOwnedInterfacesFromComponentParent(parentComp, true));
      }
    }
    return list;
  }
}
