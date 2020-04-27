/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;

/**
 *
 */
public class ItemQuery_Flow_sourceOwner implements IQuery {

	/**
	 * 
	 */
	public ItemQuery_Flow_sourceOwner() {
    // do nothing
	}

	/**
	 * 
	 * source.owner
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof AbstractInformationFlow) {
			AbstractInformationFlow f = (AbstractInformationFlow) object;
			
		// connection 
      if (f instanceof ComponentExchange) {
        Part sourcePart = ComponentExchangeExt.getSourcePart((ComponentExchange) f);
        if (null != sourcePart) {
          result.add(sourcePart); 
        } else{
          InformationsExchanger source = f.getSource();
          if (null != source && (source instanceof ComponentPort || source instanceof PhysicalPort)) {
            EObject eContainer = source.eContainer();
            if (null != eContainer && eContainer instanceof Component) {
              result.add(eContainer);    
            }
          }
        }
        
        return result;
      }
      
			if (null != f.getSource()) result.add(f.getSource().eContainer());
		}
        return result;
	}
}
