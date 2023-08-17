/*******************************************************************************
* Copyright (c) 2023 THALES GLOBAL SERVICES.
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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.PhysicalPort;

/**
 * return container of a @PhysicalPort
 */
public class PhysicalPortOwner implements IQuery {

  @Override
  public List<Object> compute(Object object) {
    if (object instanceof PhysicalPort) {
      PhysicalPort pp = (PhysicalPort) object;
      EObject eContainer = pp.eContainer();
      if (eContainer instanceof Component) {
        return Collections.singletonList(eContainer);
      }
    }
    return Collections.emptyList();
  }

}
