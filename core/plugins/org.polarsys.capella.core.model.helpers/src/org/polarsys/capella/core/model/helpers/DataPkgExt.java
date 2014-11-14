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

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
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
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.queries.debug.QueryDebugger;

/**
 * DataPkg helpers
 */
public class DataPkgExt {

  /**
   * Gets all the classes recursively from the class package
   * @param dataPkg_p the ClassPkg
   * @return list of classes.
   */
  static public List<Class> getAllClasses(DataPkg dataPkg_p) {
    List<Class> list = new ArrayList<Class>();
    if (null != dataPkg_p) {
      list.addAll(dataPkg_p.getOwnedClasses());
      for (DataPkg subDataPkg : dataPkg_p.getOwnedDataPkgs()) {
        list.addAll(getAllClasses(subDataPkg));
      }
    }
    return list;
  }

  /**
   * Gets all the associations outgoing or incoming to ownedclasses
   * @param dataPkg_p the ClassPkg
   * @return list of associations.
   */
  static public Set<Association> getAllInvolvedAssociations(DataPkg dataPkg_p) {
    Set<Association> set = new HashSet<Association>();
    List<Class> allClasses = getAllClasses(dataPkg_p);
    for (Class clazz : allClasses) {
      set.addAll(ClassExt.getAllAssociations(clazz));
    }
    return set;
  }

  /**
   * Gets all the associations recursively from the class package
   * @param dataPkg_p the ClassPkg
   * @return list of associations.
   */
  static public List<Association> getAllAssociations(DataPkg dataPkg_p) {
    List<Association> list = new ArrayList<Association>();
    if (null != dataPkg_p) {
      list.addAll(dataPkg_p.getOwnedAssociations());
      for (DataPkg subDataPkg : dataPkg_p.getOwnedDataPkgs()) {
        list.addAll(getAllAssociations(subDataPkg));
      }
    }
    return list;
  }

