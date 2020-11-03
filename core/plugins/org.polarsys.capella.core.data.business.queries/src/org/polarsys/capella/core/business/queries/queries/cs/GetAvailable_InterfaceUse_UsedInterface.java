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
package org.polarsys.capella.core.business.queries.queries.cs;

import java.util.Collections;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

public class GetAvailable_InterfaceUse_UsedInterface extends AbstractQuery {

  @Override
  public List<Object> execute(Object element, IQueryContext context) {

    if (element instanceof InterfaceUse) {

      InterfaceUse implem = (InterfaceUse) element;
      
      String queryIdentifier = ""; //$NON-NLS-1$
      Component component = implem.getInterfaceUser();

      if (component instanceof SystemComponent) {
        queryIdentifier = QueryConstants.GET_AVAILABLE__SYSTEM_COMPONENT__USED_INTERFACES___LIB;

      } else if (component instanceof LogicalComponent) {
        queryIdentifier = QueryConstants.GET_AVAILABLE__LOGICAL_COMPONENT__USED_INTERFACES___LIB;

      } else if (component instanceof PhysicalComponent) {
        queryIdentifier = QueryConstants.GET_AVAILABLE__PHYSICAL_COMPONENT__USED_INTERFACES___LIB;

      } else if (component instanceof ConfigurationItem) {
        queryIdentifier = QueryConstants.GET_AVAILABLE__CONFIGURATION_ITEM__USED_INTERFACES___LIB;
      }
      return QueryInterpretor.executeQuery(queryIdentifier, component, context);
    }
    return Collections.emptyList();

  }
}
