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

import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.model.helpers.StateExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return all the available Elements of State & Mode
 *
 */
public class AbstractStateAvailableElements implements IQuery {

  public AbstractStateAvailableElements() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    if (object instanceof State) {
      // Note that Mode is sub type of State
      return StateExt.getActiveElements((State) object);
    }
    return new ArrayList<Object>();
  }
}
