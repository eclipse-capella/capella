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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.datatype.DataType;

/**
 * This is the Union query for the discriminant
 */
public class Union_Discriminant implements IBusinessQuery {

  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (element_p instanceof Union) {
      Union union = (Union) element_p;
      // Adds the union properties which are both DataType and Discrete
      availableElements.addAll(getDiscreteDatatypesProperties(union));
      // Also look into the inherited Unions
      List<GeneralizableElement> unionInheritanceHierarchy = GeneralizableElementExt.getAllSuperGeneralizableElements(union);
      for (CapellaElement element : unionInheritanceHierarchy) {
        if (element instanceof Union) {
          // Also adds the ones of the inherited Unions
          Union inheritedUnion = (Union) element;
          availableElements.addAll(getDiscreteDatatypesProperties(inheritedUnion));
        }
      }
    }
    return availableElements;
  }

  /**
   * Gets the owned properties of the given <code>Union</code> which are both DataType and Discrete
   * @param union_p the union
   * @return a <code>List</code> containing <code>CapellaElement</code> instances
   */
  protected List<CapellaElement> getDiscreteDatatypesProperties(Union union_p) {
    List<CapellaElement> properties = new ArrayList<CapellaElement>();
    for (Feature aFeature : union_p.getOwnedFeatures()) {
      if (aFeature instanceof UnionProperty) {
        Type type = ((UnionProperty) aFeature).getType();
        if (((type instanceof DataType) && ((DataType) type).isDiscrete())
         || ((type instanceof Class) && ((Class) type).isIsPrimitive()))
        {
          properties.add(aFeature);
        }
      }
    }
    return properties;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement, boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    if (element_p instanceof Union) {
      Union currentUnion = (Union) element_p;
      UnionProperty disc = currentUnion.getDiscriminant();
      if (disc != null) {
        currentElements.add(disc);
      }
    }
    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return InformationPackage.Literals.UNION;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InformationPackage.Literals.UNION__DISCRIMINANT);
  }
}
