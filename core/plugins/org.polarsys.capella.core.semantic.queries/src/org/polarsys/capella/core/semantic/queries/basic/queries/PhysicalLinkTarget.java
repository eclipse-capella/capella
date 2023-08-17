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

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.helpers.cs.services.PhysicalLinkExt;
import org.polarsys.capella.core.data.information.Port;

/**
 * return target @Port of a given @PhysicalLink
 */
public class PhysicalLinkTarget implements IQuery {

  @Override
  public List<Object> compute(Object object) {
    if (object instanceof PhysicalLink) {
      PhysicalLink link = (PhysicalLink) object;
      Port port = PhysicalLinkExt.getTargetPort(link);
      if (null != port) {
        return Collections.singletonList(port);
      }
    }
    return Collections.emptyList();
  }

}
