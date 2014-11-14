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
package org.polarsys.capella.core.libraries.extendedqueries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.common.libraries.IAbstractLibrary;
import org.polarsys.capella.common.libraries.IAbstractModel;
import org.polarsys.capella.common.libraries.ILibraryManager;
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
import org.polarsys.capella.core.libraries.capellaModel.CapellaLibrary;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.PropertyExt;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveSubTypesFilter;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveUnnamedElementFilter;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.core.queries.helpers.QueryExt;

/**
 */
public class AbstractTypeHelpers {

  public static List<CapellaElement> getAvailableElements_Type_InheritedType(CapellaElement element_p, EClass typeClass, String allTypeInstanceQueryIdentifier) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    BlockArchitecture currentBlock = BlockArchitectureExt.getRootBlockArchitecture(element_p);
    IAbstractModel currentProject = ILibraryManager.INSTANCE.getAbstractModel(element_p);

    if (typeClass.isInstance(element_p)) {
      final GeneralizableElement currentElement = (GeneralizableElement) element_p;
      MultiFilter filter = new MultiFilter(new IQueryFilter[] { new RemoveUnnamedElementFilter(), new RemoveSubTypesFilter(currentElement) });
      java.util.Collection<IAbstractLibrary> libraries = ILibraryManager.INSTANCE.getAllReferencedLibraries(currentProject, true);
      for (IAbstractLibrary library : libraries) {
        BlockArchitecture correspondingBlock = (BlockArchitecture) QueryExt.getCorrespondingElementInLibrary(currentBlock, (CapellaLibrary) library);
        for (BlockArchitecture blockArchitecture : BlockArchitectureExt.getAllAllocatedArchitectures(correspondingBlock)) {
          List<CapellaElement> elements = QueryInterpretor.executeQuery(allTypeInstanceQueryIdentifier, blockArchitecture, new QueryContext(), filter);
          availableElements.addAll(elements);
        }
      }

    }
    availableElements = ListExt.removeDuplicates(availableElements);
    return availableElements;
  }

  public static List<CapellaElement> getAvailableElements_Type_Type(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    BlockArchitecture currentBlock = BlockArchitectureExt.getRootBlockArchitecture(element_p);
    IAbstractModel currentProject = ILibraryManager.INSTANCE.getAbstractModel(element_p);

    if (element_p instanceof Property) {
      java.util.Collection<IAbstractLibrary> libraries = ILibraryManager.INSTANCE.getAllReferencedLibraries(currentProject, true);
      for (IAbstractLibrary library : libraries) {
        BlockArchitecture correspondingBlock = (BlockArchitecture) QueryExt.getCorrespondingElementInLibrary(currentBlock, (CapellaLibrary) library);
        for (BlockArchitecture blockArchitecture : BlockArchitectureExt.getAllAllocatedArchitectures(correspondingBlock)) {
          DataPkg dataPkg = blockArchitecture.getOwnedDataPkg();
          if (dataPkg != null) {
            Association association = PropertyExt.getRegardingAssociation(element_p);
            List<CapellaElement> returnValue =
                CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg, InformationPackage.Literals.CLASS, null);
            returnValue.addAll(CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg, InformationPackage.Literals.COLLECTION, null));
            if (null != association) {
              if (element_p instanceof Property) {
                Property prop = (Property) element_p;
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
              List<CapellaElement> dataTypes =
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
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    for (CapellaElement element : availableElements) {
      if (filter.keepElement(element, null)) {
        result.add(element);
      }
    }
    return result;
  }

  /**
   * Allows to remove primitive or non primitive classes from a list
   * @param elements_p the list
   * @param removePrimitive_p <code>true</code> if you want to remove the primitive classes, <code>false</code> if you want to remove the non primitive classes
   * @return the processed list
   */
  private static List<CapellaElement> removePrimitiveOrNonPrimitiveClasses(List<CapellaElement> elements_p, boolean removePrimitive_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    for (CapellaElement element : elements_p) {
      if (element instanceof Class) {
        Class currentClass = (Class) element;
        if ((!removePrimitive_p && currentClass.isIsPrimitive()) || (removePrimitive_p && !currentClass.isIsPrimitive())) {
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
   * @param elements_p the list
   * @param removePrimitive_p <code>true</code> if you want to remove the primitive Collections, <code>false</code> if you want to remove the non primitive
   *          Collections
   * @return the processed list
   */
  private static List<CapellaElement> removePrimitiveOrNonPrimitiveCollections(List<CapellaElement> elements_p, boolean removePrimitive_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    for (CapellaElement element : elements_p) {
      if (element instanceof Collection) {
        Collection currentCollection = (Collection) element;
        if ((!removePrimitive_p && currentCollection.isIsPrimitive()) || (removePrimitive_p && !currentCollection.isIsPrimitive())) {
          returnValue.add(currentCollection);
        }
      } else {
        returnValue.add(element);
      }
    }
    return returnValue;
  }
}
