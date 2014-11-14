/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.InterfaceAllocator;
import org.polarsys.capella.common.helpers.query.IQuery;

public class Interface_provisioningInterfaces implements IQuery {

	public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof Interface) {
		  Interface itf = (Interface) object_p;
		  
			for (InterfaceAllocation alloc : itf.getProvisioningInterfaceAllocations()) {
			  InterfaceAllocator itfAllocator = alloc.getAllocatingInterfaceAllocator();
        if (itfAllocator instanceof Interface)
          result.add(itfAllocator);
      }
		}
		return result;
	}
}
