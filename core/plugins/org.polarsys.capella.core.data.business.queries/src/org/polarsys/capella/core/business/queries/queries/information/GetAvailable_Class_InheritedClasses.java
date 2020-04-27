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

package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.filters.MultiFilter;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ClassExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.helpers.queries.filters.KeepClassWithSamePrimitiveStateFilter;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveEClassInstanceFilter;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveSubTypesFilter;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * <p>
 * Gets all the classes contained by the class package (and all sub packages) of the current element.
 * </p>
 * <p>
 * Gets all the classes contained by the class package (and all sub packages) of the current element's parent.
 * </p>
 * <p>
 * Gets all the classes contained by the class package (and all sub packages) of the current element's parent hierarchy.
 * </p>
 * <p>
 * Gets all the classes contained by the class package (and all sub packages) of the SharedPkg.
 * </p>
 * <p>
 * Except the current class and the classes in the inheritance hierarchy of the current class
 * </p>
 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
 */
public class GetAvailable_Class_InheritedClasses extends AbstractQuery {

  @Override
  public List<Object> execute(Object inputElement, IQueryContext context) {
    List<EObject> availableElements = new ArrayList<EObject>();
    if (inputElement instanceof Class) {
      Class currentClass = (Class) inputElement;
      SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentClass);
      if (systemEngineering != null) {
        DataPkg rootDataPkg = ClassExt.getRootOwnerDataPkg(currentClass);
        if (rootDataPkg != null) {
          // All the Classes contained by the Class Package (and all of its sub-packages) of the current Element.
          availableElements.addAll(DataPkgExt.getAllClasses(rootDataPkg));
          // All the Classes contained by the Class Package (and all of its sub-packages) of the current Element's parents hierarchy.
          availableElements.addAll(DataPkgExt.getClassesFromParentHierarchy(rootDataPkg));
          // All the Classes contained by the Class Package (and all of its sub-packages) of the current Element's parent (can be a Component, a Component
          // Architecture Decomposition package, or a Component Architecture root package).
          BlockArchitecture parentBlockArchitecture = DataPkgExt.getRootBlockArchitecture(rootDataPkg);
          if (parentBlockArchitecture != null) {
            for (BlockArchitecture block : BlockArchitectureExt.getRootAndPreviousBlockArchitectures(parentBlockArchitecture)) {
              List<CapellaElement> classes = QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_CLASSES_IN_BLOCK_DATA_PACKAGE, block, context);
              availableElements.addAll(classes);
            }
          }
          Component parentComponent = DataPkgExt.getRootComponent(rootDataPkg);
          if (null != parentComponent) {
            DataPkg dataPkg = parentComponent.getOwnedDataPkg();
            if (dataPkg != null) {
              availableElements.addAll(DataPkgExt.getAllClasses(dataPkg));
            }
          }
        }
      }
      availableElements = ListExt.removeDuplicates(availableElements);
      availableElements.remove(inputElement);
      MultiFilter filter =
          new MultiFilter(new IQueryFilter[] { new RemoveEClassInstanceFilter(InformationPackage.Literals.UNION), new RemoveSubTypesFilter(currentClass),
                                              new KeepClassWithSamePrimitiveStateFilter(currentClass) });
      availableElements = QueryInterpretor.executeFilter(availableElements, filter);
    }
    return (List) availableElements;
  }
}
