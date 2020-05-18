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
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetCurrent_BooleanType_InheritedBooleanType extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<EObject> currentElements = getCurrentElements(capellaElement, false);
    return (List) currentElements;
  }

  public List<EObject> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
    List<EObject> currentElements = new ArrayList<EObject>();
    if (!systemEngineeringExists(element)) {
      return currentElements;
    }
    if (element instanceof GeneralizableElement) {
      GeneralizableElement generalizableElement = (GeneralizableElement) element;
      currentElements.addAll(generalizableElement.getSuper());
      currentElements = ListExt.removeDuplicates(currentElements);
      currentElements.remove(generalizableElement);
    }
    return currentElements;
  }

  /**
   * Verifies that there is a "system engineering folder" above the given capella element
   * @param element the given capella element
   * @return <code>true</code> if there is such folder, <code>false</code> otherwise
   */
  public boolean systemEngineeringExists(CapellaElement element) {
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
    if (null == systemEngineering) {
      SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
      for (ReuseLink link : sharedPkg.getReuseLinks()) {
        if (SystemEngineeringExt.getSystemEngineering(link) != null) {
          systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
          break;
        }
      }
      if (systemEngineering == null) {
        return false;
      }
    }
    return true;
  }

}
