/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
