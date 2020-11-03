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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.model.helpers.PhysicalPathExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 */
public class PhysicalPath_PhysicalLinks implements IQuery {
  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>(0);
    if (object instanceof PhysicalPath) {
      PhysicalPath path = (PhysicalPath) object;
      List<AbstractPathInvolvedElement> involvedElements = PhysicalPathExt.getInvolvedElements(path);
      for (AbstractPathInvolvedElement involvedElement : involvedElements) {
        if (involvedElement instanceof PhysicalLink) {
          result.add(involvedElement);
        }
      }
    }
    return result;
  }
}
