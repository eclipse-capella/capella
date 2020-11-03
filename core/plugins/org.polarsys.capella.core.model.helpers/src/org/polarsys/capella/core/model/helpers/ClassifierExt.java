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
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * The Classifier helpers
 */
public class ClassifierExt {

  /**
   * Gets all types for a classifier
   * @param classifier the classifier
   * @return list of all the available types (from DataPkg) and Interfaces
   */
  static public List<EObject> getAllTypes(GeneralizableElement classifier) {
    List<EObject> list = new ArrayList<EObject>();
    if (null != classifier) {
      Structure structure = (Structure) classifier.eContainer();

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
        SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(classifier);

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

//  /**
//   * @param classifier_p
//   * @return
//   */
//  public static ComponentArchitecture getRootComponentArchitecture(GeneralizableElement classifier_p) {
//    ComponentArchitecture arch = null;
//    if (null != classifier_p) {
//      Structure structure = (Structure) classifier_p.eContainer();
//      if (null != structure)
//        arch = StructureExt.getRootComponentArchitecture(structure);
//    }
//    return arch;
//  }

  /**
   * @param modelElement : any 'ModelElement'
   * @return : 'BlockArchitecture', value can also be null
   */
  public static BlockArchitecture getRootBlockArchitecture(ModelElement modelElement) {
	  return BlockArchitectureExt.getRootBlockArchitecture(modelElement);
  }

  /**
   * @param classifier
   * @return
   */
  public static Component getRootComponent(GeneralizableElement classifier) {
    Component comp = null;
    if (null != classifier) {
      Structure structure = (Structure) classifier.eContainer();
      if (null != structure)
        comp = StructureExt.getRootComponent(structure);
    }
    return comp;
  }

  /**
   * Gets all the DataPkgs from the Parent Hierarchy of the root component/Block architecture of the current classifier according to layer visibility and
   * multiple decomposition rules
   * @param classifier the Classifier
   * @return list of DataPkgs
   */
  static public List<DataPkg> getDataPkgsFromParentHierarchy(GeneralizableElement classifier) {
    List<DataPkg> list = new ArrayList<DataPkg>(1);
    if (null != classifier) {
      BlockArchitecture compArch = getRootBlockArchitecture(classifier);
      if (null != compArch) {
        DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(compArch);
        if (null != dataPkg) {
          list.add(dataPkg);
        }
        // if the layer visibility is there
        if (compArch instanceof SystemEngineering)
          return list; // return if SystemEngineering
        list.addAll(DataPkgExt.getDataPkgsFromBlockArchitectureParent(compArch));
      }
      Component parentComp = getRootComponent(classifier);
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
   * Gets all the Interfaces from the Parent Hierarchy of the root component/Block architecture of the current operation according to layer visibility and
   * multiple decomposition rules
   * @param classifier the Classifier
   * @return list of Interfaces
   */
  static public List<Interface> getOwnedInterfacesFromParentHierarchy(GeneralizableElement classifier) {
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

  /**
   * @param classifier
   * @return
   */
  static public DataPkg getRootDataPkg(GeneralizableElement classifier) {
    DataPkg dataPkg = null;
    if (null != classifier) {
      Object container = classifier.eContainer();
      if (container instanceof DataPkg) {
        dataPkg = (DataPkg) container;
      } else if (container instanceof Structure) {
        dataPkg = getDataPkg((Structure) container);
      }
    }
    return dataPkg;
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
  
  public static Collection<Property> getOwnedProperties(final Classifier classifier){
    Collection<Property> returnedCollection = new HashSet<Property>();
    for (Feature aFeature : classifier.getOwnedFeatures()){
      if (aFeature instanceof Property){
        returnedCollection.add((Property) aFeature);
      }
    }
    return returnedCollection;
  }
  
  public static Collection<Association> getIncomingAndOutgoingAssociations(final Classifier classifier){
    Collection<Association> returnedCollection = new HashSet<Association>();
    Set<Property> linkedProperties = new HashSet<Property>();
    linkedProperties.addAll(getOwnedProperties(classifier));
    for (TypedElement aTypedElement : classifier.getTypedElements()){
      if (aTypedElement instanceof Property){
        linkedProperties.add((Property) aTypedElement);
      }
    }
    for (Property aProperty : linkedProperties){
      if (aProperty.getAssociation() != null){
        returnedCollection.add(aProperty.getAssociation());
      }
      if (aProperty.eContainer() instanceof Association){
        returnedCollection.add((Association) aProperty.eContainer());
      }
    }
    return returnedCollection;
  }
}