  /**
   */
  static public List<CapellaElement> getAllClassifierFromDataPkg(DataPkg dataPkg_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != dataPkg_p) {
      list.addAll(DataPkgExt.getAllClasses(dataPkg_p));
      list.addAll(DataPkgExt.getAllMessages(dataPkg_p));
      list.addAll(DataPkgExt.getAllDataTypes(dataPkg_p));
      list.addAll(DataPkgExt.getAllExceptions(dataPkg_p));
      list = ListExt.removeDuplicates(list);
    }
    return list;
  }

  /**
   * Gets all the collections recursively from a DataPkg
   * @param dataPkg_p the DataPkg
   * @return list of collections
   */
  static public List<org.polarsys.capella.core.data.information.Collection> getAllCollections(
      DataPkg dataPkg_p) {
    List<org.polarsys.capella.core.data.information.Collection> list =
        new ArrayList<org.polarsys.capella.core.data.information.Collection>();
    if (null != dataPkg_p) {
      list.addAll(dataPkg_p.getOwnedCollections());
      for (DataPkg subDataTypePkg : dataPkg_p.getOwnedDataPkgs()) {
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
   * @param dataPkg_p the DataPkg
   * @return Set of generalization
   */
  static public Set<Generalization> getAllGeneralization(DataPkg dataPkg_p) {
    Set<Generalization> generalizations = new HashSet<Generalization>();
    List<GeneralizableElement> classifiers = new ArrayList<GeneralizableElement>();
    classifiers.addAll(dataPkg_p.getOwnedClasses());
    classifiers.addAll(dataPkg_p.getOwnedCollections());
    classifiers.addAll(dataPkg_p.getOwnedDataTypes());
    if (null != dataPkg_p) {
      for (GeneralizableElement generalizableElement : classifiers) {
        generalizations.addAll(generalizableElement.getOwnedGeneralizations());
      }
    }
    return generalizations;
  }

  /**
   * @param context_p
   * @return all DataPkgs which are in current or previous architectures
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
public static List<DataPkg> getAllDataPkgs(EObject context_p) {
    // OLD CODE
    List<DataPkg> returnedList = new ArrayList<DataPkg>();
    for (BlockArchitecture anArchitecture : BlockArchitectureExt
        .getRootAndPreviousBlockArchitectures(context_p)) {
      for (EObject aDataPkg : EObjectExt.getAll(anArchitecture,
          InformationPackage.Literals.DATA_PKG)) {
        returnedList.add((DataPkg) aDataPkg);
      }
    }
    // NEW CODE
    returnedList =
        (List) QueryDebugger.executeQueryWithInclusionDebug(
            QueryIdentifierConstants.GET_ALL_DATA_PCK, context_p, returnedList);
    // END CODE REFACTOR
    return returnedList;
  }

  /**
   * @param context_p
   * @return all DataPkgs which are in current block architectures
   */
  public static List<DataPkg> getAllDataPkgsInCurrentBlockArchitectures(EObject context_p) {
    List<DataPkg> returnedList = new ArrayList<DataPkg>();
    BlockArchitecture aArchitecture = BlockArchitectureExt.getRootBlockArchitecture(context_p);
    for (EObject aDataPkg : EObjectExt.getAll(aArchitecture, InformationPackage.Literals.DATA_PKG)) {
      returnedList.add((DataPkg) aDataPkg);
    }

    return returnedList;
  }

  /**
   * Gets all the datatypes recursively from a DataPkg
   * @param dataPkg_p the DataPkg
   * @return list of datatypes
   */
  static public List<DataType> getAllDataTypes(DataPkg dataPkg_p) {
    List<DataType> list = new ArrayList<DataType>();
    if (null != dataPkg_p) {
      list.addAll(dataPkg_p.getOwnedDataTypes());
      for (DataPkg subDataTypePkg : dataPkg_p.getOwnedDataPkgs()) {
        list.addAll(getAllDataTypes(subDataTypePkg));
      }
    }
    return list;
  }

  /**
   * Gets all DataTypes in DataTypePkg of the parent component architecture / system engineering
   * @param dataPkg_p the DataTypePkg whose parent is to be found
   * @return list of datatypes
   */
  static public List<DataType> getAllDataTypesFromParent(DataPkg dataPkg_p) {
    List<DataType> list = new ArrayList<DataType>();
    if (null != dataPkg_p) {
      BlockArchitecture arch = StructureExt.getRootBlockArchitecture(dataPkg_p);// get it from the Structure
      if (null != arch) {
        DataPkg dataPkg = getDataPkgOfBlockArchitecture(arch);
        if (null != dataPkg) {
          list.addAll(DataPkgExt.getAllDataTypes(dataPkg));
        }
      }

      // ComponentArchitecture is null; Get the SharedPkg
      SystemEngineering sysEng =
          CapellaQueries.getInstance().getRootQueries().getSystemEngineering(dataPkg_p);
      if (null != sysEng) {
        for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(dataPkg_p)) {
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
   * @param dataPkg_p the DataPkg
   * @return list of datavalues
   */
  static public List<DataValue> getAllDataValues(DataPkg dataPkg_p) {
    List<DataValue> list = new ArrayList<DataValue>();
    if (null != dataPkg_p) {
      list.addAll(dataPkg_p.getOwnedDataValues());
      for (DataPkg subPkg : dataPkg_p.getOwnedDataPkgs()) {
        list.addAll(getAllDataValues(subPkg));
      }
    }
    return list;
  }

  /**
   * Gets all DataValues in DataTypePkg of the parent component architecture / system engineering
   * @param dataPkg_p the DataTypePkg whose parent is to be found
   * @return list of datavalues
   */
  static public List<DataValue> getAllDataValuesFromParent(DataPkg dataPkg_p) {
    List<DataValue> list = new ArrayList<DataValue>();
    if (null != dataPkg_p) {
      BlockArchitecture arch = StructureExt.getRootBlockArchitecture(dataPkg_p);// get it from the Structure
      if (null != arch) {
        DataPkg dataPkg = getDataPkgOfBlockArchitecture(arch);
        if (null != dataPkg) {
          list.addAll(DataPkgExt.getAllDataValues(dataPkg));
        }
      }

      // ComponentArchitecture is null; Get the SharedPkg
      SystemEngineering sysEng =
          CapellaQueries.getInstance().getRootQueries().getSystemEngineering(dataPkg_p);
      if (null != sysEng) {
        for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(dataPkg_p)) {
          list.addAll(DataPkgExt.getAllDataValues(sharedPkg.getOwnedDataPkg()));
        }
      }
    }
    return list;
  }

  /**
   */
  static public List<Exception> getAllExceptions(DataPkg dataPkg_p) {
    List<Exception> list = new ArrayList<Exception>();
    if (null != dataPkg_p) {
      list.addAll(dataPkg_p.getOwnedExceptions());
      for (DataPkg subDataPkg : dataPkg_p.getOwnedDataPkgs()) {
        list.addAll(getAllExceptions(subDataPkg));
      }
    }
    return list;
  }

  /**
   * Gets all the messages from a data package
   * @param dataPkg_p the {@link DataPkg}
   * @return list of Messages
   */
  static public List<Message> getAllMessages(DataPkg dataPkg_p) {
    List<Message> list = new ArrayList<Message>();
    if (null != dataPkg_p) {
      list.addAll(dataPkg_p.getOwnedMessages());
      for (DataPkg subDataPkg : dataPkg_p.getOwnedDataPkgs()) {
        list.addAll(getAllMessages(subDataPkg));
      }
    }
    return list;
  }

  /**
   * Gets all the signals recursively from a DataPkg
   * @param dataPkg_p the DataPkg
   * @return list of signals
   */
  static public List<Signal> getAllSignals(DataPkg dataPkg_p) {
    List<Signal> list = new ArrayList<Signal>();
    if (null != dataPkg_p) {
      list.addAll(dataPkg_p.getOwnedSignals());
      for (DataPkg subDataPkg : dataPkg_p.getOwnedDataPkgs()) {
        list.addAll(getAllSignals(subDataPkg));
      }
    }
    return list;
  }

  /**
   * Gets all DataTypes from DataPkg
   * @param dataPkg_p the DataPkg
   * @return list of all data types
   */
  static public List<CapellaElement> getAllTypesFromDataPkg(DataPkg dataPkg_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != dataPkg_p) {
      list.addAll(DataPkgExt.getAllClasses(dataPkg_p));
      list.addAll(DataPkgExt.getAllCollections(dataPkg_p));
      list.addAll(DataPkgExt.getAllDataTypes(dataPkg_p));
      list = ListExt.removeDuplicates(list);
    }
    return list;
  }

  /**
   * Gets all DataTypes from DataPkg, except signals
   * @param dataPkg_p the DataPkg
   * @return list of all data types
   */
  static public List<CapellaElement> getAllTypesFromDataPkgForPropsNParams(DataPkg dataPkg_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != dataPkg_p) {
      list.addAll(DataPkgExt.getAllClasses(dataPkg_p));
      list.addAll(DataPkgExt.getAllCollections(dataPkg_p));
      list.addAll(DataPkgExt.getAllDataTypes(dataPkg_p));
      list = ListExt.removeDuplicates(list);
    }
    return list;
  }

  /**
   * Gets all the unions recursively from the Data package
   * @param dataPkg_p the DataPkg
   * @return list of unions.
   */
  static public List<Union> getAllUnions(DataPkg dataPkg_p) {
    List<Union> list = new ArrayList<Union>();
    if (null != dataPkg_p) {
      EList<Class> ownedClasses = dataPkg_p.getOwnedClasses();
      for (Class class1 : ownedClasses) {
        if (class1 instanceof Union) {
          list.add((Union) class1);
        }
      }
      for (DataPkg subDataPkg : dataPkg_p.getOwnedDataPkgs()) {
        list.addAll(getAllUnions(subDataPkg));
      }
    }
    return list;
  }

  static public List<Unit> getAllUnits(DataPkg dataPkg_p) {
    List<Unit> list = new ArrayList<Unit>();
    if (null != dataPkg_p) {
      list.addAll(dataPkg_p.getOwnedUnits());
      for (DataPkg subDataPkg : dataPkg_p.getOwnedDataPkgs()) {
        list.addAll(getAllUnits(subDataPkg));
      }
    }
    return list;
  }

  /**
   * Gets all the classes from the parent block architecture and its hierarchy
   * @param compArch_p the Parent BlockArchitecture
   * @return list of classes
   */
  static private List<Class> getClassesFromBlockArchitectureParent(BlockArchitecture compArch_p) {
    List<Class> list = new ArrayList<Class>(1);
    Object container = compArch_p.eContainer();
    if (container instanceof Component) {
      Component parentComp = (Component) container;
      DataPkg dataPkg = parentComp.getOwnedDataPkg();
      if (null != dataPkg) {
        list.addAll(getAllClasses(dataPkg));
      }
      list.addAll(getClassesFromComponentParent((Component) container));
    }
    else if (container instanceof BlockArchitecture) {
      BlockArchitecture compArch = (BlockArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(compArch);
      if (null != dataPkg) {
        list.addAll(getAllClasses(dataPkg));
      }
      list.addAll(getClassesFromBlockArchitectureParent((BlockArchitecture) container));
    }
    return list;
  }

  /**
   * Gets all the classes from the parent component architecture and its hierarchy
   * @param compArch_p the Parent ComponentArchitecture
   * @return list of classes
   */
  static private List<Class> getClassesFromComponentArchitectureParent(
      ComponentArchitecture compArch_p) {
    List<Class> list = new ArrayList<Class>(1);
    Object container = compArch_p.eContainer();
    if (container instanceof Component) {
      Component parentComp = (Component) container;
      DataPkg dataPkg = parentComp.getOwnedDataPkg();
      if (null != dataPkg) {
        list.addAll(getAllClasses(dataPkg));
      }
      list.addAll(getClassesFromComponentParent((Component) container));
    }
    else if (container instanceof ComponentArchitecture) {
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
   * Gets all the classes from the parent component and its hierarchy
   * @param component_p the Parent Component
   * @return list of classes
   */
  static private List<Class> getClassesFromComponentParent(Component component_p) {
    List<Class> list = new ArrayList<Class>(1);
    Object container = component_p.eContainer();
    if (container instanceof Component) {
      Component parentComp = (Component) container;
      DataPkg dataPkg = parentComp.getOwnedDataPkg();
      if (null != dataPkg) {
        list.addAll(getAllClasses(dataPkg));
      }
      list.addAll(getClassesFromComponentParent((Component) container));
    }
    else if (container instanceof ComponentArchitecture) {
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
   * @param classPkg_p the ClassPkg
   * @return list of classes
   */
  static public List<Class> getClassesFromParentHierarchy(DataPkg classPkg_p) {
    List<Class> list = new ArrayList<Class>(1);
    if (null != classPkg_p) {
      BlockArchitecture compArch = getRootBlockArchitecture(classPkg_p);
      if (null != compArch) {
        DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(compArch);
        if (null != dataPkg) {
          list.addAll(getAllClasses(dataPkg));
        }
        list.addAll(getClassesFromBlockArchitectureParent(compArch));
      }
      Component parentComp = getRootComponent(classPkg_p);
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
   * @param dataPkg_p
   * @return all the packages that depends on dataPkg_p
   */
  public static Collection<AbstractDependenciesPkg> getDataPkgDependencies(DataPkg dataPkg_p) {
    Collection<AbstractDependenciesPkg> returnedDependencies =
        new HashSet<AbstractDependenciesPkg>();
    if (dataPkg_p.eContainer() instanceof DataPkg) {
      return getDataPkgDependenciesHierarchy(dataPkg_p, 0);
    }
    for (AbstractDependenciesPkg aDependentPackage : getDataPkgDependenciesHierarchy(dataPkg_p, 0)) {
      AbstractDependenciesPkg currentDependentPkg = aDependentPackage;
      returnedDependencies.add(currentDependentPkg);
      while (aDependentPackage.eContainer() instanceof DataPkg) {
        aDependentPackage = (AbstractDependenciesPkg) aDependentPackage.eContainer();
        returnedDependencies.add(aDependentPackage);
      }
    }
    return returnedDependencies;

  }

  /** for internal use */
  private static void addToResultMap(EObject tgt_p,
      Map<AbstractDependenciesPkg, Collection<EObject>> map_p,
      Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> result_p) {

    Couple<EObject, Collection<EObject>> couple = null;

    Set<Couple<EObject, Collection<EObject>>> col = null;

    if (null != map_p) {
      for (AbstractDependenciesPkg pkg : map_p.keySet()) {

        if (!result_p.containsKey(pkg)) {
          col = new HashSet<Couple<EObject, Collection<EObject>>>();
          result_p.put(pkg, col);
        }

        couple = new Couple<EObject, Collection<EObject>>(tgt_p, map_p.get(pkg));
        result_p.get(pkg).add(couple);
      }

    }

    return;
  }

  /**
   * @see #getDataPkgDependenciesHierarchy(DataPkg, int)
   * @return < dependent package, Collection < object introducing the dependencies, Collection of pointed object > >
   */
  public static Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> getDataPkgDependenciesHierarchy2(
      DataPkg dataPkg_p) {

    Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> result =
        new HashMap<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>>();

    // classes
    for (Class aClass : dataPkg_p.getOwnedClasses()) {
      addToResultMap(aClass, ClassExt.getClassDependencies2(aClass), result);
    }

    // signals
    for (Signal aSignal : dataPkg_p.getOwnedSignals()) {
      addToResultMap(aSignal, SignalExt.getSignalDependencies2(aSignal), result);
    }

    // Datatypes
    for (DataType aDataType : dataPkg_p.getOwnedDataTypes()) {
      addToResultMap(aDataType, DataTypeExt.getDataTypeDependencies2(aDataType), result);
    }

    // ExchangeItem
    for (ExchangeItem anExchangeItem : dataPkg_p.getOwnedExchangeItems()) {
      addToResultMap(anExchangeItem, ExchangeItemExt.getExchangeItemDependencies2(anExchangeItem),
          result);
    }

    // Collection
    for (org.polarsys.capella.core.data.information.Collection aCollection : dataPkg_p
        .getOwnedCollections()) {
      addToResultMap(aCollection, CollectionExt.getCollectionDependencies2(aCollection), result);
    }

    // sub dependencies
    Map<AbstractDependenciesPkg, Collection<Couple<EObject, Collection<EObject>>>> tmp = null;

    for (DataPkg aSubPkg : dataPkg_p.getOwnedDataPkgs()) {
      tmp = getDataPkgDependenciesHierarchy2(aSubPkg);
      for (AbstractDependenciesPkg pkg : tmp.keySet()) {
        if (result.containsKey(pkg)) {
          result.get(pkg).addAll(tmp.get(pkg));
        } else {
          result.put(pkg, tmp.get(pkg));
        }
      }
    }

    return result;
  }

  private static Collection<AbstractDependenciesPkg> getDataPkgDependenciesHierarchy(
      DataPkg dataPkg_p, int hierarchy) {
    Collection<AbstractDependenciesPkg> dependencies = new HashSet<AbstractDependenciesPkg>(); // dependencies
    Collection<AbstractDependenciesPkg> dependenciesHierarchy =
        new HashSet<AbstractDependenciesPkg>(); // dependencies of dataPkg_p ancestors
    // classes
    for (Class aClass : dataPkg_p.getOwnedClasses()) {
      dependencies.addAll(ClassExt.getClassDependencies(aClass));
    }
    // signals
    for (Signal aSignal : dataPkg_p.getOwnedSignals()) {
      dependencies.addAll(SignalExt.getSignalDependencies(aSignal));
    }
    // Datatypes
    for (DataType aDataType : dataPkg_p.getOwnedDataTypes()) {
      dependencies.addAll(DataTypeExt.getDataTypeDependencies(aDataType));
    }
    // ExchangeItem
    for (ExchangeItem anExchangeItem : dataPkg_p.getOwnedExchangeItems()) {
      dependencies.addAll(ExchangeItemExt.getExchangeItemDependencies(anExchangeItem));
    }
    // Collection
    for (org.polarsys.capella.core.data.information.Collection aCollection : dataPkg_p
        .getOwnedCollections()) {
      dependencies.addAll(CollectionExt.getCollectionDependencies(aCollection));
    }

    // Retrieving the dependencies for the ancestors.
    for (AbstractDependenciesPkg aPackage : dependencies) {
      int i = hierarchy;
      AbstractDependenciesPkg dependentPackage = aPackage;
      AbstractDependenciesPkg currentPackage = dataPkg_p;
      while ((i > 0)
             && (dependentPackage.eContainer() instanceof DataPkg)
             && (!(EcoreUtil.isAncestor(dependentPackage.eContainer(), currentPackage) || EcoreUtil
                 .isAncestor(currentPackage, dependentPackage.eContainer())))) {
        dependentPackage = (AbstractDependenciesPkg) dependentPackage.eContainer();
        currentPackage = (AbstractDependenciesPkg) currentPackage.eContainer();
        i--;
      }
      while (i > 0) {
        currentPackage = (AbstractDependenciesPkg) currentPackage.eContainer();
        i--;
      }
      if (!(EcoreUtil.isAncestor(currentPackage, dependentPackage)
            || EcoreUtil.isAncestor(dependentPackage, currentPackage) || currentPackage
          .equals(dependentPackage))) {
        dependenciesHierarchy.add(dependentPackage);
      }
    }
    // Retrieving the dependencies of the sub-packages.
    for (DataPkg aSubPkg : dataPkg_p.getOwnedDataPkgs()) {
      dependenciesHierarchy.addAll(getDataPkgDependenciesHierarchy(aSubPkg, hierarchy + 1));
    }
    return dependenciesHierarchy;
  }

  /**
   * Gets all the datavalues recursively from a DataTypePkg
   * @param dataPkg_p the DataPkg
   * @return list of datavalues
   */
  public static DataPkg getDataPkgOfBlockArchitecture(BlockArchitecture architecture_p) {
    if (architecture_p instanceof OperationalAnalysis) {
      return ((OperationalAnalysis) architecture_p).getOwnedDataPkg();
    } else if (architecture_p instanceof SystemAnalysis) {
      return ((SystemAnalysis) architecture_p).getOwnedDataPkg();
    } else if (architecture_p instanceof LogicalArchitecture) {
      return ((LogicalArchitecture) architecture_p).getOwnedDataPkg();
    } else if (architecture_p instanceof PhysicalArchitecture) {
      return ((PhysicalArchitecture) architecture_p).getOwnedDataPkg();
    } else if (architecture_p instanceof EPBSArchitecture) {
      return ((EPBSArchitecture) architecture_p).getOwnedDataPkg();
    }
    return null;
  }

  /**
   */
  public static DataPkg getDataPkgOfComponentArchitecture(
      ComponentArchitecture componentArchitecture_p) {
    DataPkg dataPkg = null;
    if (componentArchitecture_p instanceof SystemEngineering) {
      SystemAnalysis ca =
          SystemEngineeringExt.getOwnedSystemAnalysis((SystemEngineering) componentArchitecture_p);
      dataPkg = ca.getOwnedDataPkg();
    } else if (componentArchitecture_p instanceof SystemAnalysis) {
      dataPkg = ((SystemAnalysis) componentArchitecture_p).getOwnedDataPkg();
    } else if (componentArchitecture_p instanceof LogicalArchitecture) {
      dataPkg = ((LogicalArchitecture) componentArchitecture_p).getOwnedDataPkg();
    } else if (componentArchitecture_p instanceof PhysicalArchitecture) {
      dataPkg = ((PhysicalArchitecture) componentArchitecture_p).getOwnedDataPkg();
    } else if (componentArchitecture_p instanceof EPBSArchitecture) {
      dataPkg = ((EPBSArchitecture) componentArchitecture_p).getOwnedDataPkg();
    }
    return dataPkg;
  }

  /**
   * Gets all the DataPkgs from the parent block architecture and its hierarchy
   * @param blockArch_p the Parent BlockArchitecture
   * @return list of DataPkgs
   */
  static public List<DataPkg> getDataPkgsFromBlockArchitectureParent(BlockArchitecture blockArch_p) {
    List<DataPkg> list = new ArrayList<DataPkg>(1);
    Object container = blockArch_p.eContainer();
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
   * @param compArch_p the Parent ComponentArchitecture
   * @return list of DataPkgs
   */
  static public List<DataPkg> getDataPkgsFromComponentArchitectureParent(
      ComponentArchitecture compArch_p) {
    List<DataPkg> list = new ArrayList<DataPkg>(1);
    Object container = compArch_p.eContainer();
    if (container instanceof Component) {
      Component parentComp = (Component) container;
      DataPkg dataPkg = parentComp.getOwnedDataPkg();
      if (null != dataPkg) {
        list.add(dataPkg);
      }
      list.addAll(getDataPkgsFromComponentParent((Component) container));
    }
    if (container instanceof ComponentArchitecture) {
      ComponentArchitecture compArch = (ComponentArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(compArch);
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
   * @param component_p the Parent Component
   * @return list of DataPkgs
   */
  static public List<DataPkg> getDataPkgsFromComponentParent(Component component_p) {
    List<DataPkg> list = new ArrayList<DataPkg>(1);
    Object container = component_p.eContainer();
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
   * Gets all the DataPkgs from the Parent Hierarchy of the root component/component architecture of the current DataTypePkg according to layer visibility and
   * multiple decomposition rules
   * @param dataPkg_p the DataTypePkg
   * @return list of DataPkgs
   */
  static public List<DataPkg> getDataPkgsFromParentHierarchy(DataPkg dataPkg_p) {
    List<DataPkg> list = new ArrayList<DataPkg>(1);
    if (null != dataPkg_p) {
      BlockArchitecture blockArch = getRootBlockArchitecture(dataPkg_p);
      if (null != blockArch) {
        DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(blockArch);
        if (null != dataPkg) {
          list.add(dataPkg);
        }
        // if the layer visibility is there
        if (blockArch instanceof SystemEngineering) {
          return list; // return if SystemEngineering
        }
        list.addAll(DataPkgExt.getDataPkgsFromBlockArchitectureParent(blockArch));
      }
      Component parentComp = getRootComponent(dataPkg_p);
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
   * Gets all the Exception from the parent component architecture and its hierarchy
   * @param compArch_p the Parent ComponentArchitecture
   * @return list of Exception
   */
  static private List<Exception> getExceptionsFromBlockArchitectureParent(
      BlockArchitecture compArch_p) {
    List<Exception> list = new ArrayList<Exception>(1);
    Object container = compArch_p.eContainer();
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
      BlockArchitecture compArch = (BlockArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(compArch);
      if (null != dataPkg) {
        list.addAll(getAllExceptions(dataPkg));
      }
      list.addAll(getExceptionsFromBlockArchitectureParent((BlockArchitecture) container));
    }
    return list;
  }

  /**
   * Gets all the Exception from the parent component architecture and its hierarchy
   * @param compArch_p the Parent ComponentArchitecture
   * @return list of Exception
   */
  static private List<Exception> getExceptionsFromComponentArchitectureParent(
      ComponentArchitecture compArch_p) {
    List<Exception> list = new ArrayList<Exception>(1);
    Object container = compArch_p.eContainer();
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
  static private List<Exception> getExceptionsFromComponentParent(Component component_p) {
    List<Exception> list = new ArrayList<Exception>(1);
    Object container = component_p.eContainer();
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
  static public List<Exception> getExceptionsFromParentHierarchy(DataPkg exceptionPkg_p) {
    List<Exception> list = new ArrayList<Exception>(1);
    if (null != exceptionPkg_p) {
      BlockArchitecture compArch = getRootBlockArchitecture(exceptionPkg_p);
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
      Component parentComp = getRootComponent(exceptionPkg_p);
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
   * @param interfacePkg_p the InterfacePkg
   * @return list of Exception
   */
  static public List<Exception> getExceptionsFromParentHierarchy(InterfacePkg interfacePkg_p) {
    List<Exception> list = new ArrayList<Exception>(1);
    if (null != interfacePkg_p) {
      BlockArchitecture compArch = InterfacePkgExt.getRootBlockArchitecture(interfacePkg_p);
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
      Component parentComp = InterfacePkgExt.getRootComponent(interfacePkg_p);
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
   * @param compArch_p the Parent ComponentArchitecture
   * @return list of messages
   */
  static private List<Message> getMessagesFromBlockArchitectureParent(BlockArchitecture compArch_p) {
    List<Message> list = new ArrayList<Message>(1);
    Object container = compArch_p.eContainer();
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
      BlockArchitecture compArch = (BlockArchitecture) container;
      DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(compArch);
      if (null != dataPkg) {
        list.addAll(getAllMessages(dataPkg));
      }
      list.addAll(getMessagesFromBlockArchitectureParent((BlockArchitecture) container));
    }
    return list;
  }

  /**
   * Gets all the messages from the parent component architecture and its hierarchy
   * @param compArch_p the Parent ComponentArchitecture
   * @return list of messages
   */
  static private List<Message> getMessagesFromComponentArchitectureParent(
      ComponentArchitecture compArch_p) {
    List<Message> list = new ArrayList<Message>(1);
    Object container = compArch_p.eContainer();
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
   * Gets all the messages from the parent component and its hierarchy
   * @param component_p the Parent Component
   * @return list of messages
   */
  static private List<Message> getMessagesFromComponentParent(Component component_p) {
    List<Message> list = new ArrayList<Message>(1);
    Object container = component_p.eContainer();
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
   * @param messagePkg_p the MessagePkg
   * @return list of messages
   */
  static public List<Message> getMessagesFromParentHierarchy(DataPkg messagePkg_p) {
    List<Message> list = new ArrayList<Message>(1);
    if (null != messagePkg_p) {
      BlockArchitecture compArch = getRootBlockArchitecture(messagePkg_p);
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
      Component parentComp = getRootComponent(messagePkg_p);
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
   * @param interfacePkg_p the InterfacePkg
   * @return list of messages
   */
  static public List<Message> getMessagesFromParentHierarchy(InterfacePkg interfacePkg_p) {
    List<Message> list = new ArrayList<Message>(1);
    if (null != interfacePkg_p) {
      BlockArchitecture compArch = InterfacePkgExt.getRootBlockArchitecture(interfacePkg_p);
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
      Component parentComp = InterfacePkgExt.getRootComponent(interfacePkg_p);
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
   * @param dataPkg_p current Data Package
   * @return recursively all sub Data Packages
   */
  public static List<DataPkg> getRecursiveSubDataPkgs(DataPkg dataPkg_p) {
    List<DataPkg> returnedList = new ArrayList<DataPkg>();
    returnedList.addAll(dataPkg_p.getOwnedDataPkgs());
    for (DataPkg aSubPkg : dataPkg_p.getOwnedDataPkgs()) {
      returnedList.addAll(getRecursiveSubDataPkgs(aSubPkg));
    }
    return returnedList;
  }

  /**
   * @param modelElement_p : any 'ModelElement'
   * @return : 'BlockArchitecture', value can also be null
   */
  public static BlockArchitecture getRootBlockArchitecture(ModelElement modelElement_p) {
    BlockArchitecture arch = null;
    if (modelElement_p != null) {
      EObject container = modelElement_p.eContainer();
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
   * @param dataPkg_p the DataPkg
   * @return the component
   */
  static public Component getRootComponent(DataPkg dataPkg_p) {
    Component comp = null;
    if (null != dataPkg_p) {
      Object container = dataPkg_p.eContainer();
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
   * @param dataPkg_p the DataPkg
   * @return the component architecture
   */
  static public ComponentArchitecture getRootComponentArchitecture(DataPkg dataPkg_p) {
    ComponentArchitecture compArchitecture = null;
    if (null != dataPkg_p) {
      Object container = dataPkg_p.eContainer();
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
  static public DataPkg getRootDataPkg(DataPkg exceptionPkg_p) {
    DataPkg rootPkg = null;
    if (null != exceptionPkg_p) {
      Object container = exceptionPkg_p.eContainer();
      if (container instanceof DataPkg) {
        rootPkg = getRootDataPkg((DataPkg) container);
      } else {
        return exceptionPkg_p;
      }
    }
    return rootPkg;
  }

  /**
   * Gets all the signals from the parent component architecture and its hierarchy
   * @param compArch_p the Parent ComponentArchitecture
   * @return list of signals
   */
  static private List<Signal> getSignalsFromComponentArchitectureParent(
      ComponentArchitecture compArch_p) {
    return getSignalsFromComponentArchitectureParent(compArch_p, false);
  }

  static private List<Signal> getSignalsFromComponentArchitectureParent(
      ComponentArchitecture compArch_p, boolean isLayerVisibilityRequired) {
    List<Signal> list = new ArrayList<Signal>(1);
    Object container = compArch_p.eContainer();
    if (isLayerVisibilityRequired) {
      if (container instanceof System) {
        return list;// return if System has come in the recursion level
      } else if (container instanceof SystemEngineering) {
        return list; // return if SystemEngineering has come in the recursion
      }
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
   * Gets all the signals from the parent component and its hierarchy
   * @param component_p the Parent Component
   * @return list of signals
   */
  static private List<Signal> getSignalsFromComponentParent(Component component_p) {
    return getSignalsFromComponentParent(component_p, false);
  }

  /**
   */
  static private List<Signal> getSignalsFromComponentParent(Component component_p,
      boolean isLayerVisibilityRequired) {
    List<Signal> list = new ArrayList<Signal>(1);
    Object container = component_p.eContainer();
    if (isLayerVisibilityRequired) {
      if (container instanceof System) {
        return list;// return if System has come in the recursion level
      } else if (container instanceof SystemEngineering) {
        return list; // return if SystemEngineering has come in the recursion
      }
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
   * @param signalPkg_p the SignalPkg
   * @return list of signals
   */
  static public List<Signal> getSignalsFromParentHierarchy(DataPkg signalPkg_p) {
    List<Signal> list = new ArrayList<Signal>(1);
    if (null != signalPkg_p) {
      ComponentArchitecture compArch = getRootComponentArchitecture(signalPkg_p);
      if (null != compArch) {
        DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(compArch);
        if (null != dataPkg) {
          list.addAll(getAllSignals(dataPkg));
        }
        list.addAll(getSignalsFromComponentArchitectureParent(compArch));
      }
      Component parentComp = getRootComponent(signalPkg_p);
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
   * @param interfacePkg_p the InterfacePkg
   * @return list of signals
   */
  static public List<Signal> getSignalsFromParentHierarchy(InterfacePkg interfacePkg_p) {
    List<Signal> list = new ArrayList<Signal>(1);
    if (null != interfacePkg_p) {
      ComponentArchitecture compArch = InterfacePkgExt.getRootComponentArchitecture(interfacePkg_p);
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
      Component parentComp = InterfacePkgExt.getRootComponent(interfacePkg_p);
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
   * Check if elt2_p element is belong to Data package and same layer than elt1_p
   */
  public static boolean isBelongToSameDataPkgLayer(ModelElement elt1_p, ModelElement elt2_p) {
    if ((elt1_p instanceof CapellaElement) && (elt2_p instanceof CapellaElement)) {
      if ((null != elt2_p)
          && EcoreUtil2.isContainedBy(elt2_p, InformationPackage.Literals.DATA_PKG)
          && (CapellaLayerCheckingExt.getLayersName((CapellaElement) elt1_p) == CapellaLayerCheckingExt
              .getLayersName((CapellaElement) elt2_p))) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return parent DataPkg if any
   * @param capellaElement_p : capella element
   * @return data pkg
   */
  public static DataPkg getParentDataPkg(CapellaElement capellaElement_p) {
    if (null != capellaElement_p) {
      if (capellaElement_p instanceof DataPkg) {
        return (DataPkg) capellaElement_p;
      }
      EObject eContainer = capellaElement_p.eContainer();
      if ((null != eContainer) && (eContainer instanceof CapellaElement)) {
        return getParentDataPkg((CapellaElement) eContainer);
      }
    }
    return null;
  }

  /**
   * Return first dataPkg of Root architecture
   * @param eObject_p
   * @return
   */
  static public DataPkg getFirstDataPkgOfRootBlockArchitecture(EObject eObject_p) {
    if (null != eObject_p) {
      BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(eObject_p);
      DataPkgExt.getDataPkgOfBlockArchitecture(arch);
    }
    return null;
  }

  /**
   * Get DataValues form DataPkgs (consider owned DataTypes)
   * @param listPackages
   * @return
   */
  @Deprecated
  // => Use getDataValues(EObject) instead
  public static List<DataValue> getDataValues(List<DataPkg> listPackages) {
    List<DataValue> result = new ArrayList<DataValue>(1);
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
   * @param listPackages
   * @return
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
public static List<DataValue> getDataValues(EObject semanticsObject) {
    // OLD CODE
    List<DataValue> result = new ArrayList<DataValue>(1);
    List<DataPkg> listPackages = DataPkgExt.getAllDataPkgs(semanticsObject);
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
    // NEW CODE
    result =
        (List) QueryDebugger.executeQueryWithEqualityDebug(
            QueryIdentifierConstants.GET_DATA_VALUES, semanticsObject, result);
    // END CODE REFACTOR
    return result;
  }

  public static boolean isPredefinedDataType(EObject element_p) {
    // We allow differences of dataPkg
    EObject parentDataPkg =
        EcoreUtil2.getFirstContainer(element_p, InformationPackage.Literals.DATA_PKG);
    if (parentDataPkg != null) {
      if (((DataPkg) parentDataPkg).getName().equals(
          NamingConstants.PredefinedTypesCmd_predefinedDataTypePkg_name)) {
        return true;
      }
    }
    return false;
  }
}
