/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.cs.Component;

public class Component_representingParts implements IQuery {


	@Override
  public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<>();
		if (object instanceof Component) {
			Component component = (Component) object;
			result.addAll(component.getRepresentingParts());
		}
		return result;
	}
}
