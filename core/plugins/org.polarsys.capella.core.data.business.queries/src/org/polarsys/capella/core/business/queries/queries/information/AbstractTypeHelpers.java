/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.filters.MultiFilter;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.PropertyExt;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveSubTypesFilter;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveUnnamedElementFilter;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class AbstractTypeHelpers {

  public static List<EObject> getAvailableElements_Type_InheritedType(CapellaElement element, EClass typeClass, String allTypeInstanceQueryIdentifier) {
    List<EObject> availableElements = new ArrayList<EObject>();
    BlockArchitecture currentBlock = BlockArchitectureExt.getRootBlockArchitecture(element);
    IModel currentProject =  ILibraryManager.INSTANCE.getModel(element);

    if (typeClass.isInstance(element)) {
      final GeneralizableElement currentElement = (GeneralizableElement) element;
      MultiFilter filter = new MultiFilter(new IQueryFilter[] { new RemoveUnnamedElementFilter(), new RemoveSubTypesFilter(currentElement) });
      java.util.Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);
      for (IModel library : libraries) {
        BlockArchitecture correspondingBlock = (BlockArchitecture) QueryExt.getCorrespondingElementInLibrary(currentBlock, (CapellaModel) library);
        for (BlockArchitecture blockArchitecture : BlockArchitectureExt.getAllAllocatedArchitectures(correspondingBlock)) {
          List<CapellaElement> elements = QueryInterpretor.executeQuery(allTypeInstanceQueryIdentifier, blockArchitecture, new QueryContext(), filter);
          availableElements.addAll(elements);
        }
      }

    }
    availableElements = ListExt.removeDuplicates(availableElements);
    return availableElements;
  }

  public static List<EObject> getAvailableElements_Type_Type(CapellaElement element) {
    List<EObject> availableElements = new ArrayList<EObject>();
    BlockArchitecture currentBlock = BlockArchitectureExt.getRootBlockArchitecture(element);
    IModel currentProject =  ILibraryManager.INSTANCE.getModel(element);

    if (element instanceof Property) {
      java.util.Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);
      for (IModel library : libraries) {
        BlockArchitecture correspondingBlock = (BlockArchitecture) QueryExt.getCorrespondingElementInLibrary(currentBlock, (CapellaModel) library);
        for (BlockArchitecture blockArchitecture : BlockArchitectureExt.getAllAllocatedArchitectures(correspondingBlock)) {
          DataPkg dataPkg = blockArchitecture.getOwnedDataPkg();
          if (dataPkg != null) {
            Association association = PropertyExt.getRegardingAssociation(element);
            List<EObject> returnValue =
                CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg, InformationPackage.Literals.CLASS, null);
            returnValue.addAll(CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg, InformationPackage.Literals.COLLECTION, null));
            if (null != association) {
              if (element instanceof Property) {
                Property prop = (Property) element;
                AggregationKind aggregationKind = prop.getAggregationKind();
                if (aggregationKind == AggregationKind.ASSOCIATION) {
                  returnValue.addAll(CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg, CommunicationPackage.Literals.SIGNAL, null));
                  returnValue.addAll(CapellaElementsHelperForBusinessQueries
                      .getCapellaElementsInstancesOf(dataPkg, CommunicationPackage.Literals.EXCEPTION, null));
                }
              }
              returnValue = removePrimitiveOrNonPrimitiveClasses(returnValue, true);
              returnValue = removePrimitiveOrNonPrimitiveCollections(returnValue, true);
            } else {
              List<EObject> dataTypes =
                  CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg, DatatypePackage.Literals.DATA_TYPE, null);
              returnValue.addAll(dataTypes);
              returnValue = removePrimitiveOrNonPrimitiveClasses(returnValue, false);
              returnValue = removePrimitiveOrNonPrimitiveCollections(returnValue, false);
            }
            availableElements.addAll(returnValue);
          }
        }
      }
    }
    availableElements = ListExt.removeDuplicates(availableElements);
    IQueryFilter filter = new RemoveUnnamedElementFilter();
    List<EObject> result = new ArrayList<EObject>();
    for (EObject elt : availableElements) {
      if (filter.keepElement(elt, null)) {
        result.add(elt);
      }
    }
    return result;
  }

  /**
   * Allows to remove primitive or non primitive classes from a list
   * @param elements the list
   * @param removePrimitive <code>true</code> if you want to remove the primitive classes, <code>false</code> if you want to remove the non primitive classes
   * @return the processed list
   */
  private static List<EObject> removePrimitiveOrNonPrimitiveClasses(List<EObject> elements, boolean removePrimitive) {
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
  private static List<EObject> removePrimitiveOrNonPrimitiveCollections(List<EObject> elements, boolean removePrimitive) {
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
}
