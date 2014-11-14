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
package org.polarsys.capella.core.business.queries.capellacore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 */
public class Constraint_Expression implements IBusinessQuery {

  /**
   * @param arch_p
   * @param constraint_p
   * @return
   */
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p, Constraint constraint_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

    if (arch_p != null) {
      TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch_p, false);
      while (allContents.hasNext()) {
        Object object = allContents.next();
        if (object instanceof AbstractExpressionValue) {
          AbstractType type = ((AbstractExpressionValue) object).getAbstractType();
          if (type instanceof BooleanType) {
            availableElements.add((AbstractExpressionValue) object);
          }
        }
      }
    }

    // remove existing from the availableElements
    for (CapellaElement elt : getCurrentElements(constraint_p, false)) {
      availableElements.remove(elt);
    }

    return availableElements;
  }

  /**
   * same level Visibility Layer
   * @param constraint_p
   */
  private List<CapellaElement> getRule_MQRY_Constraint_AvailableExpressions_11(Constraint constraint_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

    BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(constraint_p);
    if (null != currentBlockArchitecture) {
      availableElements.addAll(getElementsFromBlockArchitecture(currentBlockArchitecture, constraint_p));
    }

    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    if (element_p instanceof Constraint) {
      availableElements.addAll(getRule_MQRY_Constraint_AvailableExpressions_11((Constraint) element_p));
    }

    return availableElements;
  }

	/**
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement, boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		if (element_p instanceof Constraint) {
		  currentElements.add(((Constraint) element_p).getExpression());
		}

		return currentElements;
	}

	public EClass getEClass() {
		return CapellacorePackage.Literals.CONSTRAINT;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CapellacorePackage.Literals.CONSTRAINT__EXPRESSION);
	}
}
