/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;

/**
 *
 */
public class InstanceRole_representedInstance implements IQuery {

  /**
   * 
   */
  public InstanceRole_representedInstance() {
    // do nothing
  }

  /** 
	 *  
	 * current.representedInstance
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	@Override
  public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<>();
		if (object instanceof InstanceRole) {
			InstanceRole instanceRole = (InstanceRole) object;
			AbstractInstance representedInstance = instanceRole.getRepresentedInstance();			
			
			if(representedInstance != null) {			
			  result.add(representedInstance);			  
			  
			  AbstractType abstractType = representedInstance.getAbstractType();			  
	      if(abstractType != null) {
	        result.add(abstractType);
	      }
			}
		}
		return result;
	}
}
