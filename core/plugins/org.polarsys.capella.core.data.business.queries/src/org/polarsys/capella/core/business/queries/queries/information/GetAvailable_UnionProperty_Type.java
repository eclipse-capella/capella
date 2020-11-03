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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.PropertyExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_UnionProperty_Type extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<EObject> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
   */
  public List<EObject> getAvailableElements(CapellaElement element) {
    List<EObject> returnValue = new ArrayList<EObject>();
    BlockArchitecture currentBlockArchitecture = DataPkgExt.getRootBlockArchitecture(element);
    SystemEngineering systemEngineering = SystemEngineeringExt.getSystemEngineering(element);
    OperationalAnalysis operationalAnalysis = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEngineering);
    returnValue.addAll(getDataFromLevel(operationalAnalysis, element));
    if (!(currentBlockArchitecture instanceof OperationalAnalysis)) {
      SystemAnalysis systemAnalysis = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
      returnValue.addAll(getDataFromLevel(systemAnalysis, element));
      if (!(currentBlockArchitecture instanceof SystemAnalysis)) {
        LogicalArchitecture logicalArchitecture = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
        returnValue.addAll(getDataFromLevel(logicalArchitecture, element));
        if (!(currentBlockArchitecture instanceof LogicalArchitecture)) {
          PhysicalArchitecture physicalArchitecture = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
          returnValue.addAll(getDataFromLevel(physicalArchitecture, element));
          if (!(currentBlockArchitecture instanceof PhysicalArchitecture)) {
            EPBSArchitecture epbsArchitecture = SystemEngineeringExt.getEPBSArchitecture((systemEngineering));
            returnValue.addAll(getDataFromLevel(epbsArchitecture, element));
          }
        }
      }
    }
    returnValue.addAll(getUnlevelizedData(element));
    returnValue.addAll(getDataFromComponentHierarchy(element));
    returnValue.addAll(getDataFromRealizedComponentsHierarchy(element));
    returnValue.addAll(getTypesFromComponentHierarchy(element));
    returnValue = filterUnNamedElements(returnValue);
    return returnValue;
  }

  /**
   * This method purpose is to get the available data related to the given element in the given layer
   * @param blockArchitecture the layer
   * @param capellaElement the capella element
   * @return the available elements
   */
  public List<EObject> getDataFromLevel(BlockArchitecture blockArchitecture, CapellaElement capellaElement) {
    if (null != blockArchitecture) {
      DataPkg dataPkg = blockArchitecture.getOwnedDataPkg();
      if (null != dataPkg) {
        return getDataFromLevel(dataPkg, capellaElement);
      }
    }
    return Collections.emptyList();
  }

  /**
   * Returns the available data which do not need to use a level to be found.<br>
   * The default implementation returns an empty list.<br>
   * May be overridden when queries need to find data without using layers.
   * @param capellaElement the capella element
   * @return the available elements
   */
  public List<CapellaElement> getUnlevelizedData(CapellaElement capellaElement) {
    return Collections.emptyList();
  }

  /** 
	*/
  protected List<EObject> getDataFromComponentHierarchy(CapellaElement element) {
    List<EObject> allDatas = new ArrayList<EObject>();
    for (Component cpnt : CapellaElementExt.getComponentHierarchy(element)) {
      DataPkg dataPkg = cpnt.getOwnedDataPkg();
      if (null != dataPkg) {
        allDatas.addAll(getDataFromLevel(dataPkg, element));
      }
    }
    return allDatas;
  }

  /** 
	*/
  protected List<EObject> getDataFromRealizedComponentsHierarchy(CapellaElement element) {
    List<EObject> allDatas = new ArrayList<EObject>();
    Component currentCpnt = (element instanceof Component) ? (Component) element : null;
    if (null == currentCpnt) {
      currentCpnt = (Component) EcoreUtil2.getFirstContainer(element, CsPackage.Literals.COMPONENT);
    }
    if (null != currentCpnt) {
      for (Component allocatedCpnt : currentCpnt.getRealizedComponents()) {
        List<Component> componentHierarchy = CapellaElementExt.getComponentHierarchy(allocatedCpnt);
        componentHierarchy.add(allocatedCpnt);
        for (Component cpnt : componentHierarchy) {
          DataPkg dataPkg = cpnt.getOwnedDataPkg();
          if (null != dataPkg) {
            for (EObject data : getDataFromLevel(dataPkg, element)) {
              if (!allDatas.contains(data)) {
                allDatas.add(data);
              }
            }
          }
        }
      }
    }
    return allDatas;
  }

  /** 
	*/
  protected List<EObject> getTypesFromComponentHierarchy(CapellaElement element) {
    List<EObject> allDatas = new ArrayList<EObject>();
    for (Component cpnt : CapellaElementExt.getComponentHierarchy(element)) {
      DataPkg dataPkg = cpnt.getOwnedDataPkg();
      if (null != dataPkg) {
        allDatas.addAll(DataPkgExt.getAllTypesFromDataPkg(dataPkg));
      }
    }
    allDatas = removeNonPrimitiveClasses(allDatas);
    allDatas = removeNonPrimitiveCollections(allDatas);
    return allDatas;
  }

  /**
   * filter unNamed Capella Elements
   * @param list
   * @return : List<CapellaElement>
   */
  protected List<EObject> filterUnNamedElements(List<EObject> list) {
    List<EObject> result = new ArrayList<EObject>(1);
    for (EObject capellaElement : list) {
      if (capellaElement instanceof AbstractNamedElement) {
        String name = ((AbstractNamedElement) capellaElement).getName();
        if ((null != name) && !ICommonConstants.EMPTY_STRING.equals(name)) {
          result.add(capellaElement);
        }
      }
    }
    return result;
  }

  /**
   * Removes the primitives classes from the given list
   * @param elements the list to handle
   * @return the processed list
   */
  protected List<EObject> removePrimitiveClasses(List<EObject> elements) {
    return removePrimitiveOrNonPrimitiveClasses(elements, true);
  }

  /**
   * Removes the primitives collections from the given list
   * @param elements the list to handle
   * @return the processed list
   */
  protected List<EObject> removePrimitiveCollections(List<EObject> elements) {
    return removePrimitiveOrNonPrimitiveCollections(elements, true);
  }

  /**
   * Removes the non primitives classes from the given list
   * @param elements the list to handle
   * @return the processed list
   */
  protected List<EObject> removeNonPrimitiveClasses(List<EObject> elements) {
    return removePrimitiveOrNonPrimitiveClasses(elements, false);
  }

  /**
   * Removes the non primitives Collections from the given list
   * @param elements the list to handle
   * @return the processed list
   */
  protected List<EObject> removeNonPrimitiveCollections(List<EObject> elements) {
    return removePrimitiveOrNonPrimitiveCollections(elements, false);
  }

  /**
   * Allows to remove primitive or non primitive classes from a list
   * @param elements the list
   * @param removePrimitive <code>true</code> if you want to remove the primitive classes, <code>false</code> if you want to remove the non primitive classes
   * @return the processed list
   */
  protected List<EObject> removePrimitiveOrNonPrimitiveClasses(List<EObject> elements, boolean removePrimitive) {
    List<EObject> returnValue = new ArrayList<EObject>();
    for (EObject element : elements) {
      if (element instanceof Class) {
        Class currentClass = (Class) element;
        if ((!removePrimitive && currentClass.isIsPrimitive()) || (removePrimitive && !currentClass.isIsPrimitive())) {
          returnValue.add(currentClass);
        }
      } else {
        returnValue.add(element);
      }
    }
    return returnValue;
  }

  /**
   * Allows to remove primitive or non primitive Collections from a list
   * @param elements the list
   * @param removePrimitive <code>true</code> if you want to remove the primitive Collections, <code>false</code> if you want to remove the non primitive
   *          Collections
   * @return the processed list
   */
  protected List<EObject> removePrimitiveOrNonPrimitiveCollections(List<EObject> elements, boolean removePrimitive) {
    List<EObject> returnValue = new ArrayList<EObject>();
    for (EObject element : elements) {
      if (element instanceof Collection) {
        Collection currentCollection = (Collection) element;
        if ((!removePrimitive && currentCollection.isIsPrimitive()) || (removePrimitive && !currentCollection.isIsPrimitive())) {
          returnValue.add(currentCollection);
        }
      } else {
        returnValue.add(element);
      }
    }
    return returnValue;
  }

  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<EObject> getDataFromLevel(DataPkg dataPkg, CapellaElement capellaElement) {
    Association association = getRegardingAssociation(capellaElement);
    List<EObject> returnValue = CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg, InformationPackage.Literals.CLASS, null);
    returnValue.addAll(CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg, InformationPackage.Literals.COLLECTION, null));
    List<EClass> eClasses = new ArrayList<EClass>();
    eClasses.add(InformationPackage.Literals.CLASS);
    eClasses.add(InformationPackage.Literals.COLLECTION);
    returnValue.addAll(getElementsFromDataPkgContainedInComponent(capellaElement, eClasses, capellaElement));
    eClasses.clear();
    if (null != association) {
      returnValue = addAssociationsSpecificElements(returnValue, association, capellaElement, dataPkg);
      returnValue = removePrimitiveClasses(returnValue);
      returnValue = removePrimitiveCollections(returnValue);
    } else {
      List<EObject> dataTypes = CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg, DatatypePackage.Literals.DATA_TYPE, null);
      returnValue.addAll(dataTypes);
      eClasses.add(DatatypePackage.Literals.DATA_TYPE);
      returnValue.addAll(getElementsFromDataPkgContainedInComponent(capellaElement, eClasses, capellaElement));
      returnValue = removeNonPrimitiveClasses(returnValue);
      returnValue = removeNonPrimitiveCollections(returnValue);
    }
    return returnValue;
  }

  /**
   * Returns the regarding association, i.e the association the current property is bound to (since the current query is applied to properties)
   * @param elem the property (must be a <code>Property</code> instance
   * @return the <code>Association</code> or <code>null</code> if the property is not bound to an association
   */
  protected static Association getRegardingAssociation(CapellaElement elem) {
    return PropertyExt.getRegardingAssociation(elem);
  }

  /**
   * Returns the Capella Elements instances of the given <code>EClass</code> in the given <code>DataPkg</code>, but avoiding the given capella Element.
   * Considering only DataPkgs contained in Component
   * @param capellaElement
   * @param class
   * @param capellaElement2
   * @return
   */
  private java.util.Collection<? extends EObject> getElementsFromDataPkgContainedInComponent(CapellaElement capellaElement, List<EClass> classes,
      CapellaElement capellaElement2) {
    List<EObject> returnValue = new ArrayList<EObject>(1);
    List<Component> componentHierarchy = CapellaElementExt.getComponentHierarchy(capellaElement);
    if (!componentHierarchy.isEmpty()) {
      for (Component component : componentHierarchy) {
        List<DataPkg> allDataPkgs = DataPkgExt.getAllDataPkgs(component);
        for (DataPkg dataPkg : allDataPkgs) {
          for (EClass eClass : classes) {
            returnValue.addAll(CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg, eClass, null));
          }
        }
      }
    }
    return returnValue;
  }

  /**
   * Adds the association specific (i.e when the property is bound to an association and when the aggregation kind of the property is "ASSOCIATION") elements to
   * the query result
   * @param list the current query result
   * @param association the association
   * @param elem
   * @param dataPkg
   * @return
   */
  protected List<EObject> addAssociationsSpecificElements(List<EObject> list, Association association, CapellaElement elem, DataPkg dataPkg) {
    if ((null != association) && (elem instanceof Property)) {
      Property prop = (Property) elem;
      AggregationKind aggregationKind = prop.getAggregationKind();
      if (aggregationKind == AggregationKind.ASSOCIATION) {
        list.addAll(CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg, CommunicationPackage.Literals.SIGNAL, null));
        list.addAll(CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg, CommunicationPackage.Literals.EXCEPTION, null));
      }
    }
    return list;
  }

}
