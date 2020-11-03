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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.communication.Exception;
import org.polarsys.capella.core.data.information.communication.Message;
import org.polarsys.capella.core.data.information.communication.Signal;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * DataPkg helpers
 */
public class DataPkgExt {
  
  private DataPkgExt() {
    //  To hide the implicit public one
  }

  /**
   * Gets all the classes recursively from the class package
   * 
   * @param dataPkg
   *          the ClassPkg
   * @return list of classes.
   */
  public static List<Class> getAllClasses(DataPkg dataPkg) {
    List<Class> list = new ArrayList<>();
    if (null != dataPkg) {
      list.addAll(dataPkg.getOwnedClasses());
      for (DataPkg subDataPkg : dataPkg.getOwnedDataPkgs()) {
        list.addAll(getAllClasses(subDataPkg));
      }
    }
    return list;
  }

  /**
   * Gets all the associations outgoing or incoming to ownedclasses
   * 
   * @param dataPkg
   *          the ClassPkg
   * @return list of associations.
   */
  public static Set<Association> getAllInvolvedAssociations(DataPkg dataPkg) {
    Set<Association> set = new HashSet<>();
    List<Class> allClasses = getAllClasses(dataPkg);
    for (Class clazz : allClasses) {
      set.addAll(ClassExt.getAllAssociations(clazz));
    }
    return set;
  }

  /**
   * Gets all the associations recursively from the class package
   * 
   * @param dataPkg
   *          the ClassPkg
   * @return list of associations.
   */
  public static List<Association> getAllAssociations(DataPkg dataPkg) {
    List<Association> list = new ArrayList<>();
    if (null != dataPkg) {
      list.addAll(dataPkg.getOwnedAssociations());
      for (DataPkg subDataPkg : dataPkg.getOwnedDataPkgs()) {
        list.addAll(getAllAssociations(subDataPkg));
      }
    }
    return list;
  }

  /**
   */
  public static List<EObject> getAllClassifierFromDataPkg(DataPkg dataPkg) {
    List<EObject> list = new ArrayList<>();
    if (null != dataPkg) {
      list.addAll(DataPkgExt.getAllClasses(dataPkg));
      list.addAll(DataPkgExt.getAllMessages(dataPkg));
      list.addAll(DataPkgExt.getAllDataTypes(dataPkg));
      list.addAll(DataPkgExt.getAllExceptions(dataPkg));
      list = ListExt.removeDuplicates(list);
    }
    return list;
  }

  /**
   * Gets all the collections recursively from a DataPkg
   * 
   * @param dataPkg
   *          the DataPkg
   * @return list of collections
   */
  public static List<org.polarsys.capella.core.data.information.Collection> getAllCollections(DataPkg dataPkg) {
    List<org.polarsys.capella.core.data.information.Collection> list = new ArrayList<>();
    if (null != dataPkg) {
      list.addAll(dataPkg.getOwnedCollections());
      for (DataPkg subDataTypePkg : dataPkg.getOwnedDataPkgs()) {
        list.addAll(getAllCollections(subDataTypePkg));
      }
    }
    return list;
  }

  /**
   * @param sourcePackage
   * @param targetPackage
   * @return true if the target package is an ancestor of the source package
   */
  public static boolean isAncestorPackage(DataPkg sourcePackage, DataPkg targetPackage) {
    boolean isAncestor = false;
    if (!sourcePackage.equals(targetPackage)
        && org.eclipse.emf.ecore.util.EcoreUtil.isAncestor(sourcePackage, targetPackage)) {
      isAncestor = true;
    }
    return isAncestor;
  }

  /**
   * Gets all the funded generalization from the given DataPkg
   * 
   * @param dataPkg
   *          the DataPkg
   * @return Set of generalization
   */
  public static Set<Generalization> getAllGeneralization(DataPkg dataPkg) {
    Set<Generalization> generalizations = new HashSet<>();
    if (null != dataPkg) {
    List<GeneralizableElement> classifiers = new ArrayList<>();
    classifiers.addAll(dataPkg.getOwnedClasses());
    classifiers.addAll(dataPkg.getOwnedCollections());
    classifiers.addAll(dataPkg.getOwnedDataTypes());
      for (GeneralizableElement generalizableElement : classifiers) {
        generalizations.addAll(generalizableElement.getOwnedGeneralizations());
      }
    }
    return generalizations;
  }

