/*******************************************************************************
 * Copyright (c) 2018, 2022 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.oa.OperationalActivity;

public class StateFragmentRelatedFunctions implements IQuery {

  @Override
  public List<Object> compute(Object object) {

    List<Object> result = new ArrayList<Object>();
    if (object instanceof StateFragment) {
      StateFragment f = (StateFragment) object;
      AbstractFunction absFunc = f.getRelatedAbstractFunction();
      if (isValidInstanceOf(absFunc)) {
        result.add(absFunc);
      }
    }
    return result;
  }

  protected boolean isValidInstanceOf(AbstractFunction absFunc) {
      return !(absFunc instanceof OperationalActivity);
  }
}
