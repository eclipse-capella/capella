/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return abstractTypedElements of the current AbstractType Element and all its recursive sub AbstractTypes
 *
 */
public class AbstractTypeAbstractTypedElement implements IQuery {

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (!TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven((ModelElement) object)))
      return result;
    if (object instanceof AbstractType) {
      AbstractType abstractType = (AbstractType) object;
      // List to collect all the sub abstract type of current abstract type
      List<AbstractType> list = new ArrayList<AbstractType>();
      // add all sub abstract type elements
      if (abstractType instanceof GeneralizableElement) {
        GeneralizableElement ele = (GeneralizableElement) abstractType;
        List<GeneralizableElement> allSuperGenElts = GeneralizableElementExt.getAllSubGeneralizableElements(ele);
        if (!allSuperGenElts.isEmpty()) {
          list.addAll(allSuperGenElts);
        }
      }

      // add the abstract typed elements to the result
      for (AbstractType abtype : list) {
        EList<AbstractTypedElement> abstractTypedElements = abtype.getAbstractTypedElements();
        if (!abstractTypedElements.isEmpty()) {
          result.addAll(abstractTypedElements);
        }
      }

    }
    return result;
  }
}