  /**
   * @param context
   * @return all DataPkgs which are in current or previous architectures
   */
  public static List<DataPkg> getAllDataPkgs(EObject context) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_DATA_PCK, context);
  }

  /**
   * @param context
   * @return all DataPkgs which are in current block architectures
   */
  public static List<DataPkg> getAllDataPkgsInCurrentBlockArchitectures(EObject context) {
    List<DataPkg> returnedList = new ArrayList<>();
    BlockArchitecture aArchitecture = BlockArchitectureExt.getRootBlockArchitecture(context);
    for (EObject aDataPkg : EObjectExt.getAll(aArchitecture, InformationPackage.Literals.DATA_PKG)) {
      returnedList.add((DataPkg) aDataPkg);
    }
    return returnedList;
  }

  /**
   * Gets all the datatypes recursively from a DataPkg
   * 
   * @param dataPkg
   *          the DataPkg
   * @return list of datatypes
   */
  public static List<DataType> getAllDataTypes(DataPkg dataPkg) {
    List<DataType> list = new ArrayList<>();
    if (null != dataPkg) {
      list.addAll(dataPkg.getOwnedDataTypes());
      for (DataPkg subDataTypePkg : dataPkg.getOwnedDataPkgs()) {
        list.addAll(getAllDataTypes(subDataTypePkg));
      }
    }
    return list;
  }

  /**
   * Gets all DataTypes in DataTypePkg of the parent component architecture / system engineering
   * 
   * @param dataPkg
   *          the DataTypePkg whose parent is to be found
   * @return list of datatypes
   */
  public static List<DataType> getAllDataTypesFromParent(DataPkg dataPkg) {
    List<DataType> list = new ArrayList<>();
    if (null != dataPkg) {
      BlockArchitecture arch = StructureExt.getRootBlockArchitecture(dataPkg);// get it from the Structure
      if (null != arch) {
        DataPkg pkg = getDataPkgOfBlockArchitecture(arch);
        if (null != pkg) {
          list.addAll(DataPkgExt.getAllDataTypes(pkg));
        }
      }

      // ComponentArchitecture is null; Get the SharedPkg
      SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(dataPkg);
      if (null != sysEng) {
        for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(dataPkg)) {
          if (sharedPkg.getOwnedDataPkg() != null) {
            list.addAll(DataPkgExt.getAllDataTypes(sharedPkg.getOwnedDataPkg()));
          }
        }
      }
    }
    return list;
  }

  /**
   * Gets all the datavalues recursively from a DataTypePkg
   * 
   * @param dataPkg
   *          the DataPkg
   * @return list of datavalues
   */
  public static List<DataValue> getAllDataValues(DataPkg dataPkg) {
    List<DataValue> list = new ArrayList<>();
    if (null != dataPkg) {
      list.addAll(dataPkg.getOwnedDataValues());
      for (DataPkg subPkg : dataPkg.getOwnedDataPkgs()) {
        list.addAll(getAllDataValues(subPkg));
      }
    }
    return list;
  }

  /**
   * Gets all DataValues in DataTypePkg of the parent component architecture / system engineering
   * 
   * @param dataPkg
   *          the DataTypePkg whose parent is to be found
   * @return list of datavalues
   */
  public static List<DataValue> getAllDataValuesFromParent(DataPkg dataPkg) {
    List<DataValue> list = new ArrayList<>();
    if (null != dataPkg) {
      BlockArchitecture arch = StructureExt.getRootBlockArchitecture(dataPkg);// get it from the Structure
      if (null != arch) {
        DataPkg pkg = getDataPkgOfBlockArchitecture(arch);
        if (null != pkg) {
          list.addAll(DataPkgExt.getAllDataValues(pkg));
        }
      }

      // ComponentArchitecture is null; Get the SharedPkg
      SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(dataPkg);
      if (null != sysEng) {
        for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(dataPkg)) {
          list.addAll(DataPkgExt.getAllDataValues(sharedPkg.getOwnedDataPkg()));
        }
      }
    }
    return list;
  }

  /**
   */
  public static List<Exception> getAllExceptions(DataPkg dataPkg) {
    List<Exception> list = new ArrayList<>();
    if (null != dataPkg) {
      list.addAll(dataPkg.getOwnedExceptions());
      for (DataPkg subDataPkg : dataPkg.getOwnedDataPkgs()) {
        list.addAll(getAllExceptions(subDataPkg));
      }
    }
    return list;
  }

  /**
   * Gets all the messages from a data package
   * 
   * @param dataPkg
   *          the {@link DataPkg}
   * @return list of Messages
   */
  public static List<Message> getAllMessages(DataPkg dataPkg) {
    List<Message> list = new ArrayList<>();
    if (null != dataPkg) {
      list.addAll(dataPkg.getOwnedMessages());
      for (DataPkg subDataPkg : dataPkg.getOwnedDataPkgs()) {
        list.addAll(getAllMessages(subDataPkg));
      }
    }
    return list;
  }

  /**
   * Gets all the signals recursively from a DataPkg
   * 
   * @param dataPkg
   *          the DataPkg
   * @return list of signals
   */
  public static List<Signal> getAllSignals(DataPkg dataPkg) {
    List<Signal> list = new ArrayList<>();
    if (null != dataPkg) {
      list.addAll(dataPkg.getOwnedSignals());
      for (DataPkg subDataPkg : dataPkg.getOwnedDataPkgs()) {
        list.addAll(getAllSignals(subDataPkg));
      }
    }
    return list;
  }

  /**
   * Gets all DataTypes from DataPkg
   * 
   * @param dataPkg
   *          the DataPkg
   * @return list of all data types
   */
  public static List<EObject> getAllTypesFromDataPkg(DataPkg dataPkg) {
    List<EObject> list = new ArrayList<>();
    if (null != dataPkg) {
      list.addAll(DataPkgExt.getAllClasses(dataPkg));
      list.addAll(DataPkgExt.getAllCollections(dataPkg));
      list.addAll(DataPkgExt.getAllDataTypes(dataPkg));
      list = ListExt.removeDuplicates(list);
    }
    return list;
  }

  /**
   * Gets all DataTypes from DataPkg, except signals
   * 
   * @param dataPkg
   *          the DataPkg
   * @return list of all data types
   */
  public static List<EObject> getAllTypesFromDataPkgForPropsNParams(DataPkg dataPkg) {
    List<EObject> list = new ArrayList<>();
    if (null != dataPkg) {
      list.addAll(DataPkgExt.getAllClasses(dataPkg));
      list.addAll(DataPkgExt.getAllCollections(dataPkg));
      list.addAll(DataPkgExt.getAllDataTypes(dataPkg));
      list = ListExt.removeDuplicates(list);
    }
    return list;
  }

  /**
   * Gets all the unions recursively from the Data package
   * 
   * @param dataPkg
   *          the DataPkg
   * @return list of unions.
   */
  public static List<Union> getAllUnions(DataPkg dataPkg) {
    List<Union> list = new ArrayList<>();
    if (null != dataPkg) {
      EList<Class> ownedClasses = dataPkg.getOwnedClasses();
      for (Class class1 : ownedClasses) {
        if (class1 instanceof Union) {
          list.add((Union) class1);
        }
      }
      for (DataPkg subDataPkg : dataPkg.getOwnedDataPkgs()) {
        list.addAll(getAllUnions(subDataPkg));
      }
    }
    return list;
  }

  public static List<Unit> getAllUnits(DataPkg dataPkg) {
    List<Unit> list = new ArrayList<>();
    if (null != dataPkg) {
      list.addAll(dataPkg.getOwnedUnits());
      for (DataPkg subDataPkg : dataPkg.getOwnedDataPkgs()) {
        list.addAll(getAllUnits(subDataPkg));
      }
    }
    return list;
  }

  /**
   * Gets all the classes from the parent block architecture and its hierarchy
   * 
   * @param compArch
   *          the Parent BlockArchitecture
   * @return list of classes
   */
  private static List<Class> getClassesFromBlockArchitectureParent(BlockArchitecture compArch) {
    List<Class> list = new ArrayList<>(1);
    Object container = compArch.eContainer();
    if (container instanceof Component) {
      Component parentComp = (Component) container;
      DataPkg dataPkg = parentComp.getOwnedDataPkg();
      if (null != dataPkg) {
        list.addAll(getAllClasses(dataPkg));
      }
      list.addAll(getClassesFromComponentParent((Component) container));
    } else if (container instanceof BlockArchitecture) {
      BlockArchitecture arch = (BlockArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(arch);
      if (null != dataPkg) {
        list.addAll(getAllClasses(dataPkg));
      }
      list.addAll(getClassesFromBlockArchitectureParent((BlockArchitecture) container));
    }
    return list;
  }

  /**
   * Gets all the classes from the parent component architecture and its hierarchy
   * 
   * @param compArch
   *          the Parent ComponentArchitecture
   * @return list of classes
   */
  private static List<Class> getClassesFromComponentArchitectureParent(ComponentArchitecture compArch) {
    List<Class> list = new ArrayList<>(1);
    Object container = compArch.eContainer();
    if (container instanceof Component) {
      Component parentComp = (Component) container;
      DataPkg dataPkg = parentComp.getOwnedDataPkg();
      if (null != dataPkg) {
        list.addAll(getAllClasses(dataPkg));
      }
      list.addAll(getClassesFromComponentParent((Component) container));
    } else if (container instanceof ComponentArchitecture) {
      ComponentArchitecture arch = (ComponentArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(arch);
      if (null != dataPkg) {
        list.addAll(getAllClasses(dataPkg));
      }
      list.addAll(getClassesFromComponentArchitectureParent((ComponentArchitecture) container));
    }
    return list;
  }

  /**
   * Gets all the classes from the parent component and its hierarchy
   * 
   * @param component
   *          the Parent Component
   * @return list of classes
   */
  private static List<Class> getClassesFromComponentParent(Component component) {
    List<Class> list = new ArrayList<>(1);
    Object container = component.eContainer();
    if (container instanceof Component) {
      Component parentComp = (Component) container;
      DataPkg dataPkg = parentComp.getOwnedDataPkg();
      if (null != dataPkg) {
        list.addAll(getAllClasses(dataPkg));
      }
      list.addAll(getClassesFromComponentParent((Component) container));
    } else if (container instanceof ComponentArchitecture) {
      ComponentArchitecture compArch = (ComponentArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(compArch);
      if (null != dataPkg) {
        list.addAll(getAllClasses(dataPkg));
      }
      list.addAll(getClassesFromComponentArchitectureParent((ComponentArchitecture) container));
    }
    return list;
  }

  /**
   * Gets all the classes from the Parent Hierarchy of the class package
   * 
   * @param classPkg
   *          the ClassPkg
   * @return list of classes
   */
  public static List<Class> getClassesFromParentHierarchy(DataPkg classPkg) {
    List<Class> list = new ArrayList<>(1);
    if (null != classPkg) {
      BlockArchitecture compArch = getRootBlockArchitecture(classPkg);
      if (null != compArch) {
        DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(compArch);
        if (null != dataPkg) {
          list.addAll(getAllClasses(dataPkg));
        }
        list.addAll(getClassesFromBlockArchitectureParent(compArch));
      }
      Component parentComp = getRootComponent(classPkg);
      if (null != parentComp) {
        DataPkg dataPkg = parentComp.getOwnedDataPkg();
        if (null != dataPkg) {
          list.addAll(getAllClasses(dataPkg));
        }
        list.addAll(getClassesFromComponentParent(parentComp));
      }
    }
    return list;
  }

  /**
   * @param dataPkg
   * @return all the packages that depends on dataPkg
   */
  public static Collection<AbstractDependenciesPkg> getDataPkgDependencies(DataPkg dataPkg) {
    Collection<AbstractDependenciesPkg> returnedDependencies = new HashSet<>();
    if (dataPkg.eContainer() instanceof DataPkg) {
      return getDataPkgDependenciesHierarchy(dataPkg, 0);
    }
    for (AbstractDependenciesPkg aDependentPackage : getDataPkgDependenciesHierarchy(dataPkg, 0)) {
      returnedDependencies.add(aDependentPackage);
      while (aDependentPackage.eContainer() instanceof DataPkg) {
        aDependentPackage = (AbstractDependenciesPkg) aDependentPackage.eContainer();
        returnedDependencies.add(aDependentPackage);
      }
    }
    return returnedDependencies;

  }

  /** for internal use */
  private static void addToResultMap(EObject tgt, Map<AbstractDependenciesPkg, Collection<EObject>> map,
      Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> result) {

    if (null != map) {
      for (Map.Entry<AbstractDependenciesPkg,Collection<EObject>> entry : map.entrySet()) {

        if (!result.containsKey(entry.getKey())) {
          result.put(entry.getKey(), new HashSet<Couple<EObject, Collection<EObject>>>());
        }
        result.get(entry.getKey()).add(new Couple<EObject, Collection<EObject>>(tgt, entry.getValue()));
      }
    }
  }

  /**
   * @see #getDataPkgDependenciesHierarchy(DataPkg, int)
   * @return < dependent package, Collection < object introducing the dependencies, Collection of pointed object > >
   */
  public static Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> getDataPkgDependenciesHierarchy2(
      DataPkg dataPkg) {

    Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> result = new HashMap<>();

    // classes
    for (Class aClass : dataPkg.getOwnedClasses()) {
      addToResultMap(aClass, ClassExt.getClassDependencies2(aClass), result);
    }

    // signals
    for (Signal aSignal : dataPkg.getOwnedSignals()) {
      addToResultMap(aSignal, SignalExt.getSignalDependencies2(aSignal), result);
    }

    // Datatypes
    for (DataType aDataType : dataPkg.getOwnedDataTypes()) {
      addToResultMap(aDataType, DataTypeExt.getDataTypeDependencies2(aDataType), result);
    }

    // Datavalues
    for (DataValue aDataValue : dataPkg.getOwnedDataValues()) {
      addToResultMap(aDataValue, DataValueExt.getDataValueDependencies2(aDataValue), result);
    }

    // ExchangeItem
    for (ExchangeItem anExchangeItem : dataPkg.getOwnedExchangeItems()) {
      addToResultMap(anExchangeItem, ExchangeItemExt.getExchangeItemDependencies2(anExchangeItem), result);
    }

    // Collection
    for (org.polarsys.capella.core.data.information.Collection aCollection : dataPkg.getOwnedCollections()) {
      addToResultMap(aCollection, CollectionExt.getCollectionDependencies2(aCollection), result);
    }

    // sub dependencies
    Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> tmp = null;

    for (DataPkg aSubPkg : dataPkg.getOwnedDataPkgs()) {
      tmp = getDataPkgDependenciesHierarchy2(aSubPkg);
      for (Map.Entry<AbstractDependenciesPkg,Collection<Couple<EObject,Collection<EObject>>>> entry : tmp.entrySet()) {
        if (result.containsKey(entry.getKey())) {
          result.get(entry.getKey()).addAll(entry.getValue());
        } else {
          result.put(entry.getKey(), entry.getValue());
        }
      }
    }

    return result;
  }

  private static Collection<AbstractDependenciesPkg> getDataPkgDependenciesHierarchy(DataPkg dataPkg, int hierarchy) {
    // dependencies
    Collection<AbstractDependenciesPkg> dependencies = new HashSet<>();
    
    // dependencies of dataPkg ancestors
    Collection<AbstractDependenciesPkg> dependenciesHierarchy = new HashSet<>(); 
                                                                                                        
                                                                                                        
    // classes
    for (Class aClass : dataPkg.getOwnedClasses()) {
      dependencies.addAll(ClassExt.getClassDependencies(aClass));
    }
    // signals
    for (Signal aSignal : dataPkg.getOwnedSignals()) {
      dependencies.addAll(SignalExt.getSignalDependencies(aSignal));
    }
    // Datatypes
    for (DataType aDataType : dataPkg.getOwnedDataTypes()) {
      dependencies.addAll(DataTypeExt.getDataTypeDependencies(aDataType));
    }
    // Datavalues
    for (DataValue aDataValue : dataPkg.getOwnedDataValues()) {
      dependencies.addAll(DataValueExt.getDataValueDependencies(aDataValue));
    }
    // ExchangeItem
    for (ExchangeItem anExchangeItem : dataPkg.getOwnedExchangeItems()) {
      dependencies.addAll(ExchangeItemExt.getExchangeItemDependencies(anExchangeItem));
    }
    // Collection
    for (org.polarsys.capella.core.data.information.Collection aCollection : dataPkg.getOwnedCollections()) {
      dependencies.addAll(CollectionExt.getCollectionDependencies(aCollection));
    }

    // Retrieving the dependencies for the ancestors.
    for (AbstractDependenciesPkg aPackage : dependencies) {
      int i = hierarchy;
      AbstractDependenciesPkg dependentPackage = aPackage;
      AbstractDependenciesPkg currentPackage = dataPkg;
      while ((i > 0)
          && (dependentPackage.eContainer() instanceof DataPkg)
          && (!(EcoreUtil.isAncestor(dependentPackage.eContainer(), currentPackage) || EcoreUtil.isAncestor(
              currentPackage, dependentPackage.eContainer())))) {
        dependentPackage = (AbstractDependenciesPkg) dependentPackage.eContainer();
        currentPackage = (AbstractDependenciesPkg) currentPackage.eContainer();
        i--;
      }
      while (i > 0) {
        currentPackage = (AbstractDependenciesPkg) currentPackage.eContainer();
        i--;
      }
      if (!(EcoreUtil.isAncestor(currentPackage, dependentPackage)
          || EcoreUtil.isAncestor(dependentPackage, currentPackage) || currentPackage.equals(dependentPackage))) {
        dependenciesHierarchy.add(dependentPackage);
      }
    }
    // Retrieving the dependencies of the sub-packages.
    for (DataPkg aSubPkg : dataPkg.getOwnedDataPkgs()) {
      dependenciesHierarchy.addAll(getDataPkgDependenciesHierarchy(aSubPkg, hierarchy + 1));
    }
    return dependenciesHierarchy;
  }

  /**
   * Gets all the datavalues recursively from a DataTypePkg
   * 
   * @param dataPkg
   *          the DataPkg
   * @return list of datavalues
   */
  public static DataPkg getDataPkgOfBlockArchitecture(BlockArchitecture architecture) {
    if (architecture instanceof OperationalAnalysis) {
      return ((OperationalAnalysis) architecture).getOwnedDataPkg();
    } else if (architecture instanceof SystemAnalysis) {
      return ((SystemAnalysis) architecture).getOwnedDataPkg();
    } else if (architecture instanceof LogicalArchitecture) {
      return ((LogicalArchitecture) architecture).getOwnedDataPkg();
    } else if (architecture instanceof PhysicalArchitecture) {
      return ((PhysicalArchitecture) architecture).getOwnedDataPkg();
    } else if (architecture instanceof EPBSArchitecture) {
      return ((EPBSArchitecture) architecture).getOwnedDataPkg();
    }
    return null;
  }

  /**
   */
  public static DataPkg getDataPkgOfComponentArchitecture(ComponentArchitecture componentArchitecture) {
    DataPkg dataPkg = null;
    if (componentArchitecture instanceof SystemEngineering) {
      SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis((SystemEngineering) componentArchitecture);
      dataPkg = ca.getOwnedDataPkg();
    } else if (componentArchitecture instanceof SystemAnalysis) {
      dataPkg = ((SystemAnalysis) componentArchitecture).getOwnedDataPkg();
    } else if (componentArchitecture instanceof LogicalArchitecture) {
      dataPkg = ((LogicalArchitecture) componentArchitecture).getOwnedDataPkg();
    } else if (componentArchitecture instanceof PhysicalArchitecture) {
      dataPkg = ((PhysicalArchitecture) componentArchitecture).getOwnedDataPkg();
    } else if (componentArchitecture instanceof EPBSArchitecture) {
      dataPkg = ((EPBSArchitecture) componentArchitecture).getOwnedDataPkg();
    }
    return dataPkg;
  }

  /**
   * Gets all the DataPkgs from the parent block architecture and its hierarchy
   * 
   * @param blockArch
   *          the Parent BlockArchitecture
   * @return list of DataPkgs
   */
  public static List<DataPkg> getDataPkgsFromBlockArchitectureParent(BlockArchitecture blockArch) {
    List<DataPkg> list = new ArrayList<>(1);
    Object container = blockArch.eContainer();
    if (container instanceof Component) {
      Component parentComp = (Component) container;
      DataPkg dataPkg = parentComp.getOwnedDataPkg();
      if (null != dataPkg) {
        list.add(dataPkg);
      }
      list.addAll(getDataPkgsFromComponentParent((Component) container));
    }
    if (container instanceof BlockArchitecture) {
      BlockArchitecture compArch = (BlockArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(compArch);
      if (null != dataPkg) {
        list.add(dataPkg);
      }
      if (container instanceof SystemEngineering) {
        return list; // return if SystemEngineering has come in the recursion
      }
      list.addAll(getDataPkgsFromBlockArchitectureParent((BlockArchitecture) container));
    }
    return list;
  }

  /**
   * Gets all the DataPkgs from the parent component architecture and its hierarchy
   * 
   * @param compArch
   *          the Parent ComponentArchitecture
   * @return list of DataPkgs
   */
  public static List<DataPkg> getDataPkgsFromComponentArchitectureParent(ComponentArchitecture compArch) {
    List<DataPkg> list = new ArrayList<>(1);
    Object container = compArch.eContainer();
    if (container instanceof Component) {
      Component parentComp = (Component) container;
      DataPkg dataPkg = parentComp.getOwnedDataPkg();
      if (null != dataPkg) {
        list.add(dataPkg);
      }
      list.addAll(getDataPkgsFromComponentParent((Component) container));
    }
    if (container instanceof ComponentArchitecture) {
      ComponentArchitecture arch = (ComponentArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(arch);
      if (null != dataPkg) {
        list.add(dataPkg);
      }
      if (container instanceof SystemEngineering) {
        return list; // return if SystemEngineering has come in the recursion
      }
      list.addAll(getDataPkgsFromComponentArchitectureParent((ComponentArchitecture) container));
    }
    return list;
  }

  /**
   * Gets all the DataPkg from the parent component and its hierarchy
   * 
   * @param component
   *          the Parent Component
   * @return list of DataPkgs
   */
  public static List<DataPkg> getDataPkgsFromComponentParent(Component component) {
    List<DataPkg> list = new ArrayList<>(1);
    Object container = component.eContainer();
    if (container instanceof Component) {
      Component parentComp = (Component) container;
      DataPkg dataPkg = parentComp.getOwnedDataPkg();
      if (null != dataPkg) {
        list.add(dataPkg);
      }
      list.addAll(getDataPkgsFromComponentParent((Component) container));
    }
    if (container instanceof BlockArchitecture) {
      BlockArchitecture compArch = (BlockArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(compArch);
      if (null != dataPkg) {
        list.add(dataPkg);
      }
      if (container instanceof SystemEngineering) {
        return list; // return if SystemEngineering has come in the recursion
      }
      list.addAll(getDataPkgsFromBlockArchitectureParent((BlockArchitecture) container));
    }
    return list;
  }

  /**
   * Gets all the DataPkgs from the Parent Hierarchy of the root component/component architecture of the current
   * DataTypePkg according to layer visibility and multiple decomposition rules
   * 
   * @param dataPkg
   *          the DataTypePkg
   * @return list of DataPkgs
   */
  public static List<DataPkg> getDataPkgsFromParentHierarchy(DataPkg dataPkg) {
    List<DataPkg> list = new ArrayList<>(1);
    if (null != dataPkg) {
      BlockArchitecture blockArch = getRootBlockArchitecture(dataPkg);
      if (null != blockArch) {
        DataPkg pkg = DataPkgExt.getDataPkgOfBlockArchitecture(blockArch);
        if (null != pkg) {
          list.add(pkg);
        }
        // if the layer visibility is there
        if (blockArch instanceof SystemEngineering) {
          return list; // return if SystemEngineering
        }
        list.addAll(DataPkgExt.getDataPkgsFromBlockArchitectureParent(blockArch));
      }
      Component parentComp = getRootComponent(dataPkg);
      if (null != parentComp) {
        DataPkg pkg = parentComp.getOwnedDataPkg();
        if (null != pkg) {
          list.add(pkg);
        }
        list.addAll(DataPkgExt.getDataPkgsFromComponentParent(parentComp));
      }
    }
    return list;
  }

  /**
   * Gets all the Exception from the parent component architecture and its hierarchy
   * 
   * @param compArch
   *          the Parent ComponentArchitecture
   * @return list of Exception
   */
  private static List<Exception> getExceptionsFromBlockArchitectureParent(BlockArchitecture compArch) {
    List<Exception> list = new ArrayList<>(1);
    Object container = compArch.eContainer();
    if (container instanceof System) {
      return list;// return if System has come in the recursion level
    } else if (container instanceof Component) {
      DataPkg dataPkg = ((Component) container).getOwnedDataPkg();
      if (null != dataPkg) {
        list.addAll(getAllExceptions(dataPkg));
      }
      list.addAll(getExceptionsFromComponentParent((Component) container));
    } else if (container instanceof SystemEngineering) {
      return list; // return if SystemEngineering has come in the recursion
    } else if (container instanceof BlockArchitecture) {
      BlockArchitecture arch = (BlockArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(arch);
      if (null != dataPkg) {
        list.addAll(getAllExceptions(dataPkg));
      }
      list.addAll(getExceptionsFromBlockArchitectureParent((BlockArchitecture) container));
    }
    return list;
  }

  /**
   * Gets all the Exception from the parent component architecture and its hierarchy
   * 
   * @param compArch
   *          the Parent ComponentArchitecture
   * @return list of Exception
   */
  private static List<Exception> getExceptionsFromComponentArchitectureParent(ComponentArchitecture compArch) {
    List<Exception> list = new ArrayList<>(1);
    Object container = compArch.eContainer();
    if (container instanceof System) {
      return list;// return if System has come in the recursion level
    } else if (container instanceof Component) {
      if (container instanceof LogicalComponent) {
        LogicalComponent parentComp = (LogicalComponent) container;
        DataPkg dataPkg = parentComp.getOwnedDataPkg();
        if (null != dataPkg) {
          list.addAll(getAllExceptions(dataPkg));
        }
      }
      list.addAll(getExceptionsFromComponentParent((Component) container));
    } else if (container instanceof SystemEngineering) {
      return list; // return if SystemEngineering has come in the recursion
    } else if (container instanceof ComponentArchitecture) {
      ComponentArchitecture arch = (ComponentArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(arch);
      if (null != dataPkg) {
        list.addAll(getAllExceptions(dataPkg));
      }
      list.addAll(getExceptionsFromComponentArchitectureParent((ComponentArchitecture) container));
    }
    return list;
  }

  /**
   */
  private static List<Exception> getExceptionsFromComponentParent(Component component) {
    List<Exception> list = new ArrayList<>(1);
    Object container = component.eContainer();
    if (container instanceof System) {
      return list;// return if System has come in the recursion level
    } else if (container instanceof Component) {
      if (container instanceof LogicalComponent) {
        LogicalComponent parentComp = (LogicalComponent) container;
        DataPkg dataPkg = parentComp.getOwnedDataPkg();
        if (null != dataPkg) {
          list.addAll(getAllExceptions(dataPkg));
        }
      }
      list.addAll(getExceptionsFromComponentParent((Component) container));
    } else if (container instanceof SystemEngineering) {
      return list; // return if SystemEngineering has come in the recursion
    } else if (container instanceof ComponentArchitecture) {
      ComponentArchitecture compArch = (ComponentArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(compArch);
      if (null != dataPkg) {
        list.addAll(getAllExceptions(dataPkg));
      }
      list.addAll(getExceptionsFromComponentArchitectureParent((ComponentArchitecture) container));
    }
    return list;
  }

  /**
   */
  public static List<Exception> getExceptionsFromParentHierarchy(DataPkg exceptionPkg) {
    List<Exception> list = new ArrayList<>(1);
    if (null != exceptionPkg) {
      BlockArchitecture compArch = getRootBlockArchitecture(exceptionPkg);
      if (null != compArch) {
        DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(compArch);
        if (null != dataPkg) {
          list.addAll(getAllExceptions(dataPkg));
        }
        if (compArch instanceof SystemEngineering) {
          return list; // return if SystemEngineering
        }
        list.addAll(getExceptionsFromBlockArchitectureParent(compArch));
      }
      Component parentComp = getRootComponent(exceptionPkg);
      if (null != parentComp) {
        DataPkg dataPkg = parentComp.getOwnedDataPkg();
        if (null != dataPkg) {
          list.addAll(getAllExceptions(dataPkg));
        }
        list.addAll(getExceptionsFromComponentParent(parentComp));
      }
    }
    return list;
  }

  /**
   * Gets all the Exception from the Parent Hierarchy of the InterfacePkg (Service->Owning Interface->InterfacePkg)
   * 
   * @param interfacePkg
   *          the InterfacePkg
   * @return list of Exception
   */
  public static List<Exception> getExceptionsFromParentHierarchy(InterfacePkg interfacePkg) {
    List<Exception> list = new ArrayList<>(1);
    if (null != interfacePkg) {
      BlockArchitecture compArch = InterfacePkgExt.getRootBlockArchitecture(interfacePkg);
      if (null != compArch) {
        DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(compArch);
        if (null != dataPkg) {
          list.addAll(getAllExceptions(dataPkg));
        }
        if (compArch instanceof SystemEngineering) {
          return list; // return if SystemEngineering
        }
        list.addAll(getExceptionsFromBlockArchitectureParent(compArch));
      }
      Component parentComp = InterfacePkgExt.getRootComponent(interfacePkg);
      if (null != parentComp) {
        DataPkg dataPkg = parentComp.getOwnedDataPkg();
        if (null != dataPkg) {
          list.addAll(getAllExceptions(dataPkg));
        }
        list.addAll(getExceptionsFromComponentParent(parentComp));
      }
    }
    return list;
  }

  /**
   * Gets all the messages from the parent component architecture and its hierarchy
   * 
   * @param compArch
   *          the Parent ComponentArchitecture
   * @return list of messages
   */
  private static List<Message> getMessagesFromBlockArchitectureParent(BlockArchitecture compArch) {
    List<Message> list = new ArrayList<>(1);
    Object container = compArch.eContainer();
    if (container instanceof System) {
      return list;// return if System has come in the recursion level
    } else if (container instanceof Component) {
      DataPkg dataPkg = ((Component) container).getOwnedDataPkg();
      if (null != dataPkg) {
        list.addAll(getAllMessages(dataPkg));
      }
      list.addAll(getMessagesFromComponentParent((Component) container));
    } else if (container instanceof SystemEngineering) {
      return list; // return if SystemEngineering has come in the recursion
    } else if (container instanceof BlockArchitecture) {
      BlockArchitecture arch = (BlockArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(arch);
      if (null != dataPkg) {
        list.addAll(getAllMessages(dataPkg));
      }
      list.addAll(getMessagesFromBlockArchitectureParent((BlockArchitecture) container));
    }
    return list;
  }

  /**
   * Gets all the messages from the parent component architecture and its hierarchy
   * 
   * @param compArch
   *          the Parent ComponentArchitecture
   * @return list of messages
   */
  private static List<Message> getMessagesFromComponentArchitectureParent(ComponentArchitecture compArch) {
    List<Message> list = new ArrayList<>(1);
    Object container = compArch.eContainer();
    if (container instanceof System) {
      return list;// return if System has come in the recursion level
    } else if (container instanceof Component) {
      if (container instanceof LogicalComponent) {
        LogicalComponent parentComp = (LogicalComponent) container;
        DataPkg dataPkg = parentComp.getOwnedDataPkg();
        if (null != dataPkg) {
          list.addAll(getAllMessages(dataPkg));
        }
      }
      list.addAll(getMessagesFromComponentParent((Component) container));
    } else if (container instanceof SystemEngineering) {
      return list; // return if SystemEngineering has come in the recursion
    } else if (container instanceof ComponentArchitecture) {
      ComponentArchitecture arch = (ComponentArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(arch);
      if (null != dataPkg) {
        list.addAll(getAllMessages(dataPkg));
      }
      list.addAll(getMessagesFromComponentArchitectureParent((ComponentArchitecture) container));
    }
    return list;
  }

  /**
   * Gets all the messages from the parent component and its hierarchy
   * 
   * @param component
   *          the Parent Component
   * @return list of messages
   */
  private static List<Message> getMessagesFromComponentParent(Component component) {
    List<Message> list = new ArrayList<>(1);
    Object container = component.eContainer();
    if (container instanceof System) {
      return list;// return if System has come in the recursion level
    } else if (container instanceof Component) {
      if (container instanceof LogicalComponent) {
        LogicalComponent parentComp = (LogicalComponent) container;
        DataPkg dataPkg = parentComp.getOwnedDataPkg();
        if (null != dataPkg) {
          list.addAll(getAllMessages(dataPkg));
        }
      }
      list.addAll(getMessagesFromComponentParent((Component) container));
    } else if (container instanceof SystemEngineering) {
      return list; // return if SystemEngineering has come in the recursion
    } else if (container instanceof ComponentArchitecture) {
      ComponentArchitecture compArch = (ComponentArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(compArch);
      if (null != dataPkg) {
        list.addAll(getAllMessages(dataPkg));
      }
      list.addAll(getMessagesFromComponentArchitectureParent((ComponentArchitecture) container));
    }
    return list;
  }

  /**
   * Gets all the messages from the Parent Hierarchy of the message package
   * 
   * @param messagePkg
   *          the MessagePkg
   * @return list of messages
   */
  public static List<Message> getMessagesFromParentHierarchy(DataPkg messagePkg) {
    List<Message> list = new ArrayList<>(1);
    if (null != messagePkg) {
      BlockArchitecture compArch = getRootBlockArchitecture(messagePkg);
      if (null != compArch) {
        DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(compArch);
        if (null != dataPkg) {
          list.addAll(getAllMessages(dataPkg));
        }
        if (compArch instanceof SystemEngineering) {
          return list; // return if SystemEngineering
        }
        list.addAll(getMessagesFromBlockArchitectureParent(compArch));
      }
      Component parentComp = getRootComponent(messagePkg);
      if (null != parentComp) {
        DataPkg dataPkg = parentComp.getOwnedDataPkg();
        if (null != dataPkg) {
          list.addAll(getAllMessages(dataPkg));
        }
        list.addAll(getMessagesFromComponentParent(parentComp));
      }
    }
    return list;
  }

  /**
   * Gets all the messages from the Parent Hierarchy of the InterfacePkg (Service->Owning Interface->InterfacePkg)
   * 
   * @param interfacePkg
   *          the InterfacePkg
   * @return list of messages
   */
  public static List<Message> getMessagesFromParentHierarchy(InterfacePkg interfacePkg) {
    List<Message> list = new ArrayList<>(1);
    if (null != interfacePkg) {
      BlockArchitecture compArch = InterfacePkgExt.getRootBlockArchitecture(interfacePkg);
      if (null != compArch) {
        DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(compArch);
        if (null != dataPkg) {
          list.addAll(getAllMessages(dataPkg));
        }
        if (compArch instanceof SystemEngineering) {
          return list; // return if SystemEngineering
        }
        list.addAll(getMessagesFromBlockArchitectureParent(compArch));
      }
      Component parentComp = InterfacePkgExt.getRootComponent(interfacePkg);
      if (null != parentComp) {
        DataPkg dataPkg = parentComp.getOwnedDataPkg();
        if (null != dataPkg) {
          list.addAll(getAllMessages(dataPkg));
        }
        list.addAll(getMessagesFromComponentParent(parentComp));
      }
    }
    return list;
  }

  /**
   * @param dataPkg
   *          current Data Package
   * @return recursively all sub Data Packages
   */
  public static List<DataPkg> getRecursiveSubDataPkgs(DataPkg dataPkg) {
    List<DataPkg> returnedList = new ArrayList<>();
    returnedList.addAll(dataPkg.getOwnedDataPkgs());
    for (DataPkg aSubPkg : dataPkg.getOwnedDataPkgs()) {
      returnedList.addAll(getRecursiveSubDataPkgs(aSubPkg));
    }
    return returnedList;
  }

  /**
   * @param modelElement
   *          : any 'ModelElement'
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
   * Gets the root component of the current DataPkg
   * 
   * @param dataPkg
   *          the DataPkg
   * @return the component
   */
  public static Component getRootComponent(DataPkg dataPkg) {
    Component comp = null;
    if (null != dataPkg) {
      Object container = dataPkg.eContainer();
      if (container instanceof DataPkg) {
        comp = getRootComponent((DataPkg) container);
      } else if (container instanceof Structure) {
        comp = StructureExt.getRootComponent((Structure) container);
      } else if (container instanceof Component) {
        comp = (Component) container;
      }
    }
    return comp;
  }

  /**
   * Gets the root component architecture of the current DataPkg
   * 
   * @param dataPkg
   *          the DataPkg
   * @return the component architecture
   */
  public static ComponentArchitecture getRootComponentArchitecture(DataPkg dataPkg) {
    ComponentArchitecture compArchitecture = null;
    if (null != dataPkg) {
      Object container = dataPkg.eContainer();
      if (container instanceof ComponentArchitecture) {
        compArchitecture = (ComponentArchitecture) container;
      } else if (container instanceof DataPkg) {
        compArchitecture = getRootComponentArchitecture((DataPkg) container);
      } else if (container instanceof Structure) {
        compArchitecture = StructureExt.getRootComponentArchitecture((Structure) container);
      } else if (container instanceof Component) {
        compArchitecture = ComponentExt.getRootComponentArchitecture((Component) container);
      }
    }
    return compArchitecture;
  }

  /**
   */
  public static DataPkg getRootDataPkg(DataPkg exceptionPkg) {
    DataPkg rootPkg = null;
    if (null != exceptionPkg) {
      Object container = exceptionPkg.eContainer();
      if (container instanceof DataPkg) {
        rootPkg = getRootDataPkg((DataPkg) container);
      } else {
        return exceptionPkg;
      }
    }
    return rootPkg;
  }

  /**
   * Gets all the signals from the parent component architecture and its hierarchy
   * 
   * @param compArch
   *          the Parent ComponentArchitecture
   * @return list of signals
   */
  private static List<Signal> getSignalsFromComponentArchitectureParent(ComponentArchitecture compArch) {
    return getSignalsFromComponentArchitectureParent(compArch, false);
  }

  private static List<Signal> getSignalsFromComponentArchitectureParent(ComponentArchitecture compArch,
      boolean isLayerVisibilityRequired) {
    List<Signal> list = new ArrayList<>(1);
    Object container = compArch.eContainer();
    if (isLayerVisibilityRequired && (container instanceof System || container instanceof SystemEngineering)) {
      return list;// return if System or SystemEngineering has come in the recursion level
    }
    if (container instanceof Component) {
      if (container instanceof LogicalComponent) {
        LogicalComponent parentComp = (LogicalComponent) container;
        DataPkg dataPkg = parentComp.getOwnedDataPkg();
        if (null != dataPkg) {
          list.addAll(getAllSignals(dataPkg));
        }
      }
      list.addAll(getSignalsFromComponentParent((Component) container, isLayerVisibilityRequired));
    } else if (container instanceof ComponentArchitecture) {
      ComponentArchitecture arch = (ComponentArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(arch);
      if (null != dataPkg) {
        list.addAll(getAllSignals(dataPkg));
      }
      list.addAll(getSignalsFromComponentArchitectureParent((ComponentArchitecture) container,
          isLayerVisibilityRequired));
    }
    return list;
  }

  /**
   * Gets all the signals from the parent component and its hierarchy
   * 
   * @param component
   *          the Parent Component
   * @return list of signals
   */
  private static List<Signal> getSignalsFromComponentParent(Component component) {
    return getSignalsFromComponentParent(component, false);
  }

  /**
   */
  private static List<Signal> getSignalsFromComponentParent(Component component, boolean isLayerVisibilityRequired) {
    List<Signal> list = new ArrayList<>(1);
    Object container = component.eContainer();
    if (isLayerVisibilityRequired && (container instanceof System || container instanceof SystemEngineering)) {
      return list;// return if System or SystemEngineering has come in the recursion level
    }
    if (container instanceof Component) {
      if (container instanceof LogicalComponent) {
        LogicalComponent parentComp = (LogicalComponent) container;
        DataPkg dataPkg = parentComp.getOwnedDataPkg();
        if (null != dataPkg) {
          list.addAll(getAllSignals(dataPkg));
        }
      }
      list.addAll(getSignalsFromComponentParent((Component) container, isLayerVisibilityRequired));
    } else if (container instanceof ComponentArchitecture) {
      ComponentArchitecture compArch = (ComponentArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(compArch);
      if (null != dataPkg) {
        list.addAll(getAllSignals(dataPkg));
      }
      list.addAll(getSignalsFromComponentArchitectureParent((ComponentArchitecture) container,
          isLayerVisibilityRequired));
    }
    return list;
  }

  /**
   * Gets all the signals from the Parent Hierarchy of the signal package
   * 
   * @param signalPkg
   *          the SignalPkg
   * @return list of signals
   */
  public static List<Signal> getSignalsFromParentHierarchy(DataPkg signalPkg) {
    List<Signal> list = new ArrayList<>(1);
    if (null != signalPkg) {
      ComponentArchitecture compArch = getRootComponentArchitecture(signalPkg);
      if (null != compArch) {
        DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(compArch);
        if (null != dataPkg) {
          list.addAll(getAllSignals(dataPkg));
        }
        list.addAll(getSignalsFromComponentArchitectureParent(compArch));
      }
      Component parentComp = getRootComponent(signalPkg);
      if (null != parentComp) {
        if (parentComp instanceof LogicalComponent) {
          DataPkg dataPkg = ((LogicalComponent) parentComp).getOwnedDataPkg();
          if (null != dataPkg) {
            list.addAll(getAllSignals(dataPkg));
          }
        }
        list.addAll(getSignalsFromComponentParent(parentComp));
      }
    }
    return list;
  }

  /**
   * Gets all the signals from the Parent Hierarchy of the class package
   * 
   * @param interfacePkg
   *          the InterfacePkg
   * @return list of signals
   */
  public static List<Signal> getSignalsFromParentHierarchy(InterfacePkg interfacePkg) {
    List<Signal> list = new ArrayList<>(1);
    if (null != interfacePkg) {
      ComponentArchitecture compArch = InterfacePkgExt.getRootComponentArchitecture(interfacePkg);
      if (null != compArch) {
        DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(compArch);
        if (null != dataPkg) {
          list.addAll(getAllSignals(dataPkg));
        }
        if (compArch instanceof SystemEngineering) {
          return list; // return if SystemEngineering
        }
        list.addAll(getSignalsFromComponentArchitectureParent(compArch, true));
      }
      Component parentComp = InterfacePkgExt.getRootComponent(interfacePkg);
      if (null != parentComp) {
        if (parentComp instanceof LogicalComponent) {
          DataPkg dataPkg = ((LogicalComponent) parentComp).getOwnedDataPkg();
          if (null != dataPkg) {
            list.addAll(getAllSignals(dataPkg));
          }
        }
        list.addAll(getSignalsFromComponentParent(parentComp, true));
      }
    }
    return list;
  }

  /**
   * Check if elt2 element is belong to Data package and same layer than elt1
   */
  public static boolean isBelongToSameDataPkgLayer(ModelElement elt1, ModelElement elt2) {
    return (elt1 instanceof CapellaElement) && (elt2 instanceof CapellaElement) && EcoreUtil2.isContainedBy(elt2, InformationPackage.Literals.DATA_PKG)
        && (CapellaLayerCheckingExt.getLayersName((CapellaElement) elt1) == CapellaLayerCheckingExt
        .getLayersName((CapellaElement) elt2));
  }

  /**
   * Return parent DataPkg if any
   * 
   * @param capellaElement
   *          : capella element
   * @return data pkg
   */
  public static DataPkg getParentDataPkg(CapellaElement capellaElement) {
    if (null != capellaElement) {
      if (capellaElement instanceof DataPkg) {
        return (DataPkg) capellaElement;
      }
      EObject eContainer = capellaElement.eContainer();
      if (eContainer instanceof CapellaElement) {
        return getParentDataPkg((CapellaElement) eContainer);
      }
    }
    return null;
  }

  /**
   * Return first dataPkg of Root architecture
   * 
   * @param eObject
   * @return
   */
  public static DataPkg getFirstDataPkgOfRootBlockArchitecture(EObject eObject) {
    if (null != eObject) {
      BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(eObject);
      DataPkgExt.getDataPkgOfBlockArchitecture(arch);
    }
    return null;
  }

  /**
   * Get DataValues form DataPkgs (consider owned DataTypes)
   * 
   * @param listPackages
   * @return
   * @deprecated Use getDataValues(EObject) instead
   */
  @Deprecated
  public static List<DataValue> getDataValues(List<DataPkg> listPackages) {
    List<DataValue> result = new ArrayList<>(1);
    Iterator<DataPkg> itDataPkg = listPackages.iterator();
    while (itDataPkg.hasNext()) {
      DataPkg dataPkg = itDataPkg.next();
      // get all owned dataValues
      Iterator<DataValue> itDataValue = dataPkg.getOwnedDataValues().iterator();
      while (itDataValue.hasNext()) {
        // add to result
        result.add(itDataValue.next());
      }
      // get all owned dataTypes
      Iterator<DataType> itDataTypes = dataPkg.getOwnedDataTypes().iterator();
      while (itDataTypes.hasNext()) {
        DataType dataType = itDataTypes.next();
        // get all owned DataValues from DataType and add to result
        result.addAll(DataTypeExt.getAllDataValuesFromDataType(dataType));
      }
    }
    return result;
  }

  /**
   * Get DataValues form DataPkgs (consider owned DataTypes)
   * 
   * @param listPackages
   * @return
   */
  public static List<DataValue> getDataValues(EObject semanticsObject) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_DATA_VALUES, semanticsObject);
      }

  public static boolean isPredefinedDataType(EObject element) {
    // We allow differences of dataPkg
    EObject parentDataPkg = EcoreUtil2.getFirstContainer(element, InformationPackage.Literals.DATA_PKG);
    return parentDataPkg != null
        && ((DataPkg) parentDataPkg).getName().equals(NamingConstants.PredefinedTypesCmd_predefinedDataTypePkg_name);
  }

  /**
   * Check if the dependency between a data package and a package is primitive
   * 
   * @param src
   * @param tar
   * @return
   */
  public static boolean isPrimitiveDependency(DataPkg src, AbstractDependenciesPkg tar) {
    // classes
    for (Class aClass : src.getOwnedClasses()) {
      if (ClassExt.getClassDependencies(aClass).contains(tar))
        return true;
    }
    // signals
    for (Signal aSignal : src.getOwnedSignals()) {
      if (SignalExt.getSignalDependencies(aSignal).contains(tar))
        return true;
    }
    // Datatypes
    for (DataType aDataType : src.getOwnedDataTypes()) {
      if (DataTypeExt.getDataTypeDependencies(aDataType).contains(tar))
        return true;
    }
    // Datavalues
    for (DataValue aDataValue : src.getOwnedDataValues()) {
      if (DataValueExt.getDataValueDependencies(aDataValue).contains(tar))
        return true;
    }
    // ExchangeItem
    for (ExchangeItem anExchangeItem : src.getOwnedExchangeItems()) {
      if (ExchangeItemExt.getExchangeItemDependencies(anExchangeItem).contains(tar))
        return true;
    }
    // Collection
    for (org.polarsys.capella.core.data.information.Collection aCollection : src.getOwnedCollections()) {
      if (CollectionExt.getCollectionDependencies(aCollection).contains(tar))
        return true;
    }

    return false;
  }

  /**
   * Get all the dependencies of a package, including ancestor's dependencies
   * 
   * @param dataPkg
   * @return
   */
  public static Collection<AbstractDependenciesPkg> getDataPkgDependenciesHierarchy(DataPkg dataPkg) {
    Collection<AbstractDependenciesPkg> dependencies = new HashSet<>(); // dependencies

    // classes
    for (Class aClass : dataPkg.getOwnedClasses()) {
      dependencies.addAll(ClassExt.getClassDependencies(aClass));
    }
    // signals
    for (Signal aSignal : dataPkg.getOwnedSignals()) {
      dependencies.addAll(SignalExt.getSignalDependencies(aSignal));
    }
    // Datatypes
    for (DataType aDataType : dataPkg.getOwnedDataTypes()) {
      dependencies.addAll(DataTypeExt.getDataTypeDependencies(aDataType));
    }
    // Datavalues
    for (DataValue aDataValue : dataPkg.getOwnedDataValues()) {
      dependencies.addAll(DataValueExt.getDataValueDependencies(aDataValue));
    }
    // ExchangeItem
    for (ExchangeItem anExchangeItem : dataPkg.getOwnedExchangeItems()) {
      dependencies.addAll(ExchangeItemExt.getExchangeItemDependencies(anExchangeItem));
    }
    // Collection
    for (org.polarsys.capella.core.data.information.Collection aCollection : dataPkg.getOwnedCollections()) {
      dependencies.addAll(CollectionExt.getCollectionDependencies(aCollection));
    }

    // Retrieving the dependencies for the ancestors.
    Collection<AbstractDependenciesPkg> currentDependencies = new HashSet<>(dependencies);
    for (AbstractDependenciesPkg aPackage : currentDependencies) {
      AbstractDependenciesPkg dependentPackage = aPackage;
      AbstractDependenciesPkg currentPackage = dataPkg;
      while ((dependentPackage instanceof AbstractDependenciesPkg)
          && (!(EcoreUtil.isAncestor(dependentPackage, currentPackage) || EcoreUtil.isAncestor(currentPackage,
              dependentPackage)))) {
        if (dependentPackage.eContainer() instanceof AbstractDependenciesPkg) {
          dependencies.add((AbstractDependenciesPkg) dependentPackage);
          dependentPackage = (AbstractDependenciesPkg) dependentPackage.eContainer();
        } else {
          break;
        }
      }
    }
    // Retrieving the dependencies of the sub-packages.
    for (DataPkg aSubPkg : dataPkg.getOwnedDataPkgs()) {
      dependencies.addAll(getDataPkgDependenciesHierarchy(aSubPkg));
    }
    return dependencies;
  }
}
