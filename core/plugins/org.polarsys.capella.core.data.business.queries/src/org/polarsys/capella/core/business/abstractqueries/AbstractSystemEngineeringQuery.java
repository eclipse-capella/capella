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
package org.polarsys.capella.core.business.abstractqueries;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

/**
 * This is the base query
 *
 */
public abstract class AbstractSystemEngineeringQuery implements IBusinessQuery {

 /**
  * Verifies that there is a "system engineering folder" above the given capella element
 * @param element_p the given capella element
 * @return <code>true</code> if there is such folder, <code>false</code> otherwise
 */
public boolean systemEngineeringExists(CapellaElement element_p){
   SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
   if (null == systemEngineering) {
     SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element_p);
     for (ReuseLink link : sharedPkg.getReuseLinks()) {
       if (SystemEngineeringExt.getSystemEngineering(link) != null) {
         systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
         break;
       }
     }
     if (systemEngineering == null)
       return false;
   }
   return true;
 }
}
