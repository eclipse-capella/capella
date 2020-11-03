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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * Collection helpers
 */
public class CollectionExt {

  /**
   * @param context a Capella Element
   * @return all the collections contained in the current and previous architectures
   */
  public static java.util.Collection<Collection> getAllCollections(EObject context) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_COLLECTIONS, context);
  }

  /**
   * Gets all types for a collection
   * @param collection the collection
   * @return list of all the available types (from DataPkg) and Interfaces
   */
  static public List<EObject> getAllTypes(Collection collection) {
    List<EObject> list = new ArrayList<EObject>();
    if (null != collection) {
      Structure structure = (Structure) collection.eContainer();

      if (structure != null) {
        BlockArchitecture arch = StructureExt.getRootBlockArchitecture(structure);
        if (null != arch) {
          if (arch instanceof LogicalArchitecture) {
            list.addAll(DataPkgExt.getAllTypesFromDataPkg(((LogicalArchitecture) arch).getOwnedDataPkg()));
            // Add all the interfaces
            list.addAll(BlockArchitectureExt.getAllInterfaces((LogicalArchitecture) arch));
          } else if (arch instanceof PhysicalArchitecture) {
            list.addAll(DataPkgExt.getAllTypesFromDataPkg(((PhysicalArchitecture) arch).getOwnedDataPkg()));
            list.addAll(PhysicalArchitectureExt.getAllInterfaces(arch, null, false));
          } else if (arch instanceof EPBSArchitecture) {
            list.addAll(DataPkgExt.getAllTypesFromDataPkg(((EPBSArchitecture) arch).getOwnedDataPkg()));
          }
        }
        // ComponentArchitecture is null; Get the SystemEngineering
        SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(collection);

        // FIXME : update to SystemAnalysis
        SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);

        if (null != sysEng) {
          // FIXME : update to SystemAnalysis
          list.addAll(DataPkgExt.getAllTypesFromDataPkg(ca.getOwnedDataPkg()));
          list.addAll(InterfacePkgExt.getAllInterfaces(ca.getOwnedInterfacePkg()));
        }
      }
      list = ListExt.removeDuplicates(list);
    }
    return list;
  }

  /**
   * @see #getCollectionDependencies(Collection)
   */
  public static Map<AbstractDependenciesPkg, java.util.Collection<EObject>> getCollectionDependencies2(Collection collection) {

    Map<AbstractDependenciesPkg, java.util.Collection<EObject>> result = new HashMap<AbstractDependenciesPkg, java.util.Collection<EObject>>();

    // type of collection
    AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(result, collection.getType());

    // index of collection
    for (DataType dataType : collection.getIndex()) {
      AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(result, dataType);
    }

    // superCollections
    for (Generalization aGeneralization : collection.getSuperGeneralizations()) {
      AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(result, aGeneralization.getSuper());
    }

    return result;
  }

  /**
   * @param collection
   * @return all dependent packages of the collection
   */
  public static java.util.Collection<AbstractDependenciesPkg> getCollectionDependencies(Collection collection) {
    return getCollectionDependencies2(collection).keySet();
  }

  /**
   * @param structure
   * @return
   */
  static public DataPkg getDataPkg(Structure structure) {
    DataPkg dataPkg = null;
    if (null != structure) {
      Object container = structure.eContainer();
      if (container instanceof DataPkg) {
        dataPkg = (DataPkg) container;
      } else if (container instanceof Structure) {
        dataPkg = getDataPkg((Structure) container);
      }
    }
    return dataPkg;
  }

  /**
   * Gets all the DataPkgs from the Parent Hierarchy of the root component/component architecture of the current collection according to layer visibility and
   * multiple decomposition rules
   * @param collection the collection
   * @return list of DataPkgs
   */
  static public List<DataPkg> getDataPkgsFromParentHierarchy(Collection collection) {
    List<DataPkg> list = new ArrayList<DataPkg>(1);
    if (null != collection) {
      BlockArchitecture compArch = getRootBlockArchitecture(collection);
      if (null != compArch) {
        DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(compArch);
        if (null != dataPkg) {
          list.add(dataPkg);
        }
        // if the layer visibility is there
        if (compArch instanceof SystemEngineering) {
          return list; // return if SystemEngineering
        }
        list.addAll(DataPkgExt.getDataPkgsFromBlockArchitectureParent(compArch));
      }
      Component parentComp = getRootComponent(collection);
      if (null != parentComp) {
        if (parentComp instanceof LogicalComponent) {
          DataPkg dataPkg = ((LogicalComponent) parentComp).getOwnedDataPkg();
          if (null != dataPkg) {
            list.add(dataPkg);
          }
        }
        list.addAll(DataPkgExt.getDataPkgsFromComponentParent(parentComp));
      }
    }
    return list;
  }

  /**
   * Gets all the Interfaces from the Parent Hierarchy of the root component/component architecture of the current operation according to layer visibility and
   * multiple decomposition rules
   * @param collection the collection
   * @return list of Interfaces
   */
  static public List<Interface> getOwnedInterfacesFromParentHierarchy(Collection collection) {
    List<Interface> list = new ArrayList<Interface>(1);
    if (null != collection) {
      BlockArchitecture compArch = getRootBlockArchitecture(collection);
      if (null != compArch) {
        list.addAll(InterfacePkgExt.getAllInterfaces(compArch.getOwnedInterfacePkg()));
        // Layer visibility is there
        if (compArch instanceof SystemEngineering) {
          return list; // return if SystemEngineering
        }
        list.addAll(InterfacePkgExt.getOwnedInterfacesFromBlockArchitectureParent(compArch));
      }
      Component parentComp = getRootComponent(collection);
      if (null != parentComp) {
        if (parentComp instanceof LogicalComponent) {
          list.addAll(InterfacePkgExt.getAllInterfaces(((LogicalComponent) parentComp).getOwnedInterfacePkg()));
        }
        list.addAll(InterfacePkgExt.getOwnedInterfacesFromComponentParent(parentComp));
      }
    }
    return list;
  }

  /**
   * @param modelElement : any 'ModelElement'
   * @return : 'BlockArchitecture', value can also be null
   */
  public static BlockArchitecture getRootBlockArchitecture(ModelElement modelElement) {
    BlockArchitecture arch = null;
    if (modelElement != null) {
      EObject container = modelElement.eContainer();
      if (container instanceof BlockArchitecture) {
        return (BlockArchitecture) container;
      } else if (container instanceof Component) {
        arch = ComponentExt.getRootBlockArchitecture((Component) container);
      } else if (container instanceof Structure) {
        arch = StructureExt.getRootBlockArchitecture((Structure) container);
      } else if (container instanceof Classifier) {
        arch = ClassifierExt.getRootBlockArchitecture((Classifier) container);
      } else {
        EObject container2 = container.eContainer();
        if (null != container2) {
          arch = getRootBlockArchitecture((ModelElement) container2);
        }
      }
    }
    return arch;
  }

  /**
   * @param collection
   * @return
   */
  public static Component getRootComponent(Collection collection) {
    Component comp = null;
    if (null != collection) {
      Structure structure = (Structure) collection.eContainer();
      if (null != structure) {
        comp = StructureExt.getRootComponent(structure);
      }
    }
    return comp;
  }

  /**
   * @param collection
   * @return
   */
  public static ComponentArchitecture getRootComponentArchitecture(Collection collection) {
    ComponentArchitecture arch = null;
    if (null != collection) {
      Structure structure = (Structure) collection.eContainer();
      if (null != structure) {
        arch = StructureExt.getRootComponentArchitecture(structure);
      }
    }
    return arch;
  }

  /**
   * @param collection
   * @return
   */
  static public DataPkg getRootDataPkg(Collection collection) {
    DataPkg dataPkg = null;
    if (null != collection) {
      Object container = collection.eContainer();
      if (container instanceof DataPkg) {
        dataPkg = (DataPkg) container;
      } else if (container instanceof Structure) {
        dataPkg = getDataPkg((Structure) container);
      }
    }
    return dataPkg;
  }

}
