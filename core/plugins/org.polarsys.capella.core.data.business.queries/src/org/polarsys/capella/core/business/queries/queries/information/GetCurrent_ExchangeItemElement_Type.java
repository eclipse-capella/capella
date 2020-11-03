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
import java.util.List;

import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetCurrent_ExchangeItemElement_Type extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> currentElements = getCurrentElements(capellaElement, false);
    return (List) currentElements;
  }

  /**
   * <p>
   * Gets the type of current Parameter
   * </p>
   * <p>
   * Refer MQRY_Parameter_Type_1
   * </p>
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    if (null == systemEngineering) {
      SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
      for (ReuseLink link : sharedPkg.getReuseLinks()) {
        if (SystemEngineeringExt.getSystemEngineering(link) != null) {
          systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
          break;
        }
      }
      if (systemEngineering == null) {
        return currentElements;
      }
    }
    if (element instanceof ExchangeItemElement) {
      ExchangeItemElement parameter = (ExchangeItemElement) element;
      AbstractType type = parameter.getType();
      if (null != type) {
        currentElements.add((CapellaElement) type);
      }
    }
    return currentElements;
  }

}
