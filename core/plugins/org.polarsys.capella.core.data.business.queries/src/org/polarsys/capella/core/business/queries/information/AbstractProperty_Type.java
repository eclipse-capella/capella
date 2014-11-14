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
package org.polarsys.capella.core.business.queries.information;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.PropertyExt;

/**
 * This is the base query for properties and union properties types.
 */
public abstract class AbstractProperty_Type extends CapellaElement_CurrentAndHigherLevelsQuery {

  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,
   *      org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    // Find the association if there is any, i.e if the property is an "edge" of an association
    Association association = getRegardingAssociation(capellaElement_p);
    // all classes and unions (Union extends Class, so using only the CLASS literal here will also bring up the unions)
    // from root dataPkg
    List<CapellaElement> returnValue =
        CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg_p, InformationPackage.Literals.CLASS, null);
    // all collection
    // from root dataPkg
    returnValue.addAll(CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg_p, InformationPackage.Literals.COLLECTION, null));
    // considering the possibility that the dataPkg could be under Components
    // All Classes and Collections types from dataPkgs contained by Component
    List<EClass> eClasses = new ArrayList<EClass>();
    eClasses.add(InformationPackage.Literals.CLASS);
    eClasses.add(InformationPackage.Literals.COLLECTION);
    returnValue.addAll(getElementsFromDataPkgContainedInComponent(capellaElement_p,eClasses, capellaElement_p));
    eClasses.clear();
    
    if (null != association) {
      returnValue = addAssociationsSpecificElements(returnValue, association, capellaElement_p, dataPkg_p);
      // Remove the primitive classes
      returnValue = removePrimitiveClasses(returnValue);
      // Remove the primitive collections
      returnValue = removePrimitiveCollections(returnValue);
    } else {
      // This is not an association, so adds
      // All data types
      List<CapellaElement> dataTypes =
          CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg_p, DatatypePackage.Literals.DATA_TYPE, null);
      returnValue.addAll(dataTypes);
      // All data types from dataPkgs contained by Component
      eClasses.add(DatatypePackage.Literals.DATA_TYPE);
      returnValue.addAll(getElementsFromDataPkgContainedInComponent(capellaElement_p,eClasses, capellaElement_p));
      // Removes non primitive classes
      returnValue = removeNonPrimitiveClasses(returnValue);
      // Removes non primitive collections
      returnValue = removeNonPrimitiveCollections(returnValue);

    }
    return returnValue;
  }

  /**
   * Returns the Capella Elements instances of the given
   *  <code>EClass</code> in the given <code>DataPkg</code>, but avoiding the given capella Element.
   * Considering only DataPkgs contained in Component  
   * @param capellaElement_p
   * @param class_p
   * @param capellaElement2_p
   * @return
   */
  private Collection<? extends CapellaElement> getElementsFromDataPkgContainedInComponent(CapellaElement capellaElement_p, 
		List<EClass> classes_p,	CapellaElement capellaElement2_p) {
	List<CapellaElement> returnValue = new ArrayList<CapellaElement>(1);
	// if one of the ancestor of capella element is Type Component
	List<Component> componentHierarchy = CapellaElementExt.getComponentHierarchy(capellaElement_p);
	if (!componentHierarchy.isEmpty()) {
		for (Component component : componentHierarchy) {
			List<DataPkg> allDataPkgs = DataPkgExt.getAllDataPkgs(component);
			for (DataPkg dataPkg : allDataPkgs) {
				for (EClass eClass : classes_p) {
					returnValue.addAll(CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg, eClass, null));					
				}
			}
		}
	}
	return returnValue;
  }

/**
   * Returns the regarding association, i.e the association the current property is bound to (since the current query is applied to properties)
   * @param elem_p the property (must be a <code>Property</code> instance
   * @return the <code>Association</code> or <code>null</code> if the property is not bound to an association
   */
  protected static Association getRegardingAssociation(CapellaElement elem_p) {
    return PropertyExt.getRegardingAssociation(elem_p);
  }

  /**
   * Adds the association specific (i.e when the property is bound to an association and when the aggregation kind of the property is "ASSOCIATION") elements to
   * the query result
   * @param list_p the current query result
   * @param association_p the association
   * @param elem_p
   * @param dataPkg_p
   * @return
   */
  protected List<CapellaElement> addAssociationsSpecificElements(List<CapellaElement> list_p, Association association_p, CapellaElement elem_p, DataPkg dataPkg_p) {
    // Just a security test but it should be the case here:
    if (null != association_p && elem_p instanceof Property) {
      Property prop = (Property) elem_p;
      AggregationKind aggregationKind = prop.getAggregationKind();
      if (aggregationKind == AggregationKind.ASSOCIATION) {
        // Adds the signals
        list_p.addAll(CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg_p, CommunicationPackage.Literals.SIGNAL, null));
        // Adds the exceptions
        list_p.addAll(CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg_p, CommunicationPackage.Literals.EXCEPTION, null));
      }
    }
    return list_p;
  }
}
